package DedeUniversWeb.DedeUniversBoxFichiers.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sous_categories")
public class SousCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_sous_categorie", length = 80, nullable = false)
    private String nomSousCategorie;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "sousCategorie", fetch = FetchType.LAZY)
    private List<Produit> produits;

    public SousCategorie() {
    }

    public SousCategorie(String nomSousCategorie, Categorie categorie) {
        this.nomSousCategorie = nomSousCategorie;
        this.categorie = categorie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
