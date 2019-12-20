package ipl;

public class IplDAO {

    public String playerName;
    public int match;
    public double average;
    public double strikingRate;
    public int totalRuns;
    public int wickets;
    public int century;
    public int halfCentury;
    public int fours;
    public int sixes;
    public int fourWickets;
    public int fiveWickets;

    public IplDAO(IplBatsManCSV iplMostRunsCSV) {
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
    public IplDAO(IplBowlerCSV iplBowlerCSVData) {
        this.playerName = iplBowlerCSVData.playerName;
        this.match = Integer.parseInt(String.valueOf(iplBowlerCSVData.match));
        if (iplBowlerCSVData.average.contains("-")) {
            this.average = 0;
        }
        if (!iplBowlerCSVData.average.contains("-")) {
            this.average = Double.parseDouble(iplBowlerCSVData.average);
        }
        this.wickets=Integer.parseInt(String.valueOf(iplBowlerCSVData.wickets));
        if (iplBowlerCSVData.strikingRate.contains("-")) {
            this.strikingRate = 0;
        }
        if (!iplBowlerCSVData.strikingRate.contains("-")) {
            this.strikingRate = Double.parseDouble(iplBowlerCSVData.strikingRate);
        }
        this.totalRuns=Integer.parseInt(String.valueOf(iplBowlerCSVData.totalRuns));
        this.fiveWickets = Integer.parseInt(String.valueOf(iplBowlerCSVData.fiveWickets));
        this.fourWickets = Integer.parseInt(String.valueOf(iplBowlerCSVData.fourWickets));
    }
}
