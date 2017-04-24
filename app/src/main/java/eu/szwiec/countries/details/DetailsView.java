package eu.szwiec.countries.details;


import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by szwiec on 23/04/2017.
 */

public interface DetailsView extends MvpView {

    public void setTitle(String title);

    public void setFlag(String flagUrl);
}
