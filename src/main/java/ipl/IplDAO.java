package ipl;

import com.opencsv.bean.CsvBindByName;

public class IplDAO {

    public String playerName;
    public int match;
    public double average;
    public double strikingRate;
    public int totalRuns;
    public int century;
    public int halfCentury;
    public int fours;
    public int sixes;

    public IplDAO(IplMostRunsCSV iplMostRunsCSV) {
        this.playerName = iplMostRunsCSV.playerName;
        this.match = Integer.parseInt(String.valueOf(iplMostRunsCSV.match));
        if (iplMostRunsCSV.average.contains("-")) {
            this.average = 0;
        }
        if (!iplMostRunsCSV.average.contains("-")) {
            this.average = Double.parseDouble(iplMostRunsCSV.average);
        }
        this.strikingRate = Double.parseDouble(iplMostRunsCSV.strikingRate);
        this.totalRuns=Integer.parseInt(String.valueOf(iplMostRunsCSV.totalRuns));
        this.century = Integer.parseInt(String.valueOf(iplMostRunsCSV.halfCentury));
        this.halfCentury = Integer.parseInt(String.valueOf(iplMostRunsCSV.halfCentury));
        this.sixes = Integer.parseInt(String.valueOf(iplMostRunsCSV.sixes));
        this.fours = Integer.parseInt(String.valueOf(iplMostRunsCSV.fours));
    }
}
