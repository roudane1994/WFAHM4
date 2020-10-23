package ma.beit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PieceJoindre.
 */
@Entity
@Table(name = "piece_joindre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PieceJoindre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nordre")
    private Integer nordre;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JsonIgnoreProperties(value = "pieceJoindres", allowSetters = true)
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

    public PieceJoindre nordre(Integer nordre) {
        this.nordre = nordre;
        return this;
    }

    public void setNordre(Integer nordre) {
        this.nordre = nordre;
    }

    public String getName() {
        return name;
    }

    public PieceJoindre name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public PieceJoindre url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Demande getDemande() {
        return demande;
    }

    public PieceJoindre demande(Demande demande) {
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
        if (!(o instanceof PieceJoindre)) {
            return false;
        }
        return id != null && id.equals(((PieceJoindre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PieceJoindre{" +
            "id=" + getId() +
            ", nordre=" + getNordre() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
