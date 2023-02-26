package tech.ada.exemplos.salao.beleza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.exemplos.salao.beleza.entity.Produto;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Optional<Produto> findByNomeAndMarcaAndQuantidade(String nome, String marca, long quantidade);
}
