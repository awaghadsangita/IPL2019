package ipl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IplBatmanBowlerAdapterTest {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WICKETS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenMostRunsFactSheet_ReturnExactNumberOfPlayers() {
        try {
            IplBatmanBowlerAdapter iplBatmanBowlerAdapter = new IplBatmanBowlerAdapter();
            Map<String, IplDao> iplDaoMap = iplBatmanBowlerAdapter.loadIplCsvData(RUNS_FACTSHEET,WICKETS_FACTSHEET);
            Assert.assertEquals(100,iplDaoMap.size());
        } catch (IplAnalyserException e) {
        }
    }
}
