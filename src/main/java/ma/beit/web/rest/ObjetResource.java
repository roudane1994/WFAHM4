package ma.beit.web.rest;

import ma.beit.domain.Objet;
import ma.beit.repository.ObjetRepository;
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
 * REST controller for managing {@link ma.beit.domain.Objet}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ObjetResource {

    private final Logger log = LoggerFactory.getLogger(ObjetResource.class);

    private static final String ENTITY_NAME = "objet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjetRepository objetRepository;

    public ObjetResource(ObjetRepository objetRepository) {
        this.objetRepository = objetRepository;
    }

    /**
     * {@code POST  /objets} : Create a new objet.
     *
     * @param objet the objet to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objet, or with status {@code 400 (Bad Request)} if the objet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objets")
    public ResponseEntity<Objet> createObjet(@RequestBody Objet objet) throws URISyntaxException {
        log.debug("REST request to save Objet : {}", objet);
        if (objet.getId() != null) {
            throw new BadRequestAlertException("A new objet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Objet result = objetRepository.save(objet);
        return ResponseEntity.created(new URI("/api/objets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objets} : Updates an existing objet.
     *
     * @param objet the objet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objet,
     * or with status {@code 400 (Bad Request)} if the objet is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objets")
    public ResponseEntity<Objet> updateObjet(@RequestBody Objet objet) throws URISyntaxException {
        log.debug("REST request to update Objet : {}", objet);
        if (objet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Objet result = objetRepository.save(objet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objet.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objets} : get all the objets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objets in body.
     */
    @GetMapping("/objets")
    public List<Objet> getAllObjets() {
        log.debug("REST request to get all Objets");
        return objetRepository.findAll();
    }

    /**
     * {@code GET  /objets/:id} : get the "id" objet.
     *
     * @param id the id of the objet to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objet, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objets/{id}")
    public ResponseEntity<Objet> getObjet(@PathVariable Long id) {
        log.debug("REST request to get Objet : {}", id);
        Optional<Objet> objet = objetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(objet);
    }

    /**
     * {@code DELETE  /objets/:id} : delete the "id" objet.
     *
     * @param id the id of the objet to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objets/{id}")
    public ResponseEntity<Void> deleteObjet(@PathVariable Long id) {
        log.debug("REST request to delete Objet : {}", id);
        objetRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
