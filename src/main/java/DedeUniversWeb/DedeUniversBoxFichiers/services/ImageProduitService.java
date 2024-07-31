package DedeUniversWeb.DedeUniversBoxFichiers.services;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ImageProduitRepository;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageProduitService {
    @Autowired
    private ImageProduitRepository imageProduitRepository;
    @Autowired
    private ProduitRepository produitRepository;

    public List<ImageProduit> recupereTousLesImageProduits() {
        return imageProduitRepository.findAll();
    }


    public ImageProduit metLeImageProduit(ImageProduit imageProduit) {
        return imageProduitRepository.save(imageProduit);
    }

    public void supprimeLeImageProduit(Integer id) {
        // Supprimez le produit avec l'ID spécifié (utilisez votre propre logique ici)
        imageProduitRepository.deleteById(id);
    }

    public List<ImageProduit>trouverLesImageDunProduit(Produit produit){
        return this.imageProduitRepository.findByProduitOrderByIdAsc(produit);
    }

    public void suppressionDeLImageDuProduit(Integer id) {
        // Supprimez le produit avec l'ID spécifié (utilisez votre propre logique ici)
        imageProduitRepository.deleteById(id);
    }

//    public List<ImageProduit> trouverLesImagesDuDernierProduit() {
//        Produit dernierProduit = produitRepository.findLastProduit();
//        if (dernierProduit != null) {
//            return imageProduitRepository.findByProduitOrderByIdAsc(dernierProduit);
//        } else {
//            return List.of(); // retourne une liste vide si aucun produit n'est trouvé
//        }
//    }
}
