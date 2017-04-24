package eu.szwiec.countries.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import eu.szwiec.countries.R;
import eu.szwiec.countries.data.model.Country;

/**
 * Created by szwiec on 23/04/2017.
 */

public class DetailsActivity extends MvpActivity<DetailsView, DetailsPresenter>
        implements DetailsView {

    public static final String KEY_COUNTRY = "eu.szwiec.countries.details.KEY_COUNTRY";

    private ImageView flagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        flagImageView = (ImageView) findViewById(R.id.flag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Country country = Parcels.unwrap(getIntent().getParcelableExtra(KEY_COUNTRY));
        setSupportActionBar(toolbar);

        presenter.showCountry(country);
    }

    @NonNull
    @Override
    public DetailsPresenter createPresenter() {
        return new DetailsPresenter();
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setFlag(String flagUrl) {
        Picasso.with(this).load(flagUrl)
                .into(flagImageView);
    }
}
