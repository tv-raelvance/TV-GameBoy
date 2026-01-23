# Balloon Tower Defense 5 Remake

## Overview
This project is a remake of the popular game Balloon Tower Defense 5. The game involves strategically placing towers to pop balloons before they reach the end of a path. Players must manage resources and upgrade their towers to succeed.

## Project Structure
The project is organized into the following directories:

- **src/main**: Contains the main game logic, including models, views, controllers, and utility classes.
- **src/test**: Contains unit tests for the game components.
- **pom.xml**: Maven configuration file for managing dependencies and build settings.

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd btd5-remake
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn exec:java -Dexec.mainClass="src.main.Main"
   ```

## Gameplay Mechanics
- Players can place different types of towers along the path to pop incoming balloons.
- Each tower has unique properties such as damage and range.
- Balloons have varying health and speed, requiring strategic planning to manage.
- Players earn points for popping balloons, which can be used to upgrade towers or buy new ones.

## Contribution Guidelines
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your branch and create a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.