package hu.aut.bme.dg.f1app.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by DG on 2016.05.09..
 */
public class TeamModel {

    public TeamModel() {
    }

    public List<Team> getTeams() {

        List<Team> values = Team.listAll(Team.class);
        return values;
    }

    public Team getTeam(int teamId) {

        Team team = Team.findById(Team.class, teamId);
        return team;
    }

    public void insertTeam(Team toInsert) {

        toInsert.save();
    }

    public void updateTeam(Team newTeam){

        Team team = Team.findById(Team.class, newTeam.teamId);
        team.teamName = newTeam.teamName;
        team.teamLeader = newTeam.teamLeader;
        team.teamImage = newTeam.teamImage;
//        team.firstDriver = newTeam.firstDriver;
//        team.secondDriver = newTeam.secondDriver;
        team.save();
    }

    public void deleteTeam(int teamId){

        Team team = Team.findById(Team.class, teamId);
        team.delete();
    }

}
