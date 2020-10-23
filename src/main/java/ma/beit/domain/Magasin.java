package ma.beit.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Magasin.
 */
@Entity
@Table(name = "magasin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "rtr")
    private String rtr;

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MagasinUtilisateur> magasinUtilisateurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Magasin code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Magasin libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRtr() {
        return rtr;
    }

    public Magasin rtr(String rtr) {
        this.rtr = rtr;
        return this;
    }

    public void setRtr(String rtr) {
        this.rtr = rtr;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public Magasin demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public Magasin addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setMagasin(this);
        return this;
    }

    public Magasin removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setMagasin(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Set<MagasinUtilisateur> getMagasinUtilisateurs() {
        return magasinUtilisateurs;
    }

    public Magasin magasinUtilisateurs(Set<MagasinUtilisateur> magasinUtilisateurs) {
        this.magasinUtilisateurs = magasinUtilisateurs;
        return this;
    }

    public Magasin addMagasinUtilisateur(MagasinUtilisateur magasinUtilisateur) {
        this.magasinUtilisateurs.add(magasinUtilisateur);
        magasinUtilisateur.setMagasin(this);
        return this;
    }

    public Magasin removeMagasinUtilisateur(MagasinUtilisateur magasinUtilisateur) {
        this.magasinUtilisateurs.remove(magasinUtilisateur);
        magasinUtilisateur.setMagasin(null);
        return this;
    }

    public void setMagasinUtilisateurs(Set<MagasinUtilisateur> magasinUtilisateurs) {
        this.magasinUtilisateurs = magasinUtilisateurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Magasin)) {
            return false;
        }
        return id != null && id.equals(((Magasin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Magasin{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", rtr='" + getRtr() + "'" +
            "}";
    }
}
