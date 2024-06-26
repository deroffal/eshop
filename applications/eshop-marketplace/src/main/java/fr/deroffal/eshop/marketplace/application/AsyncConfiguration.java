package fr.deroffal.eshop.marketplace.application;

import io.opentelemetry.context.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean
    public Executor productsTaskExecutor() {
        return Context.taskWrapping(new VirtualThreadTaskExecutor());
    }
}
