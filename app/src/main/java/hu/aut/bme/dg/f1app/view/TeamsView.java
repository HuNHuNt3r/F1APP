package hu.aut.bme.dg.f1app.view;

import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;

/**
 * Created by DG on 2016.05.08..
 */
public interface TeamsView {

    void showMain();

    void refreshTeams(List<Team> teams);

    void showTeamAdd();

    void showTeamDetails(long teamId);

}
