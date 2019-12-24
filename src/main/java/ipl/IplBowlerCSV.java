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

    public IplBowlerCSV(String playerName, String match, String strikingRate, String totalRuns, String wickets, String bowlerAverage, String economy, String fourWickets, String fiveWickets) {
        this.playerName = playerName;
        this.match = match;
        this.strikingRate = strikingRate;
        this.totalRuns = totalRuns;
        this.wickets = wickets;
        this.bowlerAverage = bowlerAverage;
        this.economy = economy;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
    }
}
