package com.gerenciamento.repository;

import com.gerenciamento.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
    @Query("select e from Frete e where e.produto = :produto")
    List<Frete> findAllByProduto(String produto);

    @Query("select e from Frete e where e.veiculo = :veiculo")
    List<Frete> findAllByVeiculo(String veiculo);

//    @Query("select e from Frete e where e.nome = :nome")
//    public Frete findByNome(String nome);

}
