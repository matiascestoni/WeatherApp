package weather.application.com.weatherapp.mvp.view;

import java.util.List;

import weather.application.com.weatherapp.domain.GeoCity;

/**
 * @author Matias Cestoni.
 */
public interface AddCityView {

    void navigateToMainView(GeoCity city);

    void showProgressBar();

    void hideProgressBar();

    void showFindCityError();

    void addFoundCitiesToView(List<GeoCity> cities);

    void clearSearchView();
}
