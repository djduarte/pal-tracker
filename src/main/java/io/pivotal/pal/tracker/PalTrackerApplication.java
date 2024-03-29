package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {


    public static void main(String args[]) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public InMemoryTimeEntryRepository inMemoryTimeEntryRepository() {
        return new InMemoryTimeEntryRepository();
    }

    @Bean
    @Primary
    public JdbcTimeEntryRepository jdbcTimeEntryRepository(DataSource dataSource) {

        return new JdbcTimeEntryRepository(dataSource);
    }
}
