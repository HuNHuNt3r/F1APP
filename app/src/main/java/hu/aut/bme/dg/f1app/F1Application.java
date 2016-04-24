package hu.aut.bme.dg.f1app;

import android.app.Application;

import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.04.24..
 */
public class F1Application extends Application {

    public static F1ApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerF1ApplicationComponent.builder().viewModule(new ViewModule(this)).build();
    }
}
