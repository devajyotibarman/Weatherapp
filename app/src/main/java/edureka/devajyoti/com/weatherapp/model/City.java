package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("coord")
    private Coord mCoord;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("id")
    private Double mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("population")
    private Double mPopulation;

    public Coord getCoord() {
        return mCoord;
    }

    public void setCoord(Coord coord) {
        mCoord = coord;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Double getId() {
        return mId;
    }

    public void setId(Double id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPopulation() {
        return mPopulation;
    }

    public void setPopulation(Double population) {
        mPopulation = population;
    }

}
