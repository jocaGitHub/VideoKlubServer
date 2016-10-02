/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.repository;

import com.jovana.videoklubzajednicko.domen.Zaduzenje;
import com.jovana.videoklubzajednicko.domen.ZaduzenjePK;
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
public interface ZaduzenjeRepository extends JpaRepository<Zaduzenje, ZaduzenjePK>{
    
    public List<Zaduzenje>findByClanid(String clanid);
    public Zaduzenje findByZaduzenjeid (String zaduzenjeid);

    @Query("SELECT z.zaduzenjePK.zaduzenjeid FROM Zaduzenje z ORDER BY z.zaduzenjePK.zaduzenjeid DESC")
    public List<String> findAllID();
}
