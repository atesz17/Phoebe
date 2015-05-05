package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;
import com.gto.phoebe.ui.UserInterface;

public class TestInterface implements UserInterface {

    private Movement movement;
    private TrapTypes trapTypes = TrapTypes.NONE;

    @Override
    public Movement getMovementInput(TrapperRobot robot) {
        return movement;
    }

    @Override
    public void print(String message) {

    }

    @Override
    public TrapTypes getTrapInput(TrapperRobot robot) {
        return trapTypes;
    }

    @Override
    public void gameOver(String winner) {

    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void setTrapTypes(TrapTypes trapTypes) {
        this.trapTypes = trapTypes;
    }
}
