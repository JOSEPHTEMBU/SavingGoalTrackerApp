How to Run the App
Prerequisites

Android Studio (Giraffe 2023.3.1 or newer recommended)
Android device or emulator running API 26 or higher

Steps

Clone or download the project repository.
Open the project in Android Studio.
Wait for Gradle sync to complete (it will automatically download dependencies).
Connect an Android device or start an emulator.
Click Run (green play button) or use Run > Run 'app'.
The app will build, install, and launch.

No additional configuration, API keys, or internet connection is required — the app works completely offline.
Quick Test Flow

Tap "+ Add a Goal" to create a new goal
Fill in details and create
Tap a goal card to open the detail view
Use Deposit/Withdraw buttons
Toggle the eye icon to hide/show amounts


Architecture Overview
The app follows clean MVVM architecture with Jetpack best practices:

UI Layer (Jetpack Compose - Material 3)
├── GoalListScreen
├── GoalDetailScreen
├── CreateGoalDialog
├── TransactionDialog
├── SuccessPopup

ViewModel Layer
└── GoalViewModel
    - Manages state (goals flow, dialogs, selected goal, success messages)
    - Handles business logic (create, deposit, withdraw)

Repository Layer
└── GoalRepository
    - Abstracts data operations

Data Layer (Room)
├── GoalDatabase (RoomDatabase)
├── GoalDao
├── Entities
    ├── GoalEntity
    └── TransactionEntity

    Key Technologies & Patterns:

Jetpack Compose for declarative UI
Room for local persistence
Kotlin Coroutines + Flow for reactive data streams
State hoisting with mutableStateOf and collectAsState
ViewModel Factory for dependency injection

Assumptions Made

Currency is Kenyan Shillings (KES) – amounts displayed with "KSh" prefix
Withdrawals are allowed (common in savings apps), but current amount never goes below 0
No user authentication – designed as a single-user local app
No interest or time-based calculations on savings
Target date is optional and informational only (no reminders)
Goal completion triggered when current amount ≥ target amount
Predefined goal categories (no user-defined categories)
Transactions are recorded automatically with timestamp
