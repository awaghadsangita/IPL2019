package ipl;

import csvbuilder.CSVBuilderFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Map;

public class IplBatsmanAdapterTest {
    private static final String RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/Wrong_csvFile.csv";
    private static final String INCORRECT_DELIMITER_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectDelimiter_IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_HEADERS_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IncorrectHeaders_IPL2019FactsheetMostRuns.csv";
    private static final String IOISSUE_RUNS_FACTSHEET = "/home/admin1/IdeaProjects/IPL2019/src/test/resources/IO_ISSUE_IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMostRunsFactSheet_WhenLoaded_ShouldReturnMapOfExactSize() {
        try {
            IplBatsmanAdapter iplBatsmanAdapter = new IplBatsmanAdapter();
            Map<String, IplDao> iplDaoMap = iplBatsmanAdapter.loadIplCsvData(RUNS_FACTSHEET);
            Assert.assertEquals(100,iplDaoMap.size());
        } catch (IplAnalyserException e) {
        }
    }
    @Test
    public void givenMostRunsFactSheet_ButWithWrongFile_ShouldThrowException() {
        try {
            IplBatsmanAdapter iplBatsmanAdapter = new IplBatsmanAdapter();
            Map<String, IplDao> iplDaoMap = iplBatsmanAdapter.loadIplCsvData(WRONG_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectDelimiter_ShouldThrowException() {
        try {
            IplBatsmanAdapter iplBatsmanAdapter = new IplBatsmanAdapter();
            Map<String, IplDao> iplDaoMap = iplBatsmanAdapter.loadIplCsvData(INCORRECT_DELIMITER_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithIncorrectHeaders_ShouldThrowException() {
        try {
            IplBatsmanAdapter iplBatsmanAdapter = new IplBatsmanAdapter();
            Map<String, IplDao> iplDaoMap = iplBatsmanAdapter.loadIplCsvData(INCORRECT_HEADERS_RUNS_FACTSHEET);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE, e.type);
        }
    }

    @Test
    public void givenMostRunsFactSheet_ButWithFileWithoutReadWritePermission_ShouldThrowException() {
        try {
            IplBatsmanAdapter iplBatsmanAdapter = new IplBatsmanAdapter();
            Map<String, IplDao> iplDaoMap = iplBatsmanAdapter.loadIplCsvData(IOISSUE_RUNS_FACTSHEET);
                   } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }
}
