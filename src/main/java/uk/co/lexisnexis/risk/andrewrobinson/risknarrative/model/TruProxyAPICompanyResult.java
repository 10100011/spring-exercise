package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TruProxyAPICompanyResult {

    @JsonProperty("items")
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies != null ? companies : List.of();
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "TruProxyAPICompanyResult{" +
                ", companies=" + companies +
                '}';
    }
}
