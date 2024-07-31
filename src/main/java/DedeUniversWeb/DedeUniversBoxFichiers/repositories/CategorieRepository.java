package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import java.util.List;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Categorie;
import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository  extends JpaRepository<Categorie, Integer> {

    List<Categorie> findByNomCategorieContaining(String nomCategorie);
}
