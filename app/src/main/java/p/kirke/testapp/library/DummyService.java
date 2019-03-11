package p.kirke.testapp.library;

import io.reactivex.Observable;

public class DummyService {

    public Observable<String> service1() {
        return Observable.just("one", "aaa", "two", "bbb", "tri", "ccc", "four", "ddd", "five");
    }

    public Observable<String> service2() {
        return Observable.just("aye", "eee", "beta", "fff", "charlie", "ggg", "delta", "hhh", "echo");
    }
}
