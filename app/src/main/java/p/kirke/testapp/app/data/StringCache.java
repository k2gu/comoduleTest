package p.kirke.testapp.app.data;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StringCache {

    private MutableLiveData<List<String>> liveDataList = new MutableLiveData<>();
    private List<String> data = new ArrayList<>();
    private MutableLiveData<State> state = new MutableLiveData<>();
    private static StringCache instance;

    private StringCache() {
        this.state.setValue(State.OK);
    }

    public static StringCache getInstance() {
        if (instance == null) {
            instance = new StringCache();
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
