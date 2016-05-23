package hu.aut.bme.dg.f1app.model.mock;

import java.util.LinkedList;
import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.model.prod.TeamModel;

/**
 * Created by DG on 2016.05.23..
 */
public class MockTeamModel extends TeamModel {

    LinkedList<Team> values = new LinkedList<>();

    public MockTeamModel() {

        Team test1 = new Team("Scuderia Ferrari", "Maurizio Arrivabene", null, null, null);
        test1.teamId = 1;

        Team test2 = new Team("Red Bull Racing", "Christian Horner", null, null, null);
        test1.teamId = 2;

        values.add(test1);
        values.add(test2);
    }

    @Override
    public List<Team> getTeams() {

        return values;
    }

    @Override
    public Team getTeam(int teamId) {

        for (Team team : values) {
            if(team.teamId == teamId)
                return team;
        }

        return null;
    }

    @Override
    public void insertTeam(Team toInsert) {

        int id = 0;
        boolean contains = false;

        while(id == 0 || contains)
        {
            id++;
            contains = false;
            for(int j = 0; j < values.size(); j++)
            {
                if(values.get(j).teamId == id) {
                    contains = true;
                }
            }

        }

        toInsert.teamId = id;
        values.add(toInsert);
    }

    @Override
    public void updateTeam(Team newTeam){

        deleteTeam(newTeam.teamId);
        insertTeam(newTeam);

//        Team team = values.get(newTeam.teamId);
//        team.teamName = newTeam.teamName;
//        team.teamLeader = newTeam.teamLeader;
//        team.teamImage = newTeam.teamImage;
//        team.firstDriver = newTeam.firstDriver;
//        team.secondDriver = newTeam.secondDriver;

    }

    @Override
    public void deleteTeam(int teamId){

        for (Team team : values) {
            if(team.teamId == teamId)
                values.remove(teamId);
        }
    }
}
