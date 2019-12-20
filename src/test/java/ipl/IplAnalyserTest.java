package ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IplAnalyserTest {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/Wrong_csvFile.csv";
    ;
    private static final String INCORRECT_DELIMITER_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectDelimiter_IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_HEADERS_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectHeaders_IPL2019FactsheetMostRuns.csv";
    private static final String IOISSUE_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IO_ISSUE_IPL2019FactsheetMostRuns.csv";
    private static final String WICKETS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private String SAMPLE_6s_AND_4s = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/Sample_MAX_4s_AND_6s.csv";


    @Test
    public void givenMostRunsFactSheet_ReturnExactNumberOfPlayers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            long numberOfPlayers = iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            Assert.assertEquals(100, numberOfPlayers);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithWrongFile_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WRONG_RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectDelimiter_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(INCORRECT_DELIMITER_RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectHeaders_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(INCORRECT_HEADERS_RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithoutReadWritePermission_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(IOISSUE_RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithLowestBattingAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.BATING_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithHighestBattingAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.BATING_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithLowestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithHighestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumSixesFours_ShouldReturnPlayerWithLowestSixesFours() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(SAMPLE_6s_AND_4s, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_SIXES_AND_FOURS);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Tim Southee", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumSixesFours_ShouldReturnPlayerWithHighestSixesFours() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(SAMPLE_6s_AND_4s, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_SIXES_AND_FOURS);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Faf du Plessis", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumStrikingRate_ShouldReturnPlayerWithLowestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATE_WTIH_MAX_SIXES_FOURS);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Shakib Al Hasan", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumStrikingRate_ShouldReturnPlayerWithHighestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATE_WTIH_MAX_SIXES_FOURS);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Andre Russell", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithStrikingRate_ShouldReturnPlayerWithLowestAverageWithStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Tim Southee", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithStrikingRate_ShouldReturnPlayerWithHighestAverageWithStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithMaximumRunsWithBestAverage_ShouldReturnPlayerWithLowestAverageWithRuns() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.RUNS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Tim Southee", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithMaximumRunsWithBestAverage_ShouldReturnPlayerWithHighestAverageWithRuns() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(RUNS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBowlingAverage_ShouldReturnPlayerWithLowestBowlingAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Suresh Raina", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBowlingAverage_ShouldReturnPlayerWithHighestBowlingAverageW() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnStrikingRates_ShouldReturnPlayerWithLowestStrikingRates() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Suresh Raina", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnStrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnEconomy_ShouldReturnPlayerWithLowestEconomy() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.ECONOMY);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Shivam Dube", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnEconomy_ShouldReturnPlayerWithHighestEconomy() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.LoadFactSheetCsv(WICKETS_FACTSHEET, IplAnalyser.playerTypes.WICKETS);
            String sortedIplData = iplAnalyser.getIplSortedData(FeatureEnum.ECONOMY);
            IplBatsManCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsManCSV[].class);
            Assert.assertEquals("Ben Cutting", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

}
