package hu.aut.bme.dg.f1app;

import javax.inject.Singleton;

import dagger.Component;
import hu.aut.bme.dg.f1app.interactor.InteractorModule;
import hu.aut.bme.dg.f1app.interactor.StringInteractor;
import hu.aut.bme.dg.f1app.model.ModelModule;
import hu.aut.bme.dg.f1app.presenter.MainPresenter;
import hu.aut.bme.dg.f1app.view.MainActivity;
import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.04.24..
 */
@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, ModelModule.class})
public interface F1ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(StringInteractor stringInteractor);
}
