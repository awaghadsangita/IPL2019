package ipl;

import java.util.Map;

public class IplBatsManAdapter extends IplAdapter {

    @Override
    public Map<String, IplDao> loadIplCsvData(String iplCsvFile) throws IplAnalyserException {
        Map<String, IplDao> iplCsvDataMap = super.loadIplCsvData(IplBatsManCSV.class, iplCsvFile);
        return iplCsvDataMap;
    }
}
