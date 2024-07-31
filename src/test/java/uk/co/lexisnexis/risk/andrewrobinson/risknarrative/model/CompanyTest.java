package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void testSetActive() {
        Company company = new Company();
        company.setActive(true);
        assertTrue(company.isActive());

        company.setActive(false);
        assertFalse(company.isActive());
    }

    @Test
    void testIsActive() {
        Company company = new Company();
        company.setCompanyStatus("active");
        assertTrue(company.isActive());

        company.setCompanyStatus("inactive");
        assertFalse(company.isActive());

        company.setCompanyStatus(null);
        assertFalse(company.isActive());
    }

    @Test
    void testAddOfficers() {
        Officer officer1 = new Officer();
        officer1.setName("John Doe");

        Officer officer2 = new Officer();
        officer2.setName("Jane Smith");

        Company company = new Company();
        company.addOfficers(List.of(officer1, officer2));

        assertEquals(2, company.getOfficers().size());
        assertTrue(company.getOfficers().contains(officer1));
        assertTrue(company.getOfficers().contains(officer2));
    }

    @Test
    void testJsonAnnotations() {
        Company company = new Company();
        company.setCompanyNumber("12345678");
        company.setCompanyType("Private Limited");
        company.setTitle("Example Ltd");
        company.setCompanyStatus("active");
        company.setDateOfCreation("2000-01-01");

        assertEquals("12345678", company.getCompanyNumber());
        assertEquals("Private Limited", company.getCompanyType());
        assertEquals("Example Ltd", company.getTitle());
        assertEquals("active", company.getCompanyStatus());
        assertEquals("2000-01-01", company.getDateOfCreation());
    }
}
