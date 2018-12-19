package weather.application.com.weatherapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.mvp.presenter.CityWeatherDetailPresenter;
import weather.application.com.weatherapp.mvp.view.CityWeatherDetailsView;
import weather.application.com.weatherapp.ui.fragments.CityWeatherDetailFragment;
import weather.application.com.weatherapp.util.ConditionWeatherUtil;

import static weather.application.com.weatherapp.ui.activities.MainActivity.CITY_WEATHER_PARAM;

/**
 * @author Matias Cestoni.
 */
public class CityWeatherDetailsActivity extends AppCompatActivity implements CityWeatherDetailsView {

    private CityWeatherDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_weather_details);

        CollapsingToolbarLayout appBarLayout = findViewById(R.id.toolbar_layout);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        TextView temperature = findViewById(R.id.header_temp);
        TextView humidity = findViewById(R.id.header_humidity);
        TextView wind = findViewById(R.id.header_wind);
        TextView pressure = findViewById(R.id.header_pressure);
        TextView condition = findViewById(R.id.header_condition);
        TextView date = findViewById(R.id.header_date);
        ImageView imageView = findViewById(R.id.image_background);

        Bundle extras = getIntent().getExtras();
        CityWeather cityWeather = null;

        if (extras != null) {
            cityWeather = (CityWeather) extras.getSerializable(CITY_WEATHER_PARAM);
        }

        presenter = new CityWeatherDetailPresenter();
        presenter.setView(this);

        if (cityWeather != null) {
            imageView.setImageBitmap(ConditionWeatherUtil.getImageFromAssetsFile(this, cityWeather.getImageIcon()));
            imageView.setTransitionName(cityWeather.getCity().getName());
            date.setText(cityWeather.getDate());
            condition.setText(cityWeather.getCondition());
            humidity.setText(cityWeather.getHumidity(this));
            pressure.setText(cityWeather.getPressure(this));
            wind.setText(cityWeather.getWindText(this));
            temperature.setText(cityWeather.getTemperature(this));
            appBarLayout.setTitle(cityWeather.getCity().getName());
        }

        initEnterAnimation();

        if (savedInstanceState == null) {
            CityWeatherDetailFragment fragment = CityWeatherDetailFragment.newInstance(cityWeather);
            getSupportFragmentManager().beginTransaction().add(R.id.city_detail_container, fragment).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setView(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
        initExitAnimation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void initEnterAnimation() {
        final Transition sharedElementEnterTransition = getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                sharedElementEnterTransition.removeListener(this);
            }
        });
    }

    private void initExitAnimation() {
        final Transition elementExitTransition = getWindow().getSharedElementReturnTransition();
        elementExitTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                CityWeatherDetailFragment fragment = (CityWeatherDetailFragment) getSupportFragmentManager().findFragmentById(R.id.city_detail_container);
                if (fragment != null) {
                    fragment.clearContent();
                }
                elementExitTransition.removeListener(this);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }
}
