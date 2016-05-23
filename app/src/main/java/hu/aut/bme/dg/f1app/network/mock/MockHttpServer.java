package hu.aut.bme.dg.f1app.network.mock;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DG on 2016.05.23..
 */
public class MockHttpServer {

    public static Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}
