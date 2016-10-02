/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Clan;
import com.jovana.videoklubzajednicko.domen.Zaduzenje;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public interface ZaduzenjeService {
    
    public List<Zaduzenje> findAllForClan(Clan clan);
    
    public List<Zaduzenje> findAll();
    
    public Zaduzenje save(Zaduzenje zaduzenje) throws Exception;
    
    public Zaduzenje find(Zaduzenje zaduzenje) throws Exception;
    
}
