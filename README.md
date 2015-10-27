# pgyer


```java
buildscript {
    repositories {
        mavenLocal()
        jcenter{
            url 'http://jcenter.bintray.com'
        }
    }
    dependencies {
        classpath 'me.zhangls:pgyer:0.1'
    }
}
apply plugin: 'me.zhangls.pgyer'
pgyer {
    uKey 'ec5faa5695058bb6ac5ae13026d22909'
    _api_key '1f91476238fd805b06594df3320a95da'
    file file('leying-release.apk')
    password '@c20160101'
}
```
