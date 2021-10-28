package deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;

import java.util.List;

public class Deserializer {
    private static Deserializer deserializer = null;
    private static ObjectMapper objectMapper;

    private Deserializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    };

    public static Deserializer getInstance() {
        if (deserializer == null) deserializer = new Deserializer();
        return deserializer;
    }

    public List<ListingItem> deserializeListingItems(String jsonInput) throws JsonProcessingException {
        return objectMapper.readValue(jsonInput, new TypeReference<List<ListingItem>>(){});
    }

    public List<ListingStatusItem> deserializeListingStatusItems(String jsonInput) throws JsonProcessingException {
        return objectMapper.readValue(jsonInput, new TypeReference<List<ListingStatusItem>>(){});
    }

    public List<LocationItem> deserializeLocationItems(String jsonInput) throws JsonProcessingException {
        return objectMapper.readValue(jsonInput, new TypeReference<List<LocationItem>>(){});
    }

    public List<MarketplaceItem> deserializeMarketplaceItems(String jsonInput) throws JsonProcessingException {
        return objectMapper.readValue(jsonInput, new TypeReference<List<MarketplaceItem>>(){});
    }

}
