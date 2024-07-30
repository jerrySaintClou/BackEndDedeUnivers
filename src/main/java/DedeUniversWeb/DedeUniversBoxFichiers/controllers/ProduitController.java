package DedeUniversWeb.DedeUniversBoxFichiers.controllers;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/produit")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @PostMapping("/ajout")
    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit){
        try {
            if (produit == null
                    || produit.getNomProduit() == null || produit.getNomProduit().trim().isEmpty()
                    || produit.getDescription() == null || produit.getDescription().trim().isEmpty()) {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Produit _produit = produitService.save(produit);
            if (_produit != null) {
                return new ResponseEntity<>(_produit, HttpStatus.CREATED);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
