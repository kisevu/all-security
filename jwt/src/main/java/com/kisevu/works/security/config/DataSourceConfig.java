package com.kisevu.works.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/*
*
@author ameda
@project jwt
*
*/
@Configuration
public class DataSourceConfig {

    /*
    *  Getting the data source the application is using
    * for loading the schema script explicitly rather implicitly
    * with the user h2-console
    * */
    @Autowired
    private DataSource dataSource;


    /*
    * The bean is creating an instance of data source initializer
    * Then the initializer is set with the data source.
    * Then an instance of ResourceDatasourcePopulator is created
    * which helps populate the database with contents of the sql script
    * the script is added from the class path to the populator
    * then the initializer is being returned.
    * */
    @Bean
    public DataSourceInitializer dataSourceInitializer(){
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
