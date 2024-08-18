package com.domain.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProjectDto {

    @NotEmpty(message = "nama_proyek is required")
    private String name;

    @NotEmpty(message = "client is required")
    private String client;

    @NotNull(message = "tgl_mulai is required")
    private LocalDate start;

    @NotNull(message = "tgl_selesai is required")
    private LocalDate end;

    @NotEmpty(message = "pimpinan_proyek is required")
    private String leader;

    @NotNull(message = "keterangan is required")
    private String keterangan;

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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
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
    
}
