package hu.aut.bme.dg.f1app.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.DriverModel;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.model.TeamModel;

/**
 * Created by DG on 2016.05.09..
 */
public class TeamInteractor {

    @Inject
    TeamModel model;

    public TeamInteractor() {

        F1Application.injector.inject(this);
    }

    public List<Team> getTeams() {
        return model.getTeams();
    }

    public Team getTeam(int teamId) {
        return model.getTeam(teamId);
    }

    public void insertTeam(Team newTeam)
    {
        model.insertTeam(newTeam);
    }

    public void updateTeam(Team editTeam){

        model.updateTeam(editTeam);
    }

    public void deleteTeam(int teamId){

        model.deleteTeam(teamId);
    }
}
