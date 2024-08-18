package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class LocationDto {
    
    @NotEmpty(message = "nama_lokasi is required")
    private String name;

    @NotEmpty(message = "negara is required")
    private String negara;

    @NotEmpty(message = "provinsi is required")
    private String provinsi;

    @NotEmpty(message = "kota is required")
    private String kota;

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

    
}
