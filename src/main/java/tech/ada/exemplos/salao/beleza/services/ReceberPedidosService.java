package tech.ada.exemplos.salao.beleza.services;

import lombok.RequiredArgsConstructor;
import tech.ada.exemplos.salao.beleza.client.FinanceiroClient;
import tech.ada.exemplos.salao.beleza.client.payload.request.FornecedorFinanceiroRequest;
import tech.ada.exemplos.salao.beleza.client.payload.request.ItemRequest;
import tech.ada.exemplos.salao.beleza.client.payload.request.ProdutoRequest;
import tech.ada.exemplos.salao.beleza.client.payload.request.RealizarPagamentoFinanceiro;
import tech.ada.exemplos.salao.beleza.entity.Fornecedor;
import tech.ada.exemplos.salao.beleza.entity.ItemPedido;
import tech.ada.exemplos.salao.beleza.entity.Pedido;
import tech.ada.exemplos.salao.beleza.entity.Produto;
import tech.ada.exemplos.salao.beleza.jms.out.PagamentoFornecedorFinanceiroProducer;
import tech.ada.exemplos.salao.beleza.payloads.PedidoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceberPedidosService {
    private final FinanceiroClient financeiroClient;
    private final CreateFornecedorService createFornecedorService;

    private final CreateOrRetrieveProdutoService createOrRetrieveProdutoService;

    private final CreatePedidoService createPedidoService;

    private final PagamentoFornecedorFinanceiroProducer pagamentoFornecedorFinanceiroProducer;

    public void execute(PedidoRequest pedidoRequest){

        Fornecedor fornecedor =  saveFornecedor(pedidoRequest);
        Produto produto =  saveProduto(pedidoRequest);
        Pedido pedido = savePedido(pedidoRequest, produto, fornecedor);

        sendPedidoToFinanceiro(fornecedor, produto, pedido);
    }

    private void sendPedidoToFinanceiro(Fornecedor fornecedor, Produto produto, Pedido pedido) {
        RealizarPagamentoFinanceiro realizarPagamentoFinanceiro=
                new RealizarPagamentoFinanceiro();

        FornecedorFinanceiroRequest fornecedorFinanceiroRequest = new FornecedorFinanceiroRequest();
        fornecedorFinanceiroRequest.setNomeFornecedor(fornecedorFinanceiroRequest.getNomeFornecedor());
        fornecedorFinanceiroRequest.setCnpj(fornecedor.getCnpj());
        fornecedorFinanceiroRequest.setIdentificador(fornecedor.getIdentificador());
        realizarPagamentoFinanceiro.setFornecedor(fornecedorFinanceiroRequest);

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setPreco(pedido.getItemPedidoList().iterator().next().getPreco());
        itemRequest.setQuantidade(((Double) pedido.getItemPedidoList()
                .iterator().next().getQuantidade()).intValue());

        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNome(produto.getNome());
        produtoRequest.setIdentificador(produto.getIdentificador());


        itemRequest.setProduto(produtoRequest);
        realizarPagamentoFinanceiro.setItems(List.of(itemRequest));

        //financeiroClient.realizarPagamento(realizarPagamentoFinanceiro);
        pagamentoFornecedorFinanceiroProducer.send(realizarPagamentoFinanceiro);
    }

    private Pedido savePedido(PedidoRequest pedidoRequest, Produto produto, Fornecedor fornecedor) {
        Pedido pedido = new Pedido();
        pedido.setFornecedor(fornecedor);


        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(pedidoRequest.getQuantidade());
        itemPedido.setPreco(pedidoRequest.getPrecoCompra());
        itemPedido.setProduto(produto);
        return createPedidoService.execute(pedido, List.of(itemPedido));


    }

    private Produto saveProduto(PedidoRequest pedidoRequest) {
        Produto produto = new Produto();
        produto.setQuantidade(pedidoRequest.getUnidadeQuantidade());
        produto.setMarca(pedidoRequest.getMarca());
        produto.setNome(pedidoRequest.getNome());
        produto.setUnidade(pedidoRequest.getUnidade());
        return createOrRetrieveProdutoService.execute(produto);
    }

    private Fornecedor saveFornecedor(PedidoRequest pedidoRequest) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(pedidoRequest.getFornecedor().getNome());
        fornecedor.setCnpj(pedidoRequest.getFornecedor().getCnpj());
        return createFornecedorService.execute(fornecedor);
    }
}
