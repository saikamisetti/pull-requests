package meesho.com.getprgithub.network;

import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Sai on 12/06/18.
 */
public class NetworkAdapter {
    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .build();
                    request = request.newBuilder().url(url).build();
                    okhttp3.Response response = chain.proceed(request);

                    return response;
                }
            }).addInterceptor(interceptor).build();

            Moshi moshi = new Moshi.Builder().build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return retrofit;
    }
}
