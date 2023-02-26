package tech.ada.exemplos.salao.beleza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.exemplos.salao.beleza.entity.Fornecedor;

public interface FornecedorRepository  extends JpaRepository<Fornecedor, String> {
}
