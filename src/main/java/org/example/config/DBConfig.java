package org.example.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement // 트랜잭션 때문에 필요함
public class DBConfig {
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "connectuser";
    private static final String PASSWORD = "connect123!@#";

    @Bean // 커넥션을 관리하는 객체(jdbc 드라이버, url 등등 알아야함). 빈으로 등록해주어야 한다
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;

    }
}
