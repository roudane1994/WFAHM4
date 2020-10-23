package ma.beit.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.beit.web.rest.TestUtil;

public class ObjetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Objet.class);
        Objet objet1 = new Objet();
        objet1.setId(1L);
        Objet objet2 = new Objet();
        objet2.setId(objet1.getId());
        assertThat(objet1).isEqualTo(objet2);
        objet2.setId(2L);
        assertThat(objet1).isNotEqualTo(objet2);
        objet1.setId(null);
        assertThat(objet1).isNotEqualTo(objet2);
    }
}
