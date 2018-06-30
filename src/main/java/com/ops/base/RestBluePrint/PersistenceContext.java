//package com.ops.base.RestBluePrint;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class PersistenceContext {
//
//	@Bean(destroyMethod = "close")
//	DataSource dataSource(Environment env) {
//		
//		HikariConfig dataSourceConfig = new HikariConfig();
//		
//		dataSourceConfig.setDriverClassName(env.getRequiredProperty("mysql.driver"));
//		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
//		dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
//		dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
//		
//		return new HikariDataSource(dataSourceConfig);
//		
//	}
//	
//}
