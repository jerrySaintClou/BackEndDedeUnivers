package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository  extends JpaRepository<Produit, Integer> {
//    @Query("SELECT p FROM Produit p ORDER BY p.id DESC")
//    Produit findLastProduit();

    List<Produit> findByNomProduitContaining(String nomProduit);

    List<Produit> findBySousCategorieOrderByIdAsc(SousCategorie sousCategorie);
}
