package com.sn.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {
        "com.sn.sms",
})
@ImportResource({
        "classpath:/conf/spring/applicationContext-base.xml",
        "classpath:/conf/spring/applicationContext-db-base.xml" ,
        "classpath:/conf/spring/applicationContext-db.xml"})
@Configuration
@PropertySource(value = { "classpath:/conf/mail.properties"}, ignoreResourceNotFound = true)
public class SpringBootTomcat extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(SpringBootTomcat.class);
}
