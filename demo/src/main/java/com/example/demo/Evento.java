package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {
    public LocalDate data;

    public enum Sede{
        Verona,

        Venezia,


        Treviso,


        Padova
    }

    public enum TipoServizio {

        PrimaVolta,
        Scadenza,
        FurtoOSmarrimento,
        Deterioramento,
        Urgente,
        ProlungamentoValidità,
        Minori,
        Rilascio
    }

    Sede sede;

    TipoServizio tipoServizio;

    public LocalTime inizio;
    public LocalTime fine;

    //public int numeroDisponibilità;

    public boolean disponibile;

    public boolean prenotato;

    public Evento() {
    }

    public Evento(LocalDate data, LocalTime inizio, LocalTime fine, boolean disponibile, boolean prenotato, Sede sede, TipoServizio tipoServizio) {
        this.data = data;
        this.inizio = inizio;
        this.fine = fine;
        this.disponibile = disponibile;
        this.prenotato = prenotato;
        this.sede = sede;
        this.tipoServizio = tipoServizio;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalTime inizio) {
        this.inizio = inizio;
    }

    public LocalTime getFine() {
        return fine;
    }

    public void setFine(LocalTime fine) {
        this.fine = fine;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public boolean isPrenotato() {
        return prenotato;
    }

    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }

    @Override
    public String toString() {
        return
                "\ninizio=" + inizio +
                "\nfine=" + fine;

    }
}