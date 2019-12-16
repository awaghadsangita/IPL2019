package ipl;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import iplcomprator.MaximumSixsAndFoursComparator;
import iplcomprator.MinimumBallsComparator;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

public class IplAnalyser {
    Map<String, IplDAO> iplMap = null;
    Map<FeatureEnum, Comparator<IplDAO>> featureComparator = null;

    public IplAnalyser() {
        this.iplMap = new HashMap<>();
        this.featureComparator = new HashMap<>();
        featureComparator.put(FeatureEnum.BATING_AVERAGE,Comparator.comparing(ipl->ipl.average));
        featureComparator.put(FeatureEnum.STRIKING_RATES, Comparator.comparing(ipl -> ipl.strikingRate));
        Comparator<IplDAO> strikingRateComparator = Comparator.comparing(ipl -> ipl.strikingRate);
        Comparator<IplDAO> maximumSixesAndFoursComparator = (new MaximumSixsAndFoursComparator().thenComparing(new MinimumBallsComparator()));
        Comparator<IplDAO> strikingRateWithMaxSixesAndFoursComparator = maximumSixesAndFoursComparator.thenComparing(strikingRateComparator);
        Comparator<IplDAO> averageComparator = Comparator.comparing(ipl->ipl.average);
        Comparator<IplDAO> averageWithStrikingRateComparator=averageComparator.thenComparing(strikingRateComparator);
        featureComparator.put(FeatureEnum.STRIKING_RATES, strikingRateWithMaxSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.MAX_SIXES_AND_FOURS, maximumSixesAndFoursComparator);
        featureComparator.put(FeatureEnum.BEST_AVERAGE_WITH_STRIKING_RATE,averageWithStrikingRateComparator);
    }

    public long loadMostRunsFactSheet(String csvFilePath) throws IplAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostRunsCSV.class);
            Iterable<IplMostRunsCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplMostRunsCSV.class::cast)
                    .forEach(iplMostRunsCSV -> iplMap.put(iplMostRunsCSV.playerName, new IplDAO(iplMostRunsCSV)));
            return iplMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(),
                    IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IplAnalyserException("Error capturing CSV header!",
                    IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE);
        }
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
