package eu.szwiec.countries.details;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import eu.szwiec.countries.data.model.Country;

/**
 * Created by szwiec on 23/04/2017.
 */

public class DetailsPresenter extends MvpBasePresenter<DetailsView> {

    public void showCountry(Country country) {
        getView().setTitle(country.getName());
        getView().setFlag(country.getFlagUrl());
    }
}
