package ipl;

import java.util.Map;

public class IplBatsManAdapter extends IplAdapter {

    @Override
    public Map<String, IplDAO> loadIplCsvData(String iplCsvFile) throws IplAnalyserException {
        Map<String, IplDAO> iplCsvDataMap = super.loadIplCsvData(IplBatsManCSV.class, iplCsvFile);
        return iplCsvDataMap;
    }
}
