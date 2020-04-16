package guru.springframework.sfgdi.config;


import guru.springframework.sfgdi.examplebeans.FakeDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${guru.user}")
    String user;
    @Value("${guru.password}")
    String password;
    @Value("${guru.url}")
    String url;
    @Value("${jms.broker.name}")
    String jmsBrokerName;
    @Value("${jms.broker.password}")
    String jmsBrokerpassword;
    @Value("${jms.broker.url}")
    String jmsBrokerUrl;

    @Bean
    public FakeDatasource fakeDatasource(){
        FakeDatasource ds = new FakeDatasource();

        ds.setUser(env.getProperty("GURU_USERNAME"));
        ds.setPassword(password);
        ds.setUrl(url);

        System.out.println("Jms name : " + jmsBrokerName);
        System.out.println("Jms pass : " + jmsBrokerpassword);
        System.out.println("Jms url : " +  jmsBrokerUrl);

        return ds;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer
                = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
}
