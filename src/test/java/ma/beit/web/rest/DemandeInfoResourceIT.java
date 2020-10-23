package ma.beit.web.rest;

import ma.beit.Wfahm4App;
import ma.beit.domain.DemandeInfo;
import ma.beit.repository.DemandeInfoRepository;

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
 * Integration tests for the {@link DemandeInfoResource} REST controller.
 */
@SpringBootTest(classes = Wfahm4App.class)
@AutoConfigureMockMvc
@WithMockUser
public class DemandeInfoResourceIT {

    private static final Integer DEFAULT_NORDRE = 1;
    private static final Integer UPDATED_NORDRE = 2;

    private static final String DEFAULT_INFO_DEMANDE = "AAAAAAAAAA";
    private static final String UPDATED_INFO_DEMANDE = "BBBBBBBBBB";

    private static final String DEFAULT_REPONSE = "AAAAAAAAAA";
    private static final String UPDATED_REPONSE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_REPONSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REPONSE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DemandeInfoRepository demandeInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeInfoMockMvc;

    private DemandeInfo demandeInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeInfo createEntity(EntityManager em) {
        DemandeInfo demandeInfo = new DemandeInfo()
            .nordre(DEFAULT_NORDRE)
            .infoDemande(DEFAULT_INFO_DEMANDE)
            .reponse(DEFAULT_REPONSE)
            .dateDemande(DEFAULT_DATE_DEMANDE)
            .dateReponse(DEFAULT_DATE_REPONSE);
        return demandeInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeInfo createUpdatedEntity(EntityManager em) {
        DemandeInfo demandeInfo = new DemandeInfo()
            .nordre(UPDATED_NORDRE)
            .infoDemande(UPDATED_INFO_DEMANDE)
            .reponse(UPDATED_REPONSE)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateReponse(UPDATED_DATE_REPONSE);
        return demandeInfo;
    }

    @BeforeEach
    public void initTest() {
        demandeInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeInfo() throws Exception {
        int databaseSizeBeforeCreate = demandeInfoRepository.findAll().size();
        // Create the DemandeInfo
        restDemandeInfoMockMvc.perform(post("/api/demande-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeInfo)))
            .andExpect(status().isCreated());

        // Validate the DemandeInfo in the database
        List<DemandeInfo> demandeInfoList = demandeInfoRepository.findAll();
        assertThat(demandeInfoList).hasSize(databaseSizeBeforeCreate + 1);
        DemandeInfo testDemandeInfo = demandeInfoList.get(demandeInfoList.size() - 1);
        assertThat(testDemandeInfo.getNordre()).isEqualTo(DEFAULT_NORDRE);
        assertThat(testDemandeInfo.getInfoDemande()).isEqualTo(DEFAULT_INFO_DEMANDE);
        assertThat(testDemandeInfo.getReponse()).isEqualTo(DEFAULT_REPONSE);
        assertThat(testDemandeInfo.getDateDemande()).isEqualTo(DEFAULT_DATE_DEMANDE);
        assertThat(testDemandeInfo.getDateReponse()).isEqualTo(DEFAULT_DATE_REPONSE);
    }

    @Test
    @Transactional
    public void createDemandeInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeInfoRepository.findAll().size();

        // Create the DemandeInfo with an existing ID
        demandeInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeInfoMockMvc.perform(post("/api/demande-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeInfo)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeInfo in the database
        List<DemandeInfo> demandeInfoList = demandeInfoRepository.findAll();
        assertThat(demandeInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDemandeInfos() throws Exception {
        // Initialize the database
        demandeInfoRepository.saveAndFlush(demandeInfo);

        // Get all the demandeInfoList
        restDemandeInfoMockMvc.perform(get("/api/demande-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nordre").value(hasItem(DEFAULT_NORDRE)))
            .andExpect(jsonPath("$.[*].infoDemande").value(hasItem(DEFAULT_INFO_DEMANDE)))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE)))
            .andExpect(jsonPath("$.[*].dateDemande").value(hasItem(DEFAULT_DATE_DEMANDE.toString())))
            .andExpect(jsonPath("$.[*].dateReponse").value(hasItem(DEFAULT_DATE_REPONSE.toString())));
    }
    
    @Test
    @Transactional
    public void getDemandeInfo() throws Exception {
        // Initialize the database
        demandeInfoRepository.saveAndFlush(demandeInfo);

        // Get the demandeInfo
        restDemandeInfoMockMvc.perform(get("/api/demande-infos/{id}", demandeInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandeInfo.getId().intValue()))
            .andExpect(jsonPath("$.nordre").value(DEFAULT_NORDRE))
            .andExpect(jsonPath("$.infoDemande").value(DEFAULT_INFO_DEMANDE))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE))
            .andExpect(jsonPath("$.dateDemande").value(DEFAULT_DATE_DEMANDE.toString()))
            .andExpect(jsonPath("$.dateReponse").value(DEFAULT_DATE_REPONSE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDemandeInfo() throws Exception {
        // Get the demandeInfo
        restDemandeInfoMockMvc.perform(get("/api/demande-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeInfo() throws Exception {
        // Initialize the database
        demandeInfoRepository.saveAndFlush(demandeInfo);

        int databaseSizeBeforeUpdate = demandeInfoRepository.findAll().size();

        // Update the demandeInfo
        DemandeInfo updatedDemandeInfo = demandeInfoRepository.findById(demandeInfo.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeInfo are not directly saved in db
        em.detach(updatedDemandeInfo);
        updatedDemandeInfo
            .nordre(UPDATED_NORDRE)
            .infoDemande(UPDATED_INFO_DEMANDE)
            .reponse(UPDATED_REPONSE)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateReponse(UPDATED_DATE_REPONSE);

        restDemandeInfoMockMvc.perform(put("/api/demande-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemandeInfo)))
            .andExpect(status().isOk());

        // Validate the DemandeInfo in the database
        List<DemandeInfo> demandeInfoList = demandeInfoRepository.findAll();
        assertThat(demandeInfoList).hasSize(databaseSizeBeforeUpdate);
        DemandeInfo testDemandeInfo = demandeInfoList.get(demandeInfoList.size() - 1);
        assertThat(testDemandeInfo.getNordre()).isEqualTo(UPDATED_NORDRE);
        assertThat(testDemandeInfo.getInfoDemande()).isEqualTo(UPDATED_INFO_DEMANDE);
        assertThat(testDemandeInfo.getReponse()).isEqualTo(UPDATED_REPONSE);
        assertThat(testDemandeInfo.getDateDemande()).isEqualTo(UPDATED_DATE_DEMANDE);
        assertThat(testDemandeInfo.getDateReponse()).isEqualTo(UPDATED_DATE_REPONSE);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeInfo() throws Exception {
        int databaseSizeBeforeUpdate = demandeInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeInfoMockMvc.perform(put("/api/demande-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeInfo)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeInfo in the database
        List<DemandeInfo> demandeInfoList = demandeInfoRepository.findAll();
        assertThat(demandeInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemandeInfo() throws Exception {
        // Initialize the database
        demandeInfoRepository.saveAndFlush(demandeInfo);

        int databaseSizeBeforeDelete = demandeInfoRepository.findAll().size();

        // Delete the demandeInfo
        restDemandeInfoMockMvc.perform(delete("/api/demande-infos/{id}", demandeInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DemandeInfo> demandeInfoList = demandeInfoRepository.findAll();
        assertThat(demandeInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
