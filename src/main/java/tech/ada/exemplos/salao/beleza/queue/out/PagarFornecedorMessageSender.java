package tech.ada.exemplos.salao.beleza.queue.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.ada.exemplos.salao.beleza.client.payload.request.RealizarPagamentoFinanceiro;

@Component
@RequiredArgsConstructor
public class PagarFornecedorMessageSender {
    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    private final ObjectMapper objectMapper;

    public void send(RealizarPagamentoFinanceiro realizarPagamentoFinanceiro) {
        String message = null;
        try {
            message = objectMapper.writeValueAsString(realizarPagamentoFinanceiro);
            rabbitTemplate.convertSendAndReceive(queue.getName(),message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
