package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.view.DriverAddView;
import hu.aut.bme.dg.f1app.view.DriversView;

/**
 * Created by DG on 2016.05.08..
 */
public class DriverAddPresenter extends Presenter<DriverAddView> {

    @Inject
    public DriverInteractor driverInteractor;

    public DriverAddPresenter() {
        F1Application.injector.inject(this);
    }


    public void saveDriver() {

        driverInteractor.addDriver();
        view.saveDriver();
    }


}
