import extensionpoint.MyBeanFactoryPosyProcessor;
import extensionpoint.MyBeanPostProcessor;
import extensionpoint.MyNextBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.metrics.StartupStep;
import subject.Person;


@SpringBootConfiguration
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        Person bean = context.getBean(Person.class);
        System.out.println("beansOfType = " + bean);
    }

    @Bean
    public Person getPerson() {
        return new Person();
    }

    @Bean
    public MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public MyNextBeanPostProcessor getMyNextBeanPostProcessor() {
        return new MyNextBeanPostProcessor();
    }

    @Bean
    public MyBeanFactoryPosyProcessor getMyBeanFactoryPosyProcessor() {
        return new MyBeanFactoryPosyProcessor();
    }
}
