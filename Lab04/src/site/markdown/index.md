# Lab04 — Maven and JavaFX

## Overview
This lab demonstrates how to manage JavaFX dependencies with Maven,
run a basic desktop application, and generate project documentation.

## Requirements
- JDK 21
- Apache Maven
- JavaFX 23, downloaded automatically by Maven

## Application
The main class is `edu.psu.se411.lab04.MainClass`.

It extends the JavaFX Application class and displays an empty
window titled "Lab04" with a width of 600 and a height of 400.

## Run the Application
From the folder containing pom.xml, run:

    mvn clean javafx:run

Close the application window to finish execution.

## Generate Documentation
Run:

    mvn clean site

Open `target/site/index.html` in a browser.

## Project Files
- `pom.xml`: Dependencies, plugins, and project information.
- `src/main/java`: Java source code.
- `src/site/markdown`: Documentation source files.
- `target`: Generated build output and documentation.

## Developer
Nasser Alfehaid

## License
Apache License 2.0
