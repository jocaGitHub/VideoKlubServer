/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jovana.videoklubserver.serivce.RadnikService;
import com.jovana.videoklubzajednicko.domen.Radnici;
import com.jovana.videoklubzajednicko.dto.WsDto;
import com.jovana.videoklubzajednicko.json_view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmoldovan
 */
@RestController
@RequestMapping("/radnik")
public class RadnikController {
    @Autowired
    RadnikService radnikService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Radnici>> getRadnik(@RequestBody Radnici radnik) throws Exception{
       if(radnik!=null){
            Radnici radnikDB = new Radnici();
            try {
                radnikDB= radnikService.find(radnik);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<Radnici>(null, WsDto.ERROR, "ne postoji radnik sa takvim korisnickim imenom i korisnickom sifrom u bazi"), HttpStatus.OK);
            }
            if(radnikDB!=null){
            return new ResponseEntity<>(new WsDto<Radnici>(radnikDB, WsDto.SUCCESS, "sistem je nasao radnika"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Radnici>(null, WsDto.ERROR, "ne postoji radnik sa takvim korisnickim imenom i korisnickom sifrom u bazi"), HttpStatus.OK);

        }
        return new ResponseEntity<>(new WsDto<Radnici>(null, WsDto.ERROR, "prosledjeno radnik je null"), HttpStatus.OK);
    }
}
