package dev.dornol.study.splearn.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class EmailTest implements WithAssertions {

    @Test
    void equality() {
        Email email1 = new Email("dhkim@dornol.dev");
        Email email2 = new Email("dhkim@dornol.dev");

        assertThat(email1).isEqualTo(email2);
    }

}