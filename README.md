# BBCNews

## Challenges

One of the main challenges faced during development was the constraint of using older libraries due to the minimum version requirement of Android Studio 3.0.
Due to the limitation imposed by Android Studio 3.0, which only supports Android Gradle Plugin version 3.0.0, I must had to use Kotlin 1.2. Consequently, support for coroutines jobs, are unavailable. Given these circumstances, I had change my initial architectural approach from MVVM to MVP.

## Technology

- **Android Studio 3.0**
- **Kotlin 1.2**: The app is written in Kotlin 1.2, as it was the minimum version allowed with Android Studio 3.0.
- **Architecture**: MVP (Model-View-Presenter)
- **Libraries**:
  - Retrofit: Used for making network requests.
  - Glide: Used for image loading and caching.
  - Junit: Used for unit tests.
  - MocK: Used for mock test classes.

## Features

- **News List**
- **News Detail**
- **Login with fingerprint**
- **Change flavor to change news source**
 
## Getting Started

1. **Check Out Source Code**:
   - Clone the repository to your local machine
2. **Build Project**:
   - Once the project is loaded, click on "Build".
   - Select "Clean Project" to clean the project.
   - After cleaning, select "Sync Project with Gradle Files"
