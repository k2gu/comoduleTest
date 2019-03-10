package p.kirke.testapp.app.data;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StringCache {

    private MutableLiveData<List<String>> liveDataList = new MutableLiveData<>();
    private List<String> data = new ArrayList<>();
    private static StringCache instance;

    public static StringCache getInstance() {
        if (instance == null) {
            instance = new StringCache();
        }
        return instance;
    }

    public void publishData() {
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
}
