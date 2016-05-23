package hu.aut.bme.dg.f1app.unittests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.BuildConfig;
import hu.aut.bme.dg.f1app.RobolectricDaggerTestRunner;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.network.mock.TeamMock;

import static hu.aut.bme.dg.f1app.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;

/**
 * Created by DG on 2016.05.23..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TeamUnitTest {

    @Inject
    public TeamInteractor interactor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        interactor = new TeamInteractor();
    }

    @Test
    public void getterTest() throws Exception {
        String name = "ASDASDASD Team";
        Team p = new Team(name, "Béla", null, null, null);
        assertEquals(p.teamName.equals(name), true);
    }

    @Test
    public void mockGetTest() throws Exception {
        TeamMock.resetList();
        if (BuildConfig.FLAVOR == "mock") {
            List<Team> p = interactor.getTeamsFromNetWork();
            assertEquals(p.get(0).teamName, TeamMock.testP1.teamName);
            assertEquals(p.get(1).teamName, TeamMock.testP2.teamName);
        }
    }

    @Test
    public void mockAddTest() throws Exception {
        TeamMock.resetList();
        if (BuildConfig.FLAVOR == "mock") {
            Team n = new Team("ASDASDASD Team", "Béla", null, null, null);
            interactor.insertTeamToNetwork(n);
            List<Team> p = interactor.getTeamsFromNetWork();
            assertEquals(p.get(1).teamName, TeamMock.testP1.teamName);
            assertEquals(p.get(2).teamName, TeamMock.testP2.teamName);
            assertEquals(p.get(0).teamName, n.teamName);
        }
    }

}
