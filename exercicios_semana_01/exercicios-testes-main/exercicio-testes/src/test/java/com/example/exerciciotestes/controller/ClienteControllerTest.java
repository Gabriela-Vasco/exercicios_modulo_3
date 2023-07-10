package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import com.example.exerciciotestes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
class ClienteControllerTest {

    @MockBean
    ClienteService service;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClienteRepository clienteRepository;

    //M3S01Ex10
    @Test
    void getAllCliente() throws Exception {
        List<Cliente> clientes = List.of(new Cliente("nome", 10.0));


        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/clientes")
                                .accept(MediaType.APPLICATION_JSON)
                                .content(
                                        """
                                         {
                                            "nomeCliente" : "nome",
                                            "saldoCliente" : 10.0
                                         }
                                         """
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //M3S01Ex10
    @Test
    void getClienteById() throws Exception {
        Cliente cliente = new Cliente(1L, "nome", 10.0);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/clientes/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                 {
                                    "nomeCliente" : "nome",
                                    "saldoCliente" : 10.0
                                 }
                                 """
                        )

        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //M3S01Ex10
    @Test
    void saveCliente() throws Exception{
        ClienteRequest clienteRequest = new ClienteRequest("nome", 10.0);
        Cliente cliente = new Cliente("nome", 10.0);

        when(service.salvarCliente(any())).thenReturn(cliente);

        Cliente clienteResultado = service.salvarCliente(clienteRequest);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                 {
                                    "nomeCliente" : "nome",
                                    "saldoCliente" : 10.0
                                 }
                                 """
                        )
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(
                                """
                                {
                                    "nomeCliente" : "nome",
                                    "saldoCliente" : 10.0
                                }
                                """
                ));
    }
}