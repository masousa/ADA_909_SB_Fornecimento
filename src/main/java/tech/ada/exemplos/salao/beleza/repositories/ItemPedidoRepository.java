package tech.ada.exemplos.salao.beleza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.exemplos.salao.beleza.entity.ItemPedido;
import tech.ada.exemplos.salao.beleza.entity.ItemPedidoComposedKey;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoComposedKey> {
}
