package pl.dernovyi.homework7newsbackend.configur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {
    private DataSource dataSource;

    @Autowired
    public DBConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getTemplate(){
        return new JdbcTemplate(dataSource);
    }
//            @EventListener(ApplicationReadyEvent.class)
//        public void init(){
//            String sql= "CREATE TABLE news (news_id varchar(255), title varchar(255), description varchar(255) ,url varchar(255),author varchar(255), image varchar(255),category varchar(255), published varchar(255),  primary key (news_id))";
//            getTemplate().update(sql);
//        }

}
