/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Clan;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jmoldovan
 */
public interface ClanService {
    
    public List<Clan> findAll() throws Exception;
    
    public void delete(Clan clan) throws Exception;
    
    public Clan save(Clan clan) throws Exception;
    
    public Clan find(Clan clan) throws Exception;
    
}
