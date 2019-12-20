package ipl;

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

public abstract class IplAdapter {
    public abstract Map<String, IplDAO> loadIplCsvData(String iplCsvFile) throws IplAnalyserException;

    public <E> Map<String, IplDAO> loadIplCsvData(Class<E> iplCSVClass, String csvFilePath) throws IplAnalyserException {
        Map<String, IplDAO> iplMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (iplCSVClass.getName().equals("ipl.IplBatsManCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplBatsManCSV.class::cast)
                        .forEach(iplMostRunsCSV -> iplMap.put(iplMostRunsCSV.playerName, new IplDAO(iplMostRunsCSV)));
            } else if (iplCSVClass.getName().equals("ipl.IplBowlerCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplBowlerCSV.class::cast)
                        .forEach(iplBowlerCSV -> iplMap.put(iplBowlerCSV.playerName, new IplDAO(iplBowlerCSV)));
            }
            return iplMap;
        } catch (IOException | CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            System.out.println(e);
            throw new IplAnalyserException("Error capturing CSV header!", IplAnalyserException.ExceptionType.HEADER_CAPTURING_ISSUE);
        }
    }

}
