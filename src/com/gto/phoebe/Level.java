package com.gto.phoebe;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Level {

    private Robot[] robots;
    private Trap[] traps;
    private int remainingTurns;

    public Level(int remainingTurns, Robot playerOne, Robot playerTwo)    {
        this.remainingTurns = remainingTurns;
        robots = new Robot[2];
        robots[0] = playerOne;
        robots[1] = playerTwo;
        traps = new Trap[0];
    }

    public void checkCollisionOnRobot(Robot robot)  {
        for (Trap trap : traps) {
            // nyilvan nem csak a pos-t kell nezni, hanem a kor sugarat, de ez csak prototipus
            if (trap.getPosition().equals(robot.getPosition()))  {
                trap.activateEffectOn(robot);
                removeTrapFromLevel(trap);
            }
        }
    }

    // Nincs jol megirva
    public void addTrapToLevel(Trap trap)   {
        int newArraySize = traps.length + 1;
        Trap[] tmp = new Trap[newArraySize];
        if (newArraySize != 1) {
            tmp = traps;
        }
        tmp[newArraySize - 1] = trap;
        traps = new Trap[newArraySize];
        traps = tmp;
    }

    // Checkoljuk, hogy egyaltalan benne van-e
    // Nincs jol megirva
    public void removeTrapFromLevel(Trap trap)  {
        int newArraySize = traps.length - 1;
        if (newArraySize > 0) {
            Trap[] tmp = new Trap[newArraySize];
            int z = 0;
            for (int i = 0; i < traps.length; i++) {
                if (!traps[i].equals(trap)) {
                    tmp[z] = traps[i];
                    z++;
                }
            }
        } else {
            traps = new Trap[0];
        }
    }

    public int getRemainingTurns()  {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns)   {
        this.remainingTurns = remainingTurns;
    }

    public void checkRobotHasCrossedStartLine(Robot r)  {
        // TODD
    }

    public Robot getRobot(int index) throws Exception   {
        if (index > 1)  {
            throw new Exception();
        } else  {
            return robots[index];
        }
    }

    public Robot getWinner()    {
        double maxDistance = robots[0].getTotalDistanceTraveled();
        if (maxDistance < robots[1].getTotalDistanceTraveled()) {
            return robots[1];
        } else  {
            return robots[0];
        }
    }
}
