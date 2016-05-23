package hu.aut.bme.dg.f1app;

import javax.inject.Singleton;

import dagger.Component;
import hu.aut.bme.dg.f1app.F1ApplicationComponent;
import hu.aut.bme.dg.f1app.interactor.InteractorModule;
import hu.aut.bme.dg.f1app.model.mock.MockModelModule;
import hu.aut.bme.dg.f1app.network.mock.MockNetworkModule;
import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.05.23..
 */
@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, MockModelModule.class, MockNetworkModule.class})
public interface MockF1ApplicationComponent extends F1ApplicationComponent {

}

