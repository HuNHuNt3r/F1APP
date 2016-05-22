package hu.aut.bme.dg.f1app.presenter;

import android.util.Log;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.view.DriverAddView;
import hu.aut.bme.dg.f1app.view.DriverEditView;

/**
 * Created by DG on 2016.05.08..
 */
public class DriverEditPresenter extends Presenter<DriverEditView> {

    @Inject
    public DriverInteractor driverInteractor;

    public DriverEditPresenter() {
        F1Application.injector.inject(this);
    }

    public void showDriver(int driverId) {

        try {
            view.showDriver(driverInteractor.getDriver(driverId));
        } catch (Exception e) {
            view.showDriver(driverInteractor.getDriver(driverId));
            //view.showMessage(e.getMessage());
        }
    }

    public void saveDriver(Driver editDriver) {
        driverInteractor.updateDriver(editDriver);
        view.saveDriver();
    }

    public void deleteDriver(int driverId) {
        driverInteractor.deleteDriver(driverId);
        view.deleteDriver();
    }

    public void browseDriverImage() {
        view.browseDriverImage();
    }

}
