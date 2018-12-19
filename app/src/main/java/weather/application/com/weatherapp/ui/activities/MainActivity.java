package weather.application.com.weatherapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.mvp.model.WeatherModel;
import weather.application.com.weatherapp.mvp.presenter.MainPresenter;
import weather.application.com.weatherapp.mvp.view.MainView;
import weather.application.com.weatherapp.ui.adapters.ListWeatherCityAdapter;
import weather.application.com.weatherapp.ui.listeners.CityWeatherListener;

public class MainActivity extends AppCompatActivity implements MainView, CityWeatherListener {

    public static final int REQUEST_CODE = 1;
    public static final String CITY_PARAM = "city_param";
    public static final String CITY_WEATHER_PARAM = "city_weather_param";
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainPresenter presenter;
    private ListWeatherCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MainPresenter(new WeatherModel());
        presenter.setView(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> presenter.onAddCityButtonClicked());

        RecyclerView recyclerView = findViewById(R.id.selected_cities_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListWeatherCityAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefreshLayoutSwiped());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            presenter.onSettingsButtonClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setView(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                GeoCity city = (GeoCity) data.getSerializableExtra(CITY_PARAM);
                presenter.onCitySelected(city);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                presenter.onCityCancelled();
            }
        }
    }

    public ItemTouchHelper.SimpleCallback itemTouchCallback =
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                      @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    presenter.onCityWeatherSwiped(viewHolder.getAdapterPosition());
                }
            };

    @Override
    public void navigateToSearchView() {
        Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void navigateToSettingsView() {
        //TODO Implement navigate to Settings View
    }

    @Override
    public void showCityCancelledMessage() {
        Toast.makeText(this, R.string.no_city_selected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCityWeatherListView(List<CityWeather> cityWeatherList) {
        if (adapter != null) {
            adapter.setCityWeatherList(cityWeatherList);
        }
    }

    @Override
    public void navigateToCityWeatherDetails(CityWeather cityWeather) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CITY_WEATHER_PARAM, cityWeather);
        Intent intent = new Intent(MainActivity.this, CityWeatherDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void removeCityWeatherFromList(int position) {
        adapter.removeCity(position);
    }

    @Override
    public void showErrorRetrievingWeather() {
        Toast.makeText(this, getString(R.string.error_retrieving_weather), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCityWeatherSelected(CityWeather cityWeather) {
        presenter.onCityWeatherSelected(cityWeather);
    }

    @Override
    public void onCityWeatherRemoved(CityWeather cityWeather) {
        presenter.onCityWeatherRemoved(cityWeather);
    }
}
