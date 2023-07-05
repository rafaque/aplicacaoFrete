package com.gerenciamento.repository;

import com.gerenciamento.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select e from Produto e where e.nome = :nome")
    public Produto findByNome(String nome);

    @Query("select e from Produto e where e.id = :id")
    public Produto findById(String id);

}
