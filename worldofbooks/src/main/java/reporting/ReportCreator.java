package reporting;

import dto.MarketplaceMontlySummaryDto;
import dto.MonthlyTopListerDto;
import dto.SummaryDto;
import dto.TopListerDto;
import jdbc.DatabaseOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReportCreator {
    private static ReportCreator reportCreator = null;
    private static DatabaseOperations databaseOperations = null;
    private static String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static final String AMAZON_NAME="AMAZON";
    private static final String EBAY_NAME = "EBAY";

    private ReportCreator() {

    }

    public static ReportCreator getInstance(DatabaseOperations databaseOperations) throws SQLException {
        if (databaseOperations == null) databaseOperations = DatabaseOperations.getInstance();
        ReportCreator.databaseOperations = databaseOperations;

        if (reportCreator == null) reportCreator = new ReportCreator();

        return reportCreator;
    }

    public Report constructReport() throws SQLException {
        Report report = new Report();

        report = fillGeneralReportData(report);
        report = fillMonthlyStatistics(report);
        return report;
    }

    private static Report fillMonthlyStatistics(Report report) throws SQLException {
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<MonthlyReport>();
        Arrays.stream(monthNames).forEach(monthName -> {
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.setMonthName(monthName);
            monthlyReports.add(monthlyReport);
        });

        report.setMonthlyReports(monthlyReports);

        for (MarketplaceMontlySummaryDto marketplaceMontlySummaryDto:  databaseOperations.getMarketplaceMonthlySummaryByName(EBAY_NAME)) {
            MonthlyReport monthlyReport = monthlyReports.get(marketplaceMontlySummaryDto.getMonthOrderNumber()-1);
            monthlyReport.setTotalEbayListingPrice(marketplaceMontlySummaryDto.getSumListingPrice());
            monthlyReport.setTotalEbayListingCount(marketplaceMontlySummaryDto.getListingCount());
            monthlyReport.setAverageEbayListingPrice(marketplaceMontlySummaryDto.getAverageListingPrice());
        }

        for (MarketplaceMontlySummaryDto marketplaceMontlySummaryDto:  databaseOperations.getMarketplaceMonthlySummaryByName(EBAY_NAME)) {
            MonthlyReport monthlyReport = monthlyReports.get(marketplaceMontlySummaryDto.getMonthOrderNumber()-1);
            monthlyReport.setTotalAmazonListingPrice(marketplaceMontlySummaryDto.getSumListingPrice());
            monthlyReport.setTotalAmazonListingCount(marketplaceMontlySummaryDto.getListingCount());
            monthlyReport.setAverageAmazonListingPrice(marketplaceMontlySummaryDto.getAverageListingPrice());
        }

        for (MonthlyTopListerDto monthlyTopListerDto:  databaseOperations.getMonthlyTopListers()) {
            MonthlyReport monthlyReport = monthlyReports.get(monthlyTopListerDto.getMonthNumber()-1);
            monthlyReport.setBestListerEmailAddress(monthlyTopListerDto.getEmailAddress());
        }

        return report;
    }

    private static Report fillGeneralReportData(Report report) throws SQLException {
        Integer totalListingCount = 0;

        for (SummaryDto summaryDto : databaseOperations.getTotalSummary()) {
            if (summaryDto.getMarketplaceName().toUpperCase().equals(EBAY_NAME)) {
                report.setTotalEbayListingCount(summaryDto.getListingCount());
                report.setTotalEbayListingPrice(summaryDto.getSumListingPrice());
                report.setAverageEbayListingPrice(summaryDto.getAverageListingPrice());
            } else if (summaryDto.getMarketplaceName().toUpperCase().equals(AMAZON_NAME)) {
                report.setTotalAmazonListingCount((summaryDto.getListingCount()));
                report.setTotalAmazonListingPrice(summaryDto.getSumListingPrice());
                report.setAverageAmazonListingPrice(summaryDto.getAverageListingPrice());
            }
            totalListingCount += summaryDto.getListingCount();
        }

        report.setTotalListingCount(totalListingCount);

        TopListerDto topListerDto = databaseOperations.getTopLister();
        report.setBestListerEmailAddress(topListerDto.getEmailAddress());

        return report;
    };

}
