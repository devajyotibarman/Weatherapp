package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResponse {

    @SerializedName("city")
    private City mCity;
    @SerializedName("cnt")
    private Double mCnt;
    @SerializedName("cod")
    private String mCod;
    @SerializedName("list")
    private java.util.List<edureka.devajyoti.com.weatherapp.model.List> mList;
    @SerializedName("message")
    private Double mMessage;

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public Double getCnt() {
        return mCnt;
    }

    public void setCnt(Double cnt) {
        mCnt = cnt;
    }

    public String getCod() {
        return mCod;
    }

    public void setCod(String cod) {
        mCod = cod;
    }

    public java.util.List<edureka.devajyoti.com.weatherapp.model.List> getList() {
        return mList;
    }

    public void setList(java.util.List<edureka.devajyoti.com.weatherapp.model.List> list) {
        mList = list;
    }

    public Double getMessage() {
        return mMessage;
    }

    public void setMessage(Double message) {
        mMessage = message;
    }

}
