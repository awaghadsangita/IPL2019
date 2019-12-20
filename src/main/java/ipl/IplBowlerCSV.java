package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplBowlerCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;
    @CsvBindByName(column = "Mat")
    public String match;
    @CsvBindByName(column = "SR")
    public String strikingRate;
    @CsvBindByName(column = "Runs")
    public String totalRuns;
    @CsvBindByName(column = "Wkts")
    public String wickets;
    @CsvBindByName(column = "Avg")
    public String average;
    @CsvBindByName(column = "4s")
    public String fours;
    @CsvBindByName(column = "5s")
    public String sixes;

    public IplBowlerCSV() {
    }

    public IplBowlerCSV(IplDAO iplDAO) {
    }
}
