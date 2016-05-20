package hu.aut.bme.dg.f1app.view;

import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.DriverModel;

/**
 * Created by DG on 2016.05.07..
 */
public interface DriversView {

    void refreshDrivers(List<Driver> drivers);

    void showDriverAdd();

    void showDriverDetails(int driverId);



}
