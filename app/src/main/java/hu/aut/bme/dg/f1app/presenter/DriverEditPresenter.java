package hu.aut.bme.dg.f1app.presenter;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.view.DriverAddView;
import hu.aut.bme.dg.f1app.view.DriverEditView;

/**
 * Created by DG on 2016.05.08..
 */
public class DriverEditPresenter extends Presenter<DriverEditView> {


    public DriverEditPresenter() {
        F1Application.injector.inject(this);
    }


    public void saveDriver() {
        view.saveDriver();
    }

    public void deleteDriver() {
        view.deleteDriver();
    }

}
