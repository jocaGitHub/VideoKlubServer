/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubklijent.validation.ClanValidation;
import com.jovana.videoklubserver.repository.ClanRepository;
import com.jovana.videoklubserver.serivce.ClanService;
import com.jovana.videoklubzajednicko.domen.Clan;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public class ClanServiceImpl implements ClanService{

    @Autowired
    ClanRepository clanRepository;
    
    @Override
    public List<Clan> findAll() throws Exception {
        return clanRepository.findAll();
    }

    @Override
    public void delete(Clan clan) throws Exception {
        if(find(clan) == null) {
            throw new Exception("Clan sa clanID: " + clan.getClanid() + " ne postoji u bazi");
        }
        clanRepository.delete(clan);
    }

    @Override
    public Clan save(Clan clan) throws Exception {
        List<String>jmbgList = clanRepository.findAllJmbg();
        ClanValidation.zapamtiClana(clan,jmbgList);
        if(clan.getClanid().equals("-1")){
            clan.setClanid(vratiID());
        }
        Clan clan_db = clanRepository.saveAndFlush(clan);
        return clan_db;
    }

    @Override
    public Clan find(Clan clan) throws Exception {
        return clanRepository.findOne(clan.getClanid());
    }

    private String vratiID() {
        List<String> lista = clanRepository.findAllID();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }
    
}
