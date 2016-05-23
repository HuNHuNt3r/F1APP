package hu.aut.bme.dg.f1app;

import org.robolectric.RuntimeEnvironment;

/**
 * Created by DG on 2016.05.23..
 */
public class TestHelper {

    public static void setTestInjector() {
        F1Application application = (F1Application) RuntimeEnvironment.application;
        F1ApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
        application.setInjector(injector);
    }
}
