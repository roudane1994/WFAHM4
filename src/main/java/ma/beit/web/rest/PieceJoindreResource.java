package ma.beit.web.rest;

import ma.beit.domain.PieceJoindre;
import ma.beit.repository.PieceJoindreRepository;
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
 * REST controller for managing {@link ma.beit.domain.PieceJoindre}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PieceJoindreResource {

    private final Logger log = LoggerFactory.getLogger(PieceJoindreResource.class);

    private static final String ENTITY_NAME = "pieceJoindre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PieceJoindreRepository pieceJoindreRepository;

    public PieceJoindreResource(PieceJoindreRepository pieceJoindreRepository) {
        this.pieceJoindreRepository = pieceJoindreRepository;
    }

    /**
     * {@code POST  /piece-joindres} : Create a new pieceJoindre.
     *
     * @param pieceJoindre the pieceJoindre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pieceJoindre, or with status {@code 400 (Bad Request)} if the pieceJoindre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/piece-joindres")
    public ResponseEntity<PieceJoindre> createPieceJoindre(@RequestBody PieceJoindre pieceJoindre) throws URISyntaxException {
        log.debug("REST request to save PieceJoindre : {}", pieceJoindre);
        if (pieceJoindre.getId() != null) {
            throw new BadRequestAlertException("A new pieceJoindre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PieceJoindre result = pieceJoindreRepository.save(pieceJoindre);
        return ResponseEntity.created(new URI("/api/piece-joindres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /piece-joindres} : Updates an existing pieceJoindre.
     *
     * @param pieceJoindre the pieceJoindre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceJoindre,
     * or with status {@code 400 (Bad Request)} if the pieceJoindre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pieceJoindre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/piece-joindres")
    public ResponseEntity<PieceJoindre> updatePieceJoindre(@RequestBody PieceJoindre pieceJoindre) throws URISyntaxException {
        log.debug("REST request to update PieceJoindre : {}", pieceJoindre);
        if (pieceJoindre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PieceJoindre result = pieceJoindreRepository.save(pieceJoindre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pieceJoindre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /piece-joindres} : get all the pieceJoindres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieceJoindres in body.
     */
    @GetMapping("/piece-joindres")
    public List<PieceJoindre> getAllPieceJoindres() {
        log.debug("REST request to get all PieceJoindres");
        return pieceJoindreRepository.findAll();
    }

    /**
     * {@code GET  /piece-joindres/:id} : get the "id" pieceJoindre.
     *
     * @param id the id of the pieceJoindre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pieceJoindre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/piece-joindres/{id}")
    public ResponseEntity<PieceJoindre> getPieceJoindre(@PathVariable Long id) {
        log.debug("REST request to get PieceJoindre : {}", id);
        Optional<PieceJoindre> pieceJoindre = pieceJoindreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pieceJoindre);
    }

    /**
     * {@code DELETE  /piece-joindres/:id} : delete the "id" pieceJoindre.
     *
     * @param id the id of the pieceJoindre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/piece-joindres/{id}")
    public ResponseEntity<Void> deletePieceJoindre(@PathVariable Long id) {
        log.debug("REST request to delete PieceJoindre : {}", id);
        pieceJoindreRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
