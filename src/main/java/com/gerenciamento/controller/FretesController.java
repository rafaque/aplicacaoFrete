package com.gerenciamento.controller;



import com.gerenciamento.enums.Status;
import com.gerenciamento.model.Frete;
import com.gerenciamento.model.Produto;
import com.gerenciamento.model.Veiculo;
import com.gerenciamento.repository.FreteRepository;
import com.gerenciamento.repository.ProdutoRepository;
import com.gerenciamento.repository.VeiculoRepository;
import com.gerenciamento.service.FreteService;
import com.gerenciamento.service.ServiceUsuario;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FretesController {

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FreteService freteService;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Operation(summary = "Retorna a pagina de cadastro de frete")
    @GetMapping("/inserirFrete")
    public ModelAndView insertFrete(Frete frete) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frete/cadastroFrete");
        modelAndView.addObject("frete", new Frete());
        List<String> nomesVeiculos = veiculoRepository.findAll().stream()
                .map(Veiculo::getNome)
                .collect(Collectors.toList());

        modelAndView.addObject("veiculoList", nomesVeiculos);
        List<String> nomesProdutos = produtoRepository.findAll().stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());

        modelAndView.addObject("produtoList", nomesProdutos);
        return modelAndView;
    }


    @PostMapping("InsertFrete")
    public ModelAndView inserirFrete(@Valid @ModelAttribute("frete") Frete frete, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("frete/cadastroFrete");
            modelAndView.addObject("frete", frete);
        } else {
            modelAndView.setViewName("redirect:/listFrete");
            freteService.cadastraFrete(frete);
        }
        return modelAndView;
    }

    @GetMapping("/listFrete")
    public ModelAndView listagemFrete() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frete/listFrete");
        modelAndView.addObject("freteList", freteRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarFrete/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frete/editarFrete");
        Frete frete = freteRepository.getById(id);
        List<String> nomesVeiculos = veiculoRepository.findAll().stream()
                .map(Veiculo::getNome)
                .collect(Collectors.toList());

        modelAndView.addObject("veiculoList", nomesVeiculos);
        List<String> nomesProdutos = produtoRepository.findAll().stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());

        modelAndView.addObject("produtoList", nomesProdutos);
        modelAndView.addObject("frete", frete);
        modelAndView.addObject("statusList", Status.values());
        return modelAndView;
    }

    @PostMapping("/frete/editar")
    public ModelAndView editar(Frete frete) {
        ModelAndView modelAndView = new ModelAndView();
        freteService.cadastraFrete(frete);
        modelAndView.setViewName("redirect:/listFrete");
        return modelAndView;
    }

    @GetMapping("/removerFrete/{id}")
    public String removerFrete(@PathVariable("id") Long id) {
        freteRepository.deleteById(id);
        return "redirect:/listFrete";
    }

    @Scheduled(cron = "59 59 23 * * *") // Executa às 23:59:59
    public void verificarAlteracoes() {
        List<Produto> produtos = produtoRepository.findAll();
        List<Veiculo> veiculos = veiculoRepository.findAll();

        for (Produto produto : produtos) {

            produto.setPesoAntigo(produto.getPeso());
            produtoRepository.save(produto);

            if (produto.getPeso() != produto.getPesoAntigo()) {
                List <Frete> fretes= freteRepository.findAllByProduto(produto.getNome());
                for (Frete frete : fretes){
                    freteService.cadastraFrete(frete);
                }

            }
        }

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPeso() == veiculo.getPesoAntigo()) {
                List <Frete> fretes= freteRepository.findAllByVeiculo(veiculo.getNome());
                for (Frete frete : fretes){
                    freteService.cadastraFrete(frete);
                }
            }
        }
    }
}
