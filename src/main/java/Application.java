import java.beans.PropertyVetoException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sn.sms.config.BaseConfig;

@SpringBootApplication(scanBasePackages = {
        "com.sn.sms",
})
@ImportResource({
        "classpath:/conf/spring/applicationContext-base.xml",
        "classpath:/conf/spring/applicationContext-db-base.xml" ,
        "classpath:/conf/spring/applicationContext-db.xml"})
@Configuration
@PropertySource(value = { "classpath:/conf/mail.properties"}, ignoreResourceNotFound = true)
@EnableAutoConfiguration
@ConfigurationProperties
//@EnableConfigurationProperties(BaseConfig.class)
public class Application{
//	private final BaseConfig baseConfig;
//	public Application(BaseConfig configuration) {
//        this.baseConfig = configuration;
//    }

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	
}
