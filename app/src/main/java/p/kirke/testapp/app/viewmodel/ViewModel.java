package p.kirke.testapp.app.viewmodel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import p.kirke.testapp.app.data.StringCache;
import p.kirke.testapp.library.SubscribeLibrary;
import p.kirke.testapp.library.SubscribeLibraryImplementation;

public class ViewModel {

    private StringCache cache = StringCache.getInstance();
    private SubscribeLibrary subscribeLibrary = new SubscribeLibraryImplementation();
    private Disposable activeSubscription;

    private void subscribeToService1() {
        subscribeLibrary.subscribeToService1().subscribe(getObserver());
    }

    private void subscribeToService2AndProcess() {
        subscribeLibrary.subscribeToService2AndProcess().subscribe(getObserver());
    }

    private void subscribeToFilterAndMergeServiceOutputs() {
        subscribeLibrary.subscribeToFilterAndMergeServiceOutputs().subscribe(getObserver());
    }

    private Observer<? super String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                releaseResourcesIfNeeded();
                activeSubscription = disposable;
                cache.clear();
            }

            @Override
            public void onNext(String string) {
                cache.add(string);
            }

            @Override
            public void onError(Throwable e) {
                cache.publishError();
            }

            @Override
            public void onComplete() {
                cache.publishData();
            }
        };
    }

    public void releaseResourcesIfNeeded() {
        if (activeSubscription != null && activeSubscription.isDisposed()) {
            activeSubscription.dispose();
        }
    }

    public void onClickService2Button() {
        subscribeToService2AndProcess();
    }

    public void onClickService1Button() {
        subscribeToService1();
    }

    public void onClickMergeService1And2Button() {
        subscribeToFilterAndMergeServiceOutputs();
    }
}
