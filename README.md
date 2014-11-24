# Modified Deckard (for Gradle)

Deckard is the simplest possible Android project that uses Robolectric for testing and Gradle to build. It has one Activity, a single Robolectric test of that Activity, and an Espresso test of that Activity.

I took Deckard which many people are familiar with and modified it to demonstrate the ideas expressed in https://engineering.aweber.com/avoiding-robolectric-shock-while-testing-android-with-gradle-and-espresso/

With just a bit of manual intervention, this project also imports into Android Studio. See below for instructions.

## Setup

*Note: These instructions assume you have a Java 1.6 [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.*

To start a new Android project:

1. Install the [Android SDK](http://developer.android.com/sdk/index.html).

2. Set your `ANDROID_HOME` environment variable appropriately.

3. Run the Android SDK GUI and install API 18 and any other APIs you might need. You can start the GUI by invoking `android`

4. Download this project from GitHub.

5. In the project directory you should be able to run the Robolectric tests:
    ```bash
    cd my-new-project
    ./gradlew clean test
    ```
6. You should also be able to run the Espresso tests: `./gradlew clean connectedAndroidTest`. Note: Make sure to start an Emulator or connect a device first so the test has something to connect to.
7. Change the names of things from 'Deckard' to whatever is appropriate for your project. Package name, classes, and the AndroidManifest are good places to start.

8. Build an app. Win.

## IntelliJ / Android Studio Support

### Compatibility
Currently this has been tested with Android Studio 0.9.3.

### Importing
Import the project into Android Studio by selecting 'Import Project' and selecting the project's `build.gradle`. When prompted, you can just pick the default gradle wrapper.

### Running the Robolectric Test
1. Go to your **Run/Debug configurations screen**. Click the **'app'** drop down and select **Edit Configurations...**
1. Add a new Gradle configuration.  Click on the + and select **Gradle**
1. Call this configuration 'Build test classes'
1. In the section titled **Gradle project:** select the build.gradle in your robolectric-tests subdirectory
1. Set **Tasks:** to testClasses
1. Click 'Apply'
1. Add a new JUnit configuration
1. Make the Gradle configuration a dependency of this configuration.  Under **Before launch: Make** click the + sign, 'Run Another Configuration' and choose 'Build test classes'
1.  Name this configuration 'Run Robolectric Tests'
1. Change **Test kind:** to 'All in package'
1. Change **Search for tests:** to 'In single module'
1. Change **Use classpath of module:** to 'robolectric-tests'
1. Click 'Apply'
1. Click 'Ok'

You should now be able to `DeckardActivityRobolectricTest`. Run it as a normal JUnit test - make sure to choose the JUnit test runner and not the Android one.
 
### Running the Espresso Test
To run the Espresso test, you need to set up a Run Configuration. Go to `Edit Configurations -> Defaults -> Android Tests` and, after choosing  the correct module (app), fill in the `Specific instrumentation test runner` field. The easiest way is to click the ellipsis button on the right and type in `GITR`. This will find `GoogleInstrumentationTestRunner`, which is what you want. The fully-qualified class name will appear. Now you can right click on the test method in `DeckardEspressoTest` and choose the Android test runner.
