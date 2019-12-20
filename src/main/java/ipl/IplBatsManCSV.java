package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplBatsManCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;
    @CsvBindByName(column = "Mat")
    public String match;
    @CsvBindByName(column = "SR")
    public String strikingRate;
    @CsvBindByName(column = "Runs")
    public String totalRuns;
    @CsvBindByName(column = "Avg")
    public String average;
    @CsvBindByName(column = "100")
    public String century;
    @CsvBindByName(column = "50")
    public String halfCentury;
    @CsvBindByName(column = "4s")
    public String fours;
    @CsvBindByName(column = "6s")
    public String sixes;

    public IplBatsManCSV() {
    }

    public IplBatsManCSV(IplDao iplDAO) {
    }
}
