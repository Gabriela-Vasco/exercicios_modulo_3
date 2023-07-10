package com.m3s02exercicios.despesas.services;

import com.m3s02exercicios.despesas.models.Despesa;
import com.m3s02exercicios.despesas.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

import java.util.List;

@Service
public class DespesaService {
    private final Despesa despesa;

    public DespesaService() {
        this.despesa = new Despesa();
    }

    @Autowired
    public DespesaRepository despesaRepository;

    public DespesaService(Despesa despesa) {
        this.despesa = despesa;
    }

    public Despesa save(Despesa despesa) throws Exception{
        despesa.setDataPagemento(null);
        despesa.setStatus("PE");
        despesa.setId(null);

        if(despesa.getCredor() == null){
            throw new Exception("Preencha o nome do credor");
        }
        despesa.setCredor(despesa.getCredor());

        if(despesa.getValor() == null){
            throw new Exception("Preencha o valor da despesa");
        }
        despesa.setValor(despesa.getValor());

        if(despesa.getDataVencimento() == null){
            throw new Exception("Preencha a data de vencimento da despesa");
        }
        despesa.setDataVencimento(despesa.getDataVencimento());

        despesa = despesaRepository.save(despesa);

        return despesa;
    }

    
    public List<Despesa> findAll(){

        List<Despesa> despesas;

        despesas = despesaRepository.findAll();

        return despesas;
    }

    public Despesa getDespesa() {
        return despesa;
    }

   public Despesa atualizarDespesa(Long id, Despesa despesa) throws Exception {
        Despesa despesaExistente = despesaRepository.findById(id)
                .orElseThrow(() -> new Exception("Despesa não encontrada com o ID: " + id));

        
        despesaExistente.setCredor(despesa.getCredor());
        despesaExistente.setDataVencimento(despesa.getDataVencimento());
        despesaExistente.setValor(despesa.getValor());
        despesaExistente.setDescricao(despesa.getDescricao());

        //Uma despesa não pode ser alterada caso o status seja 'PA'
        if(despesaExistente.getStatus().equals("PA")){
            throw new Exception("Despesa não pode ser alterada pois já foi paga");
        }

        //os atributos 'Data de Pagamento' e 'Status' não podem ser alterados
        despesaExistente.setDataPagemento(despesaExistente.getDataPagemento());
        despesaExistente.setStatus(despesaExistente.getStatus());


        
        return despesaRepository.save(despesaExistente);
    }

    public List<Despesa> findByStatus(String status){
        List<Despesa> despesas = despesaRepository.findByStatus(status);
        return despesas;
    }


    public boolean pagarDespesa(Long id){
        Despesa despesa = findById(id);
        despesa.setStatus("PA");
        despesa.setDataPagemento(new Date());
        return true;
    }

    public boolean estornarDespesa(Long id){
        Despesa despesa = findById(id);
        despesa.setStatus("PE");
        despesa.setDataPagemento(null);
        return true;
    }

    private Despesa findById(Long id) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(id);
        return despesaOptional.get();
    }
}
