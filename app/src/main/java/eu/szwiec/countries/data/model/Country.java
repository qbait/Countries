package eu.szwiec.countries.data.model;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by szwiec on 23/04/2017.
 */

@Parcel
public class Country extends RealmObject {

    @PrimaryKey
    String alpha2Code;
    String name;
    String capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getFlagUrl() {
        return String.format("http://www.geonames.org/flags/x/%s.gif", alpha2Code.toLowerCase());
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
