package weather.application.com.weatherapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import weather.application.com.weatherapp.domain.ForecastDay;
import weather.application.com.weatherapp.ui.fragments.FutureDayWeatherFragment;

/**
 * @author Matias Cestoni.
 */
public class DaysViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<ForecastDay> nextDays;

    public DaysViewPagerAdapter(FragmentManager fragmentManager, List<ForecastDay> nextDays) {
        super(fragmentManager);
        this.nextDays = nextDays;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nextDays.get(position).getDateToShow();
    }

    @Override
    public Fragment getItem(int position) {
        return FutureDayWeatherFragment.newInstance(nextDays.get(position));
    }

    @Override
    public int getCount() {
        return nextDays != null ? nextDays.size() : 0;
    }
}
