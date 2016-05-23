package hu.aut.bme.dg.f1app.network.mock;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.network.GsonHelper;
import hu.aut.bme.dg.f1app.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DG on 2016.05.23..
 */
public class TeamMock {

    static List<Team> teamList = new ArrayList<>();
    static boolean isInitialised = false;

    public static Team testP1 = new Team("Network Test 1 Ferrari", "Maurizio Arrivabene", null, null, null);

    public static Team testP2 = new Team("Network Test 2 Red Bull Racing", "Christian Horner", null, null, null);

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "teams") && request.method().equals("GET")) {
            if (!isInitialised) {
                teamList.add(testP1);
                teamList.add(testP2);
                isInitialised = true;
            }
            responseString = GsonHelper.getGson().toJson(teamList);
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "teams/add") && request.method().equals("GET")) {
            int startOfData = uri.getPath().lastIndexOf('/');
            String name = uri.getPath().substring(startOfData + 1);
            teamList.add(new Team(name, "Maurizio Arrivabene", null, null, null));

            responseString = "";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return MockHelper.makeResponse(request, headers, responseCode, responseString);
    }

    public static void resetList() {
        teamList.clear();
        isInitialised = false;
    }

}
