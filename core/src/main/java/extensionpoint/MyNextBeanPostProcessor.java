package extensionpoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import subject.Person;

@Component
public class MyNextBeanPostProcessor implements BeanPostProcessor, Ordered {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Instance the bean name twice = " + beanName);
        return bean;
    }

    @Bean()
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Finish instance the bean name twice = " + beanName);
        if (bean instanceof Person) {
            ((Person) bean).setName("Jungle");
            ((Person) bean).setAge(11);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
