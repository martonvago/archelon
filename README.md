## Archelon Morning Survey
Coursework for my Wireless and Mobile Computing module at Birkbeck.

Archelon, the Sea Turtle Protection Society of Greece, monitor sea turtle populations on nesting beaches with the help of staff and volunteers. This app aims to help surveyors keep track of the state of nests, turtle hatchings and weather conditions across Greece.

### "Add new morning survey" user journey
This project limits itself to implementing the "Add new morning survey" flow.
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

### Test tools:
- Truth
- JUnit 4
  - I have decided not to use JUnitParams because those tests can't be run in isolation. Instead of `@Parameterised` tests, I loop over test scenarios to cut down on code repetition when appropriate.
- MockK for mocking
- Espresso
- Roboelectric