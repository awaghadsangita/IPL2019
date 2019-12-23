package ipl;

public class IplDao {

    public String playerName;
    public int match;
    public double battingAverage;
    public double bowlingAverage;
    public double strikingRate;
    public int totalRuns;
    public int wickets;
    public int century;
    public int halfCentury;
    public int fours;
    public int sixes;
    public int fourWickets;
    public int fiveWickets;
    public double economy;

    public IplDao(IplBatsmanCSV iplMostRunsCSV) {
        this.playerName = iplMostRunsCSV.playerName;
        this.match = Integer.parseInt(String.valueOf(iplMostRunsCSV.match));
        if (iplMostRunsCSV.batingAverage.contains("-")) {
            this.battingAverage = 0;
        }
        if (!iplMostRunsCSV.batingAverage.contains("-")) {
            this.battingAverage = Double.parseDouble(iplMostRunsCSV.batingAverage);
        }
        this.strikingRate = Double.parseDouble(iplMostRunsCSV.strikingRate);
        this.totalRuns = Integer.parseInt(String.valueOf(iplMostRunsCSV.totalRuns));
        this.century = Integer.parseInt(String.valueOf(iplMostRunsCSV.halfCentury));
        this.halfCentury = Integer.parseInt(String.valueOf(iplMostRunsCSV.halfCentury));
        this.sixes = Integer.parseInt(String.valueOf(iplMostRunsCSV.sixes));
        this.fours = Integer.parseInt(String.valueOf(iplMostRunsCSV.fours));
    }

    public IplDao(IplBowlerCSV iplBowlerCSVData) {
        this.playerName = iplBowlerCSVData.playerName;
        this.match = Integer.parseInt(String.valueOf(iplBowlerCSVData.match));
        if (iplBowlerCSVData.bowlerAverage.contains("-")) {
            this.bowlingAverage = 0;
        }
        if (!iplBowlerCSVData.bowlerAverage.contains("-")) {
            this.bowlingAverage = Double.parseDouble(iplBowlerCSVData.bowlerAverage);
        }
        this.wickets = Integer.parseInt(String.valueOf(iplBowlerCSVData.wickets));
        if (iplBowlerCSVData.strikingRate.contains("-")) {
            this.strikingRate = 0;
        }
        if (!iplBowlerCSVData.strikingRate.contains("-")) {
            this.strikingRate = Double.parseDouble(iplBowlerCSVData.strikingRate);
        }
        this.economy = Double.parseDouble(iplBowlerCSVData.economy);
        this.totalRuns = Integer.parseInt(String.valueOf(iplBowlerCSVData.totalRuns));
        this.fiveWickets = Integer.parseInt(String.valueOf(iplBowlerCSVData.fiveWickets));
        this.fourWickets = Integer.parseInt(String.valueOf(iplBowlerCSVData.fourWickets));
    }
}
