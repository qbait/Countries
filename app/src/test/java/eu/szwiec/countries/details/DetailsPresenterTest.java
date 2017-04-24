package eu.szwiec.countries.details;

import org.junit.Before;
import org.junit.Test;

import eu.szwiec.countries.data.model.Country;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by szwiec on 23/04/2017.
 */
public class DetailsPresenterTest {

    private DetailsPresenter presenter;
    private DetailsView mockView;

    @Before
    public void setUp() throws Exception {
        presenter = new DetailsPresenter();
        mockView = mock(DetailsView.class);

        presenter.attachView(mockView);
    }

    @Test
    public void showFlagAndName() {
        Country country = new Country();
        country.setName("Italy");
        country.setAlpha2Code("it");

        presenter.showCountry(country);

        verify(mockView, times(1)).setTitle("Italy");
        verify(mockView, times(1)).setFlag(country.getFlagUrl());
    }

}