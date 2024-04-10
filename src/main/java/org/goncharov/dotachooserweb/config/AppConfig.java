package org.goncharov.dotachooserweb.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ManagedChannel managedChannel(){
        return ManagedChannelBuilder.forTarget("localhost:50051")
                .usePlaintext().build();
    }
}
