package DedeUniversWeb.DedeUniversBoxFichiers.controllers;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import DedeUniversWeb.DedeUniversBoxFichiers.services.CategorieService;
import DedeUniversWeb.DedeUniversBoxFichiers.services.SousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/sousCategorie")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class SousCategorieController {
    @Autowired
    SousCategorieService sousCategorieService;
    @Autowired
    CategorieService categorieService;


    @GetMapping("/all")
    public ResponseEntity<List<SousCategorie>> getAllSousCategories(@RequestParam(required = false) String nomSousCategorie ) {

        try {
            List<SousCategorie> sousCategories = new ArrayList<>();
            if (nomSousCategorie == null || nomSousCategorie.trim().isEmpty()) {
                sousCategorieService.recupereTousLesSousCategories().forEach(sousCategories::add);
            }
            if (sousCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(SousCategorie sousCategorie:sousCategories){
                sousCategorie.getCategorie().setSousCategorie(null);
            }
            return new ResponseEntity<>(sousCategories, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/trouverLesSousCategorieParRapportAUneCategorie/{categorieId}")
    public ResponseEntity<List<SousCategorie>> allImagesForProduct(@PathVariable Integer categorieId){
        try {
            Categorie categorie = categorieService.trouveLaCategorieAvecSonId(categorieId);
            List<SousCategorie> sousCategories =sousCategorieService.trouverLesSousCategorieDUneCategorie(categorie);
            for(SousCategorie sousCategorie: sousCategories){
                sousCategorie.getCategorie().setSousCategorie(null);
            }
            return new ResponseEntity<>(sousCategories, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ajout/{categorieId}")
    public ResponseEntity<SousCategorie> addSousCategorie(@RequestBody SousCategorie sousCategorie, @PathVariable Integer categorieId) {

        try {
            if (sousCategorie == null || sousCategorie.getNomSousCategorie() == null || sousCategorie.getNomSousCategorie().trim().isEmpty())
            {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Categorie categorie = categorieService.trouveLaCategorieAvecSonId(categorieId);
            sousCategorie.setCategorie(categorie);
            SousCategorie _sousCategorie= sousCategorieService.metLaSousCategorie(sousCategorie);
            for(SousCategorie sousCategorie2: _sousCategorie.getCategorie().getSousCategorie()){
                sousCategorie2.setCategorie(null);
            }
            if (_sousCategorie != null) {
                return new ResponseEntity<>(_sousCategorie, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping("/modif")
    public ResponseEntity<SousCategorie>updateSousCategorie(@RequestBody SousCategorie sousCategorie){

        try {
            if (sousCategorie == null || sousCategorie.getNomSousCategorie() == null || sousCategorie.getNomSousCategorie().trim().isEmpty())
            {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            SousCategorie _sousCategorie= sousCategorieService.metLaSousCategorie(sousCategorie);
            for(SousCategorie sousCategorie2: _sousCategorie.getCategorie().getSousCategorie()){
                sousCategorie2.setCategorie(null);
            }
            if (_sousCategorie != null) {
                return new ResponseEntity<>(_sousCategorie, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/suppressions/{id}")
    public ResponseEntity<String> removeSousCategorie(@PathVariable("id") Integer id) {

        try {
            if (id <= 0) {
                return new ResponseEntity<String>("Erreur : L'id de la  sous categorie à supprimer doit être > 0 !",
                        HttpStatus.BAD_REQUEST);
            }
            sousCategorieService.suppressionDeLaSousCategorie(id);
            return new ResponseEntity<String>("Suppression de la sous categorie avec id = '" + id + "' effectuée avec succès.",
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
