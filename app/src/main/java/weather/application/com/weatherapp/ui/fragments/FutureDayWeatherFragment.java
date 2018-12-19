package weather.application.com.weatherapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.ForecastDay;
import weather.application.com.weatherapp.ui.adapters.FutureDaysViewAdapter;

/**
 * @author Matias Cestoni.
 */
public class FutureDayWeatherFragment extends Fragment {

    public final static String FORECAST_DAY_PARAM = "forecast_param";

    public static FutureDayWeatherFragment newInstance(ForecastDay forecastDay) {
        Bundle args = new Bundle();
        args.putSerializable(FORECAST_DAY_PARAM, forecastDay);
        FutureDayWeatherFragment dayWeatherFragment = new FutureDayWeatherFragment();
        dayWeatherFragment.setArguments(args);
        return dayWeatherFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_days_tabs, container, false);
        ForecastDay forecastDay = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            forecastDay = (ForecastDay) bundle.getSerializable(FORECAST_DAY_PARAM);
        }
        RecyclerView recyclerView = view.findViewById(R.id.days_list);
        FutureDaysViewAdapter adapter = new FutureDaysViewAdapter(forecastDay);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
