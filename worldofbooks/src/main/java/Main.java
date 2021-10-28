import dto.*;
import jdbc.DatabaseOperations;
import properties.ConfigProperties;
import reporting.Report;
import reporting.ReportCreator;
import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;
import restservice.RestService;
import serializer.Serializer;
import utility.Utility;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main( String[] args ) {
        DatabaseOperations databaseOperations;
        RestService restService = new RestService();
        ConfigProperties configProperties = new ConfigProperties();
        try {
            databaseOperations = DatabaseOperations.getInstance();
            List<ListingItem> listingItems = restService.getListingItems();
            List<ListingStatusItem> listingStatusItems = restService.getListingStatusItems();
            List<LocationItem> locationItems = restService.getLocationItems();
            List<MarketplaceItem> marketplaceItems = restService.getMarketplaceItems();

            Validator validator = new Validator();
            validator.setListingStatusItems(listingStatusItems);
            validator.setLocationItems(locationItems);
            validator.setMarketplaceItems(marketplaceItems);

            ArrayList<String> csvLines = validator.validateListings(listingItems);
            String filenameCsv= configProperties.readProperty("filename_csv");
            Utility.writeToFile(csvLines, filenameCsv);

            //saving data to database
            for (MarketplaceItem marketplaceItem : marketplaceItems) {
                databaseOperations.insertMarketplaceItem(marketplaceItem);
            }

            for (ListingStatusItem listingStatusItem : listingStatusItems) {
                databaseOperations.insertListingStatusItem(listingStatusItem);
            }

            for (LocationItem locationItem : locationItems) {
                databaseOperations.insertLocationItem(locationItem);
            }

            for (ListingItem listingItem : listingItems) {
                databaseOperations.insertListingItem(listingItem);
            }

            ReportCreator reportCreator = ReportCreator.getInstance(databaseOperations);
            Report report = reportCreator.constructReport();

            Serializer serializer = Serializer.getInstance();


            String filenameReport= configProperties.readProperty("filename_report");
            Utility.writeToFile(serializer.serializeReport(report), filenameReport);
            Utility.sendFileThroughFtp(filenameReport);
            Utility.sendFileThroughFtp(filenameCsv);
            databaseOperations.finalizeOperations();
        } catch (Exception exception) {
            System.out.println("An error occured during execution: " + exception.getMessage());
        }


    }
}
