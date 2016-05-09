package hu.aut.bme.dg.f1app.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by DG on 2016.05.09..
 */
@Table
public class DriverModel {

    private Long id;

    private String driverName;
    private int driverNumber;
    private int driverAge;
    private long driverTeamId;

    public DriverModel() {

    }

    public DriverModel(String driverName, int driverNumber, int driverAge, long driverTeamId){
        this.driverName = driverName;
        this.driverNumber = driverNumber;
        this.driverAge = driverAge;
        this.driverTeamId =  driverTeamId;
    }

    public String getName() {
        return this.driverName;
    }
}

