import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;
import validator.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidatorTests {
    Validator validator;

    List<ListingItem> getValidListings() {
        ArrayList<ListingItem> listingItems = new ArrayList<ListingItem>();

        ListingItem listingItem = new ListingItem();
        listingItem.setId(UUID.fromString("78af0b74-f224-4287-9746-a8fd71ce6194"));
        listingItem.setTitle("Gravel Milkvetch");
        listingItem.setDescription("Astragalus sabulonum A. Gray");
        listingItem.setInventoryItemLocationId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127dc"));
        listingItem.setListingPrice(792.25);
        listingItem.setCurrency("HUF");
        listingItem.setQuantity(40);
        listingItem.setListingStatusId(1);
        listingItem.setMarketplaceId(1);
        listingItem.setUploadTime(LocalDate.of(2018, 12,2));
        listingItem.setOwnerEmailAddress("jipsen@hotmail.com");

        listingItems.add(listingItem);

        return listingItems;
    }

    List<ListingItem> getMixedListings() {
        ArrayList<ListingItem> listingItems = new ArrayList<ListingItem>();

        ListingItem listingItem = new ListingItem();
        listingItem.setId(UUID.fromString("78af0b74-f224-4287-9746-a8fd71ce6194"));
        listingItem.setTitle("Gravel Milkvetch");
        listingItem.setDescription("Astragalus sabulonum A. Gray");
        listingItem.setInventoryItemLocationId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127de"));
        listingItem.setListingPrice(792.25);
        listingItem.setCurrency("HUF");
        listingItem.setQuantity(40);
        listingItem.setListingStatusId(1);
        listingItem.setMarketplaceId(1);
        listingItem.setUploadTime(LocalDate.of(2018, 12,2));
        listingItem.setOwnerEmailAddress("jipsen@hotmail.com");

        listingItems.add(listingItem);

        listingItem = new ListingItem();
        listingItem.setId(UUID.fromString("78af0b74-f224-4287-9746-a8fd71ce61aa"));
        listingItem.setTitle("Tourmax Fuelpump");
        listingItem.setDescription("Tourmax description");
        listingItem.setInventoryItemLocationId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127dc"));
        listingItem.setListingPrice(70.00);
        listingItem.setCurrency("USD");
        listingItem.setQuantity(-40);
        listingItem.setListingStatusId(1);
        listingItem.setMarketplaceId(1);
        listingItem.setUploadTime(LocalDate.of(2020, 12,7));
        listingItem.setOwnerEmailAddress("jipsen@hotmail.com");

        listingItems.add(listingItem);

        listingItem = new ListingItem();
        listingItem.setId(UUID.fromString("78af0b74-f224-4287-9746-a8fd71ce61bc"));
        listingItem.setTitle("Tall Lamp");
        listingItem.setDescription("Light beams");
        listingItem.setInventoryItemLocationId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127dc"));
        listingItem.setListingPrice(800.00);
        listingItem.setCurrency("CZK");
        listingItem.setQuantity(40);
        listingItem.setListingStatusId(1);
        listingItem.setMarketplaceId(1);
        listingItem.setUploadTime(LocalDate.of(2019, 5,4));
        listingItem.setOwnerEmailAddress("jipsen@hotmail.com");

        listingItems.add(listingItem);

        return listingItems;
    }

    List<LocationItem> getValidLocation() {
        ArrayList<LocationItem> locationItems = new ArrayList<LocationItem>();

        LocationItem locationItem = new LocationItem();
        locationItem.setId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127de"));
        locationItem.setManagerName("Joe Nagy");
        locationItem.setPhone("541-116-4567");
        locationItem.setAddressPrimary("77 Big Street");
        locationItem.setAddressSecondary(null);
        locationItem.setCountry("Belgium");
        locationItem.setTown("Bruxelles");
        locationItem.setPostalCode("4537 POAD");

        locationItems.add(locationItem);

        locationItem = new LocationItem();
        locationItem.setId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127dc"));
        locationItem.setManagerName("Anna Karenyina");
        locationItem.setPhone("541-116-7846");
        locationItem.setAddressPrimary("79 Krasnaja");
        locationItem.setAddressSecondary(null);
        locationItem.setCountry("Russia");
        locationItem.setTown("Skt. Petersburg");
        locationItem.setPostalCode("9987 AB");

        locationItems.add(locationItem);

        locationItem = new LocationItem();
        locationItem.setId(UUID.fromString("013cc380-c18b-4c87-8043-16e14f7127df"));
        locationItem.setManagerName("Fred Smith");
        locationItem.setPhone("541-134-3654");
        locationItem.setAddressPrimary("98. street");
        locationItem.setAddressSecondary(null);
        locationItem.setCountry("USA");
        locationItem.setTown("Pittsburg");
        locationItem.setPostalCode("45314 ASG");

        locationItems.add(locationItem);

        return locationItems;
    }

    List<MarketplaceItem> getValidMarketplaceItems() {
        ArrayList<MarketplaceItem> marketplaceItems = new ArrayList<MarketplaceItem>();
        MarketplaceItem marketplaceItem = new MarketplaceItem();
        marketplaceItem.setId(1);
        marketplaceItem.setMarketplaceName("EBAY");
        marketplaceItems.add(marketplaceItem);

        marketplaceItem = new MarketplaceItem();
        marketplaceItem.setId(2);
        marketplaceItem.setMarketplaceName("AMAZON");
        marketplaceItems.add(marketplaceItem);

        return marketplaceItems;
    }

    List<ListingStatusItem> getValidListStatusItems() {
        ArrayList<ListingStatusItem> listingStatusItems = new ArrayList<ListingStatusItem>();
        ListingStatusItem listingStatusItem = new ListingStatusItem();
        listingStatusItem.setId(1);
        listingStatusItem.setStatusName("ACTIVE");
        listingStatusItems.add(listingStatusItem);

        listingStatusItem = new ListingStatusItem();
        listingStatusItem.setId(2);
        listingStatusItem.setStatusName("SCHEDULED");
        listingStatusItems.add(listingStatusItem);

        listingStatusItem = new ListingStatusItem();
        listingStatusItem.setId(3);
        listingStatusItem.setStatusName("CANCELLED");
        listingStatusItems.add(listingStatusItem);

        return listingStatusItems;
    }

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void validListItems_returnsEmptyArrayList() {
        List<ListingItem> listingItems = getValidListings();
        List<LocationItem> locationItems = getValidLocation();
        List<MarketplaceItem> marketplaceItems = getValidMarketplaceItems();
        List<ListingStatusItem> listingStatusItems = getValidListStatusItems();
        validator.setLocationItems(locationItems);
        validator.setMarketplaceItems(marketplaceItems);
        validator.setListingStatusItems(listingStatusItems);

        ArrayList<String> invalidListings = validator.validateListings(listingItems);
        assertNotNull(invalidListings);
        assertEquals(true, invalidListings.isEmpty());
    }

    @Test
    void validListItems_returnsMin40EbayQuanytiy() {
        List<ListingItem> listingItems = getMixedListings();
        List<LocationItem> locationItems = getValidLocation();
        List<MarketplaceItem> marketplaceItems = getValidMarketplaceItems();
        List<ListingStatusItem> listingStatusItems = getValidListStatusItems();
        validator.setLocationItems(locationItems);
        validator.setMarketplaceItems(marketplaceItems);
        validator.setListingStatusItems(listingStatusItems);

        ArrayList<String> invalidListings = validator.validateListings(listingItems);
        assertNotNull(invalidListings);
        assertEquals(false, invalidListings.isEmpty());
        assertEquals("\"-40\",\"EBAY\",\"quantity\"", invalidListings.get(0));
    }
}
