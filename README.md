# Kotlin Multiplatform Sample

This is a **Kotlin Multiplatform (KMP) Project**. It includes iOS and Android applications with a native UI and a module with code shared on iOS and Android.
This is a Kotlin Multiplatform project targeting Android, iOS.

## How to use

With the [KMP plugin for Android Studio](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) you can run shared code on both platforms without switching IDEs.

## Tech-Stack

* Tech-stack
  * [Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
  * [Swift](https://www.swift.com/ru) - for additional settings native to ios
  * [Ktor](https://ktor.io/docs/client-create-multiplatform-application.html) - networking
  * [Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
  * [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
  * [Decompose](https://arkivanov.github.io/Decompose/) - library for breaking down your code into lifecycle-aware business logic components, with routing functionality and pluggable UI (Compose, SwiftUI, Kotlin).
    * [Navigation](https://arkivanov.github.io/Decompose/navigation/overview/) - in-app navigation
    * [Lifecycle](https://arkivanov.github.io/Decompose/component/lifecycle/) - perform an action when
      lifecycle state changes
    * [Component](https://arkivanov.github.io/Decompose/component/overview/) - store and manage UI-related
      data in a lifecycle-aware way
  * [Kamel](https://github.com/Kamel-Media/Kamel) - image loading library
* Modern Architecture
  * [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  * MVVM + MVI (presentation layer)
* UI for Android and IOS
  * [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern, UI kit
  * [Material Design 3](https://m3.material.io/) - application design system providing UI components
* Di
  * [Koin](https://insert-koin.io/) - dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in project
