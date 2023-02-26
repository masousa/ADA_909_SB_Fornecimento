package tech.ada.exemplos.salao.beleza.payloads;

import lombok.Data;

@Data
public class FornecedorRequest {
    private String identificador;

    private String cnpj;
    private String nome;
}
