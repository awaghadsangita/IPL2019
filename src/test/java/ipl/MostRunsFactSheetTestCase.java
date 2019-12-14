package ipl;

import org.junit.Assert;
import org.junit.Test;

public class MostRunsFactSheetTestCase {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/Wrong_csvFile.csv";;

    @Test
    public void givenMostRunsFactSheet_ReturnExactNumberOfPlayers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            int numberOfPlayers = iplAnalyser.loadMostRunsFactSheet(RUNS_FACTSHEET);
            Assert.assertEquals(100, numberOfPlayers);
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
}
