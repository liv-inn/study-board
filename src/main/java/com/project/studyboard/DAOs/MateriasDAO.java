
package com.project.studyboard.DAOs;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.studyboard.models.Materias;

@Repository
public class MateriasDAO {

    private JdbcTemplate jdbc;

    @Autowired
    public MateriasDAO(DataSource dataSource){
        this.jdbc = new JdbcTemplate(dataSource);
    }


    public void inserir(Materias materia){
        String sql = "INSERT INTO materias(nome) VALUES(?)";
        jdbc.update(sql, materia.getNome());
    }

    public List<Materias> listarMaterias(){
        String sql = "SELECT * FROM materias";
        return jdbc.query(sql, (rs, rowNum) -> {
            Materias materia = new Materias();
            materia.setId(rs.getLong("id"));
            materia.setNome(rs.getString("nome"));
            return materia;
        });
    }


    public Materias buscarPorId(Long id){
        String sql = "SELECT * FROM materias WHERE id = ?";
        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Materias materia = new Materias();
            materia.setId(rs.getLong("id"));
            materia.setNome(rs.getString("nome"));
            return materia;
        }, id);
    }

    //delete

    public void deletar(Long id){
        String sql = "DELETE FROM materias WHERE id = ?";
        jdbc.update(sql, id);
    }




}
