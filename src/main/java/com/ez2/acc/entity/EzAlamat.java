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
@Table(name = "ez_alamat", schema = "ez")
public class EzAlamat extends AuditTrail implements Serializable {

    @Id
    @Column(name = "alamat_id", nullable = false, length = 20)
    private String alamatId;
    @Column(name = "jalan", nullable = false)
    private String jalan;
    @Column(name = "kode_provinsi", length = 20)
    private String kodeProvinsi;
    @Column(name = "kode_kabupaten", length = 20)
    private String kodeKabupaten;
    @Column(name = "kode_kecamatan", length = 20)
    private String kodeKecamatan;
    @Column(name = "kode_kelurahan", length = 20)
    private String kodeKelurahan;
    @Column(name = "kode_pos", length = 6)
    private String kodePos;
    @Column(name = "rt", length = 5)
    private String rt;
    @Column(name = "rw", length = 5)
    private String rw;

    public EzAlamat() {
    }

    public String getAlamatId() {
        return alamatId;
    }

    public void setAlamatId(String alamatId) {
        this.alamatId = alamatId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKodeProvinsi() {
        return kodeProvinsi;
    }

    public void setKodeProvinsi(String kodeProvinsi) {
        this.kodeProvinsi = kodeProvinsi;
    }

    public String getKodeKabupaten() {
        return kodeKabupaten;
    }

    public void setKodeKabupaten(String kodeKabupaten) {
        this.kodeKabupaten = kodeKabupaten;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }
}
