package weather.application.com.weatherapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Matias Cestoni.
 */
public class GeoCityResponse extends GeoApiResponse {

    private int totalResultsCount;

    @SerializedName("geonames")
    private List<GeoCity> cities;

    public List<GeoCity> getCities() {
        return cities;
    }

    public int getTotalResultsCount() {
        return totalResultsCount;
    }
}
