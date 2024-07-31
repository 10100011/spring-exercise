package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TruProxyAPICompanyResultTest {

    @Test
    void testGetCompanies() {
        TruProxyAPICompanyResult result = new TruProxyAPICompanyResult();
        assertNotNull(result.getCompanies());
        assertTrue(result.getCompanies().isEmpty());

        Company company1 = new Company();
        company1.setCompanyNumber("123");
        result.setCompanies(List.of(company1));

        assertEquals(1, result.getCompanies().size());
        assertEquals("123", result.getCompanies().getFirst().getCompanyNumber());
    }

    @Test
    void testSetCompanies() {
        TruProxyAPICompanyResult result = new TruProxyAPICompanyResult();
        Company company1 = new Company();
        company1.setCompanyNumber("123");
        Company company2 = new Company();
        company2.setCompanyNumber("456");

        result.setCompanies(List.of(company1, company2));
        List<Company> companies = result.getCompanies();

        assertEquals(2, companies.size());
        assertEquals("123", companies.get(0).getCompanyNumber());
        assertEquals("456", companies.get(1).getCompanyNumber());
    }
}
