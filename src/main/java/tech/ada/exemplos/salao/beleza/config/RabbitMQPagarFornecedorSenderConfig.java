package tech.ada.exemplos.salao.beleza.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPagarFornecedorSenderConfig {
    @Value("${business.financeiro.message.queue.pagamento_fornecedor}")
    private String queueName;

    @Bean
    public Queue queue(){
        return new Queue(queueName, true);
    }
}
