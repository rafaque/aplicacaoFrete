package com.gerenciamento.controller;


import com.gerenciamento.model.Produto;
import com.gerenciamento.repository.ProdutoRepository;
import com.gerenciamento.service.ProdutoService;
import com.gerenciamento.service.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/inserirProduto")
    public ModelAndView insertProduto(Produto produto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("produto/cadastroProduto");
        modelAndView.addObject("produto", new Produto());
        return modelAndView;
    }

    @PostMapping("InsertProduto")
    public ModelAndView inserirProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("produto/cadastroProduto");
            modelAndView.addObject("produto", produto);
        } else {
            modelAndView.setViewName("redirect:/listProduto");
            produto.setPesoAntigo(produto.getPeso());
            produtoService.cadastraProduto(produto);
        }
        return modelAndView;
    }

    @GetMapping("/listProduto")
    public ModelAndView listagemProdutos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("produto/listProduto");
        modelAndView.addObject("produtoList", produtoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarProduto/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("produto/editarProduto");
        Produto produto = produtoRepository.getById(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @PostMapping("/produto/editar")
    public ModelAndView editar(Produto produto) {
        ModelAndView modelAndView = new ModelAndView();
        Produto produtoAntigo = produtoRepository.getById(produto.getId());
        produto.setPesoAntigo(produtoAntigo.getPesoAntigo());
        produtoRepository.save(produto);
        modelAndView.setViewName("redirect:/listProduto");
        return modelAndView;
    }

    @GetMapping("/removerProduto/{id}")
    public String removerProduto(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/listProduto";
    }
}
