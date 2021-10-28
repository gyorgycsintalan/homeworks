package validator;

import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;

import java.util.*;

public class Validator {
    private static final String CSV_ROW_TEMPLATE = "\"%s\",\"%s\",\"%s\"";
    private HashMap<Integer, String> marketplaceNamesById;
    private HashSet<UUID> locationItemIds;
    private HashSet<Integer> listingStatusIds;

    private static boolean isStringLengthEquals(String input, int length) {
        return input.length() == length;
    }

    private static boolean isDoubleValidPrice(Double input) {
        if (input == null || input<0 || !Double.toString(input).matches("[0-9]+[.][0-9][1-9]?")) return false;
        return true;
    }

    private static boolean isValidEmailAddressFormat(String emailAddress) {
        final String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return (emailAddress != null && emailAddress.matches(regex));
    }

    public void setMarketplaceItems(List<MarketplaceItem> marketplaceItems) {
        //marketplace items to map
        HashMap<Integer, String> marketplaceNamesById = new HashMap<Integer, String>();
        marketplaceItems.forEach(marketplaceItem -> marketplaceNamesById.put(marketplaceItem.getId(), marketplaceItem.getMarketplaceName()));
        this.marketplaceNamesById = marketplaceNamesById;
    }

    public void setLocationItems(List<LocationItem> locationItems) {
        //collect location item ids to set
        HashSet<UUID> locationItemIds = new HashSet<UUID>();
        locationItems.forEach(locationItem -> locationItemIds.add(locationItem.getId()));
        this.locationItemIds = locationItemIds;
    }

    public void setListingStatusItems(List<ListingStatusItem> listingStatusItems) {
        //collect listing status ids to set
        HashSet<Integer> listingStatusIds = new HashSet<Integer>();
        listingStatusItems.forEach(listingStatusItem -> listingStatusIds.add(listingStatusItem.getId()));

        this.listingStatusIds = listingStatusIds;
    }

    public ArrayList<String> validateListings(List<ListingItem> listingItems) {
        ArrayList<String> invalidRowsCsv = new  ArrayList<String>();
        boolean isLineInvalid;

        Iterator<ListingItem> iterator = listingItems.iterator();
        while (iterator.hasNext()) {
            isLineInvalid = false;
            ListingItem listingItem = iterator.next();

            // id
            if (listingItem.getId() == null) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getId() == null ? "null" : listingItem.getId().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "id"
                ));
                isLineInvalid |= true;
            }

            // title;
            if (listingItem.getTitle() == null) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        "null",
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "title"
                ));
                isLineInvalid |= true;
            }

            // description;
            if (listingItem.getDescription() == null) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        "null",
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "description"
                ));
                isLineInvalid |= true;
            }

            // inventory_item_location_id
            if (listingItem.getInventoryItemLocationId() == null || !locationItemIds.contains(listingItem.getInventoryItemLocationId())) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getInventoryItemLocationId() == null ? "null" : listingItem.getInventoryItemLocationId().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "inventory_item_location_id"
                ));
                isLineInvalid |= true;
            }

            // listing_price
            if (!isDoubleValidPrice(listingItem.getListingPrice())) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getListingPrice() == null ? "null" : listingItem.getListingPrice().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "listing_price"
                ));
                isLineInvalid |= true;
            }

            // currency;
            if (listingItem.getCurrency() == null || !isStringLengthEquals(listingItem.getCurrency(), 3)) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getCurrency() == null ? "null" : listingItem.getCurrency().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "currency"
                ));
                isLineInvalid |= true;
            }

            // quantity;
            if (listingItem.getQuantity() == null || !(listingItem.getQuantity() > 0)) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getQuantity() == null ? "null" : listingItem.getQuantity().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "quantity"
                ));
                isLineInvalid |= true;
            }

            // listing_status
            if (listingItem.getListingStatusId() == null || !listingStatusIds.contains(listingItem.getListingStatusId())) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getListingStatusId() == null ? "null" : listingItem.getListingStatusId().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "listing_status"
                ));
                isLineInvalid |= true;
            }

            // marketplace
            if (listingItem.getMarketplaceId() == null || !marketplaceNamesById.containsKey(listingItem.getMarketplaceId())) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getMarketplaceId() == null ? "null" : listingItem.getMarketplaceId().toString(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "marketplace"
                ));
                isLineInvalid |= true;
            }

            // upload_time
            if (listingItem.getUploadTime() == null) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getUploadTime() == null,
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "upload_time"
                ));
                isLineInvalid |= true;
            }

            // owner_email_address
            if (!isValidEmailAddressFormat(listingItem.getOwnerEmailAddress())) {
                invalidRowsCsv.add(String.format(
                        CSV_ROW_TEMPLATE,
                        listingItem.getOwnerEmailAddress() == null ? "null" : listingItem.getOwnerEmailAddress(),
                        marketplaceNamesById.get(listingItem.getMarketplaceId()),
                        "owner_email_address"
                ));
                isLineInvalid |= true;
            }

            if (isLineInvalid) iterator.remove();
        }

        return invalidRowsCsv;
    }

}
