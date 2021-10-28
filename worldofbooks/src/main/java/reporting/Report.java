package reporting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {
    @JsonProperty("total_listing_count")
    private Integer totalListingCount;

    @JsonProperty("total_ebay_listing_count")
    private Integer totalEbayListingCount;

    @JsonProperty("total_ebay_listing_price")
    private Double totalEbayListingPrice;

    @JsonProperty("average_ebay_listing_price")
    private Double averageEbayListingPrice;

    @JsonProperty("total_amazon_listing_count")
    private Integer totalAmazonListingCount;

    @JsonProperty("total_amazon_listing_price")
    private Double totalAmazonListingPrice;

    @JsonProperty("average_amazon_listing_price")
    private Double averageAmazonListingPrice;

    @JsonProperty("best_lister_email_address")
    private String bestListerEmailAddress;


    ArrayList<MonthlyReport> monthlyReports;

    public Report() {
    }

    public Integer getTotalListingCount() {
        return totalListingCount;
    }

    public void setTotalListingCount(Integer totalListingCount) {
        this.totalListingCount = totalListingCount;
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

    public ArrayList<MonthlyReport> getMonthlyReports() {
        return monthlyReports;
    }

    public void setMonthlyReports(ArrayList<MonthlyReport> monthlyReports) {
        this.monthlyReports = monthlyReports;
    }
}
