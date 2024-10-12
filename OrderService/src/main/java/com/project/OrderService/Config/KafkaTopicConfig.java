package com.project.OrderService.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderEventTopic() {
        return TopicBuilder.name("order-event")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic stockDeductedTopic() {
        return TopicBuilder.name("stock-deducted-topic-failure")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentStatusSuccessTopic() {
        return TopicBuilder.name("payment-status-topic-success")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentStatusFailureTopic() {
        return TopicBuilder.name("payment-status-topic-failure")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
