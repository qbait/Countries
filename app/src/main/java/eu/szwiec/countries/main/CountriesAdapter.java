package eu.szwiec.countries.main;

/**
 * Created by szwiec on 23/04/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.szwiec.countries.data.model.Country;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    public interface CountryClickedListener {
        public void onCountryClicked(Country country);
    }

    private CountryClickedListener clickListener;

    private List<Country> countries = new ArrayList<>();

    public CountriesAdapter(CountryClickedListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setData(List<Country> data) {
        this.countries = data;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(android.R.id.text1);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Country country = countries.get(position);

        viewHolder.getTextView().setText(country.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCountryClicked(country);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
