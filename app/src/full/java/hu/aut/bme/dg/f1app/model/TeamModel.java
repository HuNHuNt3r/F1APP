package hu.aut.bme.dg.f1app.model;

import com.orm.dsl.Table;

/**
 * Created by DG on 2016.05.09..
 */
@Table
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
