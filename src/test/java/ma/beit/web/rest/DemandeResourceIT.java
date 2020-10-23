package ma.beit.web.rest;

import ma.beit.Wfahm4App;
import ma.beit.domain.Demande;
import ma.beit.repository.DemandeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DemandeResource} REST controller.
 */
@SpringBootTest(classes = Wfahm4App.class)
@AutoConfigureMockMvc
@WithMockUser
public class DemandeResourceIT {

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_RTR = "AAAAAAAAAA";
    private static final String UPDATED_RTR = "BBBBBBBBBB";

    private static final String DEFAULT_RTR_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_RTR_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_BESION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_BESION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATEUR = "AAAAAAAAAA";
    private static final String UPDATED_CREATEUR = "BBBBBBBBBB";

    private static final Double DEFAULT_BUDGET = 1D;
    private static final Double UPDATED_BUDGET = 2D;

    private static final String DEFAULT_AFFECTATION_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_AFFECTATION_LIBELLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_AFFICTATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_AFFICTATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MORE_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_MORE_INFORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT = "AAAAAAAAAA";
    private static final String UPDATED_ETAT = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_VALIDATION = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_VALIDATION = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_CLOUTURE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_CLOUTURE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CLOUTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CLOUTURE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MEILLEUR_PRIX_MAGASIN = 1D;
    private static final Double UPDATED_MEILLEUR_PRIX_MAGASIN = 2D;

    private static final Double DEFAULT_PRIX_NEGOCIE = 1D;
    private static final Double UPDATED_PRIX_NEGOCIE = 2D;

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeMockMvc;

    private Demande demande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demande createEntity(EntityManager em) {
        Demande demande = new Demande()
            .numero(DEFAULT_NUMERO)
            .rtr(DEFAULT_RTR)
            .rtrLibelle(DEFAULT_RTR_LIBELLE)
            .objet(DEFAULT_OBJET)
            .description(DEFAULT_DESCRIPTION)
            .dateBesion(DEFAULT_DATE_BESION)
            .dateCreation(DEFAULT_DATE_CREATION)
            .createur(DEFAULT_CREATEUR)
            .budget(DEFAULT_BUDGET)
            .affectationLibelle(DEFAULT_AFFECTATION_LIBELLE)
            .dateAffictation(DEFAULT_DATE_AFFICTATION)
            .moreInformation(DEFAULT_MORE_INFORMATION)
            .etat(DEFAULT_ETAT)
            .messageValidation(DEFAULT_MESSAGE_VALIDATION)
            .messageClouture(DEFAULT_MESSAGE_CLOUTURE)
            .dateClouture(DEFAULT_DATE_CLOUTURE)
            .meilleurPrixMagasin(DEFAULT_MEILLEUR_PRIX_MAGASIN)
            .prixNegocie(DEFAULT_PRIX_NEGOCIE);
        return demande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demande createUpdatedEntity(EntityManager em) {
        Demande demande = new Demande()
            .numero(UPDATED_NUMERO)
            .rtr(UPDATED_RTR)
            .rtrLibelle(UPDATED_RTR_LIBELLE)
            .objet(UPDATED_OBJET)
            .description(UPDATED_DESCRIPTION)
            .dateBesion(UPDATED_DATE_BESION)
            .dateCreation(UPDATED_DATE_CREATION)
            .createur(UPDATED_CREATEUR)
            .budget(UPDATED_BUDGET)
            .affectationLibelle(UPDATED_AFFECTATION_LIBELLE)
            .dateAffictation(UPDATED_DATE_AFFICTATION)
            .moreInformation(UPDATED_MORE_INFORMATION)
            .etat(UPDATED_ETAT)
            .messageValidation(UPDATED_MESSAGE_VALIDATION)
            .messageClouture(UPDATED_MESSAGE_CLOUTURE)
            .dateClouture(UPDATED_DATE_CLOUTURE)
            .meilleurPrixMagasin(UPDATED_MEILLEUR_PRIX_MAGASIN)
            .prixNegocie(UPDATED_PRIX_NEGOCIE);
        return demande;
    }

    @BeforeEach
    public void initTest() {
        demande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemande() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();
        // Create the Demande
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isCreated());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate + 1);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testDemande.getRtr()).isEqualTo(DEFAULT_RTR);
        assertThat(testDemande.getRtrLibelle()).isEqualTo(DEFAULT_RTR_LIBELLE);
        assertThat(testDemande.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testDemande.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDemande.getDateBesion()).isEqualTo(DEFAULT_DATE_BESION);
        assertThat(testDemande.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testDemande.getCreateur()).isEqualTo(DEFAULT_CREATEUR);
        assertThat(testDemande.getBudget()).isEqualTo(DEFAULT_BUDGET);
        assertThat(testDemande.getAffectationLibelle()).isEqualTo(DEFAULT_AFFECTATION_LIBELLE);
        assertThat(testDemande.getDateAffictation()).isEqualTo(DEFAULT_DATE_AFFICTATION);
        assertThat(testDemande.getMoreInformation()).isEqualTo(DEFAULT_MORE_INFORMATION);
        assertThat(testDemande.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testDemande.getMessageValidation()).isEqualTo(DEFAULT_MESSAGE_VALIDATION);
        assertThat(testDemande.getMessageClouture()).isEqualTo(DEFAULT_MESSAGE_CLOUTURE);
        assertThat(testDemande.getDateClouture()).isEqualTo(DEFAULT_DATE_CLOUTURE);
        assertThat(testDemande.getMeilleurPrixMagasin()).isEqualTo(DEFAULT_MEILLEUR_PRIX_MAGASIN);
        assertThat(testDemande.getPrixNegocie()).isEqualTo(DEFAULT_PRIX_NEGOCIE);
    }

    @Test
    @Transactional
    public void createDemandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande with an existing ID
        demande.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDemandes() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get all the demandeList
        restDemandeMockMvc.perform(get("/api/demandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demande.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].rtr").value(hasItem(DEFAULT_RTR)))
            .andExpect(jsonPath("$.[*].rtrLibelle").value(hasItem(DEFAULT_RTR_LIBELLE)))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateBesion").value(hasItem(DEFAULT_DATE_BESION.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].createur").value(hasItem(DEFAULT_CREATEUR)))
            .andExpect(jsonPath("$.[*].budget").value(hasItem(DEFAULT_BUDGET.doubleValue())))
            .andExpect(jsonPath("$.[*].affectationLibelle").value(hasItem(DEFAULT_AFFECTATION_LIBELLE)))
            .andExpect(jsonPath("$.[*].dateAffictation").value(hasItem(DEFAULT_DATE_AFFICTATION.toString())))
            .andExpect(jsonPath("$.[*].moreInformation").value(hasItem(DEFAULT_MORE_INFORMATION)))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT)))
            .andExpect(jsonPath("$.[*].messageValidation").value(hasItem(DEFAULT_MESSAGE_VALIDATION)))
            .andExpect(jsonPath("$.[*].messageClouture").value(hasItem(DEFAULT_MESSAGE_CLOUTURE)))
            .andExpect(jsonPath("$.[*].dateClouture").value(hasItem(DEFAULT_DATE_CLOUTURE.toString())))
            .andExpect(jsonPath("$.[*].meilleurPrixMagasin").value(hasItem(DEFAULT_MEILLEUR_PRIX_MAGASIN.doubleValue())))
            .andExpect(jsonPath("$.[*].prixNegocie").value(hasItem(DEFAULT_PRIX_NEGOCIE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", demande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demande.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.rtr").value(DEFAULT_RTR))
            .andExpect(jsonPath("$.rtrLibelle").value(DEFAULT_RTR_LIBELLE))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.dateBesion").value(DEFAULT_DATE_BESION.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.createur").value(DEFAULT_CREATEUR))
            .andExpect(jsonPath("$.budget").value(DEFAULT_BUDGET.doubleValue()))
            .andExpect(jsonPath("$.affectationLibelle").value(DEFAULT_AFFECTATION_LIBELLE))
            .andExpect(jsonPath("$.dateAffictation").value(DEFAULT_DATE_AFFICTATION.toString()))
            .andExpect(jsonPath("$.moreInformation").value(DEFAULT_MORE_INFORMATION))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT))
            .andExpect(jsonPath("$.messageValidation").value(DEFAULT_MESSAGE_VALIDATION))
            .andExpect(jsonPath("$.messageClouture").value(DEFAULT_MESSAGE_CLOUTURE))
            .andExpect(jsonPath("$.dateClouture").value(DEFAULT_DATE_CLOUTURE.toString()))
            .andExpect(jsonPath("$.meilleurPrixMagasin").value(DEFAULT_MEILLEUR_PRIX_MAGASIN.doubleValue()))
            .andExpect(jsonPath("$.prixNegocie").value(DEFAULT_PRIX_NEGOCIE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDemande() throws Exception {
        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Update the demande
        Demande updatedDemande = demandeRepository.findById(demande.getId()).get();
        // Disconnect from session so that the updates on updatedDemande are not directly saved in db
        em.detach(updatedDemande);
        updatedDemande
            .numero(UPDATED_NUMERO)
            .rtr(UPDATED_RTR)
            .rtrLibelle(UPDATED_RTR_LIBELLE)
            .objet(UPDATED_OBJET)
            .description(UPDATED_DESCRIPTION)
            .dateBesion(UPDATED_DATE_BESION)
            .dateCreation(UPDATED_DATE_CREATION)
            .createur(UPDATED_CREATEUR)
            .budget(UPDATED_BUDGET)
            .affectationLibelle(UPDATED_AFFECTATION_LIBELLE)
            .dateAffictation(UPDATED_DATE_AFFICTATION)
            .moreInformation(UPDATED_MORE_INFORMATION)
            .etat(UPDATED_ETAT)
            .messageValidation(UPDATED_MESSAGE_VALIDATION)
            .messageClouture(UPDATED_MESSAGE_CLOUTURE)
            .dateClouture(UPDATED_DATE_CLOUTURE)
            .meilleurPrixMagasin(UPDATED_MEILLEUR_PRIX_MAGASIN)
            .prixNegocie(UPDATED_PRIX_NEGOCIE);

        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemande)))
            .andExpect(status().isOk());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testDemande.getRtr()).isEqualTo(UPDATED_RTR);
        assertThat(testDemande.getRtrLibelle()).isEqualTo(UPDATED_RTR_LIBELLE);
        assertThat(testDemande.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testDemande.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDemande.getDateBesion()).isEqualTo(UPDATED_DATE_BESION);
        assertThat(testDemande.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testDemande.getCreateur()).isEqualTo(UPDATED_CREATEUR);
        assertThat(testDemande.getBudget()).isEqualTo(UPDATED_BUDGET);
        assertThat(testDemande.getAffectationLibelle()).isEqualTo(UPDATED_AFFECTATION_LIBELLE);
        assertThat(testDemande.getDateAffictation()).isEqualTo(UPDATED_DATE_AFFICTATION);
        assertThat(testDemande.getMoreInformation()).isEqualTo(UPDATED_MORE_INFORMATION);
        assertThat(testDemande.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testDemande.getMessageValidation()).isEqualTo(UPDATED_MESSAGE_VALIDATION);
        assertThat(testDemande.getMessageClouture()).isEqualTo(UPDATED_MESSAGE_CLOUTURE);
        assertThat(testDemande.getDateClouture()).isEqualTo(UPDATED_DATE_CLOUTURE);
        assertThat(testDemande.getMeilleurPrixMagasin()).isEqualTo(UPDATED_MEILLEUR_PRIX_MAGASIN);
        assertThat(testDemande.getPrixNegocie()).isEqualTo(UPDATED_PRIX_NEGOCIE);
    }

    @Test
    @Transactional
    public void updateNonExistingDemande() throws Exception {
        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeDelete = demandeRepository.findAll().size();

        // Delete the demande
        restDemandeMockMvc.perform(delete("/api/demandes/{id}", demande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
