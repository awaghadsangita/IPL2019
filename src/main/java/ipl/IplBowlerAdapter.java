package ipl;

import java.util.Map;

public class IplBowlerAdapter extends IplAdapter {
    @Override
    public Map<String, IplDao> loadIplCsvData(String iplCsvFile) throws IplAnalyserException {
        Map<String, IplDao> iplCsvData = super.loadIplCsvData(IplBowlerCSV.class, iplCsvFile);
        return iplCsvData;
    }
}
