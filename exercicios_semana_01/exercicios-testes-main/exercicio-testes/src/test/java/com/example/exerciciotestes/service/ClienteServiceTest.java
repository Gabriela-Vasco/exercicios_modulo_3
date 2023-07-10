package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;


    //M3S01Ex01
    @Test
    void buscaTodosClientes() {
        //Declaração dos dados utilizados no teste
        List<Cliente> clientes = List.of(new Cliente(1L, "nome", 10.0));

        //Mock ("falsificação") da resposta do findAll(), só serve para testar a lógica do método
        when(clienteRepository.findAll()).thenReturn(clientes);

        //Chamada do método a ser testado, nesse caso é o buscaTodosClientes()
        List<Cliente> clientesResultado = clienteService.buscaTodosClientes();

        //Validação do resultado da chamada do método
        assertEquals("nome", clientesResultado.get(0).getNomeCliente());
        //Validação do uso do mock criado assim, se o findAll foi de fato chamado
        verify(clienteRepository).findAll();
    }

    //M3S01Ex02
    @Test
    void salvarCliente() {
        ClienteRequest clienteRequest = new ClienteRequest("nome", 10.0);
        Cliente cliente = new Cliente("nome", 10.0);

        when(clienteRepository.save(any())).thenReturn(cliente);

        Cliente clienteResultado = clienteService.salvarCliente(clienteRequest);

        assertEquals("nome", clienteResultado.getNomeCliente());
        assertEquals(10.0, clienteResultado.getSaldoCliente(), 0.001);
        verify(clienteRepository).save(any());
    }

   //M2S01Ex03
    @Test
    void atualizarCliente_ClienteReal() {
        //simula um cliente existente
        ClienteRequest clienteRequest = new ClienteRequest("nome", 1000.0);

        Cliente cliente = new Cliente(1L,"nomeNovo", 500.0);
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.atualizarCliente(anyLong(), clienteRequest);

        assertNotNull(resultado);
        assertEquals(clienteRequest.getNomeCliente(), resultado.getNomeCliente());
        assertEquals(clienteRequest.getSaldoCliente(), resultado.getSaldoCliente());
    }

    //M2S01Ex03
    @Test
    public void atualizarCliente_ClienteInexistente() {
        //simula um cliente que não existe
        Long id = 2L;
        ClienteRequest clienteNovo = new ClienteRequest("Novo Nome", 1000.0);

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.atualizarCliente(id, clienteNovo);

        assertNull(resultado);
    }


    //M3S01Ex01
    @Test
    void buscaClientePorId() {
        Cliente cliente = new Cliente(1L, "nome", 10.0);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Cliente clienteResultado = clienteService.buscaClientePorId(anyLong());

        assertEquals(1L, clienteResultado.getId());
        verify(clienteRepository).findById(anyLong());
    }

    //M3S01Ex04
    @Test
    void detelaClientePorId_ClienteExistente() {
        Cliente cliente = new Cliente(1L,"nome", 10.0);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).deleteById(anyLong());

        clienteService.detelaClientePorId(cliente.getId());

        verify(clienteRepository).findById(cliente.getId());
        verify(clienteRepository).deleteById(cliente.getId());
    }

    //M3S01Ex04
    @Test
    void detelaClientePorId_ClienteInexistente() {
        Cliente cliente = new Cliente();
        cliente.setId(999L);

        assertThrows(HttpClientErrorException.class, () -> clienteService.detelaClientePorId(cliente.getId()));
    }

}