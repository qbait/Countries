package eu.szwiec.countries.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import javax.inject.Inject;

import eu.szwiec.countries.App;
import eu.szwiec.countries.IntentStarter;
import eu.szwiec.countries.R;
import eu.szwiec.countries.data.Repository;
import eu.szwiec.countries.data.model.Country;

/**
 * Created by szwiec on 23/04/2017.
 */

public class MainActivity extends MvpLceViewStateActivity<SwipeRefreshLayout, List<Country>, MainView, MainPresenter> implements MainView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private CountriesAdapter adapter;
    @Inject
    IntentStarter intentStarter;
    @Inject
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);

        presenter.setRepository(repository);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        contentView.setOnRefreshListener(this);

        adapter = new CountriesAdapter(new CountriesAdapter.CountryClickedListener() {
            @Override
            public void onCountryClicked(Country country) {
                intentStarter.showCountryDetails(MainActivity.this, country);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public LceViewState<List<Country>, MainView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadFromRepository();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setData(List<Country> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        presenter.loadFromRemoteDatastore();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public List<Country> getData() {
        return adapter == null ? null : adapter.getCountries();
    }
}
