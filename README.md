# EduAdmission
The Android application for EduAdmission has been created with a Clean MVVM Architecture, Jetpack Navigation, Room Database for local persistence, and a Fake Repository to simulate network calls without a backend. The UI is built using Material 3 components as requested.

# Key Components Implemented:

## **Tech Stacks**

| **Layer**     | **Technology**               |
| ------------- | ---------------------------- |
| Language      | Kotlin                       |
| Architecture  | MVVM                         |
| Navigation    | Jetpack Navigation           |
| UI            | Material 3 Components        |
| Data Source   | Local JSON / Fake Repository |
| Storage       | SharedPreferences + Room     |
| Image Loading | Coil (preferred for Kotlin)  |

## **Target User Flow (Core Journey)**

**Step 1 — Onboarding / Auth**

1. Splash Screen
2. Login / Register
3. Home Dashboard

**Step 2 — Discovery**

1. University List
2. Course List
3. Course Details

**Step 3 — Application**

1. Application Form
2. Document Upload Simulation
3. Review & Submit

**Step 4 — Tracking**

1. Application Status Screen



## **Architecture (MVVM)**

- **Data Layer:** EduDatabase (Room), EduDao, EduRepository, and FakeDataSource.
- **ViewModel Layer:** AuthViewModel, UniversityViewModel, ApplicationViewModel.
- **UI Layer:** Fragments (Splash, Login, Home, UniversityList, CourseList, CourseDetail, ApplicationForm, Status) and Adapters.



## **Features**

- Splash Screen: Checks login status locally.
- Authentication: Mocked Login/Register with local persistence.
- Home Dashboard: Featured universities and quick links.
- Discovery: Browse universities and view course details.
- Application Form: Form validation and simulated document upload.
- Status Tracking: "Submitted" status automatically updates to "Under Review" after 5 seconds to demonstrate background logic.


# Screenshots
<div class="grid cards" markdown>
	<img src="/images/1.png" width="300px"/>
	<img src="/images/2.png" width="300px"/>
	<img src="/images/3.png" width="300px"/>
	<img src="/images/4.png" width="300px"/>
	<img src="/images/5.png" width="300px"/>
	<img src="/images/6.png" width="300px"/>
	<img src="/images/7.png" width="300px"/>
</div>