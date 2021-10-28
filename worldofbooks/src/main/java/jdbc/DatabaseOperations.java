package jdbc;

import dto.*;
import properties.ConfigProperties;
import rest_response_model.ListingItem;
import rest_response_model.ListingStatusItem;
import rest_response_model.LocationItem;
import rest_response_model.MarketplaceItem;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseOperations implements DriverAction {
    private static DatabaseOperations databaseOperations = null;
    private Connection connection = null;
    Driver driver = null;

    @Override
    public void deregister() {

    }

    private DatabaseOperations() throws SQLException {
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver, this);


        ConfigProperties configProperties = new ConfigProperties();
        String datasourceUrl= configProperties.readProperty("datasource_url");
        String databaseUser= configProperties.readProperty("database_user");
        String databasePassword = configProperties.readProperty("database_password");
        connection = DriverManager.getConnection(datasourceUrl, databaseUser, databasePassword);
    }

    public static DatabaseOperations getInstance() throws SQLException {
        if (databaseOperations == null) databaseOperations = new DatabaseOperations();
        return databaseOperations;
    }

    public static byte[] uuidToBytes(UUID uuid) {
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return uuidBytes;
    }

    public static UUID bytesToUuid(byte[] uuidBytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(uuidBytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();

        return new UUID(high, low);
    }

    public void insertLocationItem(LocationItem locationItem) throws SQLException {
        final String INSERT_STATEMENT = "INSERT INTO LOCATION(ID, MANAGER_NAME, PHONE, ADDRESS_PRIMARY, ADDRESS_SECONDARY, COUNTRY, TOWN, POSTAL_CODE) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?) " +
                                        "ON DUPLICATE KEY UPDATE MANAGER_NAME=?, PHONE=?, ADDRESS_PRIMARY=?, ADDRESS_SECONDARY=?, COUNTRY=?, TOWN=?, POSTAL_CODE=?";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);

        preparedStatement.setBytes(1, uuidToBytes(locationItem.getId()));
        preparedStatement.setString(2, locationItem.getManagerName());
        preparedStatement.setString(3, locationItem.getPhone());
        preparedStatement.setString(4, locationItem.getAddressPrimary());
        preparedStatement.setString(5, locationItem.getAddressSecondary() == null ? "null" : locationItem.getAddressSecondary());
        preparedStatement.setString(6, locationItem.getCountry());
        preparedStatement.setString(7, locationItem.getTown());
        preparedStatement.setString(8, locationItem.getPostalCode() == null ? "null" : locationItem.getPostalCode());
        preparedStatement.setString(9, locationItem.getManagerName());
        preparedStatement.setString(10, locationItem.getPhone());
        preparedStatement.setString(11, locationItem.getAddressPrimary());
        preparedStatement.setString(12, locationItem.getAddressSecondary() == null ? "null" : locationItem.getAddressSecondary());
        preparedStatement.setString(13, locationItem.getCountry());
        preparedStatement.setString(14, locationItem.getTown());
        preparedStatement.setString(15, locationItem.getPostalCode() == null ? "null" : locationItem.getPostalCode());

        preparedStatement.executeUpdate();
    }

    public void insertListingItem(ListingItem listingItem) throws SQLException {
        final String INSERT_STATEMENT = "INSERT INTO LISTING(ID, TITLE, DESCRIPTION, LOCATION_ID, LISTING_PRICE, CURRENCY, " +
                                                            "QUANTITY, LISTING_STATUS, MARKETPLACE, UPLOAD_TIME, OWNER_EMAIL_ADDRESS) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                                        "ON DUPLICATE KEY UPDATE TITLE=?, DESCRIPTION=?, LOCATION_ID=?, LISTING_PRICE=?, CURRENCY=?, " +
                                                                "QUANTITY=?, LISTING_STATUS=?, MARKETPLACE=?, UPLOAD_TIME=?, OWNER_EMAIL_ADDRESS=?";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);

        preparedStatement.setBytes(1, uuidToBytes(listingItem.getId()));
        preparedStatement.setString(2, listingItem.getTitle());
        preparedStatement.setString(3, listingItem.getDescription());
        preparedStatement.setBytes(4, uuidToBytes(listingItem.getInventoryItemLocationId()));
        preparedStatement.setDouble(5, listingItem.getListingPrice());
        preparedStatement.setString(6, listingItem.getCurrency());
        preparedStatement.setInt(7, listingItem.getQuantity());
        preparedStatement.setInt(8, listingItem.getListingStatusId());
        preparedStatement.setInt(9, listingItem.getMarketplaceId());
        preparedStatement.setDate(10, Date.valueOf(listingItem.getUploadTime()));
        preparedStatement.setString(11, listingItem.getOwnerEmailAddress());
        preparedStatement.setString(12, listingItem.getTitle());
        preparedStatement.setString(13, listingItem.getDescription());
        preparedStatement.setBytes(14, uuidToBytes(listingItem.getInventoryItemLocationId()));
        preparedStatement.setDouble(15, listingItem.getListingPrice());
        preparedStatement.setString(16, listingItem.getCurrency());
        preparedStatement.setInt(17, listingItem.getQuantity());
        preparedStatement.setInt(18, listingItem.getListingStatusId());
        preparedStatement.setInt(19, listingItem.getMarketplaceId());
        preparedStatement.setDate(20, Date.valueOf(listingItem.getUploadTime()));
        preparedStatement.setString(21, listingItem.getOwnerEmailAddress());


        preparedStatement.executeUpdate();
    }

    public void insertListingStatusItem(ListingStatusItem listingStatusItem) throws SQLException {
        final String INSERT_STATEMENT = "INSERT INTO LISTING_STATUS(ID, STATUS_NAME) " +
                                        "VALUES(?, ?) " +
                                        "ON DUPLICATE KEY UPDATE STATUS_NAME=?";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);

        preparedStatement.setInt(1, listingStatusItem.getId());
        preparedStatement.setString(2, listingStatusItem.getStatusName());
        preparedStatement.setString(3, listingStatusItem.getStatusName());


        preparedStatement.executeUpdate();
    }

    public void insertMarketplaceItem(MarketplaceItem marketplaceItem) throws SQLException {
        final String INSERT_STATEMENT = "INSERT INTO MARKETPLACE(ID, MARKETPLACE_NAME) " +
                                        "VALUES(?, ?) " +
                                        "ON DUPLICATE KEY UPDATE MARKETPLACE_NAME=?";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);

        preparedStatement.setInt(1, marketplaceItem.getId());
        preparedStatement.setString(2, marketplaceItem.getMarketplaceName());
        preparedStatement.setString(3, marketplaceItem.getMarketplaceName());


        preparedStatement.executeUpdate();
    }

    public void testQuery() throws SQLException {
        final String QUERY_STRING = "SELECT * FROM LOCATION LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);
        resultSet.next();

        System.out.println(bytesToUuid(resultSet.getBytes("id")));

    }


    public List<SummaryDto> getTotalSummary() throws SQLException {
        final String QUERY_STRING = "SELECT MARKETPLACE_NAME, COUNT(LISTING.ID) LISTING_COUNT, SUM(LISTING_PRICE) SUM_LISTING, ROUND(AVG(LISTING_PRICE), 2) AVG_LISTING\n" +
                                    "FROM LISTING JOIN MARKETPLACE\n" +
                                    "ON LISTING.MARKETPLACE = MARKETPLACE.ID\n" +
                                    "GROUP BY MARKETPLACE_NAME;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);

        ArrayList<SummaryDto> summaryDtos = new ArrayList<SummaryDto>();
        while (resultSet.next()) {
            SummaryDto summaryDto = new SummaryDto();
            summaryDto.setMarketplaceName(resultSet.getString("MARKETPLACE_NAME"));
            summaryDto.setListingCount(resultSet.getInt("LISTING_COUNT"));
            summaryDto.setSumListingPrice(resultSet.getDouble("SUM_LISTING"));
            summaryDto.setAverageListingPrice(resultSet.getDouble("AVG_LISTING"));
            summaryDtos.add(summaryDto);
        }
        return summaryDtos;
    }

    public TopListerDto getTopLister() throws SQLException {
        final String QUERY_STRING = "SELECT SUM(LISTING_PRICE) SUMPRICE, OWNER_EMAIL_ADDRESS FROM LISTING GROUP BY OWNER_EMAIL_ADDRESS ORDER BY SUMPRICE DESC LIMIT 1;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);

        TopListerDto topListerDto = new TopListerDto();
        if (resultSet.next()) {
            topListerDto.setSumListingPrice(resultSet.getDouble("SUMPRICE"));
            topListerDto.setEmailAddress(resultSet.getString("OWNER_EMAIL_ADDRESS"));
        }

        return topListerDto;
    }

    public List<MarketplaceMontlySummaryDto> getMarketplaceMonthlySummaryByName(String name) throws SQLException {
        final String QUERY_STRING = "SELECT MONTH(UPLOAD_TIME) MONTH_NO, " +
                                            "MONTHNAME(UPLOAD_TIME) MONTH_NAME, " +
                                            "SUM(LISTING_PRICE) SUM_PRICE, " +
                                            "ROUND(AVG(LISTING_PRICE), 2) AVG_PRICE, " +
                                            "COUNT(LISTING.ID) LISTING_COUNT, " +
                                            "MARKETPLACE.MARKETPLACE_NAME MARKETPLACE_NAME\n" +
                                    "FROM LISTING JOIN MARKETPLACE\n" +
                                    "ON MARKETPLACE = MARKETPLACE.ID\n" +
                                    "WHERE MARKETPLACE.MARKETPLACE_NAME = '" + name + "'\n" +
                                    "GROUP BY MONTH_NAME\n" +
                                    "ORDER BY MONTH_NO";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);

        ArrayList<MarketplaceMontlySummaryDto> marketplaceMontlySummaryDtos = new ArrayList<MarketplaceMontlySummaryDto>();
        while (resultSet.next()) {
            MarketplaceMontlySummaryDto marketplaceMontlySummaryDto = new MarketplaceMontlySummaryDto();
            marketplaceMontlySummaryDto.setMonthOrderNumber(resultSet.getInt("MONTH_NO"));
            marketplaceMontlySummaryDto.setMonthName(resultSet.getString("MONTH_NAME"));
            marketplaceMontlySummaryDto.setSumListingPrice(resultSet.getDouble("SUM_PRICE"));
            marketplaceMontlySummaryDto.setAverageListingPrice(resultSet.getDouble("AVG_PRICE"));
            marketplaceMontlySummaryDto.setListingCount(resultSet.getInt("LISTING_COUNT"));
            marketplaceMontlySummaryDto.setMarketplaceName(resultSet.getString("MARKETPLACE_NAME"));
            marketplaceMontlySummaryDtos.add(marketplaceMontlySummaryDto);
        }

        return marketplaceMontlySummaryDtos;
    }

    public List<AmazonEbayMonthlySummaryDto> getAmazonEbayMonthlySummary() throws SQLException {
        final String QUERY_STRING = "SELECT MONTH(A.UPLOAD_TIME) MONTH_NO, " +
                                            "MONTHNAME(A.UPLOAD_TIME) MONTH_NAME, " +
                                            "ROUND(SUM(A.LISTING_PRICE), 2) AS SUM_LISTING_EBAY, " +
                                            "ROUND(AVG(A.LISTING_PRICE), 2) AS AVG_PRICE_EBAY, " +
                                            "ROUND(SUM(B.LISTING_PRICE), 2) AS SUM_LISTING_AMAZON, " +
                                            "ROUND(AVG(B.LISTING_PRICE),2) AS AVG_LISTING_AMAZON\n" +
                                    "FROM LISTING A LEFT JOIN LISTING B \n" +
                                    "ON MONTH(A.UPLOAD_TIME) = MONTH(B.UPLOAD_TIME)\n" +
                                    "WHERE (A.MARKETPLACE = 1) AND (B.MARKETPLACE = 2)\n" +
                                    "GROUP BY MONTH_NAME \n" +
                                    "ORDER BY MONTH_NO ASC;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);

        ArrayList<AmazonEbayMonthlySummaryDto> amazonEbayMonthlySummaryDtos = new ArrayList<AmazonEbayMonthlySummaryDto>();
        while (resultSet.next()) {
            AmazonEbayMonthlySummaryDto amazonEbayMonthlySummaryDto = new AmazonEbayMonthlySummaryDto();
            amazonEbayMonthlySummaryDto.setMonthNumber(resultSet.getInt("MONTH_NO"));
            amazonEbayMonthlySummaryDto.setMonthName(resultSet.getString("MONTH_NAME"));
            amazonEbayMonthlySummaryDto.setEbaySumListing(resultSet.getDouble("SUM_LISTING_EBAY"));
            amazonEbayMonthlySummaryDto.setEbayAverageListing(resultSet.getDouble("AVG_PRICE_EBAY"));
            amazonEbayMonthlySummaryDto.setAmazonSumListing(resultSet.getDouble("SUM_LISTING_AMAZON"));
            amazonEbayMonthlySummaryDto.setAmazonAverageListing(resultSet.getDouble("AVG_LISTING_AMAZON"));

            amazonEbayMonthlySummaryDtos.add(amazonEbayMonthlySummaryDto);
        }

        return amazonEbayMonthlySummaryDtos;
    }

    public List<MonthlyTopListerDto> getMonthlyTopListers() throws SQLException {
        final String QUERY_STRING = "SELECT MONTH(UPLOAD_TIME) MONTH_NUM, MONTHNAME(UPLOAD_TIME) MONTH_NAME , ROUND(MAX(LISTING_PRICE), 2) SUM_PRICE, OWNER_EMAIL_ADDRESS " +
                                    "FROM LISTING GROUP BY MONTH_NAME ORDER BY MONTH_NUM ASC";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_STRING);

        ArrayList<MonthlyTopListerDto> monthlyTopListerDtos = new ArrayList<MonthlyTopListerDto>();
        while (resultSet.next()) {
            MonthlyTopListerDto monthlyTopListerDto = new MonthlyTopListerDto();
            monthlyTopListerDto.setMonthNumber(resultSet.getInt("MONTH_NUM"));
            monthlyTopListerDto.setMonthName(resultSet.getString("MONTH_NAME"));
            monthlyTopListerDto.setSumListingPrice(resultSet.getDouble("SUM_PRICE"));
            monthlyTopListerDto.setEmailAddress(resultSet.getString("OWNER_EMAIL_ADDRESS"));
            monthlyTopListerDtos.add(monthlyTopListerDto);
        }

        return monthlyTopListerDtos;
    }


    public void finalizeOperations() throws SQLException {
        connection.close();
        connection = null;
        DriverManager.deregisterDriver(driver);
        driver = null;
    }
}
