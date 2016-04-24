package hu.aut.bme.dg.f1app.interactor;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.model.StringModel;

/**
 * Created by DG on 2016.04.24..
 */
public class StringInteractor {
    @Inject
    StringModel model;

    public StringInteractor() {
        F1Application.injector.inject(this);
    }

    public String getString() {
        return model.getNextString();
    }
}
