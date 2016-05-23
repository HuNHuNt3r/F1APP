package hu.aut.bme.dg.f1app;

import javax.inject.Singleton;

import dagger.Component;
import hu.aut.bme.dg.f1app.interactor.InteractorModule;
import hu.aut.bme.dg.f1app.network.mock.MockNetworkModule;

/**
 * Created by DG on 2016.05.23..
 */
@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class})
public interface TestComponent extends F1ApplicationComponent {
}
