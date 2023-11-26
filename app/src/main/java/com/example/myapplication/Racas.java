package com.example.myapplication;

public class Racas {
    private Long id;
    private String raca;
    private Integer quantidade;
    private String tipo;
    private String porte;

    public Racas(Long id, String raca, Integer quantidade, String tipo, String porte) {
        this.id = id;
        this.raca = raca;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.porte = porte;
    }

    public Long getId() {
        return id;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
