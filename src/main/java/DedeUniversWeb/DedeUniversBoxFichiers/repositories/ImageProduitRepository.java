package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.ImageProduit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageProduitRepository extends JpaRepository<ImageProduit, Integer> {
//    List<ImageProduit> findByProduitOrderByIdAsc(Produit produit);

    @Query("SELECT ip FROM ImageProduit ip WHERE ip.produit = :produit ORDER BY ip.id ASC")
    List<ImageProduit> findByProduitOrderByIdAsc(@Param("produit") Produit produit);
}
