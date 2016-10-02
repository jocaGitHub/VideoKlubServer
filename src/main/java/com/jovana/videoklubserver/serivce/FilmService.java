/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.serivce;

import com.jovana.videoklubzajednicko.domen.Film;
import java.util.List;

/**
 *
 * @author jmoldovan
 */
public interface FilmService {
    
    public List<Film> findAll() throws Exception;
    
    public void delete(Film film) throws Exception;
    
    public Film save(Film film) throws Exception;
    
    public Film find(Film film) throws Exception;

}
