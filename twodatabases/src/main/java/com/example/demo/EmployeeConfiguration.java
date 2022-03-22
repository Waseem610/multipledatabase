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
@EnableJpaRepositories(entityManagerFactoryRef = "employeeEntityManagerFactory", transactionManagerRef = "employeeTransactionManager", basePackages = {
		"com.example.demo.Employee" })
public class EmployeeConfiguration {
	@Bean(name = "employeeDataSource")
//	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource employeeDataSource() {
		return DataSourceBuilder.create().build();
	}

	//@Primary
	@Bean(name = "employeeEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("employeeDataSource") DataSource employeeDataSource) {
		return builder.dataSource(employeeDataSource).packages("com.example.demo.Employee").build();
	}
	//@Primary
	@Bean(name = "employeeTransactionManager")
	public PlatformTransactionManager employeeTransactionManager(
			@Qualifier("employeeEntityManagerFactory") EntityManagerFactory employeefactory) {
		return new JpaTransactionManager(employeefactory);
	}
//	@Bean
//	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
//	}
}
