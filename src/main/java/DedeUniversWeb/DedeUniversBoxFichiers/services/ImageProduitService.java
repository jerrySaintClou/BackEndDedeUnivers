package DedeUniversWeb.DedeUniversBoxFichiers.services;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ImageProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageProduitService {
    @Autowired
    private ImageProduitRepository imageProduitRepository;


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
}
