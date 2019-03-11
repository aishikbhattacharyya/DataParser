import java.util.List;

public class DataManager {
    private List<State> state;

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

    public void add(State s){
        state.add(s);
    }
}
