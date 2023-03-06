# 📋 Kanban Board
This is a simple Kanban board application that allows users to create and manage tasks or activities.

## ✨ Getting Started
To get started with this project, you will need to have the following software installed on your computer:

- Java Development Kit (JDK) version 8 or higher
- Maven build tool version 3.6.0 or higher
- PostgreSQL database server  

Once you have these dependencies installed, you can follow these steps to set up the project:

1. Clone the repository to your local machine.
2. Open a terminal or command prompt and navigate to the root directory of the project.
3. Create a new PostgreSQL database for the project.
4. Update the database connection details in the application.properties file.
5. Run the `mvn clean install` command to build the project and download any required dependencies.
6. Run the `mvn spring-boot:run` command to start the application. 

Once the application is running, you can access it by navigating to http://localhost:8080 in your web browser.

## ⏳ Planned features
This application will allow users to perform the following actions:

- Create and delete Kanban boards.
- Create and delete Kanban lists within a board.
- Create, update, and delete cards within a list.
- Reorder cards within a list by dragging and dropping them.
- Create accounts and collaborate with a team.

## ⚙️ Technologies Used
This project is implemented using the following technologies:

- Java programming language
- Spring Boot framework
- PostgreSQL database server

## 🪪 License
This project is licensed under the MIT License. See the LICENSE file for more information.
