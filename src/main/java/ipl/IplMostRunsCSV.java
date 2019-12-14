package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplMostRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;
    @CsvBindByName(column = "Mat")
    public String match;
    @CsvBindByName(column = "Avg")
    public int average;
    @CsvBindByName(column = "100")
    public int century;
    @CsvBindByName(column = "50")
    public int halfCentury;
    @CsvBindByName(column = "4s")
    public int fours;
    @CsvBindByName(column = "6s")
    public int sixes;

    public IplMostRunsCSV(IplMostRunsCSV iplCSV) {
        this.playerName=iplCSV.playerName;
        this.match=iplCSV.match;
        this.average=iplCSV.average;
        this.century=iplCSV.century;
        this.halfCentury=iplCSV.halfCentury;
        this.fours=iplCSV.fours;
        this.sixes=iplCSV.sixes;
    }

    public IplMostRunsCSV() {
    }
}
