package weather.application.com.weatherapp.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weather.application.com.weatherapp.BuildConfig;
import weather.application.com.weatherapp.api.GeoNameApi;
import weather.application.com.weatherapp.api.GeoNameApiClient;
import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.domain.GeoCityResponse;
import weather.application.com.weatherapp.mvp.view.AddCityView;

import static android.text.TextUtils.isEmpty;
import static weather.application.com.weatherapp.mvp.model.WeatherModel.CITIES_STYLE;
import static weather.application.com.weatherapp.mvp.model.WeatherModel.MAX_CITIES_LIST_SIZE;

/**
 * @author Matias Cestoni.
 */
public class AddCityPresenter {

    private AddCityView view;

    public void setView(@Nullable AddCityView view) {
        this.view = view;
    }

    public void onCitySelected(GeoCity city) {
        if (view != null) {
            view.navigateToMainView(city);
        }
    }

    public void onTextChanged(String query) {
        if (view != null) {
            if (!isEmpty(query)) {
                view.showProgressBar();
                getCityFromQuery(query);
            } else {
                view.clearSearchView();
                view.hideProgressBar();
            }
        }
    }

    //TODO Move this code to Model
    private void getCityFromQuery(String query) {

        GeoNameApi client = GeoNameApiClient.getClient().create(GeoNameApi.class);

        Call<GeoCityResponse> responseCall = client.findCity(query, MAX_CITIES_LIST_SIZE, Locale.getDefault().getLanguage(), CITIES_STYLE,
                BuildConfig.GEONAME_API_KEY);

        responseCall.enqueue(new Callback<GeoCityResponse>() {
            @Override
            public void onResponse(@NonNull Call<GeoCityResponse> call, @NonNull Response<GeoCityResponse> response) {
                if (response.body() != null && !response.body().getCities().isEmpty()) {
                    if (view != null) {
                        view.addFoundCitiesToView(response.body().getCities());
                        view.hideProgressBar();
                    }
                } else {
                    if (view != null) {
                        view.showFindCityError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeoCityResponse> call, @NonNull Throwable t) {
                if (view != null) {
                    view.hideProgressBar();
                    view.showFindCityError();
                }
            }
        });
    }

    public boolean onCloseListenerPressed() {
        if (view != null) {
            view.clearSearchView();
        }
        return true;
    }
}
