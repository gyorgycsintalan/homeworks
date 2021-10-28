package dto;

public class TopListerDto {
    private Double sumListingPrice; //listing_price
    private String emailAddress; //owner_email_address

    public TopListerDto() {
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
