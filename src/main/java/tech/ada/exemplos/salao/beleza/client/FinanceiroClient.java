package tech.ada.exemplos.salao.beleza.client;

import tech.ada.exemplos.salao.beleza.client.payload.request.RealizarPagamentoFinanceiro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FinanceiroClient {

    @Value("${business.financeiro.url}")
    private String url;
    public void realizarPagamento(RealizarPagamentoFinanceiro realizarPagamentoFinanceiro ){
        WebClient webClient = WebClient.create();
        webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(realizarPagamentoFinanceiro), RealizarPagamentoFinanceiro.class)
                .retrieve().bodyToMono(Void.class).block();
    }
}
