package com.sn.sms.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ConfigurationProperties("datasource")
public class BaseConfig {
	private String url;
	private String username;

    private String password;
    
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@PostConstruct
    private void postConstruct() {
    	System.out.println("##########################");
    	System.out.println(url);
    	System.out.println(username);
    	System.out.println(password);
    	System.out.println("##########################");
    }
	
	
	@Bean(name="dataSource")
    public DataSource getDataSource() throws Exception {
		System.out.println(111);
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setJdbcUrl(datasourceurl);
//		dataSource.setUser(datasourceusername);
//        dataSource.setPassword(datasourcepassword);// 密码
        dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
        dataSource.setPassword(password);// 密码
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setAcquireIncrement(30);
        dataSource.setInitialPoolSize(100);
        dataSource.setMinPoolSize(100);
        dataSource.setMaxPoolSize(400);
        dataSource.setMaxIdleTime(300);
        dataSource.setIdleConnectionTestPeriod(150);
        dataSource.setMaxStatements(300);
        dataSource.setMaxStatementsPerConnection(300);
        dataSource.setNumHelperThreads(50);
        dataSource.setStatementCacheNumDeferredCloseThreads(1);
        return dataSource;
    }
	
}
