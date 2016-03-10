# pgyer

This is the Pugongying plugin for the Gradle. This plugin, you can use the Pugongying API from Gradle easily.


## Configuration
``` java
//reference https://plugins.gradle.org/plugin/me.zhangls.pgyer
plugins {
  id "me.zhangls.pgyer" version "0.2.2"
}
pgyer {
    uKey 'ec5faa5695058bb6ac5ae13026d22909'
    _api_key '1f91476238fd805b06594df3320a95da'
    file file('leying-release.apk')
    password '!@c20160101'

    aId '32a42e14f275cd9235e6551a0bb9da75'
    packageName 'com.duohappy.leying'
    lanuchActivity 'com.duohappy.leying.ui.activity.LeyingLaunchActivity'
}

task pgyerGuest(dependsOn: ['pgyerDownload', 'pgyerUninstall', 'pgyerInstall', 'pgyerRun'])
pgyerInstall.mustRunAfter pgyerDownload
pgyerUninstall.mustRunAfter pgyerDownload
pgyerInstall.mustRunAfter pgyerUninstall
pgyerRun.mustRunAfter pgyerUninstall

task pgyerMaster(dependsOn: ['pgyerUpload', 'pgyerGuest'])
pgyerGuest.mustRunAfter pgyerUpload
```

## Explain
```
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

//应用ID(pgyer作为唯一标识，包名对应)
String aId
//包名(运行APK时要用)
String packageName
//主界面(运行APK时要用)
String lanuchActivity
```

## Pgyer tasks
```
pgyerDownload - 下载APK
pgyerInstall - 安装APK
pgyerRun - 运行APK
pgyerUninstall - 卸载APK
pgyerUpload - 上传APK

pgyerMaster
pgyerGuest
```
## Dependences
- adb

## Reference
- https://github.com/dodocat/pgyer
- http://www.pgyer.com/doc/api#uploadApp

## License

    Copyright 2016 zhangls
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

