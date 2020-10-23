package ma.beit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MagasinUtilisateur.
 */
@Entity
@Table(name = "magasin_utilisateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MagasinUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nordre")
    private Integer nordre;

    @Column(name = "utilisateur")
    private String utilisateur;

    @ManyToOne
    @JsonIgnoreProperties(value = "magasinUtilisateurs", allowSetters = true)
    private Magasin magasin;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNordre() {
        return nordre;
    }

    public MagasinUtilisateur nordre(Integer nordre) {
        this.nordre = nordre;
        return this;
    }

    public void setNordre(Integer nordre) {
        this.nordre = nordre;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public MagasinUtilisateur utilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public MagasinUtilisateur magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MagasinUtilisateur)) {
            return false;
        }
        return id != null && id.equals(((MagasinUtilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MagasinUtilisateur{" +
            "id=" + getId() +
            ", nordre=" + getNordre() +
            ", utilisateur='" + getUtilisateur() + "'" +
            "}";
    }
}
