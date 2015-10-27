package me.zhangls.pgyer

import com.squareup.okhttp.*
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

import java.util.concurrent.TimeUnit

class PgyerTask extends DefaultTask {

    {
        group = 'pgyer'
        description = """Gradle plugin for pgyer.com upload
        See https://github.com/ihrthk/pgyer for more information.
        Available configuration options and default values:
        //用户Key 点击获取uKey
        String uKey
        //API Key 点击获取API Key
        String _api_key
        //需要上传的ipa或者apk文件
        File file
        //(ipa上传时为必填) 填写发布范围，值为（2，3），2：直接发布，3：只有我安装
        String publishRange
        //(选填) 是否发布到广场，值为（1，2），1：发布到广场，2：不发布到广场。默认为不发布到广场
        String isPublishToPublic
        //(选填) 设置App安装密码，如果不想设置密码，请传空字符串，或不传。
        String password
        //(选填) 版本更新描述，请传空字符串，或不传。
        String updateDescription """
    }
    private final String API_END_POINT = "http://www.pgyer.com/apiv1"

    @TaskAction
    void upload() {
        Pgyer pgyer = project.pgyer
        String endPoint = getEndPoint(pgyer)

        try {
            String result = httpPost(endPoint, pgyer)
            println "result: ${result.toString()}"
        } catch (Exception e) {
            e.printStackTrace()
        }
    }


    private String httpPost(String endPoint, Pgyer pgyer) throws Exception {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(60, TimeUnit.SECONDS);

        Request request = makeRequest(endPoint, pgyer)

        Response response = client.newCall(request).execute();

        if (response == null || response.body() == null) return null;
        InputStream is = response.body().byteStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        String result = reader.readLine()
        is.close()
        return result
    }

    private String getEndPoint(Pgyer pgyer) {
        if (pgyer.uKey == null || pgyer._api_key == null) {
            throw new GradleException("uKey or apiKey is missing")
        }
        String endPoint = API_END_POINT + "/app/upload"
        return endPoint
    }

    private Request makeRequest(String endPoint, Pgyer pgyer) throws Exception {
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM)

        multipartBuilder.addFormDataPart("_api_key", new String(pgyer._api_key))
        multipartBuilder.addFormDataPart("uKey", new String(pgyer.uKey))

        MediaType mediaType = MediaType.parse("application/vnd.android.package-archive")
        RequestBody requestBody = RequestBody.create(mediaType, pgyer.file)
        multipartBuilder.addFormDataPart("file", pgyer.file.name, requestBody)

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

        Request request = new Request.Builder().url(endPoint).post(multipartBuilder.build()).build()
        return request;
    }
}