package com.domain.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_lokasi")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Location implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OrderColumn
    @Column(name = "nama_lokasi", nullable = false)
    private String name;

    @Column(nullable = false)
    private String negara;

    @Column(nullable = false)
    private String provinsi;

    @Column(nullable = false)
    private String kota;

    @Column(nullable = false, updatable = true, columnDefinition = "timestamp")
    private Timestamp created_at;

    // Relasi
    @ManyToMany(mappedBy = "lokasiList")
    private List<Project> proyekList;

    // Getter Setter
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // Default constructor
    public Location() {}

    // Constructor with parameters
    public Location(String name, String negara, String provinsi, String kota, Timestamp created_at, List<Project> proyekList) {
        this.name = name;
        this.negara = negara;
        this.provinsi = provinsi;
        this.kota = kota;
        this.created_at = created_at;
        this.proyekList = proyekList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<Project> getProyekList() {
        return proyekList;
    }

    public void setProyekList(List<Project> proyekList) {
        this.proyekList = proyekList;
    }

    @PrePersist
    protected void onCreate() {
        created_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        created_at = new Timestamp(System.currentTimeMillis());
    }
          
}
