package iplcomprator;

import ipl.IplDAO;

import java.util.Comparator;

public class MaximumSixsAndFoursComparator implements Comparator<IplDAO> {
    @Override
    public int compare(IplDAO iblDaoObject1, IplDAO iblDaoObject2) {
        return ((iblDaoObject1.sixes*6)+(iblDaoObject1.fours*4)) - ((iblDaoObject2.sixes*6)+(iblDaoObject2.fours*4));
    }
}
