package edureka.devajyoti.com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("id")
    private Double mId;
    @SerializedName("main")
    private String mMain;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Double getId() {
        return mId;
    }

    public void setId(Double id) {
        mId = id;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String main) {
        mMain = main;
    }

}
