package com.domain.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_proyek")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Project implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nama_proyek", nullable = false)
    private String name;

    @Column(nullable = false)
    private String client;

    @Column(name = "tgl_mulai", nullable = false)
    private LocalDateTime start;

    @Column(name = "tgl_selesai", nullable = false)
    private LocalDateTime end;

    @Column(name = "pimpinan_proyek", nullable = false)
    private String leader;

    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String keterangan;

    @Column(nullable = false, updatable = true, columnDefinition = "timestamp")
    private Timestamp created_at;

    // Relasi
    @ManyToMany
    @JoinTable(
        name = "tbl_proyek_lokasi",
        joinColumns = @JoinColumn(name = "proyek_id"),
        inverseJoinColumns = @JoinColumn(name = "lokasi_id")
    )
    private List<Location> lokasiList;

    // Getter Setter
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // Default constructor
    public Project() {}

    // Constructor with parameters
    public Project(String name, String client, LocalDateTime start, LocalDateTime end, String leader, String keterangan, Timestamp created_at, List<Location> lokasiList) {
        this.name = name;
        this.client = client;
        this.start = start;
        this.end = end;
        this.leader = leader;
        this.keterangan = keterangan;
        this.created_at = created_at;
        this.lokasiList = lokasiList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<Location> getLokasiList() {
        return lokasiList;
    }

    public void setLokasiList(List<Location> lokasiList) {
        this.lokasiList = lokasiList;
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
