
package com.project.studyboard.models;


public class Anotacoes {

    private Long id;
    private String texto;
    private Long estudoId;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Long getEstudoId() {
        return estudoId;
    }
    public void setEstudoId(Long estudoId) {
        this.estudoId = estudoId;
    }

    

}
