package StatePackage;

/**
 * Created by William on 2016-04-27.
 */
public class StateManager {

    // currently the state(menu, ingame, friends list)
    private static State currentState = null;

    public static void setCurrentState(State state) {
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }

}
