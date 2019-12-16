package iplcomprator;

import ipl.IplDAO;

import java.util.Comparator;

public class MinimumBallsComparator implements Comparator<IplDAO> {
    @Override
    public int compare(IplDAO iblDaoObject1, IplDAO iblDaoObject2) {
        return (iblDaoObject2.sixes + iblDaoObject2.fours)-(iblDaoObject1.sixes + iblDaoObject1.fours) ;
    }
}
