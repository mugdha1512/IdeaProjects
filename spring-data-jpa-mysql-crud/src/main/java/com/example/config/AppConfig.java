package com.example.config;
import com.example.repository.ProductRepository;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableJpaRepositories(basePackages = "com.example.repository")
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/datajpa_productdb?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("mugdha");
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new
                LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
// Set JPA Properties
        Properties jpaProperties = new Properties();
// Hibernate-specific properties
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.format_sql", "true");
        jpaProperties.setProperty("hibernate.use_sql_comments", "true");
        jpaProperties.setProperty("hibernate.id.new_generator_mappings", "true");
        jpaProperties.setProperty("hibernate.jdbc.batch_size", "50");
        jpaProperties.setProperty("hibernate.order_inserts", "true");
        jpaProperties.setProperty("hibernate.order_updates", "true");
        jpaProperties.setProperty("hibernate.jdbc.fetch_size", "100");
        jpaProperties.setProperty("hibernate.max_fetch_depth", "3");
        jpaProperties.setProperty("hibernate.default_batch_fetch_size", "8");
        jpaProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        jpaProperties.setProperty("hibernate.cache.use_query_cache", "false");
        jpaProperties.setProperty("hibernate.connection.autocommit", "false");
        jpaProperties.setProperty("hibernate.connection.isolation", "2"); // READ_COMMITTED
        jpaProperties.setProperty("hibernate.generate_statistics", "false");
        jpaProperties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
        jpaProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        jpaProperties.setProperty("hibernate.enable_lazy_load_no_trans", "false");
        jpaProperties.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
// JPA standard properties
        jpaProperties.setProperty("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        jpaProperties.setProperty("javax.persistence.jdbc.url",
                "jdbc:mysql://localhost:3306/datajpa_productdb?useSSL=false");
        jpaProperties.setProperty("javax.persistence.jdbc.user", "root");
        jpaProperties.setProperty("javax.persistence.jdbc.password", "Archer@12345");
        jpaProperties.setProperty("javax.persistence.schema-generation.database.action", "update");
        jpaProperties.setProperty("javax.persistence.schema-generation.create-source", "metadata");
        jpaProperties.setProperty("javax.persistence.schema-generation.drop-source", "metadata");
        jpaProperties.setProperty("javax.persistence.sql-load-script-source", "import.sql");
        jpaProperties.setProperty("javax.persistence.lock.timeout", "10000");
        jpaProperties.setProperty("javax.persistence.query.timeout", "20000");
        em.setJpaProperties(jpaProperties);
        return em;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}