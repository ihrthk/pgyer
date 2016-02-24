package me.zhangls.pgyer.okhttp

import me.zhangls.pgyer.Pgyer
import okhttp3.*
import okhttp3.internal.Util
import okio.BufferedSink
import okio.Okio

import java.util.concurrent.TimeUnit

/**
 * Created by zhangls on 2016/2/2.
 */
public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;


    private OkHttpUtils() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        mOkHttpClient = okHttpClientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public String download(Pgyer pgyer, File downloadFile) throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("www.pgyer.com")
                .addPathSegment("apiv1")
                .addPathSegment("app")
                .addPathSegment("install")
                .addQueryParameter("aId", pgyer.aId)
                .addQueryParameter("_api_key", pgyer._api_key)
                .addQueryParameter("password", pgyer.password)
                .build();

        def request = new Request.Builder().url(httpUrl).build()
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response)
        }

        BufferedSink bufferedSink = Okio.buffer(Okio.sink(downloadFile))
        bufferedSink.writeAll(response.body().source())
        Util.closeQuietly(bufferedSink)

        return response.toString()
    }

    public String upload(Pgyer pgyer) throws Exception {
        Request request = makeRequest(pgyer)
        Response response = okHttpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response)
        }
        return response.body().string()
    }


    private static Request makeRequest(Pgyer pgyer) throws Exception {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("_api_key", pgyer._api_key)
                .addFormDataPart("uKey", pgyer.uKey)
                .addFormDataPart("file", pgyer.file.name,
                RequestBody.create(MediaType.parse("application/vnd.android.package-archive"), pgyer.file));

        if (pgyer.publishRange != null) {
            multipartBuilder.addFormDataPart("publishRange", pgyer.publishRange)
        }
        if (pgyer.isPublishToPublic != null) {
            multipartBuilder.addFormDataPart("isPublishToPublic", pgyer.isPublishToPublic)
        }
        if (pgyer.password != null) {
            multipartBuilder.addFormDataPart("password", pgyer.password)
        }
        if (pgyer.updateDescription != null) {
            multipartBuilder.addFormDataPart("updateDescription", pgyer.updateDescription)
        }

        Request request = new Request.Builder().url("http://www.pgyer.com/apiv1/app/upload").post(
                ProgressHelper.addProgressRequestListener(multipartBuilder.build(), new ProgressListener() {
                    @Override
                    void onProgress(long currentBytes, long contentLength, boolean done) {

                    }
                })).build()
        return request;
    }
}
