package weather.application.com.weatherapp.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Matias Cestoni.
 */
public class GeoCity implements Serializable, Comparable<GeoCity> {

    @SerializedName("adminCode1")
    private String adminCode1;

    @SerializedName("adminName1")
    private String adminName1;

    @SerializedName("lng")
    private String lng;

    @SerializedName("geonameId")
    private long geoNameId;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("countryId")
    private String countryId;

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("name")
    private String name;

    @SerializedName("population")
    private long population;

    @SerializedName("toponymName")
    private String toponymName;

    @SerializedName("lat")
    private String lat;

    @SerializedName("fcl")
    private String fcl;

    @SerializedName("fcode")
    private String fcode;

    @SerializedName("fclName")
    private String fclName;

    @SerializedName("fcodeName")
    private String fcodeName;

    //TODO remove it
    public GeoCity() {
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s, %s, %s", name, adminName1, countryName);
    }

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public long getGeoNameId() {
        return geoNameId;
    }

    public void setGeoNameId(long geonameId) {
        this.geoNameId = geonameId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getToponymName() {
        return toponymName;
    }

    public void setToponymName(String toponymName) {
        this.toponymName = toponymName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getFcl() {
        return fcl;
    }

    public void setFcl(String fcl) {
        this.fcl = fcl;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    public String getFclName() {
        return fclName;
    }

    public void setFclName(String fclName) {
        this.fclName = fclName;
    }

    public String getFcodeName() {
        return fcodeName;
    }

    public void setFcodeName(String fcodeName) {
        this.fcodeName = fcodeName;
    }

    @Override
    public int compareTo(GeoCity o) {
        return (int) (this.geoNameId - o.getGeoNameId());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GeoCity geoCity = (GeoCity) obj;
        return geoNameId == geoCity.getGeoNameId();
    }


}
