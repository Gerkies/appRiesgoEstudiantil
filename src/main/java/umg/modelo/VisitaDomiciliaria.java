/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class VisitaDomiciliaria {

    private int idVisita;
    private int idEstudiante;
    private int idVisitador;
    private String familiaVisitada;
    private String fechaHora;
    private String motivo;
    private String situacionEncontrada;
    private String accionesRealizadas;
    private String compromisos;
    private String proximaVisita;
    private String observaciones;

    public VisitaDomiciliaria() {
    }

    public VisitaDomiciliaria(int idVisita, int idEstudiante, int idVisitador, String familiaVisitada, String fechaHora, String motivo, String situacionEncontrada, String accionesRealizadas, String compromisos, String proximaVisita, String observaciones) {
        this.idVisita = idVisita;
        this.idEstudiante = idEstudiante;
        this.idVisitador = idVisitador;
        this.familiaVisitada = familiaVisitada;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.situacionEncontrada = situacionEncontrada;
        this.accionesRealizadas = accionesRealizadas;
        this.compromisos = compromisos;
        this.proximaVisita = proximaVisita;
        this.observaciones = observaciones;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdVisitador() {
        return idVisitador;
    }

    public void setIdVisitador(int idVisitador) {
        this.idVisitador = idVisitador;
    }

    public String getFamiliaVisitada() {
        return familiaVisitada;
    }

    public void setFamiliaVisitada(String familiaVisitada) {
        this.familiaVisitada = familiaVisitada;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSituacionEncontrada() {
        return situacionEncontrada;
    }

    public void setSituacionEncontrada(String situacionEncontrada) {
        this.situacionEncontrada = situacionEncontrada;
    }

    public String getAccionesRealizadas() {
        return accionesRealizadas;
    }

    public void setAccionesRealizadas(String accionesRealizadas) {
        this.accionesRealizadas = accionesRealizadas;
    }

    public String getCompromisos() {
        return compromisos;
    }

    public void setCompromisos(String compromisos) {
        this.compromisos = compromisos;
    }

    public String getProximaVisita() {
        return proximaVisita;
    }

    public void setProximaVisita(String proximaVisita) {
        this.proximaVisita = proximaVisita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

