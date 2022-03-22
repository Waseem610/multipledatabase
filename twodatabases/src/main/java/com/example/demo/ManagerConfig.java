package com.example.demo;

import java.util.HashMap;

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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "managerEntityManagerFactory", transactionManagerRef = "employeeTransactionManager", basePackages = {
		"com.example.demo.Manager" })

public class ManagerConfig {
	@Primary
	@Bean(name = "managerDataSource")
	@ConfigurationProperties(prefix = "spring.seconddatasource")
	public DataSource employeeDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "managerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("managerDataSource") DataSource employeeDataSource) {
		return builder.dataSource(employeeDataSource).packages("com.example.demo.Manager").build();
	}
	@Primary
	@Bean(name = "managerTransactionManager")
	public PlatformTransactionManager employeeTransactionManager(
			@Qualifier("managerEntityManagerFactory") EntityManagerFactory managerfactory) {
		return new JpaTransactionManager(managerfactory);

	}
//	@Bean
//	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
//	}
}
