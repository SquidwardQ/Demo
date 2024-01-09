package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Evento {
    public LocalDate date;

    public enum Tipo {

        PrimaVolta,
        Scadenza,
        FurtoOSmarrimento,
        Deterioramento,
        Urgente,
        ProlungamentoValidità,
        Minori,
        Rilascio
    }

    public LocalTime time;

    //public int numeroDisponibilità;

    public boolean disponibile;

    public boolean prenotato;

    public Evento() {
    }

    public Evento(LocalDate date, LocalTime time, boolean disponibile, boolean prenotato) {
        this.date = date;
        this.time = time;
        this.disponibile = disponibile;
        this.prenotato = prenotato;
    }
}

