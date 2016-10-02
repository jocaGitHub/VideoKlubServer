/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubklijent.validation.ZaduzenjeValidation;
import com.jovana.videoklubserver.repository.FilmRepository;
import com.jovana.videoklubserver.repository.KopijaRepository;
import com.jovana.videoklubserver.repository.ZaduzenjeRepository;
import com.jovana.videoklubserver.serivce.ZaduzenjeService;
import com.jovana.videoklubzajednicko.domen.Clan;
import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Kopija;
import com.jovana.videoklubzajednicko.domen.KopijaPK;
import com.jovana.videoklubzajednicko.domen.Zaduzenje;
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
public class ZaduzenjeServiceImpl implements ZaduzenjeService{
    @Autowired
    ZaduzenjeRepository zaduzenjeRepository;
    
    @Autowired
    KopijaRepository kopijaRepository;
    
    @Autowired
    FilmRepository filmRepository;
    
    @Override
    public List<Zaduzenje> findAllForClan(Clan clan) {
        List<Zaduzenje> lista = zaduzenjeRepository.findByClanid(clan.getClanid());
        if(lista!=null){
             for (Zaduzenje z : lista) {
                System.out.println("usao u for petlju "+z.toString());
                Kopija k = kopijaRepository.findOne(new KopijaPK(z.getZaduzenjePK().getKopijaid(), z.getZaduzenjePK().getFilmid()));
                System.out.println("kopija u for petlji"+k.getFilm().getNaziv());
                z.setKopija(k);
             }
         }
         return lista;
    }
    
    @Override
    public Zaduzenje save(Zaduzenje zaduzenje) throws Exception {
        ZaduzenjeValidation.zapamtiZaduzenje(zaduzenje);
        
        if (zaduzenje.getZaduzenjePK().getZaduzenjeid().equals("-1")) {
                String zadid = vratiID();
                zaduzenje.getZaduzenjePK().setZaduzenjeid(zadid);
            }else {
                Kopija k = kopijaRepository.findOne(zaduzenje.getKopija().getKopijaPK());
                Film f = filmRepository.findOne(k.getKopijaPK().getFilmid());
                f.getKopijaList().add(k);
            }
        Zaduzenje zaduzenje_db = zaduzenjeRepository.saveAndFlush(zaduzenje);
        return zaduzenje_db;
    }

    @Override
    public Zaduzenje find(Zaduzenje zaduzenje) throws Exception {
        return zaduzenjeRepository.findByZaduzenjeid(zaduzenje.getZaduzenjePK().getZaduzenjeid());
    }

    @Override
    public List<Zaduzenje> findAll() {
        return zaduzenjeRepository.findAll();
    }

    private String vratiID() {
        List<String> lista = zaduzenjeRepository.findAllID();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }
    
}
