package hu.aut.bme.dg.f1app.view;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;

/**
 * Created by DG on 2016.05.08..
 */
public interface TeamEditView {

    void showTeam(Team editTeam);
    void saveTeam();
    void deleteTeam();
    void browseTeamImage();

}
