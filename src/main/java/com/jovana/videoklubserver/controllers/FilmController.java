/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jovana.videoklubserver.serivce.FilmService;
import com.jovana.videoklubserver.serivce.KopijaService;
import com.jovana.videoklubserver.serivce.OsobaService;
import com.jovana.videoklubserver.serivce.UlogaService;
import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Kopija;
import com.jovana.videoklubzajednicko.domen.Osoba;
import com.jovana.videoklubzajednicko.domen.Uloga;
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
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmService filmService;
    
    @Autowired
    KopijaService kopijeService;
    
    @Autowired
    UlogaService ulogaService;
    
    @Autowired
    OsobaService osobaService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Film>> getFilm(@RequestBody Film film) throws Exception{
       if(film!=null){
            Film filmDB = new Film();
            try {
                filmDB= filmService.find(film);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<Film>(null, WsDto.ERROR, "nema ga u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Film>(filmDB, WsDto.SUCCESS, "sistem je nasao film"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Film>(null, WsDto.ERROR, "prosledjeno film je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<List<Film>>> getFilmList() throws Exception{
        List<Film> listaFilmova;
        try {
            listaFilmova= filmService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Film>>(null, WsDto.ERROR, "nema nijednog filma u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Film>>(listaFilmova, WsDto.SUCCESS, "sistem je nasao filmove"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all/search", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.FullFilm.class)
    public ResponseEntity<WsDto<List<Film>>> getFilmPretraga() throws Exception{
        List<Film> listaFilmova;
        try {
            listaFilmova= filmService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Film>>(null, WsDto.ERROR, "nema nijednog filma u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Film>>(listaFilmova, WsDto.SUCCESS, "sistem je nasao filmove"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Film>> saveFilm(@RequestBody Film film) throws Exception{
        if(film!=null){
            Film filmDB = new Film();
            try {
                filmDB= filmService.save(film);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<Film>(null, WsDto.ERROR, "film se ne moze sacuvati"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Film>(filmDB, WsDto.SUCCESS, "sistem je sacuvao film"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Film>(null, WsDto.ERROR, "prosledjeni film je null"), HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/kopija", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Kopija>> getKopija(@RequestBody Kopija kopija) throws Exception{
        if(kopija!=null){
            Kopija kopijaDB;
            try {
                kopijaDB = kopijeService.find(kopija);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new WsDto<Kopija>(null, WsDto.ERROR, "nema kopija za film u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<Kopija>(kopijaDB, WsDto.SUCCESS, "sistem je nasao kopije za film"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Kopija>(null, WsDto.ERROR, "prosledjen film je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/kopija/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<List<Kopija>>> getKopijaList(@RequestBody Film film) throws Exception{
        if(film!=null){
            List<Kopija> kopijeDB = new ArrayList<Kopija>();
            try {
                kopijeDB = (List<Kopija>) kopijeService.findAllForFilm(film);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<List<Kopija>>(null, WsDto.ERROR, "nema kopija za film u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<List<Kopija>>(kopijeDB, WsDto.SUCCESS, "sistem je nasao kopije za film"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Kopija>>(null, WsDto.ERROR, "prosledjen film je null"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/uloge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<List<Uloga>>> getUlogeList(@RequestBody Film film) throws Exception{
        if(film!=null){
            List<Uloga> ulogeDB = new ArrayList<Uloga>();
            try {
                ulogeDB = (List<Uloga>) ulogaService.findAllForFilm(film);
            } catch (Exception ex) {
                return new ResponseEntity<>(new WsDto<List<Uloga>>(null, WsDto.ERROR, "nema uloga za film u bazi"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new WsDto<List<Uloga>>(ulogeDB, WsDto.SUCCESS, "sistem je nasao uloge za film"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Uloga>>(null, WsDto.ERROR, "prosledjen film je null"), HttpStatus.OK);
    
    }
    
    @RequestMapping(value = "/osoba", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<Osoba>> getOsoba(@RequestBody Osoba osoba) throws Exception{
        Osoba osobaDB;
        try {
            osobaDB= osobaService.find(osoba);
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<Osoba>(null, WsDto.ERROR, "nema te osobe u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<Osoba>(osobaDB, WsDto.SUCCESS, "sistem je nasao osobu"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/osoba/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Normal.class)
    public ResponseEntity<WsDto<List<Osoba>>> getOsobeList() throws Exception{
        List<Osoba> listaOsoba;
        try {
            listaOsoba= osobaService.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new WsDto<List<Osoba>>(null, WsDto.ERROR, "nema nijedne osobe u bazi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WsDto<List<Osoba>>(listaOsoba, WsDto.SUCCESS, "sistem je nasao osobe"), HttpStatus.OK);
    }
  
}
