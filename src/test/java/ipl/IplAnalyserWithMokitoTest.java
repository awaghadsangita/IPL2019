package ipl;


import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//import org.powermock.api.mockito.PowerMockito;

public class IplAnalyserWithMokitoTest {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    IplAnalyser iplBatsmanAnalyser;

    @Mock
    IplBatsmanAdapter iplBatsmanObject;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void runOnceBeforeClass() {
        try {
            IplDao daoObjectOne = new IplDao(new IplBatsmanCSV("David Warner", "12", "143.86", "692", "56.7", "1", "8", "57", "21"));
            IplDao daoObjectTwo = new IplDao(new IplBatsmanCSV("Ms Dhoni", "15", "134.62", "416", "83.2", "0", "3", "22", "23"));
            IplDao daoObjectThree = new IplDao(new IplBatsmanCSV("Ishant Sharma", "13", "333.33", "10", "25", "0", "0", "1", "1"));
            IplDao daoObjectFour = new IplDao(new IplBatsmanCSV("Andre Russell", "14", "204.81", "510", "56.66", "0", "4", "31", "52"));
            IplDao daoObjectFive = new IplDao(new IplBatsmanCSV("Hardik Pandya", "16", "191.42", "402", "44.66", "0", "1", "28", "29"));
            Map<String, IplDao> sampleBatsmanMap = new HashMap<>();
            sampleBatsmanMap.put("David Warner", daoObjectOne);
            sampleBatsmanMap.put("Ms Dhoni", daoObjectTwo);
            sampleBatsmanMap.put("Ishant Sharma", daoObjectThree);
            sampleBatsmanMap.put("Andre Russell", daoObjectFour);
            sampleBatsmanMap.put("Hardik Pandya", daoObjectFive);
            iplBatsmanObject = mock(IplBatsmanAdapter.class);
            when(iplBatsmanObject.loadIplCsvData(RUNS_FACTSHEET)).thenReturn(sampleBatsmanMap);
            iplBatsmanAnalyser = new IplAnalyser();
            iplBatsmanAnalyser.setIplAdapter(iplBatsmanObject);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenMostRunsFactSheet_ReturnExactNumberOfPlayersOfSampleMap() {
        try {
            long numberOfPlayers = iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            Assert.assertEquals(5, numberOfPlayers);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithLowestBattingAverage() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.BATTING_AVERAGE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnBattingAverage_ShouldReturnPlayerWithHighestBattingAverage() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.BATTING_AVERAGE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ms Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithLowestStrikingRate() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ms Dhoni", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnStrikingRates_ShouldReturnPlayerWithHighestStrikingRate() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumSixesFours_ShouldReturnPlayerWithLowestSixesFours() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.MAX_SIXES_AND_FOURS);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumSixesFours_ShouldReturnPlayerWithHighestSixesFours() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.MAX_SIXES_AND_FOURS);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Andre Russell", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_WithSortedOnMaximumStrikingRate_ShouldReturnPlayerWithHighestStrikingRate() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATE_WTIH_MAX_SIXES_FOURS);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Andre Russell", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithStrikingRate_ShouldReturnPlayerWithHighestAverageWithStrikingRate() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BATSMAN, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Ms Dhoni", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }
}
