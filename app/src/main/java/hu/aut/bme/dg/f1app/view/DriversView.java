package hu.aut.bme.dg.f1app.view;

import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;

/**
 * Created by DG on 2016.05.07..
 */
public interface DriversView {

    void showMain();

    void refreshDrivers(List<Driver> drivers);

    void showDriverAdd();

    void showDriverDetails(long driverId);



}
