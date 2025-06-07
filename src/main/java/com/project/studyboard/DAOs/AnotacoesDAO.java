package com.project.studyboard.DAOs;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.studyboard.models.Anotacoes;
import com.project.studyboard.models.Estudos;

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

    public List<Anotacoes> listarTodos() {
        String sql = "SELECT a.*, e.assunto as estudo_assunto FROM anotacao a "
                + "LEFT JOIN estudos e ON a.estudo_id = e.id";

        return jdbc.query(sql, (rs, rowNum) -> {
            Anotacoes anotacao = new Anotacoes();
            anotacao.setId(rs.getLong("id"));
            anotacao.setTexto(rs.getString("texto"));

            if (rs.getObject("estudo_id") != null) {
                Estudos estudo = new Estudos();
                estudo.setId(rs.getLong("estudo_id"));
                estudo.setAssunto(rs.getString("estudo_assunto"));
                anotacao.setEstudoId(rs.getLong("estudo_id"));
            }

            return anotacao;
        });
    }

    public Anotacoes buscarPorId(Long id) {
        String sql = """
        SELECT a.*, e.assunto as estudo_assunto, e.data as estudo_data 
        FROM anotacao a 
        LEFT JOIN estudos e ON a.estudo_id = e.id 
        WHERE a.id = ?""";
        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Anotacoes anotacao = new Anotacoes();
            anotacao.setId(rs.getLong("id"));
            anotacao.setTexto(rs.getString("texto"));
            anotacao.setEstudoId(rs.getLong("estudo_id"));
            return anotacao;
        });
    }

    public void atualizar(Anotacoes anotacao) {
        String sql = "UPDATE anotacao SET texto = ?, estudo_id = ? WHERE id = ?";
        jdbc.update(sql, anotacao.getTexto(), anotacao.getEstudoId(), anotacao.getId());
    }

    public List<Anotacoes> buscarPorEstudo(Long estudoId) {
        String sql = "SELECT * FROM anotacao WHERE estudo_id = ?";
        return jdbc.query(sql, (rs, rowNum) -> {
            Anotacoes anotacao = new Anotacoes();
            anotacao.setId(rs.getLong("id"));
            anotacao.setTexto(rs.getString("texto"));
            anotacao.setEstudoId(estudoId);
            return anotacao;
        });
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM anotacao WHERE id = ?";
        jdbc.update(sql, id);
    }

}
