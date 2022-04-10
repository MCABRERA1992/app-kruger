package ec.kruger.corporation.java.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:40
 * @project app-vacunacion
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPostgres",
                       transactionManagerRef = "transactionManagerPostgres",
                       basePackages = {"ec.kruger.corporation.java.entity.repository"})
public class PostgresConfigData {

    @Bean(name = "postgresqlDataSources")
    public DataSource postgresqlDataSources() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/BD_KRUGER");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456789");
        return dataSource;
    }


    @Bean(name = "entityManagerFactoryPostgres")
    public LocalContainerEntityManagerFactoryBean managerFactoryBean() {

        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(postgresqlDataSources());
        managerFactoryBean.setPackagesToScan("ec.kruger.corporation.java.entity");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        managerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("hibernate.hbm2ddl.auto", "create-drop");
        propertiesMap.put("hibernate.show_sql", "true");
        propertiesMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        managerFactoryBean.setJpaPropertyMap(propertiesMap);

        return managerFactoryBean;

    }

    @Bean(name = "transactionManagerPostgres")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(managerFactoryBean().getObject());
        return jpaTransactionManager;
    }
}
