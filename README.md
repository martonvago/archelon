## Archelon Morning Survey: Add new morning survey

This project implements the basic "Add new morning survey" flow for the Archelon Morning Survey app.
Currently it supports adding surveys with survey-level properties (beach, date, time, beach sector, leader, observers, sky, precipitation, wind intensity, wind direction, surf) and 2 types of events (Adult Emergence, Hatching).
The user can also view a list of saved surveys where summary information is displayed for each survey for demonstration purposes.

### Project dependencies, technologies:
- Atom UI components (customised when necessary)
- Android Gradle Plugin 3.6.4
- Gradle 6.1.1
- Hilt for dependency injection
- Navigation Components
- ThreeTenABP for date and time operations.
  - Added to be able to use `java.time` without bumping the minSdkVersion or the gradle plugin version (gradle plugin 4.0.0+ needed for desugaring).
- Room DB
- Data Binding
- LiveData
- Coroutines

### Test tools
- Truth
- JUnit 4
  - We have decided not to use JUnitParams because those tests can't be run in isolation. Instead of `@Parameterised` tests, we loop over test scenarios to cut down on code repetition when appropriate.
- MockK for mocking
- Espresso
- Roboelectric