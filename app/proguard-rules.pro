# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ----------------------------------------------------------------------------
# General Android Rules
# ----------------------------------------------------------------------------
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-dontwarn javax.annotation.**

# Keep all Fragments and Activities to prevent accidental removal if only referenced via XML
-keep public class * extends android.app.Activity
-keep public class * extends androidx.appcompat.app.AppCompatActivity
-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# ----------------------------------------------------------------------------
# Kotlin Coroutines
# ----------------------------------------------------------------------------
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.android.AndroidExceptionPreHandler {
    <init>();
}
-keepclassmembers class * extends kotlinx.coroutines.CoroutineExceptionHandler {
    <init>();
}

# ----------------------------------------------------------------------------
# AndroidX Navigation
# ----------------------------------------------------------------------------
-keep class androidx.navigation.*
-keepclassmembers class * extends androidx.navigation.Navigator {
    <init>(...);
}
-keep class * implements androidx.navigation.NavArgs
-keep class * implements java.io.Serializable

# ----------------------------------------------------------------------------
# AndroidX Room
# ----------------------------------------------------------------------------
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
-keep class * implements androidx.room.migration.Migration

# ----------------------------------------------------------------------------
# Gson
# ----------------------------------------------------------------------------
# Gson uses generic type information stored in a class file when working with fields. ProGuard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer

# Keep your data models if they are used with Gson (adjust package name)
-keep class com.example.eduadmission.data.model.** { *; }
-keep class com.example.eduadmission.data.local.** { *; }


# ----------------------------------------------------------------------------
# Coil (Image Loading)
# ----------------------------------------------------------------------------
-keep class coil.** { *; }
-dontwarn coil.**
-dontwarn okio.**

# ----------------------------------------------------------------------------
# ViewModel
# ----------------------------------------------------------------------------
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}