package ipl;

import com.google.gson.Gson;
import iplcomprator.MaximumSixsAndFoursComparator;
import iplcomprator.MinimumBallsComparator;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class IplAnalyser {
    Map<String, IplDAO> iplMap = null;
    Map<FeatureEnum, Comparator<IplDAO>> featureComparator = null;

    enum playerTypes {WICKETS, RUNS};

    public IplAnalyser() {
        this.iplMap = new HashMap<>();
        this.featureComparator = new HashMap<>();
        featureComparator.put(FeatureEnum.BATING_AVERAGE, Comparator.comparing(ipl -> ipl.average));
        featureComparator.put(FeatureEnum.STRIKING_RATES, Comparator.comparing(ipl -> ipl.strikingRate));
        Comparator<IplDAO> strikingRateComparator = Comparator.comparing(ipl -> ipl.strikingRate);
        Comparator<IplDAO> maximumSixesAndFoursComparator = (new MaximumSixsAndFoursComparator().thenComparing(new MinimumBallsComparator()));
        Comparator<IplDAO> strikingRateWithMaxSixesAndFoursComparator = maximumSixesAndFoursComparator.thenComparing(strikingRateComparator);
        Comparator<IplDAO> averageComparator = Comparator.comparing(ipl -> ipl.average);
        Comparator<IplDAO> averageWithStrikingRateComparator = averageComparator.thenComparing(strikingRateComparator);
        featureComparator.put(FeatureEnum.STRIKING_RATES_WTIH_MAX_SIXES_FOURS, strikingRateWithMaxSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.MAX_SIXES_AND_FOURS, maximumSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE, averageWithStrikingRateComparator);
        featureComparator.put(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE, averageComparator.thenComparing(ipl -> ipl.totalRuns));
    }

    public int LoadFactSheetCsv(String csvFilePath, playerTypes playerType) throws IplAnalyserException {
        IplAdapter iplAdapter = IplAdapterFactory.getIplObject(playerType);
        this.iplMap=iplAdapter.loadIplCsvData(csvFilePath);
        return iplMap.size();
    }

    public String getIplSortedData(FeatureEnum field) throws IplAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IplAnalyserException("no ipl data", IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ArrayList iplList = iplMap.values().stream()
                .sorted(this.featureComparator.get(field))
                .collect(toCollection(ArrayList::new));
        String sortedIplJson = new Gson().toJson(iplList);
        return sortedIplJson;
    }
}
