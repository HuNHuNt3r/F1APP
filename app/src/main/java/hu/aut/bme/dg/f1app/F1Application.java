package hu.aut.bme.dg.f1app;

import android.app.Application;

import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.04.24..
 *
 * Free picture from:
 * http://www.freepik.com/free-vector/racing-cars-pack_749175.htm
 */
public class F1Application extends Application {

    public static F1ApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
//        injector = DaggerF1ApplicationComponent.builder().viewModule(new ViewModule(this)).build();

        if (BuildConfig.FLAVOR.equals("mock")) {
            injector = DaggerMockF1ApplicationComponent.builder().viewModule(new ViewModule(this)).build();
        } else {
            injector = DaggerF1ApplicationComponent.builder().viewModule(new ViewModule(this)).build();
        }
    }

    public void setInjector(F1ApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
    }
}
