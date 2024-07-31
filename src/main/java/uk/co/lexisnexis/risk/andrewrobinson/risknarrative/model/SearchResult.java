package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.*;

@JsonPropertyOrder({ "total_results", "items" })
public class SearchResult {

    @JsonProperty("items")
    private List<Company> companies;

    public SearchResult(TruProxyAPICompanyResult truProxyAPICompanyResult) {
        this.companies = new ArrayList<>(truProxyAPICompanyResult.getCompanies());
    }

    public SearchResult(Company company) {
        this.companies = new ArrayList<>(Collections.singletonList(company));
    }

    @JsonProperty("total_results")
    public Integer getTotalResults() {
        return (this.companies != null) ? this.companies.size() : 0;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}