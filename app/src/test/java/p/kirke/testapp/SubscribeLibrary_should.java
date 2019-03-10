package p.kirke.testapp;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import p.kirke.testapp.library.DummyService;
import p.kirke.testapp.library.SubscribeLibraryImplementation;

import static org.mockito.BDDMockito.given;

public class SubscribeLibrary_should {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    DummyService dummyService;

    private SubscribeLibraryImplementation subscribeLibraryImplementation = new SubscribeLibraryImplementation();

    @Test
    public void not_change_observable_values_on_call_subscribe_to_service_1() {
        given(dummyService.service1()).willReturn(Observable.just("one", "aaa", "two", "bbb", "tri", "ccc", "four", "ddd", "five"));

        Observable<String> dummyService1Observable = subscribeLibraryImplementation.subscribeToService1();

        dummyService1Observable
                .test()
                .assertValues("one", "aaa", "two", "bbb", "tri", "ccc", "four", "ddd", "five");
    }

    @Test
    public void remove_repeated_letter_strings_on_call_subscribe_to_service_2_and_process() {
        given(dummyService.service1()).willReturn(Observable.just("aye", "eee", "beta", "fff", "charlie", "ggg", "delta", "hhh", "echo"));

        Observable<String> dummyService1Observable = subscribeLibraryImplementation.subscribeToService2AndProcess();

        dummyService1Observable
                .test()
                .assertValues("aye", "beta", "charlie", "delta", "echo");
    }

    @Test
    public void remove_repeated_letter_strings_and_merge_on_call_subscribe_to_service_2_and_process() {
        given(dummyService.service1()).willReturn(Observable.just("one", "aaa", "two", "bbb", "tri", "ccc", "four", "ddd", "five"));
        given(dummyService.service1()).willReturn(Observable.just("aye", "eee", "beta", "fff", "charlie", "ggg", "delta", "hhh", "echo"));

        Observable<String> dummyService1Observable = subscribeLibraryImplementation.subscribeToFilterAndMergeServiceOutputs();

        dummyService1Observable
                .test()
                .assertValues("one aye", "two beta", "tri charlie", "four delta", "five echo");
    }
}
