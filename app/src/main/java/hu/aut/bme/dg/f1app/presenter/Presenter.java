package hu.aut.bme.dg.f1app.presenter;

/**
 * Created by DG on 2016.04.24..
 */
public abstract class Presenter<S> {

    protected S view;

    public void attachView(S screen) {
        this.view = screen;
    }

    public void detachView() {
        this.view = null;
    }
}
