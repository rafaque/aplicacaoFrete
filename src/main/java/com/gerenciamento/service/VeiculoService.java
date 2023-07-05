package com.gerenciamento.service;

import com.gerenciamento.exception.UserExistsException;
import com.gerenciamento.model.Veiculo;
import com.gerenciamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;
    public void cadastraVeiculo(Veiculo veiculo) throws UserExistsException {

        if(veiculoRepository.findByNome(veiculo.getNome()) !=null) {
            throw new UserExistsException("Este veículo já esta cadastrado: " + veiculo.getNome());
        }else{
            veiculoRepository.save(veiculo);
        }
    }


    public Veiculo findByNome(String veiculo) {
        return veiculoRepository.findByNome(veiculo);
    }
}
