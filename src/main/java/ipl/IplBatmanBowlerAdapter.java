package ipl;

import java.util.Map;

public class IplBatmanBowlerAdapter extends IplAdapter {

    @Override
    public Map<String, IplDao> loadIplCsvData(String... iplCsvFile) throws IplAnalyserException {
        Map<String, IplDao> iplBatsmanDataMap = super.loadIplCsvData(IplBatsmanCSV.class, iplCsvFile[0]);
        Map<String, IplDao> iplBowlerDataMap = super.loadIplCsvData(IplBowlerCSV.class, iplCsvFile[1]);
        iplBowlerDataMap.values().stream().
                filter(iplData -> iplBatsmanDataMap.get(iplData.playerName) != null)
                .forEach(iplData->iplBatsmanDataMap.get(iplData.playerName).bowlingAverage=iplData.bowlingAverage);
        return iplBatsmanDataMap;
    }
}
