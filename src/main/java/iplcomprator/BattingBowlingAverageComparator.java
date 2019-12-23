package iplcomprator;

import ipl.IplDao;

import java.util.Comparator;

public class BattingBowlingAverageComparator implements Comparator<IplDao>{


    @Override
    public int compare(IplDao iplDaoOne, IplDao iplDaoTwo) {
        return (int)((iplDaoOne.battingAverage+iplDaoOne.bowlingAverage)-(iplDaoTwo.battingAverage+iplDaoTwo.bowlingAverage));
    }
}
