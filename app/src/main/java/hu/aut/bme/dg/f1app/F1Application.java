package hu.aut.bme.dg.f1app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import hu.aut.bme.dg.f1app.view.ViewModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by DG on 2016.04.24..
 *
 * Free picture from:
 * http://www.freepik.com/free-vector/racing-cars-pack_749175.htm
 */
public class F1Application extends Application {

    private Tracker mTracker;
    public static F1ApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
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

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
