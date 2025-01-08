■ Project description-
News is a Kotlin-based Android app that delivers real-time news from NewsAPI with offline reading support. Built on MVVM architecture, it features save news articles and share vai link to modern news experience.

■ Steps to set up the project-
1.Create an Android Project
2.Add Required Libraries
3.Set Up Retrofit for API Integration
4.Create a Repository for Data Management
5.Design the UI Using Jetpack Compose
6.Set Up Room for Local Database
7.Connect Data to UI with ViewModel
8.Integrate Navigation and Handle States
9.Test and Finalize the Application

■ Libraries used and their purpose-
1.Retrofit & OkHttp
Purpose: Network requests and API communication
Implementation: implementation(libs.retrofit)
Implementation: implementation(libs.okhttp)
2.Room Database
Purpose: Local data storage for offline access
Implementation: implementation(libs.androidx.room.runtime)
Implementation: implementation(libs.androidx.room.ktx)
3.Compose Navigation
Purpose: In-app navigation handling
Implementation: implementation(libs.androidx.navigation.compose)

■ Explanation of the architecture and design choices.
Model-View-ViewModel (MVVM) is a software design pattern that separates an application's user interface (UI) from its business logic: 
Model: Encapsulates the app's business logic and data, such as databases or web services 
View: Encapsulates the UI and UI logic 
ViewModel: Encapsulates presentation logic and state, and acts as a mediator between the model and view 
Design Decisions:
MVVM for clear separation of concerns
Repository pattern for data handling
Use cases for business logic isolation
Single activity, multiple composables design
Offline-first approach with Room database
