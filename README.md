# KMP Modularized Clean Architecture Project

This project is an educational example demonstrating how to set up a Kotlin Multiplatform (KMP) project with support for both iOS and Android. It focuses on using a modularized clean architecture approach, with an emphasis on maintainability, scalability, and best practices in mobile development.

## Project Overview

The goal of this project is to showcase how to structure a KMP project in a clean, modularized way using:

- **TOML Catalog Versioning**: Simplifying dependency management.
- **Convention-Based Build Logic**: Promoting consistency and reducing boilerplate across the project.
- **Modularized Clean Architecture**: Decoupling the code into layers for better separation of concerns and testability.

### Current Status

🚧 **Project in Early Development** 🚧

Please note that this project is currently a work in progress. Significant changes, especially in the module structure (particularly the data layer), are expected as the project evolves. This README and the codebase will be updated as the project progresses.

## Project Structure

At present, the project is divided into several key modules:

- `app`: Contains platform-specific implementations for both Android and iOS.
- `core`: Contains shared business logic that is platform-independent.
- `data`: Manages data-related operations such as network requests, database access, and repository pattern (subject to further changes).
- `domain`: Houses the use cases and business rules of the application.
- `presentation`: Contains the UI logic and ViewModel implementations.

Each module follows a clean architecture principle, ensuring a clear separation of concerns and promoting high testability.