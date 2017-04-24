package eu.szwiec.countries.main;

import org.junit.Before;
import org.junit.Test;

import eu.szwiec.countries.data.Repository;

import static org.mockito.Mockito.mock;

/**
 * Created by szwiec on 23/04/2017.
 */
public class MainPresenterTest {

    private MainPresenter presenter;
    private MainView mockView;
    private Repository mockRepository;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter();
        mockView = mock(MainView.class);
        mockRepository = mock(Repository.class);

        presenter.attachView(mockView);
    }

    @Test
    public void shouldShowContentWhenLoadFromRepository() {
        //TODO
    }

    @Test
    public void shouldShowErrorWhenLoadFromRepository() {
        //TODO
    }

    @Test
    public void shouldShowContentWhenLoadFromRemoteDatastore() {
        //TODO
    }

    @Test
    public void shouldShowErrorWhenLoadFromRemoteDatastore() {
        //TODO
    }
}