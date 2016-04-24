package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.StringInteractor;
import hu.aut.bme.dg.f1app.view.MainView;

/**
 * Created by DG on 2016.04.24..
 */
public class MainPresenter extends Presenter<MainView> {

    @Inject
    public StringInteractor interactor;

    public MainPresenter() {
        F1Application.injector.inject(this);
    }

    public void doStuff() {
        view.showString(interactor.getString());
    }
}
