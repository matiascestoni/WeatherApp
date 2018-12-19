package weather.application.com.weatherapp.mvp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.mvp.view.AddCityView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Matias Cestoni.
 */
@RunWith(RobolectricTestRunner.class)
public class AddCityPresenterTest {

    @Mock
    private AddCityView view;

    @Mock
    private GeoCity geoCity;

    private AddCityPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddCityPresenter();
    }

    @Test
    public void onCitySelected_ViewIsNull_NoInteractionWithTheView() {
        presenter.setView(null);
        presenter.onCitySelected(geoCity);

        verifyZeroInteractions(view);
    }

    @Test
    public void onCitySelected_ViewIsNotNull_InteractWithTheView() {
        presenter.setView(view);
        presenter.onCitySelected(geoCity);

        verify(view).navigateToMainView(geoCity);
    }

    @Test
    public void onTextChanged_ViewIsNull_NoInteractionWithTheView() {
        presenter.setView(null);
        presenter.onTextChanged("something");

        verifyZeroInteractions(view);
    }

    @Test
    public void onTextChanged_QueryIsNull_HideProgressBar() {
        presenter.setView(view);
        presenter.onTextChanged(null);

        verify(view).clearSearchView();
        verify(view).hideProgressBar();
    }

    @Test
    public void onTextChanged_QueryIsNotNull_ShowProgressBar() {
        presenter.setView(view);
        presenter.onTextChanged("something");

        verify(view).showProgressBar();
    }
}
