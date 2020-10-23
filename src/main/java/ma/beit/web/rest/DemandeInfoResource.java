package ma.beit.web.rest;

import ma.beit.domain.DemandeInfo;
import ma.beit.repository.DemandeInfoRepository;
import ma.beit.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ma.beit.domain.DemandeInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DemandeInfoResource {

    private final Logger log = LoggerFactory.getLogger(DemandeInfoResource.class);

    private static final String ENTITY_NAME = "demandeInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeInfoRepository demandeInfoRepository;

    public DemandeInfoResource(DemandeInfoRepository demandeInfoRepository) {
        this.demandeInfoRepository = demandeInfoRepository;
    }

    /**
     * {@code POST  /demande-infos} : Create a new demandeInfo.
     *
     * @param demandeInfo the demandeInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandeInfo, or with status {@code 400 (Bad Request)} if the demandeInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demande-infos")
    public ResponseEntity<DemandeInfo> createDemandeInfo(@RequestBody DemandeInfo demandeInfo) throws URISyntaxException {
        log.debug("REST request to save DemandeInfo : {}", demandeInfo);
        if (demandeInfo.getId() != null) {
            throw new BadRequestAlertException("A new demandeInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandeInfo result = demandeInfoRepository.save(demandeInfo);
        return ResponseEntity.created(new URI("/api/demande-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demande-infos} : Updates an existing demandeInfo.
     *
     * @param demandeInfo the demandeInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeInfo,
     * or with status {@code 400 (Bad Request)} if the demandeInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demande-infos")
    public ResponseEntity<DemandeInfo> updateDemandeInfo(@RequestBody DemandeInfo demandeInfo) throws URISyntaxException {
        log.debug("REST request to update DemandeInfo : {}", demandeInfo);
        if (demandeInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeInfo result = demandeInfoRepository.save(demandeInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demande-infos} : get all the demandeInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeInfos in body.
     */
    @GetMapping("/demande-infos")
    public List<DemandeInfo> getAllDemandeInfos() {
        log.debug("REST request to get all DemandeInfos");
        return demandeInfoRepository.findAll();
    }

    /**
     * {@code GET  /demande-infos/:id} : get the "id" demandeInfo.
     *
     * @param id the id of the demandeInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demande-infos/{id}")
    public ResponseEntity<DemandeInfo> getDemandeInfo(@PathVariable Long id) {
        log.debug("REST request to get DemandeInfo : {}", id);
        Optional<DemandeInfo> demandeInfo = demandeInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(demandeInfo);
    }

    /**
     * {@code DELETE  /demande-infos/:id} : delete the "id" demandeInfo.
     *
     * @param id the id of the demandeInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demande-infos/{id}")
    public ResponseEntity<Void> deleteDemandeInfo(@PathVariable Long id) {
        log.debug("REST request to delete DemandeInfo : {}", id);
        demandeInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
