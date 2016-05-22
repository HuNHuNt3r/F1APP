package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.view.DriversView;
import hu.aut.bme.dg.f1app.view.TeamsView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamsPresenter extends Presenter<TeamsView> {

    @Inject
    public TeamInteractor teamInteractor;

    public TeamsPresenter() {
        F1Application.injector.inject(this);
    }

    public void showMain() {
        view.showMain();
    }

    public void refreshTeams() {

        try {
            view.refreshTeams(teamInteractor.getTeams());
        } catch (Exception e) {
            view.refreshTeams(teamInteractor.getTeams());
            //view.showMessage(e.getMessage());
        }

    }

    public void showTeamAdd() {
        view.showTeamAdd();
    }

    public void showTeamDetails(long teamId) {
        view.showTeamDetails(teamId);
    }

}
