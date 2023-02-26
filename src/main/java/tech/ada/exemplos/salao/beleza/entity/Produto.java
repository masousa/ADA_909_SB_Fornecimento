package tech.ada.exemplos.salao.beleza.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long quantidade;

    private String unidade;

    private String marca;

    private String nome;

    private String identificador;
}
