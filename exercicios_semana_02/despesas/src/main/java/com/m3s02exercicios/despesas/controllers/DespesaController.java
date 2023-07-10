package com.m3s02exercicios.despesas.controllers;

import com.m3s02exercicios.despesas.models.Despesa;
import com.m3s02exercicios.despesas.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/despesas")
public class DespesaController {
    @Autowired
    private DespesaService despesaService;

    @Autowired
    private Despesa despesa;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Despesa despesa){
        try{
            return ResponseEntity.ok(despesaService.save(despesa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(despesaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@PathVariable("id") Long id, @RequestBody Despesa despesa){
        try{
            return ResponseEntity.ok(despesaService.atualizarDespesa(id, despesa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable("status") String status){
        try{
            return ResponseEntity.ok(despesaService.findByStatus(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/pagar/{id}")
    public ResponseEntity<?> pagarDespesa(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(despesaService.pagarDespesa(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/estornar/{id}")
    public ResponseEntity<?> estornarDespesa(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(despesaService.estornarDespesa(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
