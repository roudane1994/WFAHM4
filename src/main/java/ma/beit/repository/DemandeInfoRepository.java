package ma.beit.repository;

import ma.beit.domain.DemandeInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DemandeInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeInfoRepository extends JpaRepository<DemandeInfo, Long> {
}
