/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Uloga;
import java.util.List;

/**
 *
 * @author jmoldovan
 */
public interface UlogaService {
    public List<Uloga> findAll() throws Exception;
     
    public List<Uloga> findAllForFilm(Film film) throws Exception;
    
    public void delete(Uloga uloga) throws Exception;
    
    public Uloga save(Uloga uloga) throws Exception;
    
    public Uloga find(Uloga uloga) throws Exception;
}
