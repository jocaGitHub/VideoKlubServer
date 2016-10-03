/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jovana.videoklubserver.serivce.ZaduzenjeService;
import com.jovana.videoklubzajednicko.domen.Clan;
import com.jovana.videoklubzajednicko.domen.Zaduzenje;
import com.jovana.videoklubzajednicko.dto.WsDto;
import com.jovana.videoklubzajednicko.json_view.View;
import java.util.ArrayList;
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
@RequestMapping("/zaduzenje")
public class ZaduzenjeController {
    @Autowired
    ZaduzenjeService zaduzenjeService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Zaduzenje>> getZaduzenje(@RequestBody Zaduzenje zaduzenje) throws Exception{
       if(zaduzenje!=null){
            Zaduzenje zaduzenjeDB = new Zaduzenje();
            try {
                zaduzenjeDB= zaduzenjeService.find(zaduzenje);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new WsDto<Zaduzenje>(null, WsDto.ERROR, "nema ga u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Zaduzenje>(zaduzenjeDB, WsDto.SUCCESS, "sistem je nasao zaduzenje"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Zaduzenje>(null, WsDto.ERROR, "prosledjeno zaduzenje je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.FullKopija1.class)
    public ResponseEntity<WsDto<List<Zaduzenje>>> getZaduzenjeList() throws Exception{
        List<Zaduzenje> listaZaduzenja;
        try {
            listaZaduzenja= zaduzenjeService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Zaduzenje>>(null, WsDto.ERROR, "nema nijednog zaduzenja u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Zaduzenje>>(listaZaduzenja, WsDto.SUCCESS, "sistem je nasao zaduzenja"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all/clan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.FullKopija1.class)
    public ResponseEntity<WsDto<List<Zaduzenje>>> getZaduzenjaForClan(@RequestBody Clan clan) throws Exception{
        if(clan!=null){
            List<Zaduzenje> zaduzenjaDB = new ArrayList<Zaduzenje>();
            try {
                zaduzenjaDB = (List<Zaduzenje>) zaduzenjeService.findAllForClan(clan);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new WsDto<List<Zaduzenje>>(null, WsDto.ERROR, "nema zaduzenje za clana u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<List<Zaduzenje>>(zaduzenjaDB, WsDto.SUCCESS, "sistem je nasao zaduzenja za clana"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Zaduzenje>>(null, WsDto.ERROR, "prosledjen clan je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Zaduzenje>> saveZaduzenje(@RequestBody Zaduzenje zaduzenje){
        if(zaduzenje!=null){
            Zaduzenje zaduzenjeDB = new Zaduzenje();
            try {
                zaduzenjeDB= zaduzenjeService.save(zaduzenje);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new WsDto<Zaduzenje>(null, WsDto.ERROR, "zaduzenje se ne moze sacuvati" + ex.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Zaduzenje>(zaduzenjeDB, WsDto.SUCCESS, "sistem je sacuvao zaduzenje"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Zaduzenje>(null, WsDto.ERROR, "prosledjeno zaduzenje je null"), HttpStatus.OK);
    }
}
