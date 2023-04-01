package com.pripadovastudie.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pripadovastudie.main.model.Pomocna;
import com.pripadovastudie.main.model.Technologie;
import com.pripadovastudie.main.model.Uchazec;
import com.pripadovastudie.main.model.Uchazec_Technologie;
import com.pripadovastudie.main.service.TechnologieService;
import com.pripadovastudie.main.service.UchazecService;
import com.pripadovastudie.main.service.Uchazec_TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/uchazectechnologie")
public class UchazecTechnologieController {

    @Autowired
    private Uchazec_TechnologieService uchazecTechnologieService;


    @Autowired
    private TechnologieService technologieService;

    @Autowired
    private UchazecService uchazecService;


    //uchazecId - ID uchazece, technoId - ID technologie
    @PostMapping("/add/uchazec/{uchazecId}/techno/{technoId}")
    public ResponseEntity<?> add(@RequestBody Pomocna pomocna, @PathVariable int uchazecId, @PathVariable int technoId){
        //Kontroluji, zda hodnota je v daném intervalu
        if (!((pomocna.getHodnota() >= 0) && (pomocna.getHodnota() <= 10))) {
            return new ResponseEntity<>("Hodnota není v intervalu <0,10>", HttpStatus.BAD_REQUEST);
        }
        //inicializace entit + hledání v databázi
        Uchazec_Technologie uchazecTechnologie = new Uchazec_Technologie();
        Technologie technologie = technologieService.findById(technoId);
        Uchazec uchazec = uchazecService.findById(uchazecId);

        //pokud entity nejsou v DB, tak se vyhodí error
        if (isTechnoNull(technologie) || isUchazecNull(uchazec)) {
            return new ResponseEntity<>("Technologie nebo Uchazec v databázi nenalezena", HttpStatus.NOT_FOUND);
        }

        uchazecTechnologie.setUchazec(uchazec);
        uchazecTechnologie.setTechnologie(technologie);
        uchazecTechnologie.setHodnota(pomocna.getHodnota());
        uchazecTechnologie.setPoznamka(pomocna.getPoznamka());

        uchazecTechnologieService.addUchazTechno(uchazecTechnologie);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);

    }

    //uchazecId - ID uchazece, technoId - ID technologie, {id} je id entity UchazecTegnologie k vyhledání
    @PostMapping("/update/uchazec/{uchazecId}/techno/{technoId}/{id}")
    public ResponseEntity<?> update(@RequestBody Pomocna pomocna, @PathVariable int uchazecId, @PathVariable int technoId, @PathVariable int id){
        //Kontroluji, zda hodnota je v daném intervalu
        if (!((pomocna.getHodnota() >= 0) && (pomocna.getHodnota() <= 10))) {
            return new ResponseEntity<>("Hodnota není v intervalu <0,10>", HttpStatus.BAD_REQUEST);
        }
        //hledání v DB, pokud se nenajde, tak se vyhodí error
        Uchazec_Technologie uchazecTechnologie = uchazecTechnologieService.findById(id);
        if (uchazecTechnologie == null) {
            return new ResponseEntity<>("UchazecTechnologie se v databazi nenaleza", HttpStatus.NOT_FOUND);
        }

        //Incializace technologie, uchazece pomocí hlední v DB pomocí id z url
        Technologie technologie = technologieService.findById(technoId);
        Uchazec uchazec = uchazecService.findById(uchazecId);
        if (isTechnoNull(technologie) || isUchazecNull(uchazec)) {
            return new ResponseEntity<>("Technologie nebo Uchazec v databázi nenalezena", HttpStatus.NOT_FOUND);
        }

        uchazecTechnologie.setUchazec(uchazec);
        uchazecTechnologie.setTechnologie(technologie);
        uchazecTechnologie.setPoznamka(pomocna.getPoznamka());
        uchazecTechnologie.setHodnota(pomocna.getHodnota());

        uchazecTechnologieService.addUchazTechno(uchazecTechnologie);

        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        //hledání v DB, pokud se nenajde, tak se vyhodí error
        Uchazec_Technologie uchazecTechnologie = this.uchazecTechnologieService.findById(id);
        if (uchazecTechnologie == null) {
            return new ResponseEntity<>("UchazecTechnologie v databázi nenalezena", HttpStatus.NOT_FOUND);
        }
        //Vybírám si specifické atributy, co chci vypsat
        String jmeno = uchazecTechnologie.getUchazec().getJmeno();
        String techno = uchazecTechnologie.getTechnologie().getPoznamka();
        int hodnota = uchazecTechnologie.getHodnota();
        String poznamka = uchazecTechnologie.getPoznamka();

        //Převádění Stringů na JsonObject
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("jmeno_uchazece", jmeno);
        jsonObject.addProperty("technologie", techno);
        jsonObject.addProperty("hodnota", hodnota);
        jsonObject.addProperty("poznamka", poznamka);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Převod zpátky na string, ale JSON body pořád zachováno
        String content = gson.toJson(jsonObject);

        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        //hledání v DB, pokud se nenajde, tak se vyhodí error
        Uchazec_Technologie uchazecTechnologie = uchazecTechnologieService.findById(id);
        if (uchazecTechnologie == null) {
            return new ResponseEntity<>("UchazecTechnologie v databázi nenalezena", HttpStatus.NOT_FOUND);
        }

        uchazecTechnologieService.deleteUchazTechno(id);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        //Z DB si vezmu všechny data z entity Uchazec_Technologie
        List<Uchazec_Technologie> list = new ArrayList<>(uchazecTechnologieService.getAllUchazTechnp());
        //pomocný list
        List<String> listString = new LinkedList<>();
        JsonObject jsonObject = new JsonObject();
        Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
        String content = "";

        //Postupně vše z listu "list" si vezmu vybraná data entity a pak je převádím na JSON formát
        for (Uchazec_Technologie temp : list){
            int id = temp.getUchazec_technlogieID();
            Technologie technologie = technologieService.findById(temp.getUchazec_technlogieID());
            Uchazec uchazec = uchazecService.findById(temp.getUchazec().getId());
            String jmeno = uchazec.getJmeno();
            String poznamkaTechno = technologie.getPoznamka();
            int hondota = temp.getHodnota();
            String poznamka = temp.getPoznamka();

            //přidávání názvů k proměným
            jsonObject.addProperty("id_uchazecTechnologie", id);
            jsonObject.addProperty("jmeno_uchazece", jmeno);
            jsonObject.addProperty("poznamka_technologie", poznamkaTechno);
            jsonObject.addProperty("honodta", hondota);
            jsonObject.addProperty("poznamka", poznamka);

            //převádění zpátky na String, ale formát JSONU je zachován
            content = gson.toJson(jsonObject.getAsJsonObject());
            listString.add(content);
        }

        return ResponseEntity.ok(listString);
    }


    //Pomocné metody
    private boolean isTechnoNull(Technologie technologie){
        return technologie == null;
    }

    private boolean isUchazecNull(Uchazec uchazec){
        return uchazec == null;
    }


}
