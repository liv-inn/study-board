package com.project.studyboard.DAOs;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.studyboard.models.Anotacoes;

@Repository
public class AnotacoesDAO {

    private JdbcTemplate jdbc;

    @Autowired
    public AnotacoesDAO(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    //inserindo
    public void inserir(Anotacoes anotacao) {
        String sql = "INSERT INTO anotacao (texto, estudo_id) VALUES (?, ?)";
        jdbc.update(sql, anotacao.getTexto(), anotacao.getEstudoId());
    }

    public void atualizar(Anotacoes anotacao) {
        String sql = "UPDATE anotacao SET texto = ?, estudo_id = ? WHERE id = ?";
        jdbc.update(sql, anotacao.getTexto(), anotacao.getEstudoId(), anotacao.getId());
    }

    public List<Anotacoes> buscarPorEstudo(Long estudoId){
        String sql = "SELECT * FROM anotacao WHERE estudo_id = ?";
        return jdbc.query(sql, (rs, rowNum) -> {
            Anotacoes anotacao = new Anotacoes();
            anotacao.setId(rs.getLong("id"));
            anotacao.setTexto(rs.getString("texto"));
            anotacao.setEstudoId(estudoId);
            return anotacao;
        });
    }

    public void deletar(Long id){
        String sql = "DELETE FROM anotacao WHERE id = ?";
        jdbc.update(sql, id);
    }

}
