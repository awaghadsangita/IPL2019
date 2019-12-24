package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplBatsmanCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;
    @CsvBindByName(column = "Mat")
    public String match;
    @CsvBindByName(column = "SR")
    public String strikingRate;
    @CsvBindByName(column = "Runs")
    public String totalRuns;
    @CsvBindByName(column = "Avg")
    public String batingAverage;
    @CsvBindByName(column = "100")
    public String century;
    @CsvBindByName(column = "50")
    public String halfCentury;
    @CsvBindByName(column = "4s")
    public String fours;
    @CsvBindByName(column = "6s")
    public String sixes;

    public IplBatsmanCSV() {
    }

    public IplBatsmanCSV(String playerName, String match, String strikingRate, String totalRuns, String batingAverage, String century, String halfCentury, String fours, String sixes) {
        this.playerName = playerName;
        this.match = match;
        this.strikingRate = strikingRate;
        this.totalRuns = totalRuns;
        this.batingAverage = batingAverage;
        this.century = century;
        this.halfCentury = halfCentury;
        this.fours = fours;
        this.sixes = sixes;
    }
}
