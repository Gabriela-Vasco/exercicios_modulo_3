package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    //M3S01Ex05
    @Test
    void buscaTodosProdutos() {
        List<Produto> produtos = List.of(new Produto(1L, "produto", 10.0));

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> produtosResultado = produtoService.buscaTodosProdutos();

        assertEquals("produto", produtosResultado.get(0).getNomeProduto());

        verify(produtoRepository).findAll();
    }

    //M3S01Ex06
    @Test
    void salvarProduto() {
        ProdutoRequest produtoRequest = new ProdutoRequest("produto", 10.0);
        Produto produto = new Produto(1L, "produto", 10.0);

        when(produtoRepository.save(ArgumentMatchers.any())).thenReturn(produto);

        Produto produtoResultado = produtoService.salvarProduto(produtoRequest);

        assertEquals("produto", produtoResultado.getNomeProduto());
        assertEquals(10.0, produtoResultado.getValorProduto(), 0.001);
        verify(produtoRepository).save(ArgumentMatchers.any());
    }

    //M3S01Ex07
    @Test
    void atualizarProduto_produtoReal() {
        ProdutoRequest produtoRequest = new ProdutoRequest("produto", 10.0);
        Produto produto = new Produto(1L, "produto", 10.0);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        when(produtoRepository.save(ArgumentMatchers.any())).thenReturn(produto);

        Produto produtoResultado = produtoService.atualizarProduto(anyLong(), produtoRequest);

        assertNotNull(produtoResultado);
        assertEquals("produto", produtoResultado.getNomeProduto());
        assertEquals(10.0, produtoResultado.getValorProduto(), 0.001);
        verify(produtoRepository).save(ArgumentMatchers.any());
        verify(produtoRepository).findById(anyLong());
    }

    //M3S01Ex07
    @Test
    void atualizarProduto_produtoInexistente() {
        ProdutoRequest produtoRequest = new ProdutoRequest("produto", 10.0);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        Produto produtoResultado = produtoService.atualizarProduto(anyLong(), produtoRequest);

        assertNull(produtoResultado);
    }

    //M3S01Ex05
    @Test
    void buscaProdutoPorId() {
        Produto produto = new Produto(1L, "produto", 10.0);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto produtoResultado = produtoService.buscaProdutoPorId(anyLong());

        assertEquals(1L, produtoResultado.getId());
        verify(produtoRepository).findById(anyLong());
    }

    //M3S01Ex08
    @Test
    void detelaProdutoPorId() {
        Produto produto = new Produto(1L, "produto", 10.0);

        doNothing().when(produtoRepository).deleteById(anyLong());

        produtoService.detelaProdutoPorId(produto.getId());

        verify(produtoRepository).deleteById(produto.getId());
    }
}
