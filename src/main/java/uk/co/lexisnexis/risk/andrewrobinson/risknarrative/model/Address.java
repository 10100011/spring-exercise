package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("locality")
    private String locality;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("premises")
    private String premises;
    @JsonProperty("address_line_1")
    private String addressLine1;
    @JsonProperty("address_line_2")
    private String addressLine2;
    @JsonProperty("country")
    private String country;

    String getLocality() {
        return locality;
    }

    void setLocality(String locality) {
        this.locality = locality;
    }

    String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    String getPremises() {
        return premises;
    }

    void setPremises(String premises) {
        this.premises = premises;
    }

    String getAddressLine1() {
        return addressLine1;
    }

    void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    String getAddressLine2() {
        return addressLine2;
    }

    void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "locality='" + locality + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", premises='" + premises + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
