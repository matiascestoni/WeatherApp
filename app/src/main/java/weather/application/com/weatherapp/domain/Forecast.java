package weather.application.com.weatherapp.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Forecast implements Serializable {

    @SerializedName("forecastday")
    private ArrayList<ForecastDay> forecastday = new ArrayList<>();

    public Forecast() {
    }

    public ArrayList<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> mForecastday) {
        this.forecastday = mForecastday;
    }
}
