package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("base")
    private String mBase;
    @SerializedName("clouds")
    private Clouds mClouds;
    @SerializedName("cod")
    private Double mCod;
    @SerializedName("coord")
    private Coord mCoord;
    @SerializedName("dt")
    private Double mDt;
    @SerializedName("id")
    private Double mId;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("name")
    private String mName;
    @SerializedName("sys")
    private Sys mSys;
    @SerializedName("visibility")
    private Double mVisibility;
    @SerializedName("weather")
    private List<Weather> mWeather;
    @SerializedName("wind")
    private Wind mWind;

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        mBase = base;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public void setClouds(Clouds clouds) {
        mClouds = clouds;
    }

    public Double getCod() {
        return mCod;
    }

    public void setCod(Double cod) {
        mCod = cod;
    }

    public Coord getCoord() {
        return mCoord;
    }

    public void setCoord(Coord coord) {
        mCoord = coord;
    }

    public Double getDt() {
        return mDt;
    }

    public void setDt(Double dt) {
        mDt = dt;
    }

    public Double getId() {
        return mId;
    }

    public void setId(Double id) {
        mId = id;
    }

    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        mMain = main;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Sys getSys() {
        return mSys;
    }

    public void setSys(Sys sys) {
        mSys = sys;
    }

    public Double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Double visibility) {
        mVisibility = visibility;
    }

    public List<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(List<Weather> weather) {
        mWeather = weather;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

}
