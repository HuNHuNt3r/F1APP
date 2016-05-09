package hu.aut.bme.dg.f1app.testmodel;

/**
 * Created by DG on 2016.05.09..
 */
public class TeamModel {

    private Long id;

    private String teamName;
    private String teamLeaderName;
    private int firstDriverId;
    private int secondDriverId;

    public TeamModel() {

    }

    public String getName() {
        return this.teamName;
    }

}
