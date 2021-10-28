package dto;

public class AmazonEbayMonthlySummaryDto {
    private Integer monthNumber; //month_no
    private String monthName; //month_name
    private Double ebaySumListing; //sum_listing_1
    private Double ebayAverageListing; //avg_price_1;
    private Double amazonSumListing; //sum_listing_1
    private Double amazonAverageListing; //avg_price_1;

    public AmazonEbayMonthlySummaryDto() {
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

    public Double getEbaySumListing() {
        return ebaySumListing;
    }

    public void setEbaySumListing(Double ebaySumListing) {
        this.ebaySumListing = ebaySumListing;
    }

    public Double getEbayAverageListing() {
        return ebayAverageListing;
    }

    public void setEbayAverageListing(Double ebayAverageListing) {
        this.ebayAverageListing = ebayAverageListing;
    }

    public Double getAmazonSumListing() {
        return amazonSumListing;
    }

    public void setAmazonSumListing(Double amazonSumListing) {
        this.amazonSumListing = amazonSumListing;
    }

    public Double getAmazonAverageListing() {
        return amazonAverageListing;
    }

    public void setAmazonAverageListing(Double amazonAverageListing) {
        this.amazonAverageListing = amazonAverageListing;
    }
}
