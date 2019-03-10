package p.kirke.testapp.app;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import p.kirke.testapp.app.data.StringCache;
import p.kirke.testapp.library.SubscribeLibraryImplementation;

public class ViewModel {

    StringCache cache = StringCache.getInstance();
    SubscribeLibraryImplementation subscribeLibrary = new SubscribeLibraryImplementation();

    public void subscribeToService1() {
        subscribeLibrary.subscribeToService1();
    }

    public void subscribeToService2AndProcess() {
        subscribeLibrary.subscribeToService2AndProcess();
    }

    public void filterAndMergeServiceOutputs() {
        subscribeLibrary.filterAndMergeServiceOutputs().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                cache.addString(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
