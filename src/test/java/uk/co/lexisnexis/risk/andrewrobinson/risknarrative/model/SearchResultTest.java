package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchResultTest {

    @Test
    void testConstructorWithTruProxyAPICompanyResult() {
        Company company1 = new Company();
        company1.setCompanyNumber("123");
        Company company2 = new Company();
        company2.setCompanyNumber("456");

        TruProxyAPICompanyResult truProxyAPICompanyResult = new TruProxyAPICompanyResult();
        truProxyAPICompanyResult.setCompanies(List.of(company1, company2));

        SearchResult searchResult = new SearchResult(truProxyAPICompanyResult);
        assertEquals(2, searchResult.getTotalResults());
        assertTrue(searchResult.getCompanies().contains(company1));
        assertTrue(searchResult.getCompanies().contains(company2));
    }

    @Test
    void testConstructorWithCompany() {
        Company company = new Company();
        company.setCompanyNumber("123");

        SearchResult searchResult = new SearchResult(company);
        assertEquals(1, searchResult.getTotalResults());
        assertTrue(searchResult.getCompanies().contains(company));
    }

    @Test
    void testGetTotalResults() {
        Company company1 = new Company();
        company1.setCompanyNumber("123");
        Company company2 = new Company();
        company2.setCompanyNumber("456");

        TruProxyAPICompanyResult truProxyAPICompanyResult = new TruProxyAPICompanyResult();
        truProxyAPICompanyResult.setCompanies(List.of(company1, company2));

        SearchResult searchResult = new SearchResult(truProxyAPICompanyResult);
        assertEquals(2, searchResult.getTotalResults());

        searchResult = new SearchResult(company1);
        assertEquals(1, searchResult.getTotalResults());
    }

    @Test
    void testGetCompanies() {
        Company company1 = new Company();
        company1.setCompanyNumber("123");
        Company company2 = new Company();
        company2.setCompanyNumber("456");

        TruProxyAPICompanyResult truProxyAPICompanyResult = new TruProxyAPICompanyResult();
        truProxyAPICompanyResult.setCompanies(List.of(company1, company2));

        SearchResult searchResult = new SearchResult(truProxyAPICompanyResult);
        assertEquals(2, searchResult.getCompanies().size());
        assertTrue(searchResult.getCompanies().contains(company1));
        assertTrue(searchResult.getCompanies().contains(company2));
    }
}
