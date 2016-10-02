/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.repository;

import com.jovana.videoklubzajednicko.domen.Radnici;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public interface RadnikRepository extends JpaRepository<Radnici, String>{
    public Radnici findByKorisnickoime(String korisnickoIme);
}
