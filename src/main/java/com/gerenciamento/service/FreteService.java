package com.gerenciamento.service;

import com.gerenciamento.enums.Status;
import com.gerenciamento.model.Frete;
import com.gerenciamento.model.Produto;
import com.gerenciamento.model.Veiculo;
import com.gerenciamento.repository.FreteRepository;
import com.gerenciamento.repository.ProdutoRepository;
import com.gerenciamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class FreteService {

    @Autowired
    FreteRepository freteRepository;
    @Autowired
    ProdutoService produtoService;

    @Autowired
    VeiculoService veiculoService;



    public void cadastraFrete(Frete frete) {

        Produto produto = produtoService.findByNome(frete.getProduto());
        Veiculo veiculo = veiculoService.findByNome(frete.getVeiculo());

        if(frete.getStatus() == null){
            frete.setStatus(Status.AGUARDANDO_MOVIMENTACAO);
        }
        if(frete.getData() == null){
            frete.setData(LocalDate.now());
        }

        frete.setValorTotal(frete.getDistancia() * produto.getPeso() * veiculo.getPeso());
        double taxa = calcularTaxa(frete.getDistancia(), frete.getValorTotal());
        frete.setValorEntregador(frete.getValorTotal() - taxa);

        freteRepository.save(frete);

    }

    public double calcularTaxa(Integer distancia, double valorTotal) {
        if (distancia <= 100) {
            return valorTotal * 0.05;
        } else if (distancia <= 200) {
            return valorTotal * 0.07;
        } else if (distancia <= 500) {
            return valorTotal * 0.09;
        } else {
            return valorTotal * 0.1;
        }
    }

}
