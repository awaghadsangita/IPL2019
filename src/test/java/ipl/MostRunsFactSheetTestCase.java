package ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class MostRunsFactSheetTestCase {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/Wrong_csvFile.csv";
    ;
    private static final String INCORRECT_DELIMITER_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectDelimiter_IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_HEADERS_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectHeaders_IPL2019FactsheetMostRuns.csv";
    private static final String IOISSUE_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IO_ISSUE_IPL2019FactsheetMostRuns.csv";


    @Test
    public void givenMostRunsFactSheet_ReturnExactNumberOfPlayers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            long numberOfPlayers = iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            Assert.assertEquals(101, numberOfPlayers);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithWrongFile_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(WRONG_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectDelimiter_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(INCORRECT_DELIMITER_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectHeaders_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(INCORRECT_HEADERS_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithoutReadWritePermission_ShouldThrowException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(IOISSUE_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithLowestBattingAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            String sortedIplData = iplAnalyser.getBattingAverageSortedData(FeatureEnum.BATING_AVERAGE);
            IplMostRunsCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplMostRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithHighestBattingAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            String sortedIplData = iplAnalyser.getBattingAverageSortedData(FeatureEnum.BATING_AVERAGE);
            IplMostRunsCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplMostRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithLowestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            String sortedIplData = iplAnalyser.getBattingAverageSortedData(FeatureEnum.STRIKING_RATES);
            IplMostRunsCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplMostRunsCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithHighestStrikingRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            String sortedIplData = iplAnalyser.getBattingAverageSortedData(FeatureEnum.STRIKING_RATES);
            IplMostRunsCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplMostRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

}
