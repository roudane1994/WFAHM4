package ma.beit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "rtr")
    private String rtr;

    @Column(name = "rtr_libelle")
    private String rtrLibelle;

    @Column(name = "objet")
    private String objet;

    @Column(name = "description")
    private String description;

    @Column(name = "date_besion")
    private LocalDate dateBesion;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "createur")
    private String createur;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "affectation_libelle")
    private String affectationLibelle;

    @Column(name = "date_affictation")
    private LocalDate dateAffictation;

    @Column(name = "more_information")
    private String moreInformation;

    @Column(name = "etat")
    private String etat;

    @Column(name = "message_validation")
    private String messageValidation;

    @Column(name = "message_clouture")
    private String messageClouture;

    @Column(name = "date_clouture")
    private LocalDate dateClouture;

    @Column(name = "meilleur_prix_magasin")
    private Double meilleurPrixMagasin;

    @Column(name = "prix_negocie")
    private Double prixNegocie;

    @OneToMany(mappedBy = "demande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DemandeInfo> demandeInfos = new HashSet<>();

    @OneToMany(mappedBy = "demande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PieceJoindre> pieceJoindres = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private DemandeInfo demandeInfos;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private PieceJoindre pieceJoindres;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private Fournisseur fournisseurMagasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private Fournisseur fournisseurFinal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Demande numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRtr() {
        return rtr;
    }

    public Demande rtr(String rtr) {
        this.rtr = rtr;
        return this;
    }

    public void setRtr(String rtr) {
        this.rtr = rtr;
    }

    public String getRtrLibelle() {
        return rtrLibelle;
    }

    public Demande rtrLibelle(String rtrLibelle) {
        this.rtrLibelle = rtrLibelle;
        return this;
    }

    public void setRtrLibelle(String rtrLibelle) {
        this.rtrLibelle = rtrLibelle;
    }

    public String getObjet() {
        return objet;
    }

    public Demande objet(String objet) {
        this.objet = objet;
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public Demande description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateBesion() {
        return dateBesion;
    }

    public Demande dateBesion(LocalDate dateBesion) {
        this.dateBesion = dateBesion;
        return this;
    }

    public void setDateBesion(LocalDate dateBesion) {
        this.dateBesion = dateBesion;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Demande dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCreateur() {
        return createur;
    }

    public Demande createur(String createur) {
        this.createur = createur;
        return this;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public Double getBudget() {
        return budget;
    }

    public Demande budget(Double budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getAffectationLibelle() {
        return affectationLibelle;
    }

    public Demande affectationLibelle(String affectationLibelle) {
        this.affectationLibelle = affectationLibelle;
        return this;
    }

    public void setAffectationLibelle(String affectationLibelle) {
        this.affectationLibelle = affectationLibelle;
    }

    public LocalDate getDateAffictation() {
        return dateAffictation;
    }

    public Demande dateAffictation(LocalDate dateAffictation) {
        this.dateAffictation = dateAffictation;
        return this;
    }

    public void setDateAffictation(LocalDate dateAffictation) {
        this.dateAffictation = dateAffictation;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public Demande moreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
        return this;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public String getEtat() {
        return etat;
    }

    public Demande etat(String etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMessageValidation() {
        return messageValidation;
    }

    public Demande messageValidation(String messageValidation) {
        this.messageValidation = messageValidation;
        return this;
    }

    public void setMessageValidation(String messageValidation) {
        this.messageValidation = messageValidation;
    }

    public String getMessageClouture() {
        return messageClouture;
    }

    public Demande messageClouture(String messageClouture) {
        this.messageClouture = messageClouture;
        return this;
    }

    public void setMessageClouture(String messageClouture) {
        this.messageClouture = messageClouture;
    }

    public LocalDate getDateClouture() {
        return dateClouture;
    }

    public Demande dateClouture(LocalDate dateClouture) {
        this.dateClouture = dateClouture;
        return this;
    }

    public void setDateClouture(LocalDate dateClouture) {
        this.dateClouture = dateClouture;
    }

    public Double getMeilleurPrixMagasin() {
        return meilleurPrixMagasin;
    }

    public Demande meilleurPrixMagasin(Double meilleurPrixMagasin) {
        this.meilleurPrixMagasin = meilleurPrixMagasin;
        return this;
    }

    public void setMeilleurPrixMagasin(Double meilleurPrixMagasin) {
        this.meilleurPrixMagasin = meilleurPrixMagasin;
    }

    public Double getPrixNegocie() {
        return prixNegocie;
    }

    public Demande prixNegocie(Double prixNegocie) {
        this.prixNegocie = prixNegocie;
        return this;
    }

    public void setPrixNegocie(Double prixNegocie) {
        this.prixNegocie = prixNegocie;
    }

    public Set<DemandeInfo> getDemandeInfos() {
        return demandeInfos;
    }

    public Demande demandeInfos(Set<DemandeInfo> demandeInfos) {
        this.demandeInfos = demandeInfos;
        return this;
    }

    public Demande addDemandeInfo(DemandeInfo demandeInfo) {
        this.demandeInfos.add(demandeInfo);
        demandeInfo.setDemande(this);
        return this;
    }

    public Demande removeDemandeInfo(DemandeInfo demandeInfo) {
        this.demandeInfos.remove(demandeInfo);
        demandeInfo.setDemande(null);
        return this;
    }

    public void setDemandeInfos(Set<DemandeInfo> demandeInfos) {
        this.demandeInfos = demandeInfos;
    }

    public Set<PieceJoindre> getPieceJoindres() {
        return pieceJoindres;
    }

    public Demande pieceJoindres(Set<PieceJoindre> pieceJoindres) {
        this.pieceJoindres = pieceJoindres;
        return this;
    }

    public Demande addPieceJoindre(PieceJoindre pieceJoindre) {
        this.pieceJoindres.add(pieceJoindre);
        pieceJoindre.setDemande(this);
        return this;
    }

    public Demande removePieceJoindre(PieceJoindre pieceJoindre) {
        this.pieceJoindres.remove(pieceJoindre);
        pieceJoindre.setDemande(null);
        return this;
    }

    public void setPieceJoindres(Set<PieceJoindre> pieceJoindres) {
        this.pieceJoindres = pieceJoindres;
    }

    public DemandeInfo getDemandeInfos() {
        return demandeInfos;
    }

    public Demande demandeInfos(DemandeInfo demandeInfo) {
        this.demandeInfos = demandeInfo;
        return this;
    }

    public void setDemandeInfos(DemandeInfo demandeInfo) {
        this.demandeInfos = demandeInfo;
    }

    public PieceJoindre getPieceJoindres() {
        return pieceJoindres;
    }

    public Demande pieceJoindres(PieceJoindre pieceJoindre) {
        this.pieceJoindres = pieceJoindre;
        return this;
    }

    public void setPieceJoindres(PieceJoindre pieceJoindre) {
        this.pieceJoindres = pieceJoindre;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Demande magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Fournisseur getFournisseurMagasin() {
        return fournisseurMagasin;
    }

    public Demande fournisseurMagasin(Fournisseur fournisseur) {
        this.fournisseurMagasin = fournisseur;
        return this;
    }

    public void setFournisseurMagasin(Fournisseur fournisseur) {
        this.fournisseurMagasin = fournisseur;
    }

    public Fournisseur getFournisseurFinal() {
        return fournisseurFinal;
    }

    public Demande fournisseurFinal(Fournisseur fournisseur) {
        this.fournisseurFinal = fournisseur;
        return this;
    }

    public void setFournisseurFinal(Fournisseur fournisseur) {
        this.fournisseurFinal = fournisseur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demande)) {
            return false;
        }
        return id != null && id.equals(((Demande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demande{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            ", rtr='" + getRtr() + "'" +
            ", rtrLibelle='" + getRtrLibelle() + "'" +
            ", objet='" + getObjet() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateBesion='" + getDateBesion() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", createur='" + getCreateur() + "'" +
            ", budget=" + getBudget() +
            ", affectationLibelle='" + getAffectationLibelle() + "'" +
            ", dateAffictation='" + getDateAffictation() + "'" +
            ", moreInformation='" + getMoreInformation() + "'" +
            ", etat='" + getEtat() + "'" +
            ", messageValidation='" + getMessageValidation() + "'" +
            ", messageClouture='" + getMessageClouture() + "'" +
            ", dateClouture='" + getDateClouture() + "'" +
            ", meilleurPrixMagasin=" + getMeilleurPrixMagasin() +
            ", prixNegocie=" + getPrixNegocie() +
            "}";
    }
}
