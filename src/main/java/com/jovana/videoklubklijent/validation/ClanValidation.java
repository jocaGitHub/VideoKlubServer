/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubklijent.validation;

import com.jovana.videoklubzajednicko.domen.Clan;
import java.util.Date;

/**
 *
 * @author jmoldovan
 */
public class ClanValidation {
    public static void zapamtiClana(Clan trenutniClan) throws Exception{
        // validacija 1
        if (trenutniClan == null || trenutniClan.getIme() == null || trenutniClan.getPrezime() == null || trenutniClan.getJmbg() == null
                || trenutniClan.getDatumosnivanja() == null) {
            throw new Exception("Polja: ime, prezime, jmbg i datum rodjenjda ne smeju biti prazna.");
        }
        
        validacijaJMBG(trenutniClan.getJmbg());
    }
    
    private static void validacijaJMBG(String jmbg) throws Exception{
        char[] niz = jmbg.toCharArray();

        if(niz.length!=13) {
            throw new Exception("JMBG mora da sadrzi 13 cifara.");
            
        }
        
        for (char c : niz) {
            if (!Character.isDigit(c)) {
                throw new Exception("JMBG mora da sadrzi samo cifre.");
            }
        }
    }
}
