package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SousCategorieRepository extends JpaRepository<SousCategorie, Integer> {
    List<SousCategorie> findByCategorieOrderByIdAsc(Categorie categorie);

}
