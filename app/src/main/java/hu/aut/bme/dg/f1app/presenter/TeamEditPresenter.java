package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.view.DriverEditView;
import hu.aut.bme.dg.f1app.view.TeamEditView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamEditPresenter extends Presenter<TeamEditView> {

    @Inject
    public TeamInteractor teamInteractor;

    public TeamEditPresenter() {
        F1Application.injector.inject(this);
    }

    public void showTeam(int teamId) {

        try {
            view.showTeam(teamInteractor.getTeam(teamId));
        } catch (Exception e) {
            view.showTeam(teamInteractor.getTeam(teamId));
            //view.showMessage(e.getMessage());
        }
    }

    public void saveTeam(Team editTeam) {
        teamInteractor.updateTeam(editTeam);
        view.saveTeam();
    }

    public void deleteTeam(int teamId) {
        teamInteractor.deleteTeam(teamId);
        view.deleteTeam();
    }

    public void browseTeamImage() {
        view.browseTeamImage();
    }

}