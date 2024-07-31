package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchRequestTest {

    @Test
    void testGetCompanyNumber() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyNumber("12345678");
        assertEquals("12345678", searchRequest.getCompanyNumber());

        searchRequest.setCompanyNumber(null);
        assertEquals("", searchRequest.getCompanyNumber());
    }

    @Test
    void testGetCompanyName() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyName("Example Ltd");
        assertEquals("Example Ltd", searchRequest.getCompanyName());

        searchRequest.setCompanyName(null);
        assertEquals("", searchRequest.getCompanyName());
    }

    @Test
    void testSearchByName() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyName("Example Ltd");
        assertTrue(searchRequest.searchByName());

        searchRequest.setCompanyName("");
        assertFalse(searchRequest.searchByName());

        searchRequest.setCompanyName(null);
        assertFalse(searchRequest.searchByName());
    }

    @Test
    void testSearchByNumber() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyNumber("12345678");
        assertTrue(searchRequest.searchByNumber());

        searchRequest.setCompanyNumber("");
        assertFalse(searchRequest.searchByNumber());

        searchRequest.setCompanyNumber(null);
        assertFalse(searchRequest.searchByNumber());
    }
}
