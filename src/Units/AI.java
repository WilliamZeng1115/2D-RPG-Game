package Units;

import Movement.PathFinding;
import Worlds.Tile;

import java.util.LinkedList;

/**
 * Created by william on 2016-12-22.
 */
public abstract class AI extends BasicUnit {

    public abstract void useMp();

    protected enum Status{friendly, neutral, aggro, fear};
    protected Status currState;
    protected LinkedList<Tile> path;
    protected double radius;
    protected float spawnX, spawnY;
    protected float targetX, targetY;

    public abstract void changeStatus(String s);


    public void useMp(int mp) {
        //stub
    }

    public static Status convertToStatus(String s) {
        if(s.equals("fd"))
            return Status.friendly;
        else if(s.equals("n"))
            return Status.neutral;
        else if(s.equals("a"))
            return Status.aggro;
        else if(s.equals("fr"))
            return Status.fear;
        else return Status.neutral;
    }



}
