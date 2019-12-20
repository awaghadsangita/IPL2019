package iplcomprator;

import ipl.IplDao;

import java.util.Comparator;

public class MaximumSixsAndFoursComparator implements Comparator<IplDao> {
    @Override
    public int compare(IplDao iblDaoObject1, IplDao iblDaoObject2) {
        return ((iblDaoObject1.sixes*6)+(iblDaoObject1.fours*4)) - ((iblDaoObject2.sixes*6)+(iblDaoObject2.fours*4));
    }
}
