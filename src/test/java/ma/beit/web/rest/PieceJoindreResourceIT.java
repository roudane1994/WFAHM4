package ma.beit.web.rest;

import ma.beit.Wfahm4App;
import ma.beit.domain.PieceJoindre;
import ma.beit.repository.PieceJoindreRepository;

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
 * Integration tests for the {@link PieceJoindreResource} REST controller.
 */
@SpringBootTest(classes = Wfahm4App.class)
@AutoConfigureMockMvc
@WithMockUser
public class PieceJoindreResourceIT {

    private static final Integer DEFAULT_NORDRE = 1;
    private static final Integer UPDATED_NORDRE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private PieceJoindreRepository pieceJoindreRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPieceJoindreMockMvc;

    private PieceJoindre pieceJoindre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJoindre createEntity(EntityManager em) {
        PieceJoindre pieceJoindre = new PieceJoindre()
            .nordre(DEFAULT_NORDRE)
            .name(DEFAULT_NAME)
            .url(DEFAULT_URL);
        return pieceJoindre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJoindre createUpdatedEntity(EntityManager em) {
        PieceJoindre pieceJoindre = new PieceJoindre()
            .nordre(UPDATED_NORDRE)
            .name(UPDATED_NAME)
            .url(UPDATED_URL);
        return pieceJoindre;
    }

    @BeforeEach
    public void initTest() {
        pieceJoindre = createEntity(em);
    }

    @Test
    @Transactional
    public void createPieceJoindre() throws Exception {
        int databaseSizeBeforeCreate = pieceJoindreRepository.findAll().size();
        // Create the PieceJoindre
        restPieceJoindreMockMvc.perform(post("/api/piece-joindres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJoindre)))
            .andExpect(status().isCreated());

        // Validate the PieceJoindre in the database
        List<PieceJoindre> pieceJoindreList = pieceJoindreRepository.findAll();
        assertThat(pieceJoindreList).hasSize(databaseSizeBeforeCreate + 1);
        PieceJoindre testPieceJoindre = pieceJoindreList.get(pieceJoindreList.size() - 1);
        assertThat(testPieceJoindre.getNordre()).isEqualTo(DEFAULT_NORDRE);
        assertThat(testPieceJoindre.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPieceJoindre.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createPieceJoindreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pieceJoindreRepository.findAll().size();

        // Create the PieceJoindre with an existing ID
        pieceJoindre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPieceJoindreMockMvc.perform(post("/api/piece-joindres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJoindre)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJoindre in the database
        List<PieceJoindre> pieceJoindreList = pieceJoindreRepository.findAll();
        assertThat(pieceJoindreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPieceJoindres() throws Exception {
        // Initialize the database
        pieceJoindreRepository.saveAndFlush(pieceJoindre);

        // Get all the pieceJoindreList
        restPieceJoindreMockMvc.perform(get("/api/piece-joindres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pieceJoindre.getId().intValue())))
            .andExpect(jsonPath("$.[*].nordre").value(hasItem(DEFAULT_NORDRE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getPieceJoindre() throws Exception {
        // Initialize the database
        pieceJoindreRepository.saveAndFlush(pieceJoindre);

        // Get the pieceJoindre
        restPieceJoindreMockMvc.perform(get("/api/piece-joindres/{id}", pieceJoindre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pieceJoindre.getId().intValue()))
            .andExpect(jsonPath("$.nordre").value(DEFAULT_NORDRE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    @Transactional
    public void getNonExistingPieceJoindre() throws Exception {
        // Get the pieceJoindre
        restPieceJoindreMockMvc.perform(get("/api/piece-joindres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePieceJoindre() throws Exception {
        // Initialize the database
        pieceJoindreRepository.saveAndFlush(pieceJoindre);

        int databaseSizeBeforeUpdate = pieceJoindreRepository.findAll().size();

        // Update the pieceJoindre
        PieceJoindre updatedPieceJoindre = pieceJoindreRepository.findById(pieceJoindre.getId()).get();
        // Disconnect from session so that the updates on updatedPieceJoindre are not directly saved in db
        em.detach(updatedPieceJoindre);
        updatedPieceJoindre
            .nordre(UPDATED_NORDRE)
            .name(UPDATED_NAME)
            .url(UPDATED_URL);

        restPieceJoindreMockMvc.perform(put("/api/piece-joindres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPieceJoindre)))
            .andExpect(status().isOk());

        // Validate the PieceJoindre in the database
        List<PieceJoindre> pieceJoindreList = pieceJoindreRepository.findAll();
        assertThat(pieceJoindreList).hasSize(databaseSizeBeforeUpdate);
        PieceJoindre testPieceJoindre = pieceJoindreList.get(pieceJoindreList.size() - 1);
        assertThat(testPieceJoindre.getNordre()).isEqualTo(UPDATED_NORDRE);
        assertThat(testPieceJoindre.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPieceJoindre.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingPieceJoindre() throws Exception {
        int databaseSizeBeforeUpdate = pieceJoindreRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPieceJoindreMockMvc.perform(put("/api/piece-joindres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJoindre)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJoindre in the database
        List<PieceJoindre> pieceJoindreList = pieceJoindreRepository.findAll();
        assertThat(pieceJoindreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePieceJoindre() throws Exception {
        // Initialize the database
        pieceJoindreRepository.saveAndFlush(pieceJoindre);

        int databaseSizeBeforeDelete = pieceJoindreRepository.findAll().size();

        // Delete the pieceJoindre
        restPieceJoindreMockMvc.perform(delete("/api/piece-joindres/{id}", pieceJoindre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PieceJoindre> pieceJoindreList = pieceJoindreRepository.findAll();
        assertThat(pieceJoindreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
