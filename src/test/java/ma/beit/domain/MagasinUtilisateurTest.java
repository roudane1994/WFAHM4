package ma.beit.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.beit.web.rest.TestUtil;

public class MagasinUtilisateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MagasinUtilisateur.class);
        MagasinUtilisateur magasinUtilisateur1 = new MagasinUtilisateur();
        magasinUtilisateur1.setId(1L);
        MagasinUtilisateur magasinUtilisateur2 = new MagasinUtilisateur();
        magasinUtilisateur2.setId(magasinUtilisateur1.getId());
        assertThat(magasinUtilisateur1).isEqualTo(magasinUtilisateur2);
        magasinUtilisateur2.setId(2L);
        assertThat(magasinUtilisateur1).isNotEqualTo(magasinUtilisateur2);
        magasinUtilisateur1.setId(null);
        assertThat(magasinUtilisateur1).isNotEqualTo(magasinUtilisateur2);
    }
}
