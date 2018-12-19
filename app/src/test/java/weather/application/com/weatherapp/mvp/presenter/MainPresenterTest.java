package weather.application.com.weatherapp.mvp.presenter;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import weather.application.com.weatherapp.mvp.model.WeatherModel;
import weather.application.com.weatherapp.mvp.view.MainView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Matias Cestoni.
 */
public class MainPresenterTest {

    @Mock
    private WeatherModel model;

    @Mock
    private MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(model);
    }

    @Test
    public void onAddCityButtonClicked_ViewIsNull_NoInteractWithTheView() {
        presenter.setView(null);
        presenter.onAddCityButtonClicked();

        verifyZeroInteractions(model, view);
    }

    @Test
    public void onAddCityButtonClicked_ViewIsNotNull_InteractWithTheView() {
        presenter.setView(view);
        presenter.onAddCityButtonClicked();

        verify(view).navigateToSearchView();
    }

    @Test
    public void onCityWeatherSwiped_RemoveCityWeatherFromList() {
        presenter.setView(view);
        presenter.onCityWeatherSwiped(2);

        verify(view).removeCityWeatherFromList(2);
    }
}
