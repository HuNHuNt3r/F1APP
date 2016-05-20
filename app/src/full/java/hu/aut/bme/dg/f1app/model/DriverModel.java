package hu.aut.bme.dg.f1app.model;

import java.util.List;

/**
 * Created by DG on 2016.05.17..
 */
public class DriverModel {

    public DriverModel() {
    }

    public List<Driver> getDrivers() {

        List<Driver> values = Driver.listAll(Driver.class);
        return values;
    }

    public Driver getDriver(int driverId) {

        Driver driver = Driver.findById(Driver.class, driverId);
        return driver;
    }

    public void insertDriver(Driver toInsert) {

        toInsert.save();
    }

    public void updateDriver(Driver newDriver){

        Driver driver = Driver.findById(Driver.class, newDriver.driverId);
        driver.driverName = newDriver.driverName;
        driver.driverNumber = newDriver.driverNumber;
        driver.driverAge = newDriver.driverAge;
        driver.driverImage = newDriver.driverImage;
        driver.driverTeam = newDriver.driverTeam;
        driver.save();
    }

    public void deleteDriver(int driverId){

        Driver driver = Driver.findById(Driver.class, driverId);
        driver.delete();
    }

}
