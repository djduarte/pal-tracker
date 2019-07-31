package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

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
    public JdbcTimeEntryRepository jdbcTimeEntryRepository() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));

        return new JdbcTimeEntryRepository(dataSource);
    }
}
