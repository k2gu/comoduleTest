package p.kirke.testapp.library;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeLibraryImplementation implements SubscribeLibrary {

    private DummyService dummyService = new DummyService();

    @Override
    public Observable<String> subscribeToService1() {
        return dummyService.service1()
                .observeOn(Schedulers.trampoline());
    }

    @Override
    public Observable<String> subscribeToService2AndProcess() {
        return dummyService.service2()
                .filter(this::allCharactersUnique)
                .observeOn(Schedulers.trampoline());
    }

    private boolean allCharactersUnique(String string) {
        Set hashSet = new HashSet();

        for (char character : string.toCharArray()) {
            hashSet.add(character);
        }
        return hashSet.size() >= string.toCharArray().length;
    }

    @Override
    public Observable<String> subscribeToFilterAndMergeServiceOutputs() {
        Observable<String> filteredService1 = dummyService.service1().filter(this::allCharactersUnique);
        Observable<String> filteredService2 = dummyService.service2().filter(this::allCharactersUnique);

        return Observable
                .zip(filteredService1, filteredService2, this::concatStrings)
                .observeOn(Schedulers.trampoline());
    }

    private String concatStrings(String stringFromService1, String stringFromService2) {
        return String.format("%s %s", stringFromService1, stringFromService2);
    }
}
