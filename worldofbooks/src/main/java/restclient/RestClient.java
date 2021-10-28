package restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RestClient {
    private static final String GET_LISTING_URL = "https://my.api.mockaroo.com/listing?key=63304c70";
    private static final String GET_LOCATION_URL = "https://my.api.mockaroo.com/location?key=63304c70";
    private static final String GET_LISTING_STATUS_URL = "https://my.api.mockaroo.com/listingStatus?key=63304c70";
    private static final String GET_MARKETPLACE_URL = "https://my.api.mockaroo.com/marketplace?key=63304c70";


    private static HttpURLConnection getConnectionSetUpForHttpGet(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        return connection;
    }

    private static ResponseObject getRestResponse(String urlString) throws IOException {
        HttpURLConnection connection = getConnectionSetUpForHttpGet(urlString);
        connection.connect();

        int statusCode = connection.getResponseCode();
        String responseMessage = connection.getResponseMessage();
        Map<String, List<String>> responseHeaders= connection.getHeaderFields();

        Reader streamReader = null;
        if (statusCode > 299) {
            streamReader = new InputStreamReader(connection.getErrorStream());
        } else {
            streamReader = new InputStreamReader(connection.getInputStream());
        }

        BufferedReader bufferedReader = new BufferedReader(streamReader);

        StringBuffer responseContent = new StringBuffer();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            responseContent.append(inputLine);
        }
        bufferedReader.close();

        connection.disconnect();

        return new ResponseObject(statusCode, responseMessage, responseHeaders, responseContent.toString());
    }


    public static ResponseObject getListingResponse() throws IOException {
        return getRestResponse(GET_LISTING_URL);
    }

    public static ResponseObject getLocationResponse() throws IOException {
        return getRestResponse(GET_LOCATION_URL);
    }

    public static ResponseObject getListingStatusResponse() throws IOException {
        return getRestResponse(GET_LISTING_STATUS_URL);
    }

    public static ResponseObject getMarketplaceResponse() throws IOException {
        return getRestResponse(GET_MARKETPLACE_URL);
    }

}
