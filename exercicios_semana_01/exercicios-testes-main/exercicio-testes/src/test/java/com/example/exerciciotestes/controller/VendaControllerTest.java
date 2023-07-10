package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.controller.request.VendaRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.model.Venda;
import com.example.exerciciotestes.repository.VendaRepository;
import com.example.exerciciotestes.service.ClienteService;
import com.example.exerciciotestes.service.ProdutoService;
import com.example.exerciciotestes.service.VendaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaControllerTest {

    @Mock
    private VendaRepository repository;

    @InjectMocks
    private VendaService service;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoService produtoService;

    //M3S01Ex09
    @Test
    void saveVenda() {
        Cliente cliente = new Cliente(1L, "nome", 10.0);
        Produto produto = new Produto(1L, "produto", 10.0);

        //given
        when(clienteService.buscaClientePorId(anyLong())).thenReturn(cliente);
        when(produtoService.buscaProdutoPorId(anyLong())).thenReturn(produto);

        Venda venda = service.realizarVenda(new VendaRequest(1L, List.of(1L), 10.0));

        Assertions.assertEquals("nome", venda.getCliente().getNomeCliente());
        Assertions.assertEquals(10.0, venda.getCliente().getSaldoCliente());

    }
}