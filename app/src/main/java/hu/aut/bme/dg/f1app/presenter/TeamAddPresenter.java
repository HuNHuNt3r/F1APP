package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.view.DriverAddView;
import hu.aut.bme.dg.f1app.view.TeamAddView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamAddPresenter extends Presenter<TeamAddView> {


    public TeamAddPresenter() {
        F1Application.injector.inject(this);
    }

    public void saveTeam() {
        view.saveTeam();
    }

}
