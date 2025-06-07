
package com.project.studyboard.models;

import java.time.LocalDate;

public class Estudos {

    private Long id;
    private LocalDate data;
    private String assunto;
    private Integer duracao;
    private Long materiaId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public Integer getDuracao() {
        return duracao;
    }
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
    public Long getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    

}
