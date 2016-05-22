package hu.aut.bme.dg.f1app.view;

import hu.aut.bme.dg.f1app.model.Driver;

/**
 * Created by DG on 2016.05.08..
 */
public interface DriverEditView {

    void showDriver(Driver editDriver);
    void saveDriver();
    void deleteDriver();
    void browseDriverImage();

}
