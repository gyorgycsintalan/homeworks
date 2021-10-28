package rest_response_model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingItem {
    private UUID id;
    private String title;
    private String description;

    @JsonProperty("location_id")
    private UUID inventoryItemLocationId;

    @JsonProperty("listing_price")
    private Double listingPrice;
    private String currency;
    private Integer quantity;

    @JsonProperty("listing_status")
    private Integer listingStatusId;

    @JsonProperty("marketplace")
    private Integer marketplaceId;

    @JsonProperty("upload_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy")
    private LocalDate uploadTime;

    @JsonProperty("owner_email_address")
    private String ownerEmailAddress;

    public ListingItem() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getInventoryItemLocationId() {
        return inventoryItemLocationId;
    }

    public void setInventoryItemLocationId(UUID inventoryItemLocationId) {
        this.inventoryItemLocationId = inventoryItemLocationId;
    }

    public Double getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Double listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getListingStatusId() {
        return listingStatusId;
    }

    public void setListingStatusId(Integer listingStatusId) {
        this.listingStatusId = listingStatusId;
    }

    public Integer getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(Integer marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public LocalDate getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDate uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }
}
