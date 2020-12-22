This project implements the basic "add new Morning Survey" flow for the Archelon Morning Survey app.

- Android Gradle Plugin 3.6.4
    - ThreeTenABP
- Gradle 6.1.1
- Hilt
- Navigation Components
- Room db
- 1 Activity
- data binding
- LiveData

- JUnit 4
  - We have decided not to use JUnitParams because those tests can't be run in isolation. Instead of @Parameterised tests, we loop over test scenarios to cut down on code repetition.
- MockK