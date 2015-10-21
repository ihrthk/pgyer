package me.zhangls.pgyer;

/**
 * Created by zhangls on 2015/10/20.
 */
public class Pgyer {

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
    String updateDescription
}

