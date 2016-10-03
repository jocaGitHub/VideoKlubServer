/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.repository;

import com.jovana.videoklubzajednicko.domen.Clan;
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
public interface ClanRepository extends JpaRepository<Clan, String>{
    
    @Query("SELECT count(*) FROM Clan c WHERE c.jmbg = ?1")
    public int findByJmbg(String jmbg);

    @Query("SELECT c.clanid FROM Clan c ORDER BY c.clanid DESC")
    public List<String> findAllID();

    @Query("SELECT c.jmbg FROM Clan c")
    public List<String> findAllJmbg();
    
}
