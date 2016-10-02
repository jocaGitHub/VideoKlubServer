/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.MestoRepository;
import com.jovana.videoklubserver.serivce.MestoService;
import com.jovana.videoklubzajednicko.domen.Mesto;
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
public class MestoServiceImpl implements MestoService{

    @Autowired
    MestoRepository mestoRepository;
    
    @Override
    public List<Mesto> findAll() throws Exception {
        return mestoRepository.findAll();
    }

    @Override
    public Mesto find(Mesto mesto) {
        return mestoRepository.findOne(mesto.getMestoid());
    }
    
}
