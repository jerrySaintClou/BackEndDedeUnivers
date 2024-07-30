package DedeUniversWeb.DedeUniversBoxFichiers.repositories;

import DedeUniversWeb.DedeUniversBoxFichiers.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository  extends JpaRepository<Produit, Integer> {
}
