package p5_storage.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.sqlite.SQLiteDataSource;

public class InfrastructureConfiguration {

    private static final String JDBC_URL = "jdbc:sqlite:/Users/ravil/experimental/sd-course/sd-p11/src/main/resources/p5.db";

    public InMemoryBankAccountRepository inMemoryBankAccountRepository() {
        return new InMemoryBankAccountRepository();
    }

    public SQLiteBankAccountRepository sqLiteBankAccountRepository() {
        return sqLiteBankAccountRepository(JDBC_URL);
    }

    public SQLiteBankAccountRepository sqLiteBankAccountRepository(String url) {
        NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplate(url);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new SQLiteBankAccountRepository(jdbcTemplate, objectMapper);
    }

    /* visible for testing */
    public NamedParameterJdbcTemplate jdbcTemplate(String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
