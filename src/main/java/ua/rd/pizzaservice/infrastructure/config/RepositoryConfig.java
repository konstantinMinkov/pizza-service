package ua.rd.pizzaservice.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.rd.pizzaservice.infrastructure.BenchmarkBeanPostProcessor;
import ua.rd.pizzaservice.repository.InMemoryOrderRepository;
import ua.rd.pizzaservice.repository.OrderRepository;


@Configuration
@ComponentScan(basePackageClasses = OrderRepository.class)
public class RepositoryConfig {

    @Bean
    public BenchmarkBeanPostProcessor benchmarkBeanPostProcessor() {
        return new BenchmarkBeanPostProcessor();
    }
}
