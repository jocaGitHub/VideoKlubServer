/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.OsobaRepository;
import com.jovana.videoklubserver.serivce.OsobaService;
import com.jovana.videoklubzajednicko.domen.Osoba;
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
public class OsobaServiceImpl implements OsobaService{
    @Autowired
    OsobaRepository osobaRepository;
    
    @Override
    public List<Osoba> findAll() {
        return osobaRepository.findAll();
    }

    @Override
    public Osoba find(Osoba osoba) {
        return osobaRepository.findOne(osoba.getOsobaid());
    }
}
