package com.gerenciamento.controller;


import com.gerenciamento.model.Veiculo;
import com.gerenciamento.repository.VeiculoRepository;
import com.gerenciamento.service.ServiceUsuario;
import com.gerenciamento.service.VeiculoService;
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
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/inserirVeiculo")
    public ModelAndView insertVeiculo(Veiculo veiculo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("veiculo/cadastroVeiculo");
        modelAndView.addObject("veiculo", new Veiculo());
        return modelAndView;
    }

    @PostMapping("InsertVeiculo")
    public ModelAndView inserirVeiculo(@Valid @ModelAttribute("veiculo") Veiculo veiculo, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("veiculo/cadastroVeiculo");
            modelAndView.addObject("veiculo", veiculo);
        } else {
            modelAndView.setViewName("redirect:/listVeiculo");
            veiculo.setPesoAntigo(veiculo.getPeso());
            veiculoService.cadastraVeiculo(veiculo);
        }
        return modelAndView;
    }

    @GetMapping("/listVeiculo")
    public ModelAndView listagemVeiculos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("veiculo/listVeiculo");
        modelAndView.addObject("veiculoList", veiculoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarVeiculo/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("veiculo/editarVeiculo");
        Veiculo veiculo = veiculoRepository.getById(id);
        modelAndView.addObject("veiculo", veiculo);
        return modelAndView;
    }

    @PostMapping("/veiculo/editar")
    public ModelAndView editar(Veiculo veiculo) {
        ModelAndView modelAndView = new ModelAndView();
        veiculoRepository.save(veiculo);
        modelAndView.setViewName("redirect:/listVeiculo");
        return modelAndView;
    }

    @GetMapping("/removerVeiculo/{id}")
    public String removerVeiculo(@PathVariable("id") Long id) {
        veiculoRepository.deleteById(id);
        return "redirect:/listVeiculo";
    }
}
