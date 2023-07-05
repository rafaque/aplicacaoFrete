package com.gerenciamento.frete.service;

import com.gerenciamento.enums.Status;
import com.gerenciamento.model.Frete;
import com.gerenciamento.model.Produto;
import com.gerenciamento.model.Veiculo;
import com.gerenciamento.repository.FreteRepository;
import com.gerenciamento.repository.ProdutoRepository;
import com.gerenciamento.repository.VeiculoRepository;
import com.gerenciamento.service.FreteService;
import com.gerenciamento.service.ProdutoService;
import com.gerenciamento.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FreteServiceTest {

    @Mock
    private FreteRepository freteRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private FreteService freteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastraFrete_WithValidFrete_CalculatesTaxaAndSavesFrete() {
        Frete frete = new Frete();
        frete.setDistancia(150);
        frete.setProduto("Produto Teste");
        frete.setVeiculo("Veiculo Teste");

        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setPeso(10.0);

        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Veiculo Teste");
        veiculo.setPeso(5.0);

        when(produtoService.findByNome(frete.getProduto())).thenReturn(produto);
        when(veiculoService.findByNome(frete.getVeiculo())).thenReturn(veiculo);

        freteService.cadastraFrete(frete);

        assertEquals(Status.AGUARDANDO_MOVIMENTACAO, frete.getStatus());
        assertEquals(LocalDate.now(), frete.getData());
        assertEquals(7500.0, frete.getValorTotal(), 0.001);
        assertEquals(6975.0, frete.getValorEntregador(), 0.001);
        verify(freteRepository, times(1)).save(frete);
    }

    @Test
    void calcularTaxa_DistanciaMaiorOuIgual100() {
        int distance = 50;
        double valorTotal = 1000.0;

        double taxa = freteService.calcularTaxa(distance, valorTotal);

        assertEquals(50.0, taxa, 0.001);
    }

    @Test
    void calcularTaxa_DistanciaMenorOuIgual200() {
        // Arrange
        int distance = 150;
        double valorTotal = 1000.0;

        // Act
        double taxa = freteService.calcularTaxa(distance, valorTotal);

        // Assert
        assertEquals(70.0, taxa, 0.001);
    }

    @Test
    void calcularTaxa_DistanciaMenorOuIgual500() {
        // Arrange
        int distance = 300;
        double valorTotal = 1000.0;

        // Act
        double taxa = freteService.calcularTaxa(distance, valorTotal);

        // Assert
        assertEquals(90.0, taxa, 0.001);
    }

    @Test
    void calcularTaxa_DistanciaMaiorQue500() {
        // Arrange
        int distance = 700;
        double valorTotal = 1000.0;

        // Act
        double taxa = freteService.calcularTaxa(distance, valorTotal);

        // Assert
        assertEquals(100.0, taxa, 0.001);
    }
}