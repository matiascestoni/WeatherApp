package weather.application.com.weatherapp.domain;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.util.ConditionWeatherUtil;

/**
 * @author Matias Cestoni.
 */
public class CityWeather implements Serializable {

    private GeoCity city;
    private ForecastWeather forecast;

    public CityWeather(GeoCity city, ForecastWeather forecast) {
        this.city = city;
        this.forecast = forecast;
    }

    public ForecastWeather getForecast() {
        return forecast;
    }

    public void setForecast(ForecastWeather forecast) {
        this.forecast = forecast;
    }

    public GeoCity getCity() {
        return city;
    }

    public void setCity(GeoCity city) {
        this.city = city;
    }

    @NonNull
    public String getHumidity(Context context) {
        if (forecast != null && forecast.getCurrent() != null) {
            return context.getString(R.string.format_humidity, forecast.getCurrent().getHumidity());
        }
        return "";
    }

    @NonNull
    public String getWindText(Context context) {
        if (forecast != null && forecast.getCurrent() != null && forecast.getCurrent().getWindDir() != null) {
            return context.getString(R.string.format_wind, forecast.getCurrent().getWindKph(), "m/s",
                    forecast.getCurrent().getWindDir());
        }
        return "";
    }

    @NonNull
    public String getTemperature(Context context) {
        if (forecast != null && forecast.getCurrent() != null) {
            double temp = forecast.getCurrent().getTempC();
            return temp > 0 ? context.getString(R.string.temperature_plus, temp, "°C")
                    : context.getString(R.string.temperature_minus, temp, "°C");
        }
        return "";
    }

    @NonNull
    public String getImageIcon() {
        if (forecast != null && forecast.getCurrent() != null && forecast.getCurrent().getCondition() != null) {
            int code = forecast.getCurrent().getCondition().getCode();
            boolean isDay = forecast.getCurrent().getIs_day() == 1;
            return ConditionWeatherUtil.getImageWithForecast(code, isDay);
        }
        return "not-available.jpg";
    }

    @NonNull
    public String getPressure(Context context) {
        if (forecast != null && forecast.getCurrent() != null) {
            return context.getString(R.string.format_pressure, forecast.getCurrent().getPressureMb(), "mmHg.");
        }
        return "";
    }

    @NonNull
    public String getCondition() {
        if (forecast != null && forecast.getCurrent() != null && forecast.getCurrent().getCondition() != null
                && forecast.getCurrent().getCondition().getText() != null) {
            return forecast.getCurrent().getCondition().getText();
        }
        return "";
    }

    @NonNull
    public String getDate() {
        if (forecast != null && forecast.getCurrent() != null && forecast.getCurrent().getLastUpdated() != null) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(forecast.getCurrent().getLastUpdated());
            } catch (ParseException e) {
                return "";
            }
            return new SimpleDateFormat("EE, dd MMM, HH:mm", Locale.getDefault()).format(date);
        }
        return "";
    }
}
