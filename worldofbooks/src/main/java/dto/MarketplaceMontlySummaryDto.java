package dto;

public class MarketplaceMontlySummaryDto {
    private Integer monthOrderNumber; //month_no
    private String monthName; //month_name
    private Double sumListingPrice; //sum_price
    private Double averageListingPrice; //avg_price
    private Integer listingCount; //listing_count
    private String marketplaceName; //marketplace_name

    public MarketplaceMontlySummaryDto() {
    }

    public Integer getMonthOrderNumber() {
        return monthOrderNumber;
    }

    public void setMonthOrderNumber(Integer monthOrderNumber) {
        this.monthOrderNumber = monthOrderNumber;
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

    public Double getAverageListingPrice() {
        return averageListingPrice;
    }

    public void setAverageListingPrice(Double averageListingPrice) {
        this.averageListingPrice = averageListingPrice;
    }

    public Integer getListingCount() {
        return listingCount;
    }

    public void setListingCount(Integer listingCount) {
        this.listingCount = listingCount;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }
}
