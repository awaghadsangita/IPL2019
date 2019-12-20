package ipl;

import java.util.Map;

public class IplBowlerAdapter extends IplAdapter {
    @Override
    public Map<String, IplDAO> loadIplCsvData(String iplCsvFile) throws IplAnalyserException {
        Map<String, ipl.IplDAO> iplCsvData = super.loadIplCsvData(IplBowlerCSV.class, iplCsvFile);
        return iplCsvData;
    }
}
