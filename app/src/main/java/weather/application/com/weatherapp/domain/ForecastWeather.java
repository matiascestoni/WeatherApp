package weather.application.com.weatherapp.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForecastWeather implements Serializable {

    @SerializedName("current")
    private Current current;

    @SerializedName("forecast")
    private Forecast forecast;

    @SerializedName("error")
    private Error error;

    public ForecastWeather() {
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current mCurrent) {
        this.current = mCurrent;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast mForecast) {
        this.forecast = mForecast;
    }

    public Error getError() {
        return error;
    }
}
