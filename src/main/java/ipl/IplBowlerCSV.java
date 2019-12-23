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
    public String bowlerAverage;
    @CsvBindByName(column = "Econ")
    public String economy;
    @CsvBindByName(column = "4w")
    public String fourWickets;
    @CsvBindByName(column = "5w")
    public String fiveWickets;

    public IplBowlerCSV() {
    }
}
