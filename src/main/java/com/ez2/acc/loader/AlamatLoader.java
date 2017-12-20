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

package com.ez2.acc.loader;

import com.ez2.acc.entity.EzKabupaten;
import com.ez2.acc.entity.EzKecamatan;
import com.ez2.acc.entity.EzKelurahan;
import com.ez2.acc.entity.EzProvinsi;
import com.ez2.acc.services.KabupatenServices;
import com.ez2.acc.services.KecamatanServices;
import com.ez2.acc.services.KelurahanServices;
import com.ez2.acc.services.ProvinsiServices;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Hashtable;
import java.util.List;

@SpringComponent
public class AlamatLoader {
    @Autowired
    private ProvinsiServices servicesProvinsi;
    @Autowired
    private KabupatenServices servicesKabupaten;
    @Autowired
    private KecamatanServices servicesKecamatan;
    @Autowired
    private KelurahanServices servicesKelurahan;

    private Hashtable<String, String> hIdKelurahanToKecamatan = new Hashtable<>();
    private Hashtable<String, String> hKodeKecamatanToKabupaten = new Hashtable<>();
    private Hashtable<String, String> hKodeKabupatenToProvinsi = new Hashtable<>();
    private Hashtable<String, String> hKodePosToKelurahan = new Hashtable<>();

    private Hashtable<String, String> hKelurahanName = new Hashtable<>();
    private Hashtable<String, String> hKecamatanName = new Hashtable<>();
    private Hashtable<String, String> hKabupatenName = new Hashtable<>();
    private Hashtable<String, String> hProvinsiName = new Hashtable<>();

    private Hashtable<String, Hashtable<String, String>> hProvinsiToKabupatens = new Hashtable<>();
    private Hashtable<String, Hashtable<String, String>> hKabupatenToKecamatan = new Hashtable<>();
    private Hashtable<String, Hashtable<String, String>> hKecamatanToKelurahan = new Hashtable<>();

    @PostConstruct
    public void load() {
        try {
            List<EzProvinsi> provinces = servicesProvinsi.getAllProvinsi();
            for (EzProvinsi prov : provinces) {
                hProvinsiName.put(prov.getKodeProvinsi(),
                        prov.getNamaProvinsi());
                Hashtable<String, String> _hKabupatenName = new Hashtable<>();
                hProvinsiToKabupatens.put(prov.getKodeProvinsi(), _hKabupatenName);
                List<EzKabupaten> kabupatens = servicesKabupaten.queryByKodeProvinsi(prov.getKodeProvinsi());
                for (EzKabupaten kab : kabupatens) {
                    hKabupatenName.put(kab.getKodeKabupaten(), kab.getNamaKabupaten());
                    _hKabupatenName.put(kab.getKodeKabupaten(), kab.getNamaKabupaten());
                    Hashtable<String, String> _hKecamatanName = new Hashtable<>();
                    hKabupatenToKecamatan.put(kab.getKodeKabupaten(), _hKecamatanName);
                    hKodeKabupatenToProvinsi.put(kab.getKodeKabupaten(), prov.getKodeProvinsi());
                    List<EzKecamatan> kecamatans = servicesKecamatan.queryByKodeKabupaten(kab.getKodeKabupaten());
                    for (EzKecamatan kec : kecamatans) {
                        hKecamatanName.put(kec.getKodeKecamatan(), kec.getNamaKecamatan());
                        _hKecamatanName.put(kec.getKodeKecamatan(), kec.getNamaKecamatan());
                        Hashtable<String, String> _hKelurahanName = new Hashtable<>();
                        hKecamatanToKelurahan.put(kec.getKodeKecamatan(), _hKelurahanName);
                        hKodeKecamatanToKabupaten.put(kec.getKodeKecamatan(), kab.getKodeKabupaten());

                        List<EzKelurahan> kelurahans = servicesKelurahan.queryByKodeKecamatan(kec.getKodeKecamatan());
                        for (EzKelurahan kel : kelurahans) {
                            hKelurahanName.put(kel.getKodeKelurahan(), kel.getNamaKelurahan());
                            _hKelurahanName.put(kel.getKodeKelurahan(), kel.getNamaKelurahan());
                            hIdKelurahanToKecamatan.put(kel.getKodeKelurahan(), kec.getKodeKecamatan());
                            hKodePosToKelurahan.put(kel.getKodeKelurahan(), kel.getKodePos());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Hashtable<String, String> getProvinsi() {
        return hProvinsiName;
    }

    public void replaceProvinsi(EzProvinsi provinsi) {
        hProvinsiName.put(provinsi.getKodeProvinsi(), provinsi.getNamaProvinsi());
    }

    public void removeProvinsi(EzProvinsi provinsi) {
        hProvinsiName.remove(provinsi.getKodeProvinsi());
    }

    public Hashtable<String, String> getKabupatenByProvinsi(String kodeProvinsi) {
        return hProvinsiToKabupatens.get(kodeProvinsi);
    }

    public void replaceKabupaten(EzKabupaten kabupaten) {
        hKabupatenName.put(kabupaten.getKodeKabupaten(), kabupaten.getNamaKabupaten());
        hKodeKabupatenToProvinsi.put(kabupaten.getKodeKabupaten(), kabupaten.getKodeProvinsi());

        Hashtable<String, String> _hKabupatenName = getKabupatenByProvinsi(kabupaten.getKodeProvinsi());
        if (_hKabupatenName == null)
            _hKabupatenName = new Hashtable<String, String>();
        _hKabupatenName.put(kabupaten.getKodeKabupaten(), kabupaten.getNamaKabupaten());
        hProvinsiToKabupatens.put(kabupaten.getKodeProvinsi(), _hKabupatenName);
    }

    public void removeKabupaten(EzKabupaten kabupaten) {
        hKabupatenName.remove(kabupaten.getKodeKabupaten());
        hKodeKabupatenToProvinsi.remove(kabupaten.getKodeKabupaten());

        Hashtable<String, String> _hKabupatenName = getKabupatenByProvinsi(kabupaten.getKodeProvinsi());
        if (_hKabupatenName != null) {
            _hKabupatenName.remove(kabupaten.getKodeKabupaten());
            hProvinsiToKabupatens.put(kabupaten.getKodeProvinsi(), _hKabupatenName);
        }
    }

    public Hashtable<String, String> getKecamatanByKabupaten(String kodeKabupaten) {
        return hKabupatenToKecamatan.get(kodeKabupaten);
    }

    public void replaceKecamatan(EzKecamatan kecamatan) {
        hKecamatanName.put(kecamatan.getKodeKecamatan(), kecamatan.getNamaKecamatan());
        hKodeKecamatanToKabupaten.put(kecamatan.getKodeKecamatan(), kecamatan.getKodeKabupaten());

        Hashtable<String, String> _hKecamatanName = getKecamatanByKabupaten(kecamatan.getKodeKabupaten());
        if (_hKecamatanName == null)
            _hKecamatanName = new Hashtable<String, String>();
        _hKecamatanName.put(kecamatan.getKodeKecamatan(), kecamatan.getNamaKecamatan());
        hKabupatenToKecamatan.put(kecamatan.getKodeKabupaten(), _hKecamatanName);
    }

    public void removeKecamatan(EzKecamatan kecamatan) {
        hKecamatanName.remove(kecamatan.getKodeKecamatan());
        hKodeKecamatanToKabupaten.remove(kecamatan.getKodeKecamatan());

        Hashtable<String, String> _hKecamatanName = getKecamatanByKabupaten(kecamatan.getKodeKabupaten());
        if (_hKecamatanName != null) {
            _hKecamatanName.remove(kecamatan.getKodeKecamatan());
            hKabupatenToKecamatan.put(kecamatan.getKodeKabupaten(), _hKecamatanName);
        }
    }

    public Hashtable<String, String> getKelurahanByKecamatan(
            String kodeKecamatan) {
        return hKecamatanToKelurahan.get(kodeKecamatan);
    }

    public void replaceKelurahan(EzKelurahan kelurahan) {
        hKelurahanName.put(kelurahan.getKodeKelurahan(), kelurahan.getNamaKelurahan());
        hIdKelurahanToKecamatan.put(kelurahan.getKodeKelurahan(), kelurahan.getKodeKecamatan());
        hKodePosToKelurahan.put(kelurahan.getKodeKelurahan(), kelurahan.getKodePos());

        Hashtable<String, String> _hKelurahanName = getKelurahanByKecamatan(kelurahan.getKodeKecamatan());
        if (_hKelurahanName == null)
            _hKelurahanName = new Hashtable<String, String>();
        _hKelurahanName.put(kelurahan.getKodeKelurahan(), kelurahan.getNamaKelurahan());
        hKecamatanToKelurahan.put(kelurahan.getKodeKecamatan(), _hKelurahanName);
    }

    public void removeKelurahan(EzKelurahan kelurahan) {
        hKelurahanName.remove(kelurahan.getKodeKelurahan());
        hIdKelurahanToKecamatan.remove(kelurahan.getKodeKelurahan());
        hKodePosToKelurahan.remove(kelurahan.getKodeKelurahan());

        Hashtable<String, String> _hKelurahanName = getKelurahanByKecamatan(kelurahan.getKodeKecamatan());
        if (_hKelurahanName != null) {
            _hKelurahanName.remove(kelurahan.getKodeKelurahan());
            hKecamatanToKelurahan.put(kelurahan.getKodeKecamatan(), _hKelurahanName);
        }
    }

    public String getKodePosByIdKelurahan(String idKelurahan) {
        return hKodePosToKelurahan.get(idKelurahan);
    }

    public String getKelurahanById(String idKelurahan) {
        return hKelurahanName.get(idKelurahan);
    }

    public String getKecamatanByKelurahan(String idKelurahan) {
        return hIdKelurahanToKecamatan.get(idKelurahan);
    }

    public String getKabupatenByKecamatan(String kodeKecamatan) {
        return hKodeKecamatanToKabupaten.get(kodeKecamatan);
    }

    public String getProvinsiByKabupaten(String kodeKabupaten) {
        return hKodeKabupatenToProvinsi.get(kodeKabupaten);
    }

    public String getKecamatanById(String idKecamatan) {
        return hKecamatanName.get(idKecamatan);
    }

    public String getKabupatenById(String idKabupatan) {
        return hKabupatenName.get(idKabupatan);
    }

    public String getProvinsiById(String idProvinsi) {
        return hProvinsiName.get(idProvinsi);
    }
    
}
