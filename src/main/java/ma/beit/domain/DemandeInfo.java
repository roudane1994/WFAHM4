package ma.beit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DemandeInfo.
 */
@Entity
@Table(name = "demande_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemandeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nordre")
    private Integer nordre;

    @Column(name = "info_demande")
    private String infoDemande;

    @Column(name = "reponse")
    private String reponse;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "date_reponse")
    private LocalDate dateReponse;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandeInfos", allowSetters = true)
    private Demande demande;

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

    public DemandeInfo nordre(Integer nordre) {
        this.nordre = nordre;
        return this;
    }

    public void setNordre(Integer nordre) {
        this.nordre = nordre;
    }

    public String getInfoDemande() {
        return infoDemande;
    }

    public DemandeInfo infoDemande(String infoDemande) {
        this.infoDemande = infoDemande;
        return this;
    }

    public void setInfoDemande(String infoDemande) {
        this.infoDemande = infoDemande;
    }

    public String getReponse() {
        return reponse;
    }

    public DemandeInfo reponse(String reponse) {
        this.reponse = reponse;
        return this;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public DemandeInfo dateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
        return this;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getDateReponse() {
        return dateReponse;
    }

    public DemandeInfo dateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
        return this;
    }

    public void setDateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
    }

    public Demande getDemande() {
        return demande;
    }

    public DemandeInfo demande(Demande demande) {
        this.demande = demande;
        return this;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeInfo)) {
            return false;
        }
        return id != null && id.equals(((DemandeInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeInfo{" +
            "id=" + getId() +
            ", nordre=" + getNordre() +
            ", infoDemande='" + getInfoDemande() + "'" +
            ", reponse='" + getReponse() + "'" +
            ", dateDemande='" + getDateDemande() + "'" +
            ", dateReponse='" + getDateReponse() + "'" +
            "}";
    }
}
