package iplcomprator;

import ipl.IplDao;

import java.util.Comparator;

public class RunsWicketsComparator implements Comparator<IplDao> {
    @Override
    public int compare(IplDao iplDao1, IplDao iplDao2) {
        return (iplDao1.wickets+iplDao1.totalRuns)-(iplDao2.wickets+iplDao2.totalRuns);
    }
}
