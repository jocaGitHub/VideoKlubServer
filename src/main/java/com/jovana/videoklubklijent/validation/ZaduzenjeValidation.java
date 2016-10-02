/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubklijent.validation;

import com.jovana.videoklubzajednicko.domen.Zaduzenje;

/**
 *
 * @author jmoldovan
 */
public class ZaduzenjeValidation {

    public static void zapamtiZaduzenje(Zaduzenje zaduzenje) throws Exception{
        if(zaduzenje.getDatumrazduzenja()!=null){
                if(zaduzenje.getDatumrazduzenja().before(zaduzenje.getDatumzaduzenja())){
                    throw new Exception("Ne mozete uneti datum razduzenja koji je pre datuma zaduzenja!");
                }
            }
    }
    
}
