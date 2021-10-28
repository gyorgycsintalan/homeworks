package reporting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyReport {
    @JsonProperty("month")
    private String monthName;

    @JsonProperty("total_ebay_listing_count_per_month")
    private Integer totalEbayListingCount;

    @JsonProperty("total_ebay_listing_price_per_month")
    private Double totalEbayListingPrice;

    @JsonProperty("average_ebay_listing_price_per_month")
    private Double averageEbayListingPrice;

    @JsonProperty("total_amazon_listing_count_per_month")
    private Integer totalAmazonListingCount;

    @JsonProperty("total_amazon_listing_price_per_month")
    private Double totalAmazonListingPrice;

    @JsonProperty("average_amazon_listing_price_per_month")
    private Double averageAmazonListingPrice;

    @JsonProperty("best_lister_email_address_of_month")
    private String bestListerEmailAddress;

    public MonthlyReport() {
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getTotalEbayListingCount() {
        return totalEbayListingCount;
    }

    public void setTotalEbayListingCount(Integer totalEbayListingCount) {
        this.totalEbayListingCount = totalEbayListingCount;
    }

    public Double getTotalEbayListingPrice() {
        return totalEbayListingPrice;
    }

    public void setTotalEbayListingPrice(Double totalEbayListingPrice) {
        this.totalEbayListingPrice = totalEbayListingPrice;
    }

    public Double getAverageEbayListingPrice() {
        return averageEbayListingPrice;
    }

    public void setAverageEbayListingPrice(Double averageEbayListingPrice) {
        this.averageEbayListingPrice = averageEbayListingPrice;
    }

    public Integer getTotalAmazonListingCount() {
        return totalAmazonListingCount;
    }

    public void setTotalAmazonListingCount(Integer totalAmazonListingCount) {
        this.totalAmazonListingCount = totalAmazonListingCount;
    }

    public Double getTotalAmazonListingPrice() {
        return totalAmazonListingPrice;
    }

    public void setTotalAmazonListingPrice(Double totalAmazonListingPrice) {
        this.totalAmazonListingPrice = totalAmazonListingPrice;
    }

    public Double getAverageAmazonListingPrice() {
        return averageAmazonListingPrice;
    }

    public void setAverageAmazonListingPrice(Double averageAmazonListingPrice) {
        this.averageAmazonListingPrice = averageAmazonListingPrice;
    }

    public String getBestListerEmailAddress() {
        return bestListerEmailAddress;
    }

    public void setBestListerEmailAddress(String bestListerEmailAddress) {
        this.bestListerEmailAddress = bestListerEmailAddress;
    }
}
