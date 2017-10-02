package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("clouds")
    private Double mClouds;
    @SerializedName("deg")
    private Double mDeg;
    @SerializedName("dt")
    private Double mDt;
    @SerializedName("humidity")
    private Double mHumidity;
    @SerializedName("pressure")
    private Double mPressure;
    @SerializedName("rain")
    private Double mRain;
    @SerializedName("speed")
    private Double mSpeed;
    @SerializedName("temp")
    private Temp mTemp;
    @SerializedName("weather")
    private java.util.List<Weather> mWeather;

    public Double getClouds() {
        return mClouds;
    }

    public void setClouds(Double clouds) {
        mClouds = clouds;
    }

    public Double getDeg() {
        return mDeg;
    }

    public void setDeg(Double deg) {
        mDeg = deg;
    }

    public Double getDt() {
        return mDt;
    }

    public void setDt(Double dt) {
        mDt = dt;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public Double getPressure() {
        return mPressure;
    }

    public void setPressure(Double pressure) {
        mPressure = pressure;
    }

    public Double getRain() {
        return mRain;
    }

    public void setRain(Double rain) {
        mRain = rain;
    }

    public Double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(Double speed) {
        mSpeed = speed;
    }

    public Temp getTemp() {
        return mTemp;
    }

    public void setTemp(Temp temp) {
        mTemp = temp;
    }

    public java.util.List<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        mWeather = weather;
    }

}
