package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    private static final String ACTIVE = "active";
    private static final String INACTIVE = "inactive";

    @Id
    @JsonProperty("company_number")
    private String companyNumber;
    @JsonProperty("company_type")
    private String companyType;
    @JsonProperty("title")
    private String title;
    @JsonProperty("company_status")
    private String companyStatus;
    @JsonProperty("date_of_creation")
    private String dateOfCreation;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("address")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("officers")
    private final List<Officer> officers = new ArrayList<>();

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    String getCompanyType() {
        return companyType;
    }

    void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getCompanyStatus() {
        return companyStatus;
    }

    void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    String getDateOfCreation() {
        return dateOfCreation;
    }

    void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    Address getAddress() {
        return address;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    public void setActive(Boolean active) {
        this.companyStatus = active ? ACTIVE : INACTIVE;
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.companyStatus != null && this.companyStatus.matches(ACTIVE);
    }

    public List<Officer> getOfficers() {
        return officers;
    }

    public void addOfficers(List<Officer> officers) { this.officers.addAll(officers); }

    @Override
    public String toString() {
        return "Company{" +
                "companyNumber='" + companyNumber + '\'' +
                ", companyType='" + companyType + '\'' +
                ", title='" + title + '\'' +
                ", companyStatus='" + companyStatus + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", address=" + address +
                ", officers=" + officers +
                '}';
    }

}
