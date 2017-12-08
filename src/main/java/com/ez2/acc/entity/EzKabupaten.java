/*
 * This file is part of the EZ2 Accounting Project.
 * Author :
 * - Ivan Aribanilia
 * - Lukman Arizal
 * - Aristion Rizki
 * - Gabriel Sebastian
 *
 * Copyright (c) 2017. EZ2 Company.
 * Inc. All Rights Reserved. Terms of Use, Privacy and Trademark Guidelines
 */

package com.ez2.acc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ez_kabupaten", schema = "ez")
public class EzKabupaten extends AuditTrail implements Serializable {
    @Id
    @Column(name = "kode_kabupaten", nullable = false, length = 4)
    private String kodeKabupaten;
    @Column(name = "nama_kabupaten", nullable = false, length = 200)
    private String namaKabupaten;
    @Column(name = "kode_provinsi", nullable = false, length = 2)
    private String kodeProvinsi;

    public EzKabupaten() {
    }

    public String getKodeKabupaten() {
        return this.kodeKabupaten;
    }

    public void setKodeKabupaten(String kodeKabupaten) {
        this.kodeKabupaten = kodeKabupaten;
    }

    public String getKodeProvinsi() {
        return this.kodeProvinsi;
    }

    public void setKodeProvinsi(String kodeProvinsi) {
        this.kodeProvinsi = kodeProvinsi;
    }

    public String getNamaKabupaten() {
        return this.namaKabupaten;
    }

    public void setNamaKabupaten(String namaKabupaten) {
        this.namaKabupaten = namaKabupaten;
    }

}
