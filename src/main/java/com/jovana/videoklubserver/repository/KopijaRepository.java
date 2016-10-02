/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.repository;

import com.jovana.videoklubzajednicko.domen.Kopija;
import com.jovana.videoklubzajednicko.domen.KopijaPK;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmoldovan
 */
@Repository
@Transactional
public interface KopijaRepository extends JpaRepository<Kopija, KopijaPK>{

    public List<Kopija> findByFilmid(String filmid);

    public Kopija findByKopijaid(String kopijaid);
    
}
