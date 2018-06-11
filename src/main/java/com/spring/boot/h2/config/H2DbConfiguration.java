package com.spring.boot.h2.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory", transactionManagerRef = "h2TransactionManager", basePackages = {
		"com.spring.boot.h2.repository" })
public class H2DbConfiguration {
	@Primary
	@Bean(name = "h2DataSource")
	@ConfigurationProperties(prefix = "spring.h2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("h2DataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.spring.boot.h2.beans").persistenceUnit("SAMPLE").build();
	}

	@Primary
	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager h2TransactionManager(
			@Qualifier("h2EntityManagerFactory") EntityManagerFactory h2EntityManagerFactory) {
		return new JpaTransactionManager(h2EntityManagerFactory);
	}
}
