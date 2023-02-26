package tech.ada.exemplos.salao.beleza.controllers;

import lombok.RequiredArgsConstructor;
import tech.ada.exemplos.salao.beleza.payloads.PedidoRequest;
import tech.ada.exemplos.salao.beleza.services.ReceberPedidosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realizarpedido")
@RequiredArgsConstructor
public class ReceberPedidosController {

    private final ReceberPedidosService receberPedidosService;
    @PostMapping(path = "")
    public void realizarPedido(@RequestBody PedidoRequest pedidoRequest){
        receberPedidosService.execute(pedidoRequest);
    }
}
