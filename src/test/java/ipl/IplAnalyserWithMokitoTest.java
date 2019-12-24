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
    private static final String WICKETS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostWkts.csv";
    IplAnalyser iplBatsmanAnalyser;
    IplAnalyser iplBowlerAnalyser;

    @Mock
    IplBatsmanAdapter iplBatsmanObject;
    IplBowlerAdapter iplBowlerObject;

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
            IplDao daoBowlerObjectOne = new IplDao(new IplBowlerCSV("Andre Russell", "14", "16.45", "287", "11", "26.09", "9.51", "0", "0"));
            IplDao daoBolwerObjectTwo = new IplDao(new IplBowlerCSV("Ms Dhoni", "17", "14.84", "431", "26", "16.57", "6.69", "2", "0"));
            IplDao daoBowlerObjectThree = new IplDao(new IplBowlerCSV("David Miller", "10", "0", "213", "13", "164", "0", "1", "19"));
            IplDao daoBowlerObjectFour = new IplDao(new IplBowlerCSV("Krishnappa Gowtham", "7", "120", "510", "1", "166", "8.3", "0", "0"));
            Map<String, IplDao> sampleBowlerMap = new HashMap<>();
            sampleBowlerMap.put("Andre Russell", daoBowlerObjectOne);
            sampleBowlerMap.put("Ms Dhoni", daoBolwerObjectTwo);
            sampleBowlerMap.put("David Miller", daoBowlerObjectThree);
            sampleBowlerMap.put("Krishnappa Gowtham", daoBowlerObjectFour);
            iplBowlerObject = mock(IplBowlerAdapter.class);
            when(iplBowlerObject.loadIplCsvData(WICKETS_FACTSHEET)).thenReturn(sampleBowlerMap);
            iplBowlerAnalyser = new IplAnalyser();
            iplBowlerAnalyser.setIplAdapter(iplBowlerObject);
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

    @Test
    public void givenFactSheetOFMostRuns_SortedOnAverageWithMaximumRunsWithBestAverage_ShouldReturnPlayerWithHighestAverageWithRuns() {
        try {
            iplBatsmanAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, RUNS_FACTSHEET);
            String sortedIplData = iplBatsmanAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("David Warner", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBowlingAverage_ShouldReturnPlayerWithHighestBowlingAverage() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBowlerStrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnStrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATES);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnEconomy_ShouldReturnPlayerWithHighestEconomy() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.ECONOMY);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Andre Russell", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBestStrikingRateWithFoursAndFivesWickets_ShouldReturnPlayerWithHighestSortedFeature() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.STRIKING_RATE_WITH_MAX_FOUR_AND_FIVE_WICKETS);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnBowlingAverageWithStrikingRate_ShouldReturnPlayerWithHighestSortedFeature() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.BOWLING_AVERAGE_WITH_STRIKING_RATE);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenFactSheetOFMostWickets_SortedOnWicketsWithBowlingAverage_ShouldReturnPlayerWithHighestSortedFeature() {
        try {
            iplBowlerAnalyser.loadFactSheetCsv(IplAnalyser.playerTypes.BOWLER, WICKETS_FACTSHEET);
            String sortedIplData = iplBowlerAnalyser.getIplSortedData(FeatureEnum.WICKETS_WITH_BOWLING);
            IplBatsmanCSV[] iplCSV = new Gson().fromJson(sortedIplData, IplBatsmanCSV[].class);
            Assert.assertEquals("David Miller", iplCSV[iplCSV.length - 1].playerName);
        } catch (IplAnalyserException e) {
        }
    }

}
