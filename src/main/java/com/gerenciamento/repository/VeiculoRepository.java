package com.gerenciamento.repository;

import com.gerenciamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("select e from Veiculo e where e.nome = :nome")
    public Veiculo findByNome(String nome);

}
