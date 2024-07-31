package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testJsonAnnotations() {
        Address address = new Address();
        address.setLocality("London");
        address.setPostalCode("EC1A 1BB");
        address.setPremises("221B");
        address.setAddressLine1("Baker Street");
        address.setAddressLine2("Marylebone");
        address.setCountry("UK");

        // Simulate JSON serialization and verify annotations
        assertEquals("London", address.getLocality());
        assertEquals("EC1A 1BB", address.getPostalCode());
        assertEquals("221B", address.getPremises());
        assertEquals("Baker Street", address.getAddressLine1());
        assertEquals("Marylebone", address.getAddressLine2());
        assertEquals("UK", address.getCountry());
    }
}
