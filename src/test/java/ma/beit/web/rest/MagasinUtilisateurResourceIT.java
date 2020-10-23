package ma.beit.web.rest;

import ma.beit.Wfahm4App;
import ma.beit.domain.MagasinUtilisateur;
import ma.beit.repository.MagasinUtilisateurRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MagasinUtilisateurResource} REST controller.
 */
@SpringBootTest(classes = Wfahm4App.class)
@AutoConfigureMockMvc
@WithMockUser
public class MagasinUtilisateurResourceIT {

    private static final Integer DEFAULT_NORDRE = 1;
    private static final Integer UPDATED_NORDRE = 2;

    private static final String DEFAULT_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_UTILISATEUR = "BBBBBBBBBB";

    @Autowired
    private MagasinUtilisateurRepository magasinUtilisateurRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMagasinUtilisateurMockMvc;

    private MagasinUtilisateur magasinUtilisateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MagasinUtilisateur createEntity(EntityManager em) {
        MagasinUtilisateur magasinUtilisateur = new MagasinUtilisateur()
            .nordre(DEFAULT_NORDRE)
            .utilisateur(DEFAULT_UTILISATEUR);
        return magasinUtilisateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MagasinUtilisateur createUpdatedEntity(EntityManager em) {
        MagasinUtilisateur magasinUtilisateur = new MagasinUtilisateur()
            .nordre(UPDATED_NORDRE)
            .utilisateur(UPDATED_UTILISATEUR);
        return magasinUtilisateur;
    }

    @BeforeEach
    public void initTest() {
        magasinUtilisateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createMagasinUtilisateur() throws Exception {
        int databaseSizeBeforeCreate = magasinUtilisateurRepository.findAll().size();
        // Create the MagasinUtilisateur
        restMagasinUtilisateurMockMvc.perform(post("/api/magasin-utilisateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinUtilisateur)))
            .andExpect(status().isCreated());

        // Validate the MagasinUtilisateur in the database
        List<MagasinUtilisateur> magasinUtilisateurList = magasinUtilisateurRepository.findAll();
        assertThat(magasinUtilisateurList).hasSize(databaseSizeBeforeCreate + 1);
        MagasinUtilisateur testMagasinUtilisateur = magasinUtilisateurList.get(magasinUtilisateurList.size() - 1);
        assertThat(testMagasinUtilisateur.getNordre()).isEqualTo(DEFAULT_NORDRE);
        assertThat(testMagasinUtilisateur.getUtilisateur()).isEqualTo(DEFAULT_UTILISATEUR);
    }

    @Test
    @Transactional
    public void createMagasinUtilisateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = magasinUtilisateurRepository.findAll().size();

        // Create the MagasinUtilisateur with an existing ID
        magasinUtilisateur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMagasinUtilisateurMockMvc.perform(post("/api/magasin-utilisateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinUtilisateur)))
            .andExpect(status().isBadRequest());

        // Validate the MagasinUtilisateur in the database
        List<MagasinUtilisateur> magasinUtilisateurList = magasinUtilisateurRepository.findAll();
        assertThat(magasinUtilisateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMagasinUtilisateurs() throws Exception {
        // Initialize the database
        magasinUtilisateurRepository.saveAndFlush(magasinUtilisateur);

        // Get all the magasinUtilisateurList
        restMagasinUtilisateurMockMvc.perform(get("/api/magasin-utilisateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(magasinUtilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nordre").value(hasItem(DEFAULT_NORDRE)))
            .andExpect(jsonPath("$.[*].utilisateur").value(hasItem(DEFAULT_UTILISATEUR)));
    }
    
    @Test
    @Transactional
    public void getMagasinUtilisateur() throws Exception {
        // Initialize the database
        magasinUtilisateurRepository.saveAndFlush(magasinUtilisateur);

        // Get the magasinUtilisateur
        restMagasinUtilisateurMockMvc.perform(get("/api/magasin-utilisateurs/{id}", magasinUtilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(magasinUtilisateur.getId().intValue()))
            .andExpect(jsonPath("$.nordre").value(DEFAULT_NORDRE))
            .andExpect(jsonPath("$.utilisateur").value(DEFAULT_UTILISATEUR));
    }
    @Test
    @Transactional
    public void getNonExistingMagasinUtilisateur() throws Exception {
        // Get the magasinUtilisateur
        restMagasinUtilisateurMockMvc.perform(get("/api/magasin-utilisateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMagasinUtilisateur() throws Exception {
        // Initialize the database
        magasinUtilisateurRepository.saveAndFlush(magasinUtilisateur);

        int databaseSizeBeforeUpdate = magasinUtilisateurRepository.findAll().size();

        // Update the magasinUtilisateur
        MagasinUtilisateur updatedMagasinUtilisateur = magasinUtilisateurRepository.findById(magasinUtilisateur.getId()).get();
        // Disconnect from session so that the updates on updatedMagasinUtilisateur are not directly saved in db
        em.detach(updatedMagasinUtilisateur);
        updatedMagasinUtilisateur
            .nordre(UPDATED_NORDRE)
            .utilisateur(UPDATED_UTILISATEUR);

        restMagasinUtilisateurMockMvc.perform(put("/api/magasin-utilisateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMagasinUtilisateur)))
            .andExpect(status().isOk());

        // Validate the MagasinUtilisateur in the database
        List<MagasinUtilisateur> magasinUtilisateurList = magasinUtilisateurRepository.findAll();
        assertThat(magasinUtilisateurList).hasSize(databaseSizeBeforeUpdate);
        MagasinUtilisateur testMagasinUtilisateur = magasinUtilisateurList.get(magasinUtilisateurList.size() - 1);
        assertThat(testMagasinUtilisateur.getNordre()).isEqualTo(UPDATED_NORDRE);
        assertThat(testMagasinUtilisateur.getUtilisateur()).isEqualTo(UPDATED_UTILISATEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingMagasinUtilisateur() throws Exception {
        int databaseSizeBeforeUpdate = magasinUtilisateurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMagasinUtilisateurMockMvc.perform(put("/api/magasin-utilisateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinUtilisateur)))
            .andExpect(status().isBadRequest());

        // Validate the MagasinUtilisateur in the database
        List<MagasinUtilisateur> magasinUtilisateurList = magasinUtilisateurRepository.findAll();
        assertThat(magasinUtilisateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMagasinUtilisateur() throws Exception {
        // Initialize the database
        magasinUtilisateurRepository.saveAndFlush(magasinUtilisateur);

        int databaseSizeBeforeDelete = magasinUtilisateurRepository.findAll().size();

        // Delete the magasinUtilisateur
        restMagasinUtilisateurMockMvc.perform(delete("/api/magasin-utilisateurs/{id}", magasinUtilisateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MagasinUtilisateur> magasinUtilisateurList = magasinUtilisateurRepository.findAll();
        assertThat(magasinUtilisateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
