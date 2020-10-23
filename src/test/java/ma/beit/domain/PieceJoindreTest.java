package ma.beit.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.beit.web.rest.TestUtil;

public class PieceJoindreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PieceJoindre.class);
        PieceJoindre pieceJoindre1 = new PieceJoindre();
        pieceJoindre1.setId(1L);
        PieceJoindre pieceJoindre2 = new PieceJoindre();
        pieceJoindre2.setId(pieceJoindre1.getId());
        assertThat(pieceJoindre1).isEqualTo(pieceJoindre2);
        pieceJoindre2.setId(2L);
        assertThat(pieceJoindre1).isNotEqualTo(pieceJoindre2);
        pieceJoindre1.setId(null);
        assertThat(pieceJoindre1).isNotEqualTo(pieceJoindre2);
    }
}
