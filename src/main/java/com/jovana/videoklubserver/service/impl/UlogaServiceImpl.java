/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.UlogaRepository;
import com.jovana.videoklubserver.serivce.UlogaService;
import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Uloga;
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
public class UlogaServiceImpl implements UlogaService{
    @Autowired
    UlogaRepository ulogaRepository;
    
    @Override
    public List<Uloga> findAll() throws Exception {
        return ulogaRepository.findAll();
    }

    @Override
    public void delete(Uloga uloga) throws Exception {
   
    }

    @Override
    public Uloga save(Uloga uloga) throws Exception {
        return null;
    }

    @Override
    public Uloga find(Uloga uloga) throws Exception {
        return ulogaRepository.findOne(uloga.getUlogaPK().getUlogaid());
    }

    @Override
    public List<Uloga> findAllForFilm(Film film) throws Exception {
        return ulogaRepository.findByFilmid(film.getFilmid());
    }
    
}
