/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.RadnikRepository;
import com.jovana.videoklubserver.serivce.RadnikService;
import com.jovana.videoklubzajednicko.domen.Radnici;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public class RadnikServiceImpl implements RadnikService{
    @Autowired
    RadnikRepository radnikRepository;
    
    @Override
    public Radnici find(Radnici radnik) {
        System.out.println("radnik u service impl"+radnik.getKorisnickoime());
        Radnici radnikDB = radnikRepository.findByKorisnickoime(radnik.getKorisnickoime());
        if(radnikDB!=null){
            if(radnikDB.getKorisnickasifra().equals(radnik.getKorisnickasifra()))
                return radnikDB;
        }
        return null;
    }
    
}
