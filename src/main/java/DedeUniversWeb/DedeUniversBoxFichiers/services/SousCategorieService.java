package DedeUniversWeb.DedeUniversBoxFichiers.services;


import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.CategorieRepository;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ImageProduitRepository;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ProduitRepository;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.SousCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SousCategorieService {
    @Autowired
    private SousCategorieRepository sousCategorieRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    public List<SousCategorie> recupereTousLesSousCategories() {
        return sousCategorieRepository.findAll();
    }

    public List<SousCategorie> trouverLesSousCategorieDUneCategorie(Categorie categorie){
        return sousCategorieRepository.findByCategorieOrderByIdAsc(categorie);
    }


    public SousCategorie trouveLaSousCategorieAvecSonId(Integer id) {
        return sousCategorieRepository.findById(id).get();
    }

    public SousCategorie metLaSousCategorie(SousCategorie sousCategorie) {
        return sousCategorieRepository.save(sousCategorie);
    }

    public void suppressionDeLaSousCategorie(Integer id) {
        // Supprimez le produit avec l'ID spécifié (utilisez votre propre logique ici)
        sousCategorieRepository.deleteById(id);
    }

}
