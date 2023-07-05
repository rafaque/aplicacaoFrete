package com.gerenciamento.service;

import com.gerenciamento.exception.UserExistsException;
import com.gerenciamento.model.Produto;
import com.gerenciamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    public void cadastraProduto(Produto produto) throws UserExistsException {

        if(produtoRepository.findByNome(produto.getNome()) !=null) {
            throw new UserExistsException("Este veículo já esta cadastrado: " + produto.getNome());
        }else{
            produtoRepository.save(produto);
        }
    }

    public Produto findByNome(String produto) {
       return produtoRepository.findByNome(produto);
    }
}
