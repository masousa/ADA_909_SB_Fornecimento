package tech.ada.exemplos.salao.beleza.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PedidoRequest {
    private String nome;
    @JsonProperty(value = "preco_compra")
    private double precoCompra;

    private String marca;

    private int quantidade;

    @JsonProperty("unidade_quantidade")
    private int unidadeQuantidade;

    private String unidade;

    private FornecedorRequest fornecedor;
}
