package com.ceiba.biblioteca.models;

import java.util.Objects;

public class Prestamo {
    
    private Boolean estado;
    private Integer id;
    private Integer tipoUsuario;
    private Long identificacionUsuario;
    private String isbn;
    private String llaveMensaje;
    private String fechaDevolucion;
    private String mensaje;

    public Prestamo() {
    }

    public Prestamo(Boolean estado, Integer id, Integer tipoUsuario, Long identificacionUsuario, String isbn, String llaveMensaje, String fechaDevolucion, String mensaje) {
        this.estado = estado;
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.identificacionUsuario = identificacionUsuario;
        this.isbn = isbn;
        this.llaveMensaje = llaveMensaje;
        this.fechaDevolucion = fechaDevolucion;
        this.mensaje = mensaje;
    }

    public Boolean isEstado() {
        return this.estado;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Long getIdentificacionUsuario() {
        return this.identificacionUsuario;
    }

    public void setIdentificacionUsuario(Long identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLlaveMensaje() {
        return this.llaveMensaje;
    }

    public void setLlaveMensaje(String llaveMensaje) {
        this.llaveMensaje = llaveMensaje;
    }

    public String getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Prestamo estado(Boolean estado) {
        setEstado(estado);
        return this;
    }

    public Prestamo id(Integer id) {
        setId(id);
        return this;
    }

    public Prestamo tipoUsuario(Integer tipoUsuario) {
        setTipoUsuario(tipoUsuario);
        return this;
    }

    public Prestamo identificacionUsuario(Long identificacionUsuario) {
        setIdentificacionUsuario(identificacionUsuario);
        return this;
    }

    public Prestamo isbn(String isbn) {
        setIsbn(isbn);
        return this;
    }

    public Prestamo llaveMensaje(String llaveMensaje) {
        setLlaveMensaje(llaveMensaje);
        return this;
    }

    public Prestamo fechaDevolucion(String fechaDevolucion) {
        setFechaDevolucion(fechaDevolucion);
        return this;
    }

    public Prestamo mensaje(String mensaje) {
        setMensaje(mensaje);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Prestamo)) {
            return false;
        }
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(estado, prestamo.estado) && Objects.equals(id, prestamo.id) && Objects.equals(tipoUsuario, prestamo.tipoUsuario) && Objects.equals(identificacionUsuario, prestamo.identificacionUsuario) && Objects.equals(isbn, prestamo.isbn) && Objects.equals(llaveMensaje, prestamo.llaveMensaje) && Objects.equals(fechaDevolucion, prestamo.fechaDevolucion) && Objects.equals(mensaje, prestamo.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, id, tipoUsuario, identificacionUsuario, isbn, llaveMensaje, fechaDevolucion, mensaje);
    }

    @Override
    public String toString() {
        return "{" +
            " estado='" + isEstado() + "'" +
            ", id='" + getId() + "'" +
            ", tipoUsuario='" + getTipoUsuario() + "'" +
            ", identificacionUsuario='" + getIdentificacionUsuario() + "'" +
            ", isbn='" + getIsbn() + "'" +
            ", llaveMensaje='" + getLlaveMensaje() + "'" +
            ", fechaDevolucion='" + getFechaDevolucion() + "'" +
            ", mensaje='" + getMensaje() + "'" +
            "}";
    }
    
    


   

}
