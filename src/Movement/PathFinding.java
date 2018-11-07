package Movement;

import Worlds.Tile;
import Worlds.World;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by william on 2016-12-22.
 */
public class PathFinding {

    private int targetX, targetY, x, y;
    private World world;
    private LinkedList<Tile> visited = new LinkedList<Tile>();
    private LinkedList<Tile> queue = new LinkedList<Tile>();

    public PathFinding(int currX, int currY, int targetX, int targetY, World world) {
        this.targetX = targetX;
        this.targetY =  targetY;
        this.x = currX;
        this.y = currY;
        this.world = world;
    }

    private int getCost(int x, int y,  int currX, int currY) {
        int tempX = Math.abs(currX - x);
        int tempY = Math.abs(currY - y);
        int sum = 0;
        while(tempX > 0 && tempY > 0) {
            sum += 14;
            tempX -= 1;
            tempY -= 1;
        }
        while(tempX > 0) {
            sum += 10;
            tempX -= 1;
        }
        while(tempY > 0) {
            sum += 10;
            tempY -= 1;
        }

        return sum;
    }


    private void reset(LinkedList<Tile> visited) {
        for(Tile t: visited) {
            t.setCost(99999);
            t.setParentTile(null);
        }
    }



    public LinkedList<Tile> findShortestPath() {

        int initX = x / Tile.getTILEWIDTH();
        int initY = y / Tile.getTILEHEIGHT();
        int tempTargetX = targetX / Tile.getTILEWIDTH();
        int tempTargetY = targetY / Tile.getTILEHEIGHT();
        int tempX = initX;
        int tempY = initY;


        Tile tile = world.getTile(tempX, tempY);

        tile.setCost(getCost(initX, initY, tempX, tempY) + getCost(tempTargetX, tempTargetY, tempX, tempY));

        queue.add(tile);

        Tile smallestT = null;

        while(world.getTile(tempTargetX, tempTargetY) != null && !queue.contains(world.getTile(tempTargetX, tempTargetY))) {

            if(queue.isEmpty())
                return null;

//            for(Tile t: queue) {
//                if(t.getCost() < smallest) {
//                    smallest = t.getCost();
//                    smallestT = t;
//                }
//            }

            Collections.sort(queue, new Comparator<Tile>() {
                @Override
                public int compare(Tile t1, Tile t2) {
                    if (t1.getCost() > t2.getCost())
                        return 1;
                    if (t1.getCost() < t2.getCost())
                        return -1;
                    return 0;
                }
            });
//            smallestT = queue.get(0);
            // visit the lowest cost tile
            smallestT = queue.get(0);
            visited.add(smallestT);
            queue.remove(0);
            tempX = smallestT.getX();
            tempY = smallestT.getY();

            Tile up = world.getTile(tempX, tempY - 1);
            Tile down = world.getTile(tempX, tempY + 1);
            Tile right = world.getTile(tempX + 1, tempY);
            Tile left = world.getTile(tempX - 1, tempY);
            Tile upRight = world.getTile(tempX + 1, tempY - 1);
            Tile upLeft = world.getTile(tempX - 1, tempY - 1);
            Tile downRight = world.getTile(tempX + 1, tempY + 1);
            Tile downLeft = world.getTile(tempX - 1, tempY + 1);

            if(smallestT.getParentTile() == null) {
                if(up != null && !up.isSolid()) {
                    if (!queue.contains(up) && !visited.contains(up)) {
                        queue.add(up);
                        up.setCost(getCost(initX, initY, tempX, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX, tempY - 1));
                        up.setParentTile(smallestT);
                    }
                }
                if(down != null && !down.isSolid()) {
                    if (!queue.contains(down) && !visited.contains(down)) {
                        queue.add(down);
                        down.setCost(getCost(initX, initY, tempX, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX, tempY + 1));
                        down.setParentTile(smallestT);
                    }
                }
                if(right != null && !right.isSolid()) {
                    if (!queue.contains(right) && !visited.contains(right)) {
                        queue.add(right);
                        right.setCost(getCost(initX, initY, tempX + 1, tempY) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY));
                        right.setParentTile(smallestT);
                    }
                }
                if(left != null && !left.isSolid()) {
                    if (!queue.contains(left) && !visited.contains(left)) {
                        queue.add(left);
                        left.setCost(getCost(initX, initY, tempX - 1, tempY) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY));
                        left.setParentTile(smallestT);
                    }
                }
                if(upRight != null && !upRight.isSolid()) {
                    if (!queue.contains(upRight) && !visited.contains(upRight)) {
                        queue.add(upRight);
                        upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                        upRight.setParentTile(smallestT);
                    }
                }
                if(upLeft != null && !upLeft.isSolid()) {
                    if (!queue.contains(upLeft) && !visited.contains(upLeft)) {
                        queue.add(upLeft);
                        upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                        upLeft.setParentTile(smallestT);
                    }
                }
                if(downLeft != null && !downLeft.isSolid()) {
                    if (!queue.contains(downLeft) && !visited.contains(downLeft)) {
                        queue.add(downLeft);
                        downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                        downLeft.setParentTile(smallestT);
                    }
                }
                if(downRight != null && !downRight.isSolid()) {
                    if (!queue.contains(downRight) && !visited.contains(downRight)) {
                        queue.add(downRight);
                        downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                        downRight.setParentTile(smallestT);
                    }
                }

                smallestT = null;
            }


            else {
                if(smallestT.getParentTile().getX() < smallestT.getX() && smallestT.getParentTile().getY() == smallestT.getY()) {

                    if(right != null && !right.isSolid()) {
                        if (!queue.contains(right) && !visited.contains(right)) {
                            queue.add(right);
                            right.setCost(getCost(initX, initY, tempX + 1, tempY) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY));
                            right.setParentTile(smallestT);
                        }
                    }
                    if(upRight != null && !upRight.isSolid()) {
                        if (!queue.contains(upRight) && !visited.contains(upRight) && up != null && up.isSolid()) {
                            queue.add(upRight);
                            upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                            upRight.setParentTile(smallestT);
                        }
                    }
                    if(downRight != null && !downRight.isSolid()) {
                        if (!queue.contains(downRight) && !visited.contains(downRight) && down != null && down.isSolid()) {
                            queue.add(downRight);
                            downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                            downRight.setParentTile(smallestT);
                        }
                    }
                }

                else if(smallestT.getParentTile().getX() > smallestT.getX() && smallestT.getParentTile().getY() == smallestT.getY()) {

                    if(left != null && !left.isSolid()) {
                        if (!queue.contains(left) && !visited.contains(left)) {
                            queue.add(left);
                            left.setCost(getCost(initX, initY, tempX - 1, tempY) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY));
                            left.setParentTile(smallestT);
                        }
                    }
                    if(upLeft != null && !upLeft.isSolid()) {
                        if (!queue.contains(upLeft) && !visited.contains(upLeft)&& up != null && up.isSolid()) {
                            queue.add(upLeft);
                            upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                            upLeft.setParentTile(smallestT);
                        }
                    }
                    if(downLeft != null && !downLeft.isSolid()) {
                        if (!queue.contains(downLeft) && !visited.contains(downLeft) && down != null && down.isSolid()) {
                            queue.add(downLeft);
                            downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                            downLeft.setParentTile(smallestT);
                        }
                    }
                }
                else if(smallestT.getParentTile().getY() < smallestT.getY() && smallestT.getParentTile().getX() == smallestT.getX()){
                    if(down != null && !down.isSolid()) {
                        if (!queue.contains(down) && !visited.contains(down)) {
                            queue.add(down);
                            down.setCost(getCost(initX, initY, tempX, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX, tempY + 1));
                            down.setParentTile(smallestT);
                        }
                    }
                    if(downLeft != null && !downLeft.isSolid()) {
                        if (!queue.contains(downLeft) && !visited.contains(downLeft) && left != null && left.isSolid()) {
                            queue.add(downLeft);
                            downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                            downLeft.setParentTile(smallestT);
                        }
                    }
                    if(downRight != null && !downRight.isSolid()) {
                        if (!queue.contains(downRight) && !visited.contains(downRight) && right != null && right.isSolid()) {
                            queue.add(downRight);
                            downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                            downRight.setParentTile(smallestT);
                        }
                    }

                }

                else if(smallestT.getParentTile().getY() > smallestT.getY() && smallestT.getParentTile().getX() == smallestT.getX()) {

                    if(up != null && !up.isSolid()) {
                        if (!queue.contains(up) && !visited.contains(up)) {
                            queue.add(up);
                            up.setCost(getCost(initX, initY, tempX, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX, tempY - 1));
                            up.setParentTile(smallestT);
                        }
                    }
                    if(upRight != null && !upRight.isSolid()) {
                        if (!queue.contains(upRight) && !visited.contains(upRight) && right != null && right.isSolid()) {
                            queue.add(upRight);
                            upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                            upRight.setParentTile(smallestT);
                        }
                    }
                    if(upLeft != null && !upLeft.isSolid()) {
                        if (!queue.contains(upLeft) && !visited.contains(upLeft)&& left != null && left.isSolid()) {
                            queue.add(upLeft);
                            upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                            upLeft.setParentTile(smallestT);
                        }
                    }
                }
                else if(smallestT.getParentTile().getY() < smallestT.getY() && smallestT.getParentTile().getX() < smallestT.getX()) {
                    if(down != null && !down.isSolid()) {
                        if (!queue.contains(down) && !visited.contains(down)) {
                            queue.add(down);
                            down.setCost(getCost(initX, initY, tempX, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX, tempY + 1));
                            down.setParentTile(smallestT);
                        }
                    }
                    if(right != null && !right.isSolid()) {
                        if (!queue.contains(right) && !visited.contains(right)) {
                            queue.add(right);
                            right.setCost(getCost(initX, initY, tempX + 1, tempY) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY));
                            right.setParentTile(smallestT);
                        }
                    }
                    if(downRight != null && !downRight.isSolid()) {
                        if (!queue.contains(downRight) && !visited.contains(downRight)) {
                            queue.add(downRight);
                            downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                            downRight.setParentTile(smallestT);
                        }
                    }
                    if(downLeft != null && !downLeft.isSolid()) {
                        if (!queue.contains(downLeft) && !visited.contains(downLeft) && left != null && left.isSolid()) {
                            queue.add(downLeft);
                            downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                            downLeft.setParentTile(smallestT);
                        }
                    }
                    if(upRight != null && !upRight.isSolid()) {
                        if (!queue.contains(upRight) && !visited.contains(upRight) && up != null && up.isSolid()) {
                            queue.add(upRight);
                            upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                            upRight.setParentTile(smallestT);
                        }
                    }
                }
                else if(smallestT.getParentTile().getY() > smallestT.getY() && smallestT.getParentTile().getX() < smallestT.getX()) {
                    if(up != null && !up.isSolid()) {
                        if (!queue.contains(up) && !visited.contains(up)) {
                            queue.add(up);
                            up.setCost(getCost(initX, initY, tempX, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX, tempY - 1));
                            up.setParentTile(smallestT);
                        }
                    }
                    if(right != null && !right.isSolid()) {
                        if (!queue.contains(right) && !visited.contains(right)) {
                            queue.add(right);
                            right.setCost(getCost(initX, initY, tempX + 1, tempY) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY));
                            right.setParentTile(smallestT);
                        }
                    }
                    if(upRight != null && !upRight.isSolid()) {
                        if (!queue.contains(upRight) && !visited.contains(upRight)) {
                            queue.add(upRight);
                            upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                            upRight.setParentTile(smallestT);
                        }
                    }
                    if(upLeft != null && !upLeft.isSolid()) {
                        if (!queue.contains(upLeft) && !visited.contains(upLeft)&& left != null && left.isSolid()) {
                            queue.add(upLeft);
                            upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                            upLeft.setParentTile(smallestT);
                        }
                    }
                    if(downRight != null && !downRight.isSolid()) {
                        if (!queue.contains(downRight) && !visited.contains(downRight) && down != null && down.isSolid()) {
                            queue.add(downRight);
                            downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                            downRight.setParentTile(smallestT);
                        }
                    }
                }
                else if(smallestT.getParentTile().getY() < smallestT.getY() && smallestT.getParentTile().getX() > smallestT.getX()) {

                    if(down != null && !down.isSolid()) {
                        if (!queue.contains(down) && !visited.contains(down)) {
                            queue.add(down);
                            down.setCost(getCost(initX, initY, tempX, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX, tempY + 1));
                            down.setParentTile(smallestT);
                        }
                    }
                    if(left != null && !left.isSolid()) {
                        if (!queue.contains(left) && !visited.contains(left)) {
                            queue.add(left);
                            left.setCost(getCost(initX, initY, tempX - 1, tempY) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY));
                            left.setParentTile(smallestT);
                        }
                    }
                    if(downLeft != null && !downLeft.isSolid()) {
                        if (!queue.contains(downLeft) && !visited.contains(downLeft)) {
                            queue.add(downLeft);
                            downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                            downLeft.setParentTile(smallestT);
                        }
                    }
                    if(upLeft != null && !upLeft.isSolid()) {
                        if (!queue.contains(upLeft) && !visited.contains(upLeft)&& up != null && up.isSolid()) {
                            queue.add(upLeft);
                            upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                            upLeft.setParentTile(smallestT);
                        }
                    }
                    if(downRight != null && !downRight.isSolid()) {
                        if (!queue.contains(downRight) && !visited.contains(downRight) && right != null && right.isSolid()) {
                            queue.add(downRight);
                            downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
                            downRight.setParentTile(smallestT);
                        }
                    }
                }
                else if(smallestT.getParentTile().getY() > smallestT.getY() && smallestT.getParentTile().getX() > smallestT.getX()) {
                    if(up != null && !up.isSolid()) {
                        if (!queue.contains(up) && !visited.contains(up)) {
                            queue.add(up);
                            up.setCost(getCost(initX, initY, tempX, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX, tempY - 1));
                            up.setParentTile(smallestT);
                        }
                    }
                    if(left != null && !left.isSolid()) {
                        if (!queue.contains(left) && !visited.contains(left)) {
                            queue.add(left);
                            left.setCost(getCost(initX, initY, tempX - 1, tempY) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY));
                            left.setParentTile(smallestT);
                        }
                    }
                    if(upLeft != null && !upLeft.isSolid()) {
                        if (!queue.contains(upLeft) && !visited.contains(upLeft)) {
                            queue.add(upLeft);
                            upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
                            upLeft.setParentTile(smallestT);
                        }
                    }
                    if(upRight != null && !upRight.isSolid()) {
                        if (!queue.contains(upRight) && !visited.contains(upRight) && right != null && right.isSolid()) {
                            queue.add(upRight);
                            upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
                            upRight.setParentTile(smallestT);
                        }
                    }
                    if(downLeft != null && !downLeft.isSolid()) {
                        if (!queue.contains(downLeft) && !visited.contains(downLeft) && down != null && down.isSolid()) {
                            queue.add(downLeft);
                            downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
                            downLeft.setParentTile(smallestT);
                        }
                    }
                }
            }
//
//
//            if(up != null && !up.isSolid()) {
//                if (!queue.contains(up) && !visited.contains(up)) {
//                    queue.add(up);
//                    up.setCost(getCost(initX, initY, tempX, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX, tempY - 1));
//                    up.setParentTile(smallestT);
//                }
//            }
//            if(down != null && !down.isSolid()) {
//                if (!queue.contains(down) && !visited.contains(down)) {
//                    queue.add(down);
//                    down.setCost(getCost(initX, initY, tempX, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX, tempY + 1));
//                    down.setParentTile(smallestT);
//                }
//            }
//            if(right != null && !right.isSolid()) {
//                if (!queue.contains(right) && !visited.contains(right)) {
//                    queue.add(right);
//                    right.setCost(getCost(initX, initY, tempX + 1, tempY) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY));
//                    right.setParentTile(smallestT);
//                }
//            }
//            if(left != null && !left.isSolid()) {
//                if (!queue.contains(left) && !visited.contains(left)) {
//                    queue.add(left);
//                    left.setCost(getCost(initX, initY, tempX - 1, tempY) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY));
//                    left.setParentTile(smallestT);
//                }
//            }
//            if(upRight != null && !upRight.isSolid()) {
//                if (!queue.contains(upRight) && !visited.contains(upRight) && up != null && up.isSolid()) {
//                    queue.add(upRight);
//                    upRight.setCost(getCost(initX, initY, tempX + 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY - 1));
//                    upRight.setParentTile(smallestT);
//                }
//            }
//            if(upLeft != null && !upLeft.isSolid()) {
//                if (!queue.contains(upLeft) && !visited.contains(upLeft)&& up != null && up.isSolid()) {
//                    queue.add(upLeft);
//                    upLeft.setCost(getCost(initX, initY, tempX - 1, tempY - 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY - 1));
//                    upLeft.setParentTile(smallestT);
//                }
//            }
//            if(downLeft != null && !downLeft.isSolid()) {
//                if (!queue.contains(downLeft) && !visited.contains(downLeft) && down != null && down.isSolid()) {
//                    queue.add(downLeft);
//                    downLeft.setCost(getCost(initX, initY, tempX - 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX - 1, tempY + 1));
//                    downLeft.setParentTile(smallestT);
//                }
//            }
//            if(downRight != null && !downRight.isSolid()) {
//                if (!queue.contains(downRight) && !visited.contains(downRight) && down != null && down.isSolid()) {
//                    queue.add(downRight);
//                    downRight.setCost(getCost(initX, initY, tempX + 1, tempY + 1) + getCost(tempTargetX, tempTargetY, tempX + 1, tempY + 1));
//                    downRight.setParentTile(smallestT);
//                }
//            }

            smallestT = null;
        }



        LinkedList<Tile> path = new LinkedList<Tile>();
        if(world.getTile(tempTargetX, tempTargetY) != null && world.getTile(tempTargetX, tempTargetY).getParentTile() != null) {
            Tile t = world.getTile(tempTargetX, tempTargetY);
            path.addFirst(t);
            while (t.getParentTile() != null) {
                path.addFirst(t.getParentTile());
                t = t.getParentTile();
            }
       }
        path.addFirst(world.getTile(initX, initY));
        reset(visited);
        reset(queue);

        visited.clear();
        queue.clear();

        return path;

    }


}
