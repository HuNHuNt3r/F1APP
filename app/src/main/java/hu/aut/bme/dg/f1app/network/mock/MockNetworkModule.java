package hu.aut.bme.dg.f1app.network.mock;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.aut.bme.dg.f1app.network.DriversApi;
import hu.aut.bme.dg.f1app.network.TeamsApi;
import hu.aut.bme.dg.f1app.network.prod.NetworkModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by DG on 2016.05.23..
 */
@Module
public class MockNetworkModule {
    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        builder.interceptors().add(0, new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return MockHttpServer.call(request);
            }
        });
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofit(client);
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
