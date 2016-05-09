package hu.aut.bme.dg.f1app.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.TeamModel;

/**
 * Created by DG on 2016.05.09..
 */
public class TeamInteractor {

    @Inject
    TeamModel model;

    private List<TeamModel> teams;

    public TeamInteractor() {

        F1Application.injector.inject(this);
        this.teams = new ArrayList<TeamModel>();
    }

    public List<TeamModel> getTeams() {
        return teams;
    }

    public TeamModel getTeam(int i) {
        return this.teams.get(i);
    }
}
