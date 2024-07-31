package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TruProxyAPIOfficerResultTest {

    @Test
    void testGetOfficers() {
        TruProxyAPIOfficerResult result = new TruProxyAPIOfficerResult();
        assertNotNull(result.getOfficers());
        assertTrue(result.getOfficers().isEmpty());

        Officer officer1 = new Officer();
        officer1.setName("John Doe");
        result.setOfficers(List.of(officer1));

        assertEquals(1, result.getOfficers().size());
        assertEquals("John Doe", result.getOfficers().getFirst().getName());
    }

    @Test
    void testSetOfficers() {
        TruProxyAPIOfficerResult result = new TruProxyAPIOfficerResult();
        Officer officer1 = new Officer();
        officer1.setName("John Doe");
        Officer officer2 = new Officer();
        officer2.setName("Jane Doe");

        result.setOfficers(List.of(officer1, officer2));
        List<Officer> officers = result.getOfficers();

        assertEquals(2, officers.size());
        assertEquals("John Doe", officers.get(0).getName());
        assertEquals("Jane Doe", officers.get(1).getName());
    }
}
