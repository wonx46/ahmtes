/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author achmad.ha
 */
@Configuration
//@PropertySource("file:///D:/jdbc.properties")
//@PropertySource({"classpath:database.properties", "file:${jxconfig}"})
@PropertySource({"classpath:database_mysql.properties"})
public class AppConfig {
    
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
    
    @Value("${jdbc.driver}")
    private String driver;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        try {
//            String realPass = CryptoSecurity.decrypt(password);
            dataSource.setPassword(password);
        } catch (Exception ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }             
        return dataSource;
    }
    
}
