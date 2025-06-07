

package com.project.studyboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.studyboard.models.Anotacoes;
import com.project.studyboard.DAOs.AnotacoesDAO;

@Service
public class AnotacoesService {

    @Autowired
    private AnotacoesDAO anotacoesDAO;

    @Transactional
    public void inserirAnotacao(Anotacoes anotacao){
        if(anotacao.getId() == null ){
            anotacoesDAO.inserir(anotacao);
        }else{
            anotacoesDAO.atualizar(anotacao);
        }
    }

    public List<Anotacoes> listarTodos(){
        return anotacoesDAO.listarTodos();
    }
    public Anotacoes listarporId(Long id){
        return anotacoesDAO.buscarPorId(id);
    }

    public void atualizarAnotacao(Anotacoes anotacao){
        anotacoesDAO.atualizar(anotacao);
    }

    public List<Anotacoes> buscarPorEstudo(Long estudoId){
        return anotacoesDAO.buscarPorEstudo(estudoId);
    }

    public void deletarAnotacao(Long id){
        anotacoesDAO.deletar(id);
    }

}
