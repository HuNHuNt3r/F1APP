package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.model.Driver;
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


    public void saveDriver(String driverName, int driverNumber, int driverAge, String driverImage) {

        Driver newDriver = new Driver();
        newDriver.driverName = driverName;
        newDriver.driverAge = driverAge;
        newDriver.driverNumber = driverNumber;
        newDriver.driverImage = driverImage;

        driverInteractor.insertDriver(newDriver);
        view.saveDriver();
    }

    public void browseDriverImage() {
        view.browseDriverImage();
    }


}
