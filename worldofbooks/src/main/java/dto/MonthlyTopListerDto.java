package dto;

public class MonthlyTopListerDto {
    private Integer monthNumber; //monthnums
    private String monthName; //monthnames
    private Double sumListingPrice; //sumprice
    private String emailAddress; //owner_email_address

    public MonthlyTopListerDto() {
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Double getSumListingPrice() {
        return sumListingPrice;
    }

    public void setSumListingPrice(Double sumListingPrice) {
        this.sumListingPrice = sumListingPrice;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
