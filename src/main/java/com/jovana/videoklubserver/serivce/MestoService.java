/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Mesto;
import java.util.List;

/**
 *
 * @author jmoldovan
 */
public interface MestoService {
    
    public List<Mesto> findAll() throws Exception;

    public Mesto find(Mesto mesto);
}
