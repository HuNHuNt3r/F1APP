package hu.aut.bme.dg.f1app.unittests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.BuildConfig;
import hu.aut.bme.dg.f1app.RobolectricDaggerTestRunner;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.network.mock.DriverMock;
import hu.aut.bme.dg.f1app.network.mock.TeamMock;

import static hu.aut.bme.dg.f1app.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;

/**
 * Created by DG on 2016.05.23..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DriverUnitTest {

    @Inject
    public DriverInteractor interactor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        interactor = new DriverInteractor();
    }

    @Test
    public void getterTest() throws Exception {
        String name = "ASDASDASD Driver";
        Driver p = new Driver(name, 5, 28, null, null);
        assertEquals(p.driverName.equals(name), true);
    }

    @Test
    public void mockGetTest() throws Exception {
        TeamMock.resetList();
        if (BuildConfig.FLAVOR == "mock") {
            List<Driver> p = interactor.getDriversFromNetWork();
            assertEquals(p.get(0).driverName, DriverMock.testP1.driverName);
            assertEquals(p.get(1).driverName, DriverMock.testP2.driverName);
        }
    }

    @Test
    public void mockAddTest() throws Exception {
        TeamMock.resetList();
        if (BuildConfig.FLAVOR == "mock") {
            Driver n = new Driver("ASDASDASD Driver", 5, 28, null, null);
            interactor.insertDriverToNetwork(n);
            List<Driver> p = interactor.getDriversFromNetWork();
            assertEquals(p.get(1).driverName, DriverMock.testP1.driverName);
            assertEquals(p.get(2).driverName, DriverMock.testP2.driverName);
            assertEquals(p.get(0).driverName, n.driverName);
        }
    }

}
