package weather.application.com.weatherapp.domain;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import weather.application.com.weatherapp.R;

public class Day implements Serializable {

    @SerializedName("maxtemp_c")
    private double maxtemp_c;

    @SerializedName("maxtemp_f")
    private double maxtemp_f;

    @SerializedName("mintemp_c")
    private double mintemp_c;

    @SerializedName("mintemp_f")
    private double mintemp_f;

    @SerializedName("avgtemp_c")
    private double avgtemp_c;

    @SerializedName("avgtemp_f")
    private double avgtemp_f;

    @SerializedName("maxwind_mph")
    private double maxwind_mph;

    @SerializedName("maxwind_kph")
    private double maxwind_kph;

    @SerializedName("totalprecip_mm")
    private double totalprecip_mm;

    @SerializedName("totalprecip_in")
    private double totalprecip_in;

    @SerializedName("avghumidity")
    private double avgHumidity;

    @SerializedName("avgvis_km")
    private double avgVisibilityKm;

    private Condition condition;

    public Day() {
    }

    public double getAvgVisibilityKm() {
        return avgVisibilityKm;
    }

    public void setAvgVisibilityKm(double avgVisibilityKm) {
        this.avgVisibilityKm = avgVisibilityKm;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }
    public void setAvgHumidity(double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public double getMaxtempC() {
        return maxtemp_c;
    }

    public void setMaxtempC(double mMaxtemp_c) {
        this.maxtemp_c = mMaxtemp_c;
    }

    public double getMaxtempF() {
        return maxtemp_f;
    }

    public void setMaxtempF(double mMaxtemp_f) {
        this.maxtemp_f = mMaxtemp_f;
    }

    public double getMintempC() {
        return mintemp_c;
    }

    public void setMintempC(double mMintemp_c) {
        this.mintemp_c = mMintemp_c;
    }

    public double getMintempF() {
        return mintemp_f;
    }

    public void setMintempF(double mMintemp_f) {
        this.mintemp_f = mMintemp_f;
    }

    public double getAvgtempC() {
        return avgtemp_c;
    }

    public void setAvgtempC(double mAvgtemp_c) {
        this.avgtemp_c = mAvgtemp_c;
    }

    public double getAvgtempF() {
        return avgtemp_f;
    }

    public void setAvgtempF(double mAvgtemp_f) {
        this.avgtemp_f = mAvgtemp_f;
    }

    public double getMaxwindMph() {
        return maxwind_mph;
    }

    public void setMaxwindMph(double mMaxwind_mph) {
        this.maxwind_mph = mMaxwind_mph;
    }

    public double getMaxwindKph() {
        return maxwind_kph;
    }

    public void setMaxwindKph(double mMaxwind_kph) {
        this.maxwind_kph = mMaxwind_kph;
    }

    public double getTotalprecipMm() {
        return totalprecip_mm;
    }

    public void setTotalprecipMm(double mTotalprecip_mm) {
        this.totalprecip_mm = mTotalprecip_mm;
    }

    public double getTotalprecipIn() {
        return totalprecip_in;
    }

    public void setTotalprecipIn(double mTotalprecip_in) {
        this.totalprecip_in = mTotalprecip_in;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @NonNull
    public String getHumidity(Context context) {
        return context.getString(R.string.format_humidity, (int) getAvgHumidity());
    }

    @NonNull
    public String getVisibility() {
        return "Visibility: " + getAvgVisibilityKm() + " Km";
    }

    @NonNull
    public String getMaxTemperature(Context context) {
        return getMaxtempF() > 0 ? "Max: " + context.getString(R.string.temperature_plus, getMaxtempF(), "°C")
                : context.getString(R.string.temperature_minus, getMaxtempF(), "°C");
    }

    @NonNull
    public String getMinTemperature(Context context) {
        return getMintempF() > 0 ? "Min: " + context.getString(R.string.temperature_plus, getMintempF(), "°C")
                : context.getString(R.string.temperature_minus, getMintempF(), "°C");
    }
}
