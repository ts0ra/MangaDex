# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# Keep all public classes and interfaces in this module
-keep public class com.ts0ra.core.** { *; }

# Keep data classes and their properties
-keep class com.ts0ra.core.data.** { *; }
-keep class com.ts0ra.core.domain.model.** { *; }
-keep class com.ts0ra.core.domain.repository.** { *; }
-keep class com.ts0ra.core.domain.usecase.** { *; }

# Keep UI components that might be referenced dynamically
-keep class com.ts0ra.core.ui.** { *; }

# Keep DI modules
-keep class com.ts0ra.core.di.** { *; }

# Keep Kotlin metadata for reflection
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Keep ViewBinding classes
-keep class * extends androidx.viewbinding.ViewBinding {
    public static *** inflate(...);
    public static *** bind(...);
}

# Keep Java invoke classes for string concatenation (Java 9+ feature)
-dontwarn java.lang.invoke.StringConcatFactory

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
