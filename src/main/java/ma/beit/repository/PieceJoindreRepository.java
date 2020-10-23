package ma.beit.repository;

import ma.beit.domain.PieceJoindre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PieceJoindre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PieceJoindreRepository extends JpaRepository<PieceJoindre, Long> {
}
