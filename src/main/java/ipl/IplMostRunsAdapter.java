package ipl;

import java.util.Map;

public class IplMostRunsAdapter extends IplAdapter {

    @Override
    public Map<String, IplDAO> loadIplCsvData(String iplCsvFile) throws IplAnalyserException {
        Map<String, IplDAO> iplCsvDataMap = super.loadIplCsvData(IplMostRunsCSV.class, iplCsvFile);
        return iplCsvDataMap;
    }
}
