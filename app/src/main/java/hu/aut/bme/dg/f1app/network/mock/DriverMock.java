package hu.aut.bme.dg.f1app.network.mock;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.network.GsonHelper;
import hu.aut.bme.dg.f1app.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DG on 2016.05.23..
 */
public class DriverMock {

    static List<Driver> driverList = new ArrayList<>();
    static boolean isInitialised = false;

    public static Driver testP1 = new Driver("Network Test 1 Vettel", 5, 28, null, null);

    public static Driver testP2 = new Driver("Network Test 2 Riccardo", 3, 26, null, null );

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "drivers") && request.method().equals("GET")) {
            if (!isInitialised) {
                driverList.add(testP1);
                driverList.add(testP2);
                isInitialised = true;
            }
            responseString = GsonHelper.getGson().toJson(driverList);
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "drivers/add") && request.method().equals("GET")) {
            int startOfData = uri.getPath().lastIndexOf('/');
            String name = uri.getPath().substring(startOfData + 1);
            driverList.add(new Driver(name, 5, 28, null, null));

            responseString = "";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return MockHelper.makeResponse(request, headers, responseCode, responseString);
    }

    public static void resetList() {
        driverList.clear();
        isInitialised = false;
    }

}
