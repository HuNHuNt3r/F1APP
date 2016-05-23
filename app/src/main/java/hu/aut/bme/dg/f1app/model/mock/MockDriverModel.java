package hu.aut.bme.dg.f1app.model.mock;

import java.util.LinkedList;
import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.prod.DriverModel;

/**
 * Created by DG on 2016.05.23..
 */
public class MockDriverModel extends DriverModel {

    LinkedList<Driver> values = new LinkedList<>();

    public MockDriverModel() {

        Driver test1 = new Driver("Sebastian Vettel", 5, 28, null, null);
        test1.driverId = 1;

        Driver test2 = new Driver("Daniel Ricciardo", 3, 26, null, null);
        test2.driverId = 2;

        values.add(test1);
        values.add(test2);
    }

    @Override
    public List<Driver> getDrivers() {

        return values;
    }

    @Override
    public Driver getDriver(int driverId) {

        for (Driver driver : values) {
            if(driver.driverId == driverId)
                return driver;
        }

        return null;
    }

    @Override
    public void insertDriver(Driver toInsert) {

        int id = 0;
        boolean contains = false;

        while(id == 0 || contains)
        {
            id++;
            contains = false;
            for(int j = 0; j < values.size(); j++)
            {
                if(values.get(j).driverId == id) {
                    contains = true;
                }
            }

        }

        toInsert.driverId = id;
        values.add(toInsert);
    }

    @Override
    public void updateDriver(Driver newDriver){

        deleteDriver(newDriver.driverId);
        insertDriver(newDriver);

//        Driver driver = Driver.findById(Driver.class, newDriver.driverId);
//        driver.driverName = newDriver.driverName;
//        driver.driverNumber = newDriver.driverNumber;
//        driver.driverAge = newDriver.driverAge;
//        driver.driverImage = newDriver.driverImage;
//        driver.driverTeam = newDriver.driverTeam;

    }

    @Override
    public void deleteDriver(int driverId){

        for (Driver driver : values) {
            if(driver.driverId == driverId)
                values.remove(driverId);
        }
    }

}
