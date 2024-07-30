package DedeUniversWeb.DedeUniversBoxFichiers.services;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

}
