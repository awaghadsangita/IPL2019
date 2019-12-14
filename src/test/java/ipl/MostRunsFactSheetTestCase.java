package ipl;

import org.junit.Assert;
import org.junit.Test;

public class MostRunsFactSheetTestCase {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMostRunsFactSheet_returnExactNumberOfPlayers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            int numberOfPlayers = iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            Assert.assertEquals(100, numberOfPlayers);
        } catch (IplAnalyserException e) {

        }
    }
}
