package weather.application.com.weatherapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.ui.adapters.DaysViewPagerAdapter;

/**
 * @author Matias Cestoni.
 */
public class CityWeatherDetailFragment extends Fragment {

    public static final String CITY_WEATHER_PARAM = "city_weather_param";
    private TabLayout tabLayout;
    private CityWeather cityWeather;

    public static CityWeatherDetailFragment newInstance(CityWeather cityWeather) {
        Bundle args = new Bundle();
        args.putSerializable(CITY_WEATHER_PARAM, cityWeather);
        CityWeatherDetailFragment cityDetailFragment = new CityWeatherDetailFragment();
        cityDetailFragment.setArguments(args);
        return cityDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            cityWeather = (CityWeather) bundle.getSerializable(CITY_WEATHER_PARAM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewPager viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        FragmentManager fragmentManager = getChildFragmentManager();

        DaysViewPagerAdapter viewPagerAdapter = new DaysViewPagerAdapter(fragmentManager, cityWeather.getForecast().getForecast().getForecastday());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.setVisibility(View.VISIBLE);
    }

    public void clearContent() {
        tabLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
