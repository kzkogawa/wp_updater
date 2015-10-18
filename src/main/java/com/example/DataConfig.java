package com.example;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@MapperScan("com.example.mapper")
public class DataConfig {
	@Autowired
	private Environment env;
	private DataSource dataSource;

	@Bean
	public DataSource dataSource() {
		if (this.dataSource == null) {
			this.dataSource = new DriverManagerDataSource(env.getProperty("database.url"), env.getProperty("database.username"), env.getProperty("database.password"));
		}
		return this.dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.example.model");
		sessionFactory.setMapperLocations(resolver.getResources("classpath:sqlmap/*.xml"));
		return sessionFactory;
	}
}
