/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.repository;

import com.jovana.videoklubzajednicko.domen.Film;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmoldovan
 */
@Repository
@Transactional
public interface FilmRepository extends JpaRepository<Film, String>{

    @Query("SELECT f.filmid FROM Film f ORDER BY f.filmid DESC")
    public List<String> findAllID();
    
}
