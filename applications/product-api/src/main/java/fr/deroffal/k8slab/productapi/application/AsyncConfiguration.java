package fr.deroffal.k8slab.productapi.application;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfiguration {

  @Bean
  public Executor productsTaskExecutor() {
    return new VirtualThreadTaskExecutor();
  }
}
