package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model.*;
import uk.co.lexisnexis.risk.andrewrobinson.risknarrative.persistence.CompanyRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SearchController searchController;

    private final String X_API_KEY = "your-api-key";

    @BeforeEach
    void setUp() {
        searchController = new SearchController();
        searchController.companyRepository = companyRepository;
        searchController.restTemplate = restTemplate;
        searchController.X_API_KEY = X_API_KEY;
    }

    @Test
    void testGetHttpEntity() {
        HttpEntity<String> httpEntity = searchController.getHttpEntity();

        HttpHeaders headers = httpEntity.getHeaders();
        assertEquals(List.of(MediaType.APPLICATION_JSON), headers.getAccept());
        assertTrue(headers.containsKey("x-api-key"));
        assertEquals("your-api-key", headers.getFirst("x-api-key"));
    }

    @Test
    void testRemoveInactiveCompanies() {
        TruProxyAPICompanyResult companyResult = new TruProxyAPICompanyResult();
        Company activeCompany = new Company();
        activeCompany.setActive(true);
        Company inactiveCompany = new Company();
        inactiveCompany.setActive(false);

        companyResult.setCompanies(new ArrayList<>(List.of(activeCompany, inactiveCompany)));
        searchController.removeInactiveCompanies(companyResult);

        assertEquals(1, companyResult.getCompanies().size());
        assertTrue(companyResult.getCompanies().getFirst().isActive());
    }

    @Test
    void testRemoveInactiveOfficers() {
        TruProxyAPIOfficerResult officerResult = new TruProxyAPIOfficerResult();
        Officer activeOfficer = new Officer();
        activeOfficer.setActive(true);
        Officer inactiveOfficer = new Officer();
        inactiveOfficer.setActive(false);

        officerResult.setOfficers(new ArrayList<>(List.of(activeOfficer, inactiveOfficer)));
        searchController.removeInactiveOfficers(officerResult);

        assertEquals(1, officerResult.getOfficers().size());
        assertTrue(officerResult.getOfficers().getFirst().isActive());
    }

    @Test
    void testSearch_byNumber_foundInRepository() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyNumber("12345");

        Company company = new Company();
        company.setCompanyNumber("12345");

        when(companyRepository.findById("12345")).thenReturn(Optional.of(company));

        SearchResult searchResult = searchController.search(true, searchRequest);

        assertNotNull(searchResult);
    }

    @Test
    void testSearch_byNumber_notFoundInRepository() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyNumber("12345");

        TruProxyAPICompanyResult apiCompanyResult = new TruProxyAPICompanyResult();
        Company company = new Company();
        company.setCompanyNumber("12345");
        company.setActive(true);
        apiCompanyResult.setCompanies(new ArrayList<>(Collections.singletonList(company)));

        TruProxyAPIOfficerResult apiOfficerResult = new TruProxyAPIOfficerResult();
        Officer officer = new Officer();
        officer.setActive(true);
        apiOfficerResult.setOfficers(new ArrayList<>(Collections.singletonList(officer)));

        when(companyRepository.findById("12345")).thenReturn(Optional.empty());
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(TruProxyAPICompanyResult.class)))
                .thenReturn(ResponseEntity.ok(apiCompanyResult));
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(TruProxyAPIOfficerResult.class)))
                .thenReturn(ResponseEntity.ok(apiOfficerResult));

        SearchResult searchResult = searchController.search(true, searchRequest);

        assertNotNull(searchResult);
        assertEquals(1, searchResult.getTotalResults());
        assertEquals("12345", searchResult.getCompanies().getFirst().getCompanyNumber());
        assertEquals(1, searchResult.getCompanies().getFirst().getOfficers().size());
    }

    @Test
    void testSearch_byName() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCompanyName("TestCompany");

        TruProxyAPICompanyResult apiCompanyResult = new TruProxyAPICompanyResult();
        Company company = new Company();
        company.setTitle("TestCompany");
        company.setCompanyNumber("12345");
        company.setActive(true);
        apiCompanyResult.setCompanies(new ArrayList<>(Collections.singletonList(company)));

        TruProxyAPIOfficerResult apiOfficerResult = new TruProxyAPIOfficerResult();
        Officer officer = new Officer();
        officer.setActive(true);
        apiOfficerResult.setOfficers(new ArrayList<>(Collections.singletonList(officer)));

        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(TruProxyAPICompanyResult.class)))
                .thenReturn(ResponseEntity.ok(apiCompanyResult));
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(TruProxyAPIOfficerResult.class)))
                .thenReturn(ResponseEntity.ok(apiOfficerResult));

        SearchResult searchResult = searchController.search(true, searchRequest);

        assertNotNull(searchResult);
        assertEquals(1, searchResult.getCompanies().size());
        assertEquals("12345", searchResult.getCompanies().getFirst().getCompanyNumber());
        assertEquals(1, searchResult.getCompanies().getFirst().getOfficers().size());
    }

    @Test
    void testSearch_noValidSearchTerm() {
        SearchRequest searchRequest = new SearchRequest();

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> searchController.search(true, searchRequest));

        assertEquals("No valid search term supplied", exception.getMessage());
    }
}
