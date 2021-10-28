package restservice;

import deserializer.Deserializer;
import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;
import restclient.ResponseObject;
import restclient.RestClient;

import java.util.List;

public class RestService {
    private Deserializer deserializer;

    public RestService() {
        this.deserializer = Deserializer.getInstance();
    }

    public List<ListingItem> getListingItems() throws Exception {
        ResponseObject responseObject = RestClient.getListingResponse();
        if (responseObject.getStatusCode() > 299) throw new Exception("Error during REST api request! Message: " + responseObject.getContent());
        return deserializer.deserializeListingItems(responseObject.getContent());
    }

    public List<ListingStatusItem> getListingStatusItems() throws Exception {
        ResponseObject responseObject = RestClient.getListingStatusResponse();
        if (responseObject.getStatusCode() > 299) throw new Exception("Error during REST api request! Message: " + responseObject.getContent());
        return deserializer.deserializeListingStatusItems(responseObject.getContent());
    }

    public List<LocationItem> getLocationItems() throws Exception {
        ResponseObject responseObject = RestClient.getLocationResponse();
        if (responseObject.getStatusCode() > 299) throw new Exception("Error during REST api request! Message: " + responseObject.getContent());
        return deserializer.deserializeLocationItems(responseObject.getContent());
    }

    public List<MarketplaceItem> getMarketplaceItems() throws Exception {
        ResponseObject responseObject = RestClient.getMarketplaceResponse();
        if (responseObject.getStatusCode() > 299) throw new Exception("Error during REST api request! Message: " + responseObject.getContent());
        return deserializer.deserializeMarketplaceItems(responseObject.getContent());
    }
}
