package hu.aut.bme.dg.f1app.interactor;

import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.model.prod.TeamModel;
import hu.aut.bme.dg.f1app.network.TeamsApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by DG on 2016.05.09..
 */
public class TeamInteractor {

    @Inject
    TeamModel model;

    @Inject
    TeamsApi teamsApi;

    public TeamInteractor() {

        F1Application.injector.inject(this);
    }

    public List<Team> getTeams() {
        return model.getTeams();
    }

    public List<Team> getTeamsFromNetWork() throws Exception {
        Response<List<Team>> response = null;

        Call<List<Team>> call = teamsApi.teamsGet(null);
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

    public Team getTeam(int teamId) {
        return model.getTeam(teamId);
    }

    public void insertTeam(Team newTeam)
    {
        model.insertTeam(newTeam);
    }

    public void insertTeamToNetwork(Team toAdd) throws Exception {
        Response response = null;

        Call call = teamsApi.teamsGet(toAdd.getId());
        try {
            response = call.execute();
        } catch (Exception e) {
            throw new Exception("Network error on execute with post!");
        }
        if (response.code() != 200) {
            throw new Exception("Network error with post!");
        }
    }

    public void updateTeam(Team editTeam){

        model.updateTeam(editTeam);
    }

    public void deleteTeam(int teamId){

        model.deleteTeam(teamId);
    }
}
