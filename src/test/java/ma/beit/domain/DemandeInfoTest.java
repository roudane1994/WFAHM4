package ma.beit.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.beit.web.rest.TestUtil;

public class DemandeInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeInfo.class);
        DemandeInfo demandeInfo1 = new DemandeInfo();
        demandeInfo1.setId(1L);
        DemandeInfo demandeInfo2 = new DemandeInfo();
        demandeInfo2.setId(demandeInfo1.getId());
        assertThat(demandeInfo1).isEqualTo(demandeInfo2);
        demandeInfo2.setId(2L);
        assertThat(demandeInfo1).isNotEqualTo(demandeInfo2);
        demandeInfo1.setId(null);
        assertThat(demandeInfo1).isNotEqualTo(demandeInfo2);
    }
}
