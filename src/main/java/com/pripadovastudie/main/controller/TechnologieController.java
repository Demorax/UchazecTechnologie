package com.pripadovastudie.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pripadovastudie.main.model.Technologie;
import com.pripadovastudie.main.model.Uchazec;
import com.pripadovastudie.main.service.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/technologie")
@CrossOrigin(origins = "http://localhost:3000")
public class TechnologieController {

    @Autowired
    private TechnologieService technologieService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Technologie technologie){
        technologieService.addTechnologie(technologie);
        return new ResponseEntity<>("Technologie created", HttpStatus.CREATED);

    }

    //{technologieId} vezmu si s URL id, pomocí id se najde danou technologii
    @PostMapping("/update/{technologieId}")
    public ResponseEntity<?> update(@RequestBody Technologie technologie, @PathVariable int technologieId){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Technologie technologieFromDb = technologieService.findById(technologieId);
        if (technologieFromDb == null) {
            return new ResponseEntity<>("Technologie v databázi nenalezena", HttpStatus.NOT_FOUND);
        }
        technologieFromDb.setPoznamka(technologie.getPoznamka());

        technologieService.addTechnologie(technologieFromDb);
        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Technologie technologie = technologieService.findById(id);
        if (technologie == null) {
            return new ResponseEntity<>("Technologie v databázi nenalezena", HttpStatus.NOT_FOUND);
        }
        String poznamka = technologie.getPoznamka();

        //Převádění Stringu na JsonObject
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("poznamka_technologie", poznamka);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Převod zpátky na string, ale JSON body pořád zachováno
        String content = gson.toJson(jsonObject);

        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Technologie technologie = technologieService.findById(id);
        if (technologie == null) {
            return new ResponseEntity<>("Technologie v databázi nenalezena", HttpStatus.NOT_FOUND);
        }

        technologieService.deleteTechnologie(id);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        //Z DB si vezmu všechny data z entity Technologie
        List<Technologie> list = new ArrayList<>(technologieService.getAllTechnologie());
        //pomocný list
        List<String> listString = new LinkedList<>();
        JsonObject jsonObject = new JsonObject();
        Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
        String content = "";

        //Postupně vše z listu "list" si vezmu vybraná data entity a pak je převádím na JSON formát
        for (Technologie temp : list){
            int id = temp.getId();
            String poznamka = temp.getPoznamka();

            //tady přidávám i id, jelikož chceme všechny data z technologie, tak se hodí vědět i id
            //přidávání názvů k proměným
            jsonObject.addProperty("id_technologie", id);
            jsonObject.addProperty("poznamka_technologie", poznamka);

            //převádění zpátky na String, ale formát JSONU je zachován
            content = gson.toJson(jsonObject.getAsJsonObject());
            listString.add(content);
        }

        return ResponseEntity.ok(listString);
    }
}

