package DedeUniversWeb.DedeUniversBoxFichiers.controllers;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import DedeUniversWeb.DedeUniversBoxFichiers.services.ProduitService;
import DedeUniversWeb.DedeUniversBoxFichiers.services.SousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/produit")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @Autowired
    SousCategorieService sousCategorieService;

    @GetMapping("/tousLesProduits")
    public ResponseEntity<List<Produit>> getAllProduits(@RequestParam(required = false) String nomProduit) {

        try {
            List<Produit> produits = new ArrayList<>();
            if(nomProduit == null || nomProduit.trim().isEmpty())
            {
                produitService.recupereTousLesProduits().forEach(produits::add);
            }else {
                produitService.getByNomProduit(nomProduit).forEach(produits::add);
            }
            if (produits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(Produit produit: produits){
                produit.getImageProduits().forEach(p->p.setProduit(null));
//                produit.setSousCategorie(null);
            }
            return new ResponseEntity<>(produits, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getBookById(@RequestParam(required = false) Integer id) {
            try{
                if (id <= 0) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                else {
                    Produit produit = produitService.findById(id);
                    produit.getImageProduits().forEach(p->p.setProduit(null));
                    return new ResponseEntity<>(produit, HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


//
//    @GetMapping("/trouverLesProduitsAvecUneSousCategorie/{sousCategorieId}")
//    public ResponseEntity<List<Produit>> allProductForSubCategory(@PathVariable Integer sousCategorieId){
//        try {
//            SousCategorie sousCategories = sousCategorieService.trouveLaSousCategorieAvecSonId(sousCategorieId);
//            List<Produit> produits =produitService.trouverLesSousCategorieDUnProduit(sousCategories);
//            for(Produit produit: produits){
//                produit.getSousCategorie().setProduits(null);
//                produit.getSousCategorie().getCategorie().setSousCategorie(null);
//            }
//            return new ResponseEntity<>(produits, HttpStatus.OK);
//        }catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


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


    @PutMapping("/modif")
    public ResponseEntity<Produit>updateProduit(@RequestBody Produit produit){
        try {
            if (produit == null
                    || produit.getNomProduit() == null || produit.getNomProduit().trim().isEmpty()
                    || produit.getDescription() == null || produit.getDescription().trim().isEmpty()
                    || produit.getId() <= 0
            ) {

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


    @DeleteMapping("/suppressions/{id}")
    public ResponseEntity<String> removeBook(@PathVariable("id") Integer id) {

        try {
            if (id <= 0) {
                return new ResponseEntity<String>("Erreur : L'id du book à supprimer doit être > 0 !",
                        HttpStatus.BAD_REQUEST);
            }
            produitService.suppressionDuProduit(id);
            return new ResponseEntity<String>("Suppression du book avec id = '" + id + "' effectuée avec succès.",
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
