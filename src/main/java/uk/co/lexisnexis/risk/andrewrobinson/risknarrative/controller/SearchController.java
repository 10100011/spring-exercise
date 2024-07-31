package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model.*;
import uk.co.lexisnexis.risk.andrewrobinson.risknarrative.persistence.CompanyRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SearchController {

    Logger logger = LogManager.getLogger(getClass());

    @Value("${X_API_KEY}")
    String X_API_KEY;

    static final String CORE_URI = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/";
    static final String COMPANY_URI = CORE_URI.concat("Search?Query=");
    static final String OFFICER_URI = CORE_URI.concat("Officers?CompanyNumber=");
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    CompanyRepository companyRepository;

    final HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("x-api-key", X_API_KEY);
        return new HttpEntity<>(headers);
    }

    void removeInactiveCompanies(TruProxyAPICompanyResult truProxyAPICompanyResult) {
        if (!truProxyAPICompanyResult.getCompanies().isEmpty()) {
            truProxyAPICompanyResult.getCompanies().removeIf(company -> !company.isActive());
        }
    }

    void removeInactiveOfficers(TruProxyAPIOfficerResult truProxyAPIOfficerResult) {
        if (!truProxyAPIOfficerResult.getOfficers().isEmpty()) {
            truProxyAPIOfficerResult.getOfficers().removeIf(officer -> !officer.isActive());
        }
    }

    @PostMapping("/search")
    public SearchResult search(@RequestParam Boolean activeOnly, @RequestBody SearchRequest searchRequest) {

        logger.debug(searchRequest);
        String searchTerm;
        if (searchRequest.searchByNumber()) {
            searchTerm = searchRequest.getCompanyNumber();

            // First, attempt to find Company from the repository
            Optional<Company> optionalCompany = companyRepository.findById(searchTerm);
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                logger.debug("Company found in repository: {}", company);
                return new SearchResult(company);
            }
            // No Company found. Continue to API.
        } else if (searchRequest.searchByName()) {
            searchTerm = searchRequest.getCompanyName();
            // Repository storage not supported when querying via company name
        } else {
            // At least one search term must be included
            throw new UnsupportedOperationException("No valid search term supplied");
        }

        TruProxyAPICompanyResult truProxyAPICompanyResult = restTemplate.exchange(URI.create(COMPANY_URI.concat(searchTerm)), HttpMethod.GET, getHttpEntity(), TruProxyAPICompanyResult.class).getBody();
        assert truProxyAPICompanyResult != null;
        if (activeOnly) {
            removeInactiveCompanies(truProxyAPICompanyResult);
        }
        logger.trace(truProxyAPICompanyResult);
        SearchResult searchResult = new SearchResult(truProxyAPICompanyResult);
        for (Company company : truProxyAPICompanyResult.getCompanies()) {
            TruProxyAPIOfficerResult truProxyAPIOfficerResult = restTemplate.exchange(URI.create(OFFICER_URI.concat(company.getCompanyNumber())), HttpMethod.GET, getHttpEntity(), TruProxyAPIOfficerResult.class).getBody();
            assert truProxyAPIOfficerResult != null;
            logger.debug("Querying API for Officers at companyNumber {}", company.getCompanyNumber());
            removeInactiveOfficers(truProxyAPIOfficerResult);
            logger.trace(truProxyAPIOfficerResult);
            company.addOfficers(truProxyAPIOfficerResult.getOfficers());

            companyRepository.save(company);
            logger.debug(companyRepository.findAll());
        }
        return searchResult;
    }
}
