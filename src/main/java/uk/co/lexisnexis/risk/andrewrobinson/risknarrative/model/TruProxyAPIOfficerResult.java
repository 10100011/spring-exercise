package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TruProxyAPIOfficerResult {

    @JsonProperty("items")
    private List<Officer> officers;

    public List<Officer> getOfficers() {
        return officers != null ? officers : List.of();
    }

    public void setOfficers(List<Officer> officers) {
        this.officers = officers;
    }

    @Override
    public String toString() {
        return "TruProxyAPIOfficerResult{" +
                "Officers=" + officers +
                '}';
    }
}
