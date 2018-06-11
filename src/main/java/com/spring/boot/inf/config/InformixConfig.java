package com.spring.boot.inf.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(entityManagerFactoryRef = "informixEntityManagerFactory", transactionManagerRef = "informixTransactionManager", basePackages = {
		"com.spring.boot.inf.repository" })
public class InformixConfig {
	@Bean(name = "informixDataSource")
	@ConfigurationProperties(prefix = "spring.informix.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "informixEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean informixEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("informixDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.spring.boot.inf.beans").persistenceUnit("TODOITEMS")
				.build();
	}

	@Bean(name = "informixTransactionManager")
	public PlatformTransactionManager informixTransactionManager(
			@Qualifier("informixEntityManagerFactory") EntityManagerFactory informixEntityManagerFactory) {
		return new JpaTransactionManager(informixEntityManagerFactory);
	}
}
