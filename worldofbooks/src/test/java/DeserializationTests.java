import deserializer.Deserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeserializationTests {
    Deserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = Deserializer.getInstance();
    }

    @Test
    void validListingItemJsonInput() {
        String jsonInput = "[{\"id\":\"78af0b74-f224-4287-9746-a8fd71ce6194\",\"title\":\"Gravel Milkvetch\",\"description\":\"Astragalus sabulonum A. Gray\",\"location_id\":\"013cc380-c18b-4c87-8043-16e14f7127de\",\"listing_price\":792,\"currency\":\"HUF\",\"quantity\":40,\"listing_status\":1,\"marketplace\":1,\"upload_time\":\"12/2/2018\",\"owner_email_address\":\"jipsen@hotmail.com\"}]";

        ListingItem listingItem = new ListingItem();
        listingItem.setId(UUID.fromString("78af0b74-f224-4287-9746-a8fd71ce6194"));
        listingItem.setTitle("Gravel Milkvetch");
        listingItem.setDescription("Astragalus sabulonum A. Gray");
        listingItem.setInventoryItemLocationId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127de"));
        listingItem.setListingPrice(792d);
        listingItem.setCurrency("HUF");
        listingItem.setQuantity(40);
        listingItem.setListingStatusId(1);
        listingItem.setMarketplaceId(1);
        listingItem.setUploadTime(LocalDate.of(2018, 12,2));
        listingItem.setOwnerEmailAddress("jipsen@hotmail.com");


        List<ListingItem> listingItemsOutput = new ArrayList<ListingItem>();
        try {
            listingItemsOutput = deserializer.deserializeListingItems(jsonInput);
        } catch (Exception exception) {
            System.err.println("Error during deserialization: " + exception.getMessage());
        }

        assertEquals(1, listingItemsOutput.size());
        assertEquals(listingItem.getId(), listingItemsOutput.get(0).getId());
        assertEquals(listingItem.getTitle(), listingItemsOutput.get(0).getTitle());
        assertEquals(listingItem.getDescription(), listingItemsOutput.get(0).getDescription());
        assertEquals(listingItem.getInventoryItemLocationId(), listingItemsOutput.get(0).getInventoryItemLocationId());
        assertEquals(listingItem.getListingPrice(), listingItemsOutput.get(0).getListingPrice());
        assertEquals(listingItem.getCurrency(), listingItemsOutput.get(0).getCurrency());
        assertEquals(listingItem.getQuantity(), listingItemsOutput.get(0).getQuantity());
        assertEquals(listingItem.getListingStatusId(), listingItemsOutput.get(0).getListingStatusId());
        assertEquals(listingItem.getMarketplaceId(), listingItemsOutput.get(0).getMarketplaceId());
        assertEquals(listingItem.getUploadTime(), listingItemsOutput.get(0).getUploadTime());
        assertEquals(listingItem.getOwnerEmailAddress(), listingItemsOutput.get(0).getOwnerEmailAddress());
    }

    @Test
    void validLocationItemJsonInput() {
        String jsonInput = "[{\"id\":\"0fe479bb-fe39-4265-b59f-bb4ac5a060d4\",\"manager_name\":\"Will Josofovitz\",\"phone\":\"541-116-0220\",\"address_primary\":\"35 Saint Paul Street\",\"address_secondary\":null,\"country\":\"France\",\"town\":\"Saint-Quentin\",\"postal_code\":\"02104 CEDEX\"}]";

        LocationItem locationItem = new LocationItem();
        locationItem.setId(UUID.fromString("0fe479bb-fe39-4265-b59f-bb4ac5a060d4"));
        locationItem.setManagerName("Will Josofovitz");
        locationItem.setPhone("541-116-0220");
        locationItem.setAddressPrimary("35 Saint Paul Street");
        locationItem.setAddressSecondary(null);
        locationItem.setCountry("France");
        locationItem.setTown("Saint-Quentin");
        locationItem.setPostalCode("02104 CEDEX");


        List<LocationItem> locationItemsOutput = new ArrayList<LocationItem>();
        try {
            locationItemsOutput = deserializer.deserializeLocationItems(jsonInput);
        } catch (Exception exception) {
            System.err.println("Error during deserialization: " + exception.getMessage());
        }

        assertEquals(1, locationItemsOutput.size());
        assertEquals(locationItem.getId(), locationItemsOutput.get(0).getId());
        assertEquals(locationItem.getManagerName(), locationItemsOutput.get(0).getManagerName());
        assertEquals(locationItem.getPhone(), locationItemsOutput.get(0).getPhone());
        assertEquals(locationItem.getAddressPrimary(), locationItemsOutput.get(0).getAddressPrimary());
        assertEquals(locationItem.getAddressSecondary(), locationItemsOutput.get(0).getAddressSecondary());
        assertEquals(locationItem.getCountry(), locationItemsOutput.get(0).getCountry());
        assertEquals(locationItem.getTown(), locationItemsOutput.get(0).getTown());
        assertEquals(locationItem.getPostalCode(), locationItemsOutput.get(0).getPostalCode());
    }

    @Test
    void validListingStatusItemJsonInput() {
        String jsonInput= "[{\"id\":1,\"status_name\":\"ACTIVE\"}]";

        ListingStatusItem listingStatusItem = new ListingStatusItem();
        listingStatusItem.setId(1);
        listingStatusItem.setStatusName("ACTIVE");

        List<ListingStatusItem> listingStatusItemsOutput = new ArrayList<ListingStatusItem>();
        try {
            listingStatusItemsOutput = deserializer.deserializeListingStatusItems(jsonInput);
        } catch (Exception exception) {
            System.err.println("Error during deserialization: " + exception.getMessage());
        }

        assertEquals(1, listingStatusItemsOutput.size());
        assertEquals(listingStatusItem.getId(), listingStatusItemsOutput.get(0).getId());
        assertEquals(listingStatusItem.getStatusName(), listingStatusItemsOutput.get(0).getStatusName());

    }

    @Test
    void validMarketplaceItemJsonInput () {
        String jsonInput= "[{\"id\":1,\"marketplace_name\":\"EBAY\"}]";

        MarketplaceItem marketplaceItem = new MarketplaceItem();
        marketplaceItem.setId(1);
        marketplaceItem.setMarketplaceName("EBAY");

        List<MarketplaceItem> marketplaceItemsOutput = null;
        try {
            marketplaceItemsOutput = deserializer.deserializeMarketplaceItems(jsonInput);
        } catch (Exception exception) {
            System.err.println("Error during deserialization: " + exception.getMessage());
        }

        assertEquals(1, marketplaceItemsOutput.size());
        assertEquals(marketplaceItem.getId(), marketplaceItemsOutput.get(0).getId());
        assertEquals(marketplaceItem.getMarketplaceName(), marketplaceItemsOutput.get(0).getMarketplaceName());
    }
}
