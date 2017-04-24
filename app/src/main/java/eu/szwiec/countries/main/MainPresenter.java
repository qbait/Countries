package eu.szwiec.countries.main;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import eu.szwiec.countries.data.RemoteDataStore;
import eu.szwiec.countries.data.Repository;
import eu.szwiec.countries.data.model.Country;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szwiec on 23/04/2017.
 */

public class MainPresenter extends MvpBasePresenter<MainView> {

    private Repository repository;

    private final CompositeDisposable disposables = new CompositeDisposable();

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void loadFromRepository() {
        getView().showLoading(false);

        disposables.add(repository.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableObserver<List<Country>>() {
                    @Override
                    public void onNext(List<Country> countries) {
                        if (isViewAttached()) {
                            getView().setData(countries);
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e, false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void loadFromRemoteDatastore() {
        disposables.add(new RemoteDataStore().getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableObserver<List<Country>>() {
                    @Override
                    public void onNext(List<Country> countries) {
                        if (isViewAttached()) {
                            getView().setData(countries);
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e, false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            disposables.clear();
        }
    }
}
