package ipl;

import java.util.Map;

public class IplBatsmanAdapter extends IplAdapter {

    @Override
    public Map<String, IplDao> loadIplCsvData(String... iplCsvFile) throws IplAnalyserException {
        Map<String, IplDao> iplCsvDataMap = super.loadIplCsvData(IplBatsmanCSV.class, iplCsvFile[0]);
        return iplCsvDataMap;
    }
}
