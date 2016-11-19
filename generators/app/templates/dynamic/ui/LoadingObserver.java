package <%= app_id %>.ui;


import io.reactivex.observers.DisposableObserver;

public abstract class LoadingObserver<T> extends DisposableObserver<T> {

    protected LoadingListener listener;

    public LoadingObserver(LoadingListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (listener != null) {
            listener.onLoadingStarted();
        }
    }

    @Override
    public void onComplete() {
        if (listener != null) {
            listener.onLoadingFinished();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (listener != null) {
            listener.onLoadingFinished();
        }
    }

}
