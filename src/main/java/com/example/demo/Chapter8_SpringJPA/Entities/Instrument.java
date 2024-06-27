package com.example.demo.Chapter8_SpringJPA.Entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INSTRUMENT")
public class Instrument implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    private String instrumentId;

    @Id
    @Column(name = "INSTRUMENT_ID")
    public String getInstrumentId() {
        return this.instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * Defining relation @ManyToMany Singers
     */
    private Set<Singer> singers = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    public Set<Singer> getSingers() {
        return this.singers;
    }
    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }
}