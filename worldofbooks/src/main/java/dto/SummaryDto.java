package dto;

public class SummaryDto {
    private String marketplaceName; //marketplace_name
    private Integer ListingCount; //listing_count
    private Double sumListingPrice; //sum_listing
    private Double averageListingPrice; //avg_listing

    public SummaryDto() {
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

    public Integer getListingCount() {
        return ListingCount;
    }

    public void setListingCount(Integer listingCount) {
        ListingCount = listingCount;
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
}
