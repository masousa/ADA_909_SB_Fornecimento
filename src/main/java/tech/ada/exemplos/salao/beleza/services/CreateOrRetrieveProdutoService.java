package tech.ada.exemplos.salao.beleza.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.exemplos.salao.beleza.entity.Produto;
import tech.ada.exemplos.salao.beleza.repositories.ProdutoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrRetrieveProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto execute(Produto produto){
        produto.setIdentificador(UUID.randomUUID().toString());
        return produtoRepository.findByNomeAndMarcaAndQuantidade(produto.getNome(), produto.getMarca()
                , produto.getQuantidade()).orElse(produtoRepository.save(produto));
    }

}
