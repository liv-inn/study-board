package com.project.studyboard.DAOs;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.studyboard.models.Estudos;
import com.project.studyboard.models.Materias;

@Repository
public class EstudosDAO {

    private JdbcTemplate jdbc;

    @Autowired
    public EstudosDAO(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Estudos estudo) {
        String sql = "INSERT INTO estudos(data, assunto, duracao, materia_id) VALUES (?,?,?,?)";
        jdbc.update(sql, estudo.getData(), estudo.getAssunto(), estudo.getDuracao(), estudo.getMateriaId());
    }

    public List<Estudos> listarTodos() {
        String sql = "SELECT e.*, m.nome as materia_nome FROM estudos e "
                + "LEFT JOIN materias m ON e.materia_id = m.id";

        return jdbc.query(sql, (rs, rowNum) -> {
            Estudos estudo = new Estudos();
            estudo.setId(rs.getLong("id"));
            estudo.setData(rs.getDate("data").toLocalDate());
            estudo.setAssunto(rs.getString("assunto"));
            estudo.setDuracao(rs.getInt("duracao"));
            estudo.setMateriaId(rs.getLong("materia_id"));
            return estudo;
        });
    }

    public Estudos buscarPorId(Long id){
          String sql = """
            SELECT e.*, m.nome as materia_nome 
            FROM estudos e
            LEFT JOIN materias m ON e.materia_id = m.id 
            WHERE e.id = ?""";
    
        return jdbc.queryForObject(sql, (rs, rowNum) ->
        {
            Estudos estudo = new Estudos();
            estudo.setId(rs.getLong("id"));
            estudo.setData(rs.getDate("data").toLocalDate());
            estudo.setAssunto(rs.getString("assunto"));
            estudo.setDuracao(rs.getInt("duracao"));
            estudo.setMateriaId(rs.getLong("materia_id"));
            return estudo;
            }, id);
            
    }
    

    //atualizar!!
    public void atualizar(Estudos estudo){
        String sql = "UPDATE estudos SET data = ?, assunto = ?, duracao = ?, materia_id = ? WHERE id = ?";
        jdbc.update(sql, estudo.getData(), estudo.getAssunto(), estudo.getDuracao(), estudo.getMateriaId());
    }

    public void deletar(Long id){
        String sql = "DELETE FROM estudos WHERE id = ?";
        jdbc.update(sql, id);
    }


}
