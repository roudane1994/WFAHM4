package ma.beit.web.rest;

import ma.beit.Wfahm4App;
import ma.beit.domain.Objet;
import ma.beit.repository.ObjetRepository;

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
 * Integration tests for the {@link ObjetResource} REST controller.
 */
@SpringBootTest(classes = Wfahm4App.class)
@AutoConfigureMockMvc
@WithMockUser
public class ObjetResourceIT {

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    @Autowired
    private ObjetRepository objetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjetMockMvc;

    private Objet objet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objet createEntity(EntityManager em) {
        Objet objet = new Objet()
            .objet(DEFAULT_OBJET);
        return objet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objet createUpdatedEntity(EntityManager em) {
        Objet objet = new Objet()
            .objet(UPDATED_OBJET);
        return objet;
    }

    @BeforeEach
    public void initTest() {
        objet = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjet() throws Exception {
        int databaseSizeBeforeCreate = objetRepository.findAll().size();
        // Create the Objet
        restObjetMockMvc.perform(post("/api/objets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objet)))
            .andExpect(status().isCreated());

        // Validate the Objet in the database
        List<Objet> objetList = objetRepository.findAll();
        assertThat(objetList).hasSize(databaseSizeBeforeCreate + 1);
        Objet testObjet = objetList.get(objetList.size() - 1);
        assertThat(testObjet.getObjet()).isEqualTo(DEFAULT_OBJET);
    }

    @Test
    @Transactional
    public void createObjetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objetRepository.findAll().size();

        // Create the Objet with an existing ID
        objet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjetMockMvc.perform(post("/api/objets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objet)))
            .andExpect(status().isBadRequest());

        // Validate the Objet in the database
        List<Objet> objetList = objetRepository.findAll();
        assertThat(objetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllObjets() throws Exception {
        // Initialize the database
        objetRepository.saveAndFlush(objet);

        // Get all the objetList
        restObjetMockMvc.perform(get("/api/objets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objet.getId().intValue())))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)));
    }
    
    @Test
    @Transactional
    public void getObjet() throws Exception {
        // Initialize the database
        objetRepository.saveAndFlush(objet);

        // Get the objet
        restObjetMockMvc.perform(get("/api/objets/{id}", objet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objet.getId().intValue()))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET));
    }
    @Test
    @Transactional
    public void getNonExistingObjet() throws Exception {
        // Get the objet
        restObjetMockMvc.perform(get("/api/objets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjet() throws Exception {
        // Initialize the database
        objetRepository.saveAndFlush(objet);

        int databaseSizeBeforeUpdate = objetRepository.findAll().size();

        // Update the objet
        Objet updatedObjet = objetRepository.findById(objet.getId()).get();
        // Disconnect from session so that the updates on updatedObjet are not directly saved in db
        em.detach(updatedObjet);
        updatedObjet
            .objet(UPDATED_OBJET);

        restObjetMockMvc.perform(put("/api/objets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedObjet)))
            .andExpect(status().isOk());

        // Validate the Objet in the database
        List<Objet> objetList = objetRepository.findAll();
        assertThat(objetList).hasSize(databaseSizeBeforeUpdate);
        Objet testObjet = objetList.get(objetList.size() - 1);
        assertThat(testObjet.getObjet()).isEqualTo(UPDATED_OBJET);
    }

    @Test
    @Transactional
    public void updateNonExistingObjet() throws Exception {
        int databaseSizeBeforeUpdate = objetRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjetMockMvc.perform(put("/api/objets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objet)))
            .andExpect(status().isBadRequest());

        // Validate the Objet in the database
        List<Objet> objetList = objetRepository.findAll();
        assertThat(objetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjet() throws Exception {
        // Initialize the database
        objetRepository.saveAndFlush(objet);

        int databaseSizeBeforeDelete = objetRepository.findAll().size();

        // Delete the objet
        restObjetMockMvc.perform(delete("/api/objets/{id}", objet.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Objet> objetList = objetRepository.findAll();
        assertThat(objetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
