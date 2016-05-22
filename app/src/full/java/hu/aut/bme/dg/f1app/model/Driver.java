package hu.aut.bme.dg.f1app.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by DG on 2016.05.09..
 */
public class Driver extends SugarRecord {

    @Ignore
    public int driverId;

    public String driverName;
    public int driverNumber;
    public int driverAge;
    public String driverImage;
    public Team driverTeam;

    public Driver() {

    }

    public Driver(String driverName, int driverNumber, int driverAge, String driverImage, Team driverTeam){

        this.driverName = driverName;
        this.driverNumber = driverNumber;
        this.driverAge = driverAge;
        this.driverImage = driverImage;
        this.driverTeam = driverTeam;

    }

}

