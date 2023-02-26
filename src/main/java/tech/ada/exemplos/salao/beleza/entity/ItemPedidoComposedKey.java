package tech.ada.exemplos.salao.beleza.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ItemPedidoComposedKey implements Serializable {

    @Column(name = "id_pedido")
    private long idPedido;
    @Column(name = "id_produto")
    private long idProduto;

    public ItemPedidoComposedKey(long idPedido, long idProduto) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
    }
}
