package p5_storage.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import p5_storage.domain.BankAccount;
import p5_storage.domain.BankAccountRepository;
import p5_storage.domain.events.DomainEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SQLiteBankAccountRepository implements BankAccountRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public SQLiteBankAccountRepository(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override public BankAccount findById(int id) {
        return new BankAccount(events(id));
    }

    @Override public void save(BankAccount bankAccount) {
        final int id = bankAccount.id;
        Integer latest = jdbcTemplate.queryForObject("""
                        SELECT max(version) AS latest
                        FROM events
                        WHERE entity_id = :entity_id
                        """,
                Map.of("entity_id", id),
                (rs, rowNum) -> rs.getInt("latest"));

        latest = latest == null ? 0 : latest;

        for (DomainEvent event : bankAccount.events) {
            jdbcTemplate.update("""
                    INSERT INTO events ( entity_id,  version,  type,  data,  created_at)
                                VALUES (:entity_id, :version, :type, :data, :created_at)
                    """, Map.of(
                    "entity_id", id,
                    "version", ++latest,
                    "type", event.getClass().getSimpleName(),
                    "data", toJSON(event),
                    "created_at", LocalDateTime.now()));
        }
    }

    @Override public List<DomainEvent> events(int id) {
        List<RawEventData> rawEventData = jdbcTemplate.query("""
                        SELECT *
                        FROM events
                        WHERE entity_id = :entity_id
                        ORDER BY version
                        """,
                Map.of("entity_id", id),
                (rs, rowNum) -> new RawEventData(
                        rs.getInt("entity_id"),
                        rs.getInt("version"),
                        rs.getString("type"),
                        rs.getString("data"), null)
        );

        List<DomainEvent> events = rawEventData.stream()
                .map(rawEvent -> (DomainEvent) fromJSON(rawEvent.data, "p5_storage.domain.events." + rawEvent.type))
                .toList();

        if (events.isEmpty())
            throw new RuntimeException("BankAccount with id " + id + " is not found.");

        return events;
    }

    private String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Object fromJSON(String data, String type) {
        try {
            // Expectation: every event in the table keeps its type
            return objectMapper.readValue(data, Class.forName(type));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private record RawEventData(int entityId, int version, String type, String data, LocalDateTime createdAt) {}
}
