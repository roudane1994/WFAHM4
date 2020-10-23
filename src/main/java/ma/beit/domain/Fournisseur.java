package ma.beit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Fournisseur.
 */
@Entity
@Table(name = "fournisseur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "fournisseurMagasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    @OneToMany(mappedBy = "fournisseurFinal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "fournisseurs", allowSetters = true)
    private Demande demandePourFournisseurMagasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "fournisseurs", allowSetters = true)
    private Demande demandePourFournisseurFinal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Fournisseur libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public Fournisseur demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public Fournisseur addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setFournisseurMagasin(this);
        return this;
    }

    public Fournisseur removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setFournisseurMagasin(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public Fournisseur demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public Fournisseur addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setFournisseurFinal(this);
        return this;
    }

    public Fournisseur removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setFournisseurFinal(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Demande getDemandePourFournisseurMagasin() {
        return demandePourFournisseurMagasin;
    }

    public Fournisseur demandePourFournisseurMagasin(Demande demande) {
        this.demandePourFournisseurMagasin = demande;
        return this;
    }

    public void setDemandePourFournisseurMagasin(Demande demande) {
        this.demandePourFournisseurMagasin = demande;
    }

    public Demande getDemandePourFournisseurFinal() {
        return demandePourFournisseurFinal;
    }

    public Fournisseur demandePourFournisseurFinal(Demande demande) {
        this.demandePourFournisseurFinal = demande;
        return this;
    }

    public void setDemandePourFournisseurFinal(Demande demande) {
        this.demandePourFournisseurFinal = demande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fournisseur)) {
            return false;
        }
        return id != null && id.equals(((Fournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fournisseur{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
