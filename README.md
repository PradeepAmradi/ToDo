# 📋 Cross-Platform ToDo App

A modern, cross-platform **ToDo App** built using **Kotlin Multiplatform** and **Jetpack Compose Multiplatform**. This app allows users to manage their tasks efficiently with a clean Material Design 3 interface.

## 🚀 Features

### ✅ Task Management
- Add, edit, and delete tasks
- Mark tasks as complete/incomplete
- Set priority levels (Low, Medium, High)
- Add descriptions and categories to tasks

### 🏷️ Task Organization
- Filter tasks by: All, Pending, Completed, Today, Upcoming
- Categorize tasks with tags
- Priority-based visual indicators

### 🏗️ Architecture
- **Kotlin Multiplatform** for shared business logic
- **Jetpack Compose** for modern Android UI
- **MVVM** architecture pattern
- **Use Cases** for clean business logic separation
- **Repository Pattern** for data abstraction

## 📱 Current Platform Support
- ✅ **Android** - Full implementation with Jetpack Compose UI
- 🔄 **iOS** - Shared logic ready (UI pending)
- 🔄 **Desktop** - Future implementation planned
- 🔄 **Web** - Future implementation planned

## 🛠️ Tech Stack
- **Kotlin Multiplatform** - Code sharing across platforms
- **Jetpack Compose** - Modern Android UI toolkit
- **Kotlinx.Coroutines** - Asynchronous programming
- **Kotlinx.DateTime** - Date and time handling
- **Material Design 3** - Modern UI design system

## 🏗️ Project Structure
```
├── androidApp/           # Android-specific code and UI
│   └── src/main/kotlin/com/todo/android/
│       ├── ui/           # Compose UI components
│       │   ├── component/
│       │   ├── screen/
│       │   ├── theme/
│       │   └── viewmodel/
│       └── MainActivity.kt
├── shared/               # Shared Kotlin Multiplatform module
│   └── src/commonMain/kotlin/com/todo/shared/
│       ├── data/         # Data models and repositories
│       │   ├── model/
│       │   └── repository/
│       └── domain/       # Business logic and use cases
│           └── usecase/
├── .github/workflows/    # CI/CD automation
└── gradle/               # Build configuration
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 17+
- Android SDK with API level 24+

### Building the Project
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run on connected device/emulator
./gradlew installDebug
```

### Running Tests
```bash
# Run unit tests
./gradlew test

# Run Android instrumented tests
./gradlew connectedAndroidTest
```

## 🔄 CI/CD

The project includes GitHub Actions workflows for:
- **Automated builds** on push/PR
- **APK generation** (debug & release)
- **Unit test execution**
- **Artifact publishing**

## 🌟 App Screenshots & Demo

The app provides a clean, intuitive interface for task management:
- **Task List** with filtering options
- **Add Task Dialog** with categories and priorities  
- **Material Design 3** theming
- **Responsive layout** for different screen sizes

## 🔮 Roadmap

- [ ] **Local Storage** - Add SQLite/Room integration
- [ ] **Cloud Sync** - Firebase/REST API integration
- [ ] **Notifications** - Local reminders and push notifications
- [ ] **iOS App** - Native iOS implementation
- [ ] **Desktop App** - Windows/macOS/Linux support
- [ ] **Web App** - Progressive web application
- [ ] **Offline Support** - Sync when connectivity restored
- [ ] **Dark Theme** - Complete dark mode support
- [ ] **Widget Support** - Home screen widgets
- [ ] **Search & Sort** - Advanced task organization

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **JetBrains** for Kotlin Multiplatform and Compose Multiplatform
- **Google** for Android development tools and Material Design
- **Kotlinx** libraries for coroutines and datetime handling
