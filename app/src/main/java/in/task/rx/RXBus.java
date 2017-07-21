package in.task.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by vivek on 21/07/17.
 */

public enum RXBus {
    INSTANCE;

    private Subject publisher = PublishSubject.create();

    public static RXBus getInstance() {
        return INSTANCE;
    }

    public void sendEvent(@NonNull Object object) {
        if (publisher.hasObservers()) {
            publisher.onNext(object);
        }
    }

    public Observable<Object> toObservable() {
        return publisher;
    }

}
