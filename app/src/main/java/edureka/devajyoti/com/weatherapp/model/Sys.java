package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("id")
    private Double mId;
    @SerializedName("message")
    private Double mMessage;
    @SerializedName("sunrise")
    private Double mSunrise;
    @SerializedName("sunset")
    private Double mSunset;
    @SerializedName("type")
    private Double mType;

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

    public Double getMessage() {
        return mMessage;
    }

    public void setMessage(Double message) {
        mMessage = message;
    }

    public Double getSunrise() {
        return mSunrise;
    }

    public void setSunrise(Double sunrise) {
        mSunrise = sunrise;
    }

    public Double getSunset() {
        return mSunset;
    }

    public void setSunset(Double sunset) {
        mSunset = sunset;
    }

    public Double getType() {
        return mType;
    }

    public void setType(Double type) {
        mType = type;
    }

}
