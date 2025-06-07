package com.project.studyboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.studyboard.DAOs.MateriasDAO;
import com.project.studyboard.models.Materias;

@Service
public class MateriasService {

    @Autowired
    private MateriasDAO materiasDAO;

    public void inserirMateria(Materias materia){
        materiasDAO.inserir(materia);
    };

    public List<Materias> listarMaterias() {
        return materiasDAO.listarMaterias();
    }

    public Materias buscarpMaterias(Long id){
        return materiasDAO.buscarPorId(id);
    }

    public void deletarMateria(Long id){
        materiasDAO.deletar(id);
    }



}
