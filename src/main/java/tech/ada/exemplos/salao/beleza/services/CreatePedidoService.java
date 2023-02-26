package tech.ada.exemplos.salao.beleza.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.exemplos.salao.beleza.entity.ItemPedido;
import tech.ada.exemplos.salao.beleza.entity.ItemPedidoComposedKey;
import tech.ada.exemplos.salao.beleza.entity.Pedido;
import tech.ada.exemplos.salao.beleza.repositories.ItemPedidoRepository;
import tech.ada.exemplos.salao.beleza.repositories.PedidoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    public Pedido execute(Pedido pedido, List<ItemPedido> itemPedidos){
        Pedido savedPedido= pedidoRepository.save(pedido);
        itemPedidos
                .forEach(itemPedido ->
                        itemPedido.setItemPedidoComposedKey
                                (new ItemPedidoComposedKey(savedPedido.getId(),itemPedido.getProduto().getId())));
        itemPedidoRepository.saveAll(itemPedidos);
        savedPedido.setItemPedidoList(itemPedidos);
        return savedPedido;
    }
}
