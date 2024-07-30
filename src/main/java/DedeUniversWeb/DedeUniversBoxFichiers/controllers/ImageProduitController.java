package DedeUniversWeb.DedeUniversBoxFichiers.controllers;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.services.ImageProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/imageProduit")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class ImageProduitController {
    @Autowired
    ImageProduitService imageProduitService;


    @GetMapping("/all")
    public ResponseEntity<List<ImageProduit>> getAllImageProduits(@RequestParam(required = false) String cheminImageProduit ) {

        try {
            List<ImageProduit> imageProduits = new ArrayList<>();
            if (cheminImageProduit == null || cheminImageProduit.trim().isEmpty()) {
                imageProduitService.recupereTousLesImageProduits().forEach(imageProduits::add);
            }
            if (imageProduits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(imageProduits, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("tousLesImageDuProduit")
    public ResponseEntity<List<ImageProduit>> allImagesForProduct(){
        try {
            List<ImageProduit> imageProduits = new ArrayList<>();
            imageProduitService.trouverLesImageDunProduit()
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ajout")
    public ResponseEntity<ImageProduit> addImageProduit(@RequestBody ImageProduit imageProduit) {

        try {
            if (imageProduit == null || imageProduit.getCheminImageProduit() == null || imageProduit.getCheminImageProduit().trim().isEmpty())
            {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            ImageProduit _imageProduit = imageProduitService.metLeImageProduit(imageProduit);
            if (_imageProduit != null) {
                return new ResponseEntity<>(_imageProduit, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
