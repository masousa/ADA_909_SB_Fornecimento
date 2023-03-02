package tech.ada.exemplos.salao.beleza.jms.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tech.ada.exemplos.salao.beleza.client.payload.request.RealizarPagamentoFinanceiro;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagamentoFornecedorFinanceiroProducer {
    @Value("${business.financeiro.kafka.out}")
    private String topicName;
    private final KafkaTemplate<String, RealizarPagamentoFinanceiro> kafkaTemplate;

    public void send(RealizarPagamentoFinanceiro realizarPagamentoFinanceiro){
        log.info("Mensagem enviada pelo kafka {}", realizarPagamentoFinanceiro);
        kafkaTemplate.send(topicName,realizarPagamentoFinanceiro);
    }
}
