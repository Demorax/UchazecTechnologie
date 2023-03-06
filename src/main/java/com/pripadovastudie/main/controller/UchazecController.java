package com.pripadovastudie.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pripadovastudie.main.model.Uchazec;
import com.pripadovastudie.main.service.UchazecService;
import com.pripadovastudie.main.service.Uchazec_TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/uchazec")
public class UchazecController {

    @Autowired
    private UchazecService uchazecService;



    @Autowired
    private Uchazec_TechnologieService uchazecTechnologieService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Uchazec uchazec){
        uchazecService.addUchazec(uchazec);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);

    }


    //{uchazecId} vezmu si s URL id, pomocí id se najde daný uchazeč
    @PostMapping("/update/{uchazecId}")
    public ResponseEntity<?> update(@RequestBody Uchazec uchazec, @PathVariable int uchazecId){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Uchazec uchazecFromDb = uchazecService.findById(uchazecId);
        if (uchazecFromDb == null) {
            return new ResponseEntity<>("Uchazeč v databázi nenalezen", HttpStatus.NOT_FOUND);
        }
        uchazecFromDb.setJmeno(uchazec.getJmeno());
        uchazecFromDb.setPrijmeni(uchazec.getPrijmeni());

        uchazecService.addUchazec(uchazecFromDb);
        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }

    //{id} vezmu si s URL id, pomocí id se najde daný uchazeč
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Uchazec uchazec = uchazecService.findById(id);
        if (uchazec == null) {
            return new ResponseEntity<>("Uchazeč v databázi nenalezen", HttpStatus.NOT_FOUND);
        }

        String jmeno = uchazec.getJmeno();
        String prijmeni = uchazec.getPrijmeni();

        //převádím Stringy "jmeno" a "prijmeni" na JsonObject a přidávám ještě jména atributů
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("jmeno_uchazece", jmeno);
        jsonObject.addProperty("prijmeni_uchazece", prijmeni);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //převedení zpátky na String, ale JSOn formát zůstal stejný
        String content = gson.toJson(jsonObject);
        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        Uchazec uchazec = uchazecService.findById(id);
        if (uchazec == null) {
            return new ResponseEntity<>("Uchazeč v databázi nenalezen", HttpStatus.NOT_FOUND);
        }


        uchazecService.deleteUchazec(id);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        //inicializace pomocí hledání v DB pomocí id, pokud v DB není, vyhodí se error
        List<Uchazec> list = new ArrayList<>(uchazecService.getAllUchazec());
        //pomocný list
        List<String> listString = new LinkedList<>();
        JsonObject jsonObject = new JsonObject();
        Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
        String content = "";

        //Postupně vše z listu "list" si vezmu vybraná data entity a pak je převádím na JSON formát
        for (Uchazec temp : list){
            int id = temp.getId();
            String jmeno = temp.getJmeno();
            String prijmeni = temp.getPrijmeni();

            //tady přidávám i id, jelikož chceme všechny data z uchazeče, tak se hodí vědět i id
            //přidávání názvů k proměným
            jsonObject.addProperty("id_uchazece", id);
            jsonObject.addProperty("jmeno_uchazece", jmeno);
            jsonObject.addProperty("prijmeni_uchazece", prijmeni);

            //převádění zpátky na String, ale formát JSONU je zachován
            content = gson.toJson(jsonObject.getAsJsonObject());
            listString.add(content);
        }

        return ResponseEntity.ok(listString);
    }

}
