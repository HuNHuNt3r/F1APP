package hu.aut.bme.dg.f1app.interactor;

import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.model.prod.DriverModel;
import hu.aut.bme.dg.f1app.network.DriversApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by DG on 2016.05.09..
 */
public class DriverInteractor {

    @Inject
    DriverModel model;

    @Inject
    DriversApi driversApi;

    public DriverInteractor() {

        F1Application.injector.inject(this);
    }

    public List<Driver> getDrivers() {
        return model.getDrivers();
    }

    public List<Driver> getDriversFromNetWork() throws Exception {
        Response<List<Driver>> response = null;

        Call<List<Driver>> call = driversApi.driversGet(null);
        try {
            response = call.execute();
        } catch (Exception e) {
            throw new Exception("Network error on execute with get!");
        }
        if (response.code() != 200) {
            throw new Exception("Network error with get!");
        }

        return response.body();
    }

    public Driver getDriver(int driverId) {
        return model.getDriver(driverId);
    }

    public void insertDriver(Driver newDriver)
    {
        model.insertDriver(newDriver);
    }

    public void insertDriverToNetwork(Driver toAdd) throws Exception {
        Response response = null;

        Call call = driversApi.driversGet(toAdd.getId());
        try {
            response = call.execute();
        } catch (Exception e) {
            throw new Exception("Network error on execute with post!");
        }
        if (response.code() != 200) {
            throw new Exception("Network error with post!");
        }
    }


    public void updateDriver(Driver editDriver){

        model.updateDriver(editDriver);
    }

    public void deleteDriver(int driverId){

        model.deleteDriver(driverId);
    }

}
