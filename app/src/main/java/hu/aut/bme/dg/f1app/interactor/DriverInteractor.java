package hu.aut.bme.dg.f1app.interactor;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.DriverModel;

/**
 * Created by DG on 2016.05.09..
 */
public class DriverInteractor {

    @Inject
    DriverModel model;

    public DriverInteractor() {

        F1Application.injector.inject(this);
    }

    public List<Driver> getDrivers() {
        return model.getDrivers();
    }

    public Driver getDriver(int driverId) {
        return model.getDriver(driverId);
    }

    public void insertDriver(Driver newDriver)
    {
        model.insertDriver(newDriver);
    }

    public void updateDriver(Driver editDriver){

        model.updateDriver(editDriver);
    }

    public void deleteDriver(int driverId){

        model.deleteDriver(driverId);
    }

}
