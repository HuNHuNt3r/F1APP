package hu.aut.bme.dg.f1app.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by DG on 2016.05.19..
 */
public class Team extends SugarRecord {

    public int teamId;
    public String teamName;
    public String teamLeader;
    public String teamImage;
    //public Driver firstDriver;
    //public Driver secondDriver;

    public Team() {

    }

    public Team(String teamName, String teamLeader, String teamImage){ //, Driver firstDriver, Driver secondDriver){

        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.teamImage = teamImage;
        //this.firstDriver = firstDriver;
        //this.secondDriver = secondDriver;
    }



}
