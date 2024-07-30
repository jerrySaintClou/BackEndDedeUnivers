package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageProduitRepository extends JpaRepository<ImageProduit, Integer> {
    List<ImageProduit> findByProduitOrderByIdAsc(Produit produit);
}
