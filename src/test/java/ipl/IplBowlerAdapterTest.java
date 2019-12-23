package ipl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IplBowlerAdapterTest {
    private static final String WICKETS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostWkts.csv";
    @Test
    public void givenMostRunsFactSheet_WhenLoaded_ShouldReturnMapOfExactSize() {
        try {
            IplBowlerAdapter iplBowlerAdapter = new IplBowlerAdapter();
            Map<String, IplDao> iplDaoMap = iplBowlerAdapter.loadIplCsvData(WICKETS_FACTSHEET);
            Assert.assertEquals(100,iplDaoMap.size());
        } catch (IplAnalyserException e) {
        }
    }
}
