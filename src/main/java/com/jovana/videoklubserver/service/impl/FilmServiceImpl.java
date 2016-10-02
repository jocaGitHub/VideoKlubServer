/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.FilmRepository;
import com.jovana.videoklubserver.repository.OsobaRepository;
import com.jovana.videoklubserver.repository.UlogaRepository;
import com.jovana.videoklubserver.repository.ZaduzenjeRepository;
import com.jovana.videoklubserver.serivce.FilmService;
import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Kopija;
import com.jovana.videoklubzajednicko.domen.Osoba;
import com.jovana.videoklubzajednicko.domen.Uloga;
import com.jovana.videoklubzajednicko.domen.Zaduzenje;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository filmRepository;
    
    @Autowired
    UlogaRepository ulogaRepository;
    
    @Autowired
    OsobaRepository osobaRepository;
    
    @Autowired
    ZaduzenjeRepository zaduzenjeRepository;
    
    @Override
    public List<Film> findAll() throws Exception {
        List<Film> lista = filmRepository.findAll();
        List<Zaduzenje> zad = zaduzenjeRepository.findAll();
        if (lista != null) {
            for (Film f1 : lista) {
                f1.getKopijaList().size();
            }
            for (Film f : lista) {
                List<Kopija> Nkop = new ArrayList<>();
                for (Kopija k : f.getKopijaList()) {
                    for (Zaduzenje z : zad) {
                        if (z.getKopija().equals(k) && z.getStatuszaduzenja().equals("zauzeto")) {
                            Nkop.add(k);
                        }
                    }
                }
                for (Kopija kk : Nkop) {
                    f.getKopijaList().remove(kk);
                }
            }
            return lista;
        } else {
            throw new Exception("Sistem ne moze da vrati listu filmova");
        }
    }

    @Override
    public void delete(Film film) throws Exception {
        if(find(film)==null){
            throw new Exception ("ne postoji film sa id "+film.getFilmid() +" u bazi");
        }
        filmRepository.delete(film);
    }

    @Override
    public Film save(Film film) throws Exception {
        if(film.getFilmid()==null){
            film.setFilmid(vratiID());
        }
        if (film.getKopijaList() != null) {
                for (Kopija k : film.getKopijaList()) {
                    k.getKopijaPK().setFilmid(film.getFilmid());
                }
            }
            if (film.getUlogaList() != null) {
                for (Uloga u : film.getUlogaList()) {
                    u.getUlogaPK().setFilmid(film.getFilmid());
                }
            }  
            return filmRepository.saveAndFlush(film);
    }
    
    public String vratiID() {
        List<String> lista = filmRepository.findAllID();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }

    @Override
    public Film find(Film film) throws Exception {
        Film f = filmRepository.findOne(film.getFilmid());
        if(f!=null){
            for (Uloga u : f.getUlogaList()) {
                Osoba osoba = osobaRepository.findOne(u.getUlogaPK().getOsobaid());
                u.setOsoba(osoba);
            }
        }
        return f;
    }

}
