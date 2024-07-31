package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficerTest {

    @Test
    void testSetActive() {
        Officer officer = new Officer();
        officer.setActive(true);
        assertTrue(officer.isActive());

        officer.setActive(false);
        assertFalse(officer.isActive());
    }

    @Test
    void testIsActive() {
        Officer officer = new Officer();
        officer.setResignedOn("");
        assertTrue(officer.isActive());

        officer.setResignedOn(null);
        assertTrue(officer.isActive());

        officer.setResignedOn("1970-01-01");
        assertFalse(officer.isActive());
    }

    @Test
    void testJsonAnnotations() {
        Officer officer = new Officer();
        officer.setName("John Doe");
        officer.setOfficerRole("Director");
        officer.setAppointedOn("2020-01-01");
        officer.setResignedOn("2020-12-31");

        assertEquals("John Doe", officer.getName());
        assertEquals("Director", officer.getOfficerRole());
        assertEquals("2020-01-01", officer.getAppointedOn());
        assertEquals("2020-12-31", officer.getResignedOn());
    }
}
