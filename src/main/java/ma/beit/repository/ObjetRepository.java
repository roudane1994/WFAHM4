package ma.beit.repository;

import ma.beit.domain.Objet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Objet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long> {
}
