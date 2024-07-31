package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("officer_role")
    private String officerRole;
    @JsonProperty("appointed_on")
    private String appointedOn;
    @JsonIgnore
    @JsonProperty("resigned_on")
    private String resignedOn;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("address")
    private Address address;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getOfficerRole() {
        return officerRole;
    }

    void setOfficerRole(String officerRole) {
        this.officerRole = officerRole;
    }

    String getAppointedOn() {
        return appointedOn;
    }

    void setAppointedOn(String appointedOn) {
        this.appointedOn = appointedOn;
    }

    String getResignedOn() {
        return resignedOn;
    }

    void setResignedOn(String resignedOn) {
        this.resignedOn = resignedOn;
    }

    Address getAddress() {
        return address;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    public void setActive(Boolean active) {
        this.resignedOn = active ? "" : "1970-01-01";
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.resignedOn == null || this.resignedOn.isEmpty();
    }

    @Override
    public String toString() {
        return "Officer{" +
                "name='" + name + '\'' +
                ", officerRole='" + officerRole + '\'' +
                ", appointedOn='" + appointedOn + '\'' +
                ", address=" + address +
                '}';
    }
}
