package ma.beit.web.rest;

import ma.beit.domain.MagasinUtilisateur;
import ma.beit.repository.MagasinUtilisateurRepository;
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
 * REST controller for managing {@link ma.beit.domain.MagasinUtilisateur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MagasinUtilisateurResource {

    private final Logger log = LoggerFactory.getLogger(MagasinUtilisateurResource.class);

    private static final String ENTITY_NAME = "magasinUtilisateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MagasinUtilisateurRepository magasinUtilisateurRepository;

    public MagasinUtilisateurResource(MagasinUtilisateurRepository magasinUtilisateurRepository) {
        this.magasinUtilisateurRepository = magasinUtilisateurRepository;
    }

    /**
     * {@code POST  /magasin-utilisateurs} : Create a new magasinUtilisateur.
     *
     * @param magasinUtilisateur the magasinUtilisateur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new magasinUtilisateur, or with status {@code 400 (Bad Request)} if the magasinUtilisateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/magasin-utilisateurs")
    public ResponseEntity<MagasinUtilisateur> createMagasinUtilisateur(@RequestBody MagasinUtilisateur magasinUtilisateur) throws URISyntaxException {
        log.debug("REST request to save MagasinUtilisateur : {}", magasinUtilisateur);
        if (magasinUtilisateur.getId() != null) {
            throw new BadRequestAlertException("A new magasinUtilisateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MagasinUtilisateur result = magasinUtilisateurRepository.save(magasinUtilisateur);
        return ResponseEntity.created(new URI("/api/magasin-utilisateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /magasin-utilisateurs} : Updates an existing magasinUtilisateur.
     *
     * @param magasinUtilisateur the magasinUtilisateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated magasinUtilisateur,
     * or with status {@code 400 (Bad Request)} if the magasinUtilisateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the magasinUtilisateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/magasin-utilisateurs")
    public ResponseEntity<MagasinUtilisateur> updateMagasinUtilisateur(@RequestBody MagasinUtilisateur magasinUtilisateur) throws URISyntaxException {
        log.debug("REST request to update MagasinUtilisateur : {}", magasinUtilisateur);
        if (magasinUtilisateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MagasinUtilisateur result = magasinUtilisateurRepository.save(magasinUtilisateur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinUtilisateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /magasin-utilisateurs} : get all the magasinUtilisateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of magasinUtilisateurs in body.
     */
    @GetMapping("/magasin-utilisateurs")
    public List<MagasinUtilisateur> getAllMagasinUtilisateurs() {
        log.debug("REST request to get all MagasinUtilisateurs");
        return magasinUtilisateurRepository.findAll();
    }

    /**
     * {@code GET  /magasin-utilisateurs/:id} : get the "id" magasinUtilisateur.
     *
     * @param id the id of the magasinUtilisateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the magasinUtilisateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/magasin-utilisateurs/{id}")
    public ResponseEntity<MagasinUtilisateur> getMagasinUtilisateur(@PathVariable Long id) {
        log.debug("REST request to get MagasinUtilisateur : {}", id);
        Optional<MagasinUtilisateur> magasinUtilisateur = magasinUtilisateurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(magasinUtilisateur);
    }

    /**
     * {@code DELETE  /magasin-utilisateurs/:id} : delete the "id" magasinUtilisateur.
     *
     * @param id the id of the magasinUtilisateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/magasin-utilisateurs/{id}")
    public ResponseEntity<Void> deleteMagasinUtilisateur(@PathVariable Long id) {
        log.debug("REST request to delete MagasinUtilisateur : {}", id);
        magasinUtilisateurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
