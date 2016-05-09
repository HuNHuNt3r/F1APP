package hu.aut.bme.dg.f1app.interactor;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.DriverModel;

/**
 * Created by DG on 2016.05.09..
 */
public class DriverInteractor {

    @Inject
    DriverModel model;

    private List<DriverModel> drivers;

    public DriverInteractor() {

        F1Application.injector.inject(this);
        this.drivers = new ArrayList<DriverModel>();
    }

    public List<DriverModel> getDrivers() {
        return drivers;
    }

    public DriverModel getDriver(int i) {
        return this.drivers.get(i);
    }

    public void addDriver() {

        DriverModel driver = new DriverModel("TesztElek", 7, 30,  1);
        SugarRecord.save(driver);
    }
}
