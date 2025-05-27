# --- Java invoke (if needed) ---
-dontwarn java.lang.invoke.StringConcatFactory

# --- Your Application Core ---
-keep class com.ts0ra.core.** { *; } # Or -keep public class com.ts0ra.core.** { *; }
# Keep specific sub-packages if the general core rule isn't desired or if you need to be more granular later.
# If using the general core rule above, these might be redundant but offer clarity.
-keep class com.ts0ra.core.data.** { *; }
-keep class com.ts0ra.core.domain.model.** { *; }
-keep class com.ts0ra.core.domain.repository.** { *; }
-keep class com.ts0ra.core.domain.usecase.** { *; }
-keep class com.ts0ra.core.ui.** { *; }
-keep class com.ts0ra.core.di.** { *; }

# --- Retrofit ---
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault # Signature, InnerClasses, EnclosingMethod are covered by the Kotlin comprehensive rule
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# --- Gson ---
# Signature and *Annotation* for attributes are covered by the Kotlin comprehensive rule
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# --- Kotlin General & Metadata ---
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod, KotlinԴMetadata
-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; } # If you use coroutines or serialization
-keep class kotlin.Metadata { *; }
-keep class kotlin.LazyKt { *; }
-keep class kotlin.UnsafeLazyImpl { *; }
-keep class kotlin.InitializedLazyImpl { *; }
-keep class kotlin.SynchronizedLazyImpl { *; }
# (Potentially other specific kotlin.jvm.internal rules if needed, yours look okay for general use)

# --- Koin ---
-keep class org.koin.** { *; }
-keep class * extends org.koin.core.module.Module
-keepclassmembers class * {
    @org.koin.core.annotation.* <methods>;
}

# --- Glide ---
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule { <init>(...); }
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; public *; }
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder { *** rewind(); }

# --- SQLCipher ---
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# --- ViewBinding ---
-keep class * extends androidx.viewbinding.ViewBinding {
    public static *** inflate(...);
    public static *** bind(...);
}

# --- Room ---
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**