package ipl;

import com.google.gson.Gson;
import iplcomprator.BattingBowlingAverageComparator;
import iplcomprator.MaximumSixsAndFoursComparator;
import iplcomprator.MinimumBallsComparator;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class IplAnalyser {
    Map<String, IplDao> iplMap = null;
    Map<FeatureEnum, Comparator<IplDao>> featureComparator = null;

    enum playerTypes {BOWLER, BATSMAN,MERGE};

    public IplAnalyser() {
        this.iplMap = new HashMap<>();
        this.featureComparator = new HashMap<>();
        Comparator<IplDao> battingAverageComparator = Comparator.comparing(ipl -> ipl.battingAverage);
        Comparator<IplDao> bowlingAverageComparator = Comparator.comparing(ipl -> ipl.bowlingAverage);
        featureComparator.put(FeatureEnum.BATTING_AVERAGE,battingAverageComparator);
        featureComparator.put(FeatureEnum.STRIKING_RATES, Comparator.comparing(ipl -> ipl.strikingRate));
        Comparator<IplDao> strikingRateComparator = Comparator.comparing(ipl -> ipl.strikingRate);
        Comparator<IplDao> maximumSixesAndFoursComparator = (new MaximumSixsAndFoursComparator().thenComparing(new MinimumBallsComparator()));
        Comparator<IplDao> strikingRateWithMaxSixesAndFoursComparator = maximumSixesAndFoursComparator.thenComparing(strikingRateComparator);
        Comparator<IplDao> averageWithStrikingRateComparator = battingAverageComparator.thenComparing(strikingRateComparator);
        Comparator<IplDao> maximumFourWicketsComparator=Comparator.comparing(ipl -> ipl.fourWickets);
        Comparator<IplDao> maximumFiveWicketsComparator=Comparator.comparing(ipl -> ipl.fiveWickets);
        Comparator<IplDao> maximumFoursAndFiveWicketsComparator = maximumFiveWicketsComparator.thenComparing(maximumFourWicketsComparator);
        featureComparator.put(FeatureEnum.STRIKING_RATE_WTIH_MAX_SIXES_FOURS, strikingRateWithMaxSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.MAX_SIXES_AND_FOURS, maximumSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE, averageWithStrikingRateComparator);
        featureComparator.put(FeatureEnum.MAX_RUNS_WITH_BEST_AVERAGE, bowlingAverageComparator.thenComparing(ipl -> ipl.totalRuns));
        featureComparator.put(FeatureEnum.ECONOMY,Comparator.comparing(ipl-> ipl.economy));
        featureComparator.put(FeatureEnum.STRIKING_RATE_WITH_MAX_FOUR_AND_FIVE_WICKETS,strikingRateComparator.thenComparing(maximumFoursAndFiveWicketsComparator));
        featureComparator.put(FeatureEnum.BOWLING_AVERAGE_WITH_STRIKING_RATE,bowlingAverageComparator.thenComparing(strikingRateComparator));
        featureComparator.put(FeatureEnum.WICKETS_WITH_BOWLING,maximumFiveWicketsComparator.thenComparing(bowlingAverageComparator));
        featureComparator.put(FeatureEnum.BATTING_AVERAGE_WITH_BLOWING_AVERAGE,new BattingBowlingAverageComparator());
        Comparator<IplDao> maximumRunsComparator=Comparator.comparing(ipl -> ipl.totalRuns);
        Comparator<IplDao> maximumWicketsComparator=Comparator.comparing(ipl -> ipl.wickets);
        featureComparator.put(FeatureEnum.RUNS_WITH_WICKETS,maximumWicketsComparator.thenComparing(maximumRunsComparator));
    }

    public int LoadFactSheetCsv(playerTypes playerType,String... csvFilePath) throws IplAnalyserException {
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
