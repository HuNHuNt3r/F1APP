package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.view.DriverAddView;
import hu.aut.bme.dg.f1app.view.TeamAddView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamAddPresenter extends Presenter<TeamAddView> {

    @Inject
    public TeamInteractor teamInteractor;

    public TeamAddPresenter() {
        F1Application.injector.inject(this);
    }


    public void saveTeam(String teamName, String teamLeader, String teamImage) {

        Team newTeam = new Team();
        newTeam.teamName = teamName;
        newTeam.teamLeader = teamLeader;
        newTeam.teamImage = teamImage;

        teamInteractor.insertTeam(newTeam);
        view.saveTeam();
    }

    public void browseTeamImage() {
        view.browseTeamImage();
    }

}
