package tech.ada.exemplos.salao.beleza.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Item_Pedido")
public class ItemPedido {
    @Id
    @EmbeddedId
    private ItemPedidoComposedKey itemPedidoComposedKey;
    @ManyToOne
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    private double preco;
    private double quantidade;

}
