package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model;

public class SearchRequest {
    public String getCompanyNumber() { return companyNumber != null ? companyNumber : ""; }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName != null ? companyName : "";
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean searchByName() {
        return !this.getCompanyName().isEmpty();
    }

    public Boolean searchByNumber() {
        return !this.getCompanyNumber().isEmpty();
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "companyName='" + companyName + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                ", searchByNumber()='" + this.searchByNumber() + '\'' +
                ", searchByName()='" + this.searchByName() + '\'' +
                '}';
    }

    private String companyName;
    private String companyNumber;

}
