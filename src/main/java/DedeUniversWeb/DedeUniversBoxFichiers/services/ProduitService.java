package DedeUniversWeb.DedeUniversBoxFichiers.services;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit findById(Integer id) {
        return produitRepository.findById(id).get();
    }

    public List<Produit> recupereTousLesProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> getByNomProduit(String nomProduit){
        return produitRepository.findByNomProduitContaining(nomProduit);
    }


    public List<Produit> trouverLesSousCategorieDUnProduit(SousCategorie sousCategorie){
        return produitRepository.findBySousCategorieOrderByIdAsc(sousCategorie);
    }

    public void suppressionDuProduit(Integer id) {
        // Supprimez le produit avec l'ID spécifié (utilisez votre propre logique ici)
        produitRepository.deleteById(id);
    }


}
