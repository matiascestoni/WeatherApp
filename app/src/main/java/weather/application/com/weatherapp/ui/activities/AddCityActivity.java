package weather.application.com.weatherapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.mvp.presenter.AddCityPresenter;
import weather.application.com.weatherapp.mvp.view.AddCityView;
import weather.application.com.weatherapp.ui.adapters.AddCityAdapter;
import weather.application.com.weatherapp.ui.listeners.CityItemListener;

import static weather.application.com.weatherapp.ui.activities.MainActivity.CITY_PARAM;

/**
 * @author Matias Cestoni.
 */
public class AddCityActivity extends AppCompatActivity implements CityItemListener, AddCityView {

    private AddCityPresenter presenter;
    private ProgressBar progressBar;
    private AddCityAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        presenter = new AddCityPresenter();
        presenter.setView(this);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setOnCloseListener(() -> presenter.onCloseListenerPressed());
        progressBar = findViewById(R.id.progressBar);

        RecyclerView recyclerView = findViewById(R.id.city_search_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new AddCityAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            presenter.onTextChanged(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            presenter.onTextChanged(query);
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCitySelected(GeoCity city) {
        presenter.onCitySelected(city);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setView(null);
    }

    @Override
    public void navigateToMainView(GeoCity city) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(CITY_PARAM, city);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFindCityError() {
        Toast.makeText(this, getString(R.string.city_not_found), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addFoundCitiesToView(List<GeoCity> cities) {
        adapter.addCities(cities);
    }

    @Override
    public void clearSearchView() {
        adapter.clear();
        searchView.clearFocus();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
