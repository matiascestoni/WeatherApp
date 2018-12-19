package weather.application.com.weatherapp.domain;

import android.content.Context;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Matias Cestoni.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = Build.VERSION_CODES.O)
public class CityWeatherTest {

    @Mock
    private ForecastWeather forecastWeather;

    @Mock
    private GeoCity geoCity;

    @Mock
    private Context context;

    @Mock
    private Current current;

    @Mock
    private Condition condition;

    private CityWeather cityWeather;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        context =  ApplicationProvider.getApplicationContext();
        cityWeather = new CityWeather(geoCity, forecastWeather);
        when(forecastWeather.getCurrent()).thenReturn(current);
    }

    @Test
    public void getHumidity_GetHumidity() {
        when(forecastWeather.getCurrent()).thenReturn(null);

        assertEquals("", cityWeather.getHumidity(context));
    }

    @Test
    public void getHumidity_HumidityIsNull_GetEmptyString() {
        when(current.getHumidity()).thenReturn(85);

        assertEquals("Humidity: 85%", cityWeather.getHumidity(context));
    }

    @Test
    public void getWeatherCondition_GetWeatherCondition() {
        when(current.getCondition()).thenReturn(condition);
        when(condition.getText()).thenReturn("Isolated thundershowers");

        assertEquals("Isolated thundershowers", cityWeather.getCondition());
    }

    @Test
    public void getForecastImageIcon_GetForecastImageIcon() {
        when(current.getCondition()).thenReturn(condition);
        when(current.getIs_day()).thenReturn(1);
        when(condition.getCode()).thenReturn(1000);

        assertEquals("Weather Forecast Image Icon should be", "01d.jpg", cityWeather.getImageIcon());
    }
}
