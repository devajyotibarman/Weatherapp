package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private Double mAll;

    public Double getAll() {
        return mAll;
    }

    public void setAll(Double all) {
        mAll = all;
    }

}
