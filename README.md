# KMP Modularized Clean Architecture Project

This project is an educational example demonstrating how to set up a Kotlin Multiplatform (KMP) project with support for both iOS and Android. It highlights a modularized clean architecture approach with an emphasis on maintainability, scalability, and best practices in mobile development.

## Project Overview

The goal of this project is to showcase how to structure a KMP project in a clean, modularized way using:

- **TOML Catalog Versioning**: Simplifies dependency management.
- **Convention-Based Build Logic**: Promotes consistency and reduces boilerplate across the project.
- **Modularized Clean Architecture**: Decouples the code into layers for better separation of concerns and enhanced testability.
- **Compose Resources**: Integrates Compose multiplatform resources into native iOS SwiftUI.

### Current Status

🚧 **Project in Early Development** 🚧

Please note that this project is a work in progress. Significant changes, particularly in the module structure (especially the data layer), are expected as development continues. This README and the codebase will be updated as the project evolves.

## Project Structure

The project is organized into several key modules:

- **`app`**: Contains platform-specific implementations for both Android and iOS.
- **`core`**: Contains shared logic and resources.
- **`data`**: Manages data-related operations such as network requests, database access, and the repository pattern (subject to further changes).
- **`domain`**: Houses the use cases and business rules of the application.
- **`presentation`**: Contains the UI logic and ViewModel implementations.

Each module adheres to clean architecture principles, ensuring clear separation of concerns and promoting high testability.

## Project Setup

To set up and build the project, use the following tools and follow these instructions:

### Toolset

- **Android Studio Iguana** | 2023.2.1 Patch 2
    - Install the following plugins:
        - **Swift Support**: Enables Swift integration for iOS development.
        - **Kotlin Multiplatform Mobile (KMM)**: Provides support for Kotlin Multiplatform projects.
        - **SQLDelight**: For database management and code generation.

- **Xcode** | 15.0.1

### Secrets Configuration

The project requires certain secrets for build purposes. These secrets can be empty placeholders if not needed for actual builds:

- **Files requiring secrets**:
    - `UserRemoteService`
    - `AccountRemoteService`

Ensure that the required secrets are provided or placeholder values are set in the relevant files.

### iOS Build Configuration

- Verify that the correct Java version is used in the Build phase (Run script) for the iOS build.

For any issues or additional configuration details, refer to the project’s issue tracker or contact the project maintainers.
