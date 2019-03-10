package p.kirke.testapp.library;

import io.reactivex.Observable;

public interface SubscribeLibrary {

    Observable<String> subscribeToService1();

    Observable<String> subscribeToService2AndProcess();

    Observable<String> filterAndMergeServiceOutputs();
}
