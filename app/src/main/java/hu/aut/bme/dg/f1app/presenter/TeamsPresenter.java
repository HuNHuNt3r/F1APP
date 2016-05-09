package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.view.DriversView;
import hu.aut.bme.dg.f1app.view.TeamsView;

/**
 * Created by DG on 2016.05.08..
 */
public class TeamsPresenter extends Presenter<TeamsView> {

    public TeamsPresenter() {
        F1Application.injector.inject(this);
    }

    public void refreshTeams() {
//        networkExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                artistsInteractor.getArtists(artistQuery);
//            }
//        });
    }

    public void showTeamAdd() {
        view.showTeamAdd();
    }

    public void showTeamDetails(int teamId) {
        view.showTeamDetails(teamId);
    }

}
