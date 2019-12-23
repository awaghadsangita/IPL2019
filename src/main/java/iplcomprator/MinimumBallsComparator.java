package iplcomprator;

import ipl.IplDao;

import java.util.Comparator;

public class MinimumBallsComparator implements Comparator<IplDao> {
    @Override
    public int compare(IplDao iblDaoObject1, IplDao iblDaoObject2) {
        return (iblDaoObject2.sixes + iblDaoObject2.fours) - (iblDaoObject1.sixes + iblDaoObject1.fours);
    }
}
