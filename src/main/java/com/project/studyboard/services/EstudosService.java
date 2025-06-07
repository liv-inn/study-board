package com.project.studyboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.studyboard.models.Estudos;
import com.project.studyboard.models.Materias;
import com.project.studyboard.DAOs.EstudosDAO;

@Service
public class EstudosService {

    @Autowired
    private EstudosDAO estudosDAO;

    @Transactional
    public void inserirEstudo(Estudos estudo) {
        if (estudo.getId() == null) {
            estudosDAO.inserir(estudo);
        } else {
            estudosDAO.atualizar(estudo);
        }
    }

    public List<Estudos> listarTodos(){
        return estudosDAO.listarTodos();
    };

       public Estudos buscarpEstudos(Long id){
        return estudosDAO.buscarPorId(id);
    }

    public void atualizarEstudo(Estudos estudo){
        estudosDAO.atualizar(estudo);
    }

    public void deletarEstudo(Long id){
        estudosDAO.deletar(id);
        }
}
