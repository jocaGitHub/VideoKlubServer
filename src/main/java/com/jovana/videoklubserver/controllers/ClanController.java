/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jovana.videoklubserver.serivce.ClanService;
import com.jovana.videoklubserver.serivce.MestoService;
import com.jovana.videoklubzajednicko.domen.Clan;
import com.jovana.videoklubzajednicko.domen.Mesto;
import com.jovana.videoklubzajednicko.dto.WsDto;
import com.jovana.videoklubzajednicko.json_view.View;
import java.util.List;
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
@RequestMapping("/clan")
public class ClanController {
    
    @Autowired
    ClanService clanService;
    
    @Autowired 
    MestoService mestoService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Clan>> getClan(@RequestBody Clan clan) throws Exception{
//        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        if(clan!=null){
            Clan clanDB = new Clan();
            try {
                clanDB= clanService.find(clan);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<Clan>(null, WsDto.ERROR, "nema ga u bazi"), HttpStatus.OK);
            }
//            return mapperFactory.getMapperFacade().map(clanDB, Clan.class);
            return new ResponseEntity<>(new WsDto<Clan>(clanDB, WsDto.SUCCESS, "sistem je nasao clana"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Clan>(null, WsDto.ERROR, "prosledjen clan je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<List<Clan>>> getClanAll(){
        List<Clan> listaClanova;
        try {
            listaClanova= clanService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Clan>>(null, WsDto.ERROR, "nema nijednog clana u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Clan>>(listaClanova, WsDto.SUCCESS, "sistem je nasao clanove"), HttpStatus.OK);
    
    }
    
    @RequestMapping(value = "/mesta", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
     public ResponseEntity<WsDto<List<Mesto>>> getMestoList(){
        List<Mesto> listaMesta;
        try {
            listaMesta= mestoService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Mesto>>(null, WsDto.ERROR, "nema nijedno mesto u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Mesto>>(listaMesta, WsDto.SUCCESS, "sistem je nasao mesta"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/mesto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
     public ResponseEntity<WsDto<Mesto>> getMesto(@RequestBody Mesto mesto){
        Mesto mestoDB;
        try {
            mestoDB= mestoService.find(mesto);
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<Mesto>(null, WsDto.ERROR, "nema nijedno mesto u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Mesto>(mestoDB, WsDto.SUCCESS, "sistem je nasao mesta"), HttpStatus.OK);
    } 
     
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Clan>> saveClan(@RequestBody Clan clan){
        if(clan!=null){
            Clan clanDB = new Clan();
            try {
                clanDB= clanService.save(clan);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<Clan>(null, WsDto.ERROR, "clan se ne moze sacuvati "+ ex.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Clan>(clanDB, WsDto.SUCCESS, "sistem je sacuvao clana"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Clan>(null, WsDto.ERROR, "prosledjen clan je null"), HttpStatus.OK);
    }

    
}
