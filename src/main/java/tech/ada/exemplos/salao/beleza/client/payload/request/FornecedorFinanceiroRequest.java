package tech.ada.exemplos.salao.beleza.client.payload.request;

import lombok.Data;

@Data
public class FornecedorFinanceiroRequest {

    private String cnpj;
    private String nomeFornecedor;

    private String identificador;
}
