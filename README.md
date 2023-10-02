# Revamp (work in progress)
The Revamp App while serve as a wallpaper search engine utilizing the [Wallhaven api](https://wallhaven.cc/help/api), is designed to provide users with a seamless experience when searching for and setting wallpapers on their Android devices. 
While the primary goal of the app is to showcase the project structure and code, it also offers a practical utility for users who want to discover and apply beautiful wallpapers to personalize their devices.

### App Modularization 
![Screenshot from 2023-10-02 16-04-24](https://github.com/guiBrisson/revamp/assets/54915600/6693f474-e106-4438-b27a-ed2671e8e7c8)

1. **Home Module**:
    The home module is responsible for displaying the app's main interface.
    It provides a user-friendly home screen where users can explore featured wallpapers.

3. **Search Module**:
    The search module allows users to search for wallpapers based on keywords, categories, or tags.
    It provides advanced search functionality for a more personalized wallpaper experience.

4. **Wallpaper Module**:
    The wallpaper features module focuses on displaying detailed information about wallpapers, including resolution, featured colors, and user views.
    Users can preview and set current image as the device wallpaper.

5. **Common Module**:
    The common module contains Common classes used across different parts of the app.

6. **Data Module**:
    The data module handles data retrieval and storage.
    It interacts with local databases and remote APIs to fetch and cache wallpapers.

7. **Model Module**:
    The model module defines data models and entities used throughout the app.
    It plays a crucial role in data serialization and manipulation.

8. **Network Core Module**:
    The network core module manages network-related operations.
    It uses technologies like Retrofit for making network requests.


### Technologies Used

- **Kotlin**: The primary programming language for Android app development, known for its conciseness and safety.
- **Jetpack Compose**: A modern Android UI toolkit for building native user interfaces with a declarative syntax.
- **Compose Navigation**: A navigation library for Jetpack Compose that simplifies app navigation.
- **Dagger Hilt**: A dependency injection framework for Android that makes it easier to manage dependencies and maintain code quality.
- **Retrofit**: A popular HTTP client library for Android that simplifies network requests and data retrieval.
- Testing:
    - **JUnit**: A widely-used testing framework for writing and running unit tests in Android.
    - **Espresso**: A testing framework for writing UI tests to ensure the app's user interface behaves correctly.

#### License
This project is licensed under the [MIT License](https://github.com/guiBrisson/revamp/blob/main/LICENSE).

