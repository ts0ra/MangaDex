# MangaDex

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/ts0ra/MangaDex/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/ts0ra/MangaDex/tree/master)

A modern Android application for browsing and managing manga collections using the MangaDex API. Built with Clean Architecture principles and modular design for scalability and maintainability.

## ✨ Features

- **📚 Browse Manga**: Discover and explore manga titles from the MangaDex database
- **🖼️ Cover Art Display**: High-quality manga cover images with error fallback
- **❤️ Favorites System**: Save and manage your favorite manga titles
- **🔍 Detailed View**: View comprehensive manga information including descriptions
- **📱 Responsive UI**: Grid layout optimized for different screen sizes
- **🔒 Data Security**: Encrypted local database using SQLCipher
- **⚡ Performance**: Efficient data loading with caching and pagination
- **🌙 Modern UI**: Material Design 3 with edge-to-edge display

## 🏗️ Architecture

This project follows **Clean Architecture** principles with a modular design:

### Modules Structure
```
📦 MangaDex
├── 📱 app/              # Main application module
├── 🧱 core/             # Core business logic and shared components  
└── ❤️ favorite/         # Dynamic feature module for favorites
```

### Architecture Layers
- **Presentation Layer**: Activities, ViewModels, and UI components
- **Domain Layer**: Use cases, entities, and repository interfaces
- **Data Layer**: Repository implementations, data sources, and mappers

### Design Patterns
- **MVVM**: Model-View-ViewModel for presentation layer
- **Repository Pattern**: Centralized data access
- **Dependency Injection**: Using Koin framework
- **Single Source of Truth**: Room database as local cache

## 🛠️ Tech Stack

### Core Technologies
- **Language**: Kotlin 2.0.21
- **Build System**: Gradle 8.10.0 with Kotlin DSL
- **Minimum SDK**: 28 (Android 9.0)
- **Target SDK**: 35 (Android 15)

### Architecture Components
- **Room Database**: Local data persistence with encryption
- **ViewModel & LiveData**: Reactive UI components  
- **Coroutines & Flow**: Asynchronous programming
- **View Binding**: Type-safe view references

### Networking & Data
- **Retrofit 2.11.0**: REST API client
- **Gson**: JSON serialization/deserialization
- **OkHttp**: HTTP client with logging interceptor
- **SQLCipher 4.5.4**: Database encryption

### Dependency Injection
- **Koin 4.0.3**: Lightweight dependency injection

### Image Loading
- **Glide 4.14.2**: Efficient image loading and caching

### Security & Performance
- **ProGuard/R8**: Code obfuscation and optimization
- **LeakCanary**: Memory leak detection (debug builds)
- **Certificate Pinning**: Enhanced network security

## 📱 Screenshots

*Add your app screenshots here*

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 17 or higher
- Android SDK 35

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/ts0ra/MangaDex.git
   cd MangaDex
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## 🔧 Configuration

### API Configuration
The app uses the MangaDex API. No API key is required for basic functionality.

### Database Security
The local database is encrypted using SQLCipher with a default passphrase. For production:

1. Update the passphrase in `CoreModule.kt`:
```kotlin
val passphrase: ByteArray = SQLiteDatabase.getBytes("your-secure-passphrase".toCharArray())
```

### Network Security
Certificate pinning is configured for enhanced security. Update certificates in `CoreModule.kt` if needed.

## 📂 Project Structure

```
app/
├── src/main/java/com/ts0ra/mangadex/
│   ├── MainActivity.kt              # Main manga list screen
│   ├── MainViewModel.kt             # Main screen view model
│   ├── detail/
│   │   ├── DetailActivity.kt        # Manga detail screen
│   │   └── DetailViewModel.kt       # Detail screen view model
│   ├── di/
│   │   └── AppModule.kt            # App dependency injection
│   └── MyApplication.kt            # Application class

core/
├── src/main/java/com/ts0ra/core/
│   ├── data/
│   │   ├── MangaRepository.kt      # Repository implementation
│   │   ├── NetworkBoundResource.kt # Network-first data strategy
│   │   ├── Resource.kt             # Data wrapper class
│   │   └── source/
│   │       ├── local/              # Local data source (Room)
│   │       └── remote/             # Remote data source (API)
│   ├── domain/
│   │   ├── model/                  # Domain entities
│   │   ├── repository/             # Repository interfaces
│   │   └── usecase/                # Business logic
│   ├── ui/
│   │   └── MangaAdapter.kt         # RecyclerView adapter
│   ├── utils/                      # Utility classes
│   └── di/
│       └── CoreModule.kt           # Core dependency injection

favorite/
└── src/main/java/com/ts0ra/favorite/
    ├── FavoriteActivity.kt         # Favorites screen
    ├── FavoriteViewModel.kt        # Favorites view model
    └── di/
        └── FavoriteModule.kt       # Favorite module DI
```

## 🧪 Testing

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# All tests
./gradlew check
```

### Test Coverage
- Unit tests for ViewModels and Use Cases
- Integration tests for Repository
- UI tests for critical user flows

## 🔄 CI/CD

This project uses CircleCI for continuous integration:
- Automated testing on every commit
- Code quality checks
- Build verification for multiple SDK versions

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add KDoc comments for public APIs
- Run `./gradlew ktlintCheck` before submitting

## 📋 Roadmap

- [ ] **Search Functionality**: Search manga by title, author, or genre
- [ ] **Reading Lists**: Create custom manga reading lists
- [ ] **Reading Progress**: Track reading progress for each manga
- [ ] **Offline Support**: Enhanced offline reading capabilities
- [ ] **User Authentication**: MangaDex account integration
- [ ] **Push Notifications**: New chapter notifications
- [ ] **Dark Theme**: Complete dark mode implementation
- [ ] **Tablet Support**: Optimized layouts for tablets

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [MangaDex](https://mangadex.org/) for providing the manga database API
- [Material Design](https://material.io/design) for design guidelines
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) for architectural patterns

## 📞 Support

If you encounter any issues or have questions:
- Create an [issue](https://github.com/ts0ra/MangaDex/issues)
- Check existing [discussions](https://github.com/ts0ra/MangaDex/discussions)

---

Made with ❤️ by [ts0ra](https://github.com/ts0ra)
