package hu.aut.bme.dg.f1app.network.mock;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import hu.aut.bme.dg.f1app.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static hu.aut.bme.dg.f1app.network.mock.MockHelper.makeResponse;

/**
 * Created by DG on 2016.05.23..
 */
public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "drivers")) {
            return DriverMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "drivers/add")) {
            return DriverMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "teams")) {
            return TeamMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "teams/add")) {
            return TeamMock.process(request);
        }
        else {
            return makeResponse(request, headers, 404, "Unknown");
        }

    }
}
