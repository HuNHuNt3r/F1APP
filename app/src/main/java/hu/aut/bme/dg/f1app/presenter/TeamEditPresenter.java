package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.view.DriverEditView;
import hu.aut.bme.dg.f1app.view.TeamEditView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamEditPresenter extends Presenter<TeamEditView> {

    public TeamEditPresenter() {
        F1Application.injector.inject(this);
    }

    public void saveTeam() {
        view.saveTeam();
    }

    public void deleteTeam() {
        view.deleteTeam();
    }

}