package p5_storage.domain;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import p5_storage.infrastructure.InfrastructureConfiguration;
import p5_storage.infrastructure.SQLiteBankAccountRepository;

class TestInfrastuctureConfiguration {

    private static final String TEST_JDBC_URL = "jdbc:sqlite:/Users/ravil/experimental/sd-course/sd-p11/src/test/resources/p5.db";
    private static final InfrastructureConfiguration configuration = new InfrastructureConfiguration();

    SQLiteBankAccountRepository sqLiteBankAccountRepository() {
        return configuration.sqLiteBankAccountRepository(TEST_JDBC_URL);
    }

    NamedParameterJdbcTemplate jdbcTemplate() {
        return configuration.jdbcTemplate(TEST_JDBC_URL);
    }
}
