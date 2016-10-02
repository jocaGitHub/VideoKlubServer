/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Kopija;
import java.util.List;

/**
 *
 * @author jmoldovan
 */
public interface KopijaService {
    
    public List<Kopija> findAll() throws Exception;
    
    public List<Kopija> findAllForFilm(Film film) throws Exception;
    
    public void delete(Kopija kopija) throws Exception;
    
    public Kopija save(Kopija kopija) throws Exception;
    
    public Kopija find(Kopija kopija) throws Exception;
}
