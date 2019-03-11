package p.kirke.testapp.app.data;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StringCache {

    private MutableLiveData<List<String>> liveDataList = new MutableLiveData<>();
    private List<String> data = new ArrayList<>();
    private static StringCache instance;
    private static MutableLiveData<State> state = new MutableLiveData<>();

    public static StringCache getInstance() {
        if (instance == null) {
            instance = new StringCache();
            state.setValue(State.OK);
        }
        return instance;
    }

    public void publishData() {
        state.setValue(State.OK);
        liveDataList.setValue(data);
    }

    public MutableLiveData<List<String>> getData() {
        return liveDataList;
    }

    public void clear() {
        data.clear();
    }

    public void add(String string) {
        data.add(string);
    }

    public MutableLiveData<State> getState() {
        return state;
    }

    public void publishError() {
        state.setValue(State.ERROR);
    }

    public enum State {OK, ERROR}
}
