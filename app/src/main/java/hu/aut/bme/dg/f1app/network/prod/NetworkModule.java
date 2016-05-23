package hu.aut.bme.dg.f1app.network.prod;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.aut.bme.dg.f1app.network.DriversApi;
import hu.aut.bme.dg.f1app.network.GsonHelper;
import hu.aut.bme.dg.f1app.network.NetworkConfig;
import hu.aut.bme.dg.f1app.network.TeamsApi;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by DG on 2016.05.23..
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder clientBuilder = null;
        try {
            clientBuilder = UnsafeClientFactory.getUnsafeClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clientBuilder == null) {
            throw new RuntimeException("HttpClient cannot be initialized!");
        }

        return clientBuilder;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public DriversApi provideDefaultApi(Retrofit retrofit) {
        return retrofit.create(DriversApi.class);
    }

    @Provides
    @Singleton
    public TeamsApi provideApi(Retrofit retrofit) {
        return retrofit.create(TeamsApi.class);
    }

}