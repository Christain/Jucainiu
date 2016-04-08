# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Christain/Work/AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-ignorewarnings

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep class com.zimadai.model.**{*;}
-keep class com.zimadai.http.**{*;}
-dontwarn android.net.http.**
-keep public class android.webkit.WebView {*;}
-keep public class android.webkit.WebViewClient {*;}

#*************EventBus*************#
-keep class de.greenrobot.event.**{*;}
-keepclassmembers class ** {
    public void onEvent*(**);
}

#***********Glide*****************#
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#*************Fresco**************#
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.common.internal.DoNotStrip *;
#}
#-dontwarn javax.annotation.**
#-keep class com.facebook.imagepipeline.gif.** { *; }
#-keep class com.facebook.imagepipeline.webp.** { *; }

#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.common.internal.DoNotStrip *;
#}
#-keepclassmembers class * {
#    native <methods>;
#}
#
#-dontwarn okio.**
#-dontwarn javax.annotation.**
#-dontwarn com.android.volley.toolbox.**

#*************butterknife**************#
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class * implements java.io.Serializable {

}

-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod

#**********ShareSDK**************#
-keep class cn.sharesdk.**{*;}
-keep class m.framework.** {*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

#百度推送
-dontwarn com.baidu.**
-keep class com.baidu.**{*;}


# universal-image-loader 混淆
#-dontwarn com.nostra13.universalimageloader.**
#-keep class com.nostra13.universalimageloader.** { *; }

-keep class com.baidu.mobstat.** {*;}
-keep class android.support.v4.** {*;}
-keep class com.google.** {*;}
-keep class org.apache.http.entity.mime.** {*;}
-keep class android.net.http.** {*;}
-keep class com.weibo.sdk.android.** {*;}
-keep class com.sina.sso.** {*;}
-keep class com.baidu.mapapi.** {*;}
-keep class com.tencent.tauth.** {*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage { *;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}


#记录生成的日志数据,gradle build时在本项目根目录输出
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
