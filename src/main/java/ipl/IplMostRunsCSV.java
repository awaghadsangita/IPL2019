package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplMostRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;
    @CsvBindByName(column = "Mat")
    public String match;
    @CsvBindByName(column = "100")
    public String century;
    @CsvBindByName(column = "50")
    public String halfCentury;
    @CsvBindByName(column = "4s")
    public String fours;
    @CsvBindByName(column = "6s")
    public String sixes;

    public IplMostRunsCSV(IplMostRunsCSV iplCSV) {
    }

    public IplMostRunsCSV() {
    }
}