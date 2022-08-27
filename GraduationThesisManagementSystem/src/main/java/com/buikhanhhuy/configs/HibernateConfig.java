package com.buikhanhhuy.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.buikhanhhuy.pojo");
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(properties());

        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(this.environment.getProperty("hibernate.connection.driverClass", "com.mysql.cj.jdbc.Driver"));
        managerDataSource.setUrl(this.environment.getProperty("hibernate.connection.url"));
        managerDataSource.setUsername(this.environment.getProperty("hibernate.connection.username"));
        managerDataSource.setPassword(this.environment.getProperty("hibernate.connection.password"));

        return managerDataSource;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager ()
    {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactoryBean().getObject());

        return manager;
    }
    public Properties properties() {
        Properties props = new Properties();
        props.put(org.hibernate.cfg.Environment.DIALECT, this.environment.getProperty("hibernate.dialect"));
        props.put(org.hibernate.cfg.Environment.SHOW_SQL, this.environment.getProperty("hibernate.showSql"));

        return props;
    }
}
