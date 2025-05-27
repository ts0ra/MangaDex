# Consumer rules for core module
# These rules are applied to consuming modules (like app module)

# Keep all public API classes from core module
-keep public class com.ts0ra.core.** { *; }

# Keep data classes and sealed classes
-keep class com.ts0ra.core.data.Resource { *; }
-keep class com.ts0ra.core.data.Resource$* { *; }
-keep class com.ts0ra.core.domain.model.** { *; }
-keep class com.ts0ra.core.domain.repository.** { *; }
-keep class com.ts0ra.core.domain.usecase.** { *; }

# Keep UI components
-keep class com.ts0ra.core.ui.** { *; }

# Keep DI modules that are referenced by consuming modules
-keep class com.ts0ra.core.di.** { *; }

# Keep ViewBinding classes
-keep class * extends androidx.viewbinding.ViewBinding {
    public static *** inflate(...);
    public static *** bind(...);
}

# Keep Kotlin metadata
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Keep Gson serialization
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Keep Room database classes
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Kotlin standard library classes used by lazy initialization and other features
-keep class kotlin.LazyKt { *; }
-keep class kotlin.UnsafeLazyImpl { *; } # Often used with Lazy
-keep class kotlin.InitializedLazyImpl { *; } # Another implementation
-keep class kotlin.SynchronizedLazyImpl { *; } # Another common implementation

# General rules for Kotlin, often recommended:
-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; } # If you use coroutines or serialization
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod, KotlinԴMetadata

# Keep all Kotlin metadata annotations
-keep class kotlin.Metadata { *; }