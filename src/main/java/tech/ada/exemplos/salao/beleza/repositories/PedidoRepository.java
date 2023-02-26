package tech.ada.exemplos.salao.beleza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.exemplos.salao.beleza.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
