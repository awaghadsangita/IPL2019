package ipl;

import censusanalyser.CensusAnalyserException;
import censusanalyser.IndiaCensusCSV;
import censusanalyser.IndiaStateCodeCSV;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplAnalyser {
    Map<String, IplMostRunsCSV> iplMap = null;

    public IplAnalyser() {
        this.iplMap = new HashMap<>();
    }

    public int loadMostRunsFactSheet(String csvFilePath) throws IplAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostRunsCSV.class);
            Iterable<IplMostRunsCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplMostRunsCSV.class::cast)
                    .forEach(iplCSV -> iplMap.put(iplCSV.playerName, new IplMostRunsCSV(iplCSV)));
            return iplMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(),
                    IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }catch (RuntimeException e){
            throw new IplAnalyserException("Error capturing CSV header!",
                    IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE);
        }
    }
}
