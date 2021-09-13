XumakWeatherData

Xumak challenge app to show weather data from allweather.docs.apiary.io API

# How to use the app?

First time, the app will start empty. After a small time it should retrieve the default city (
Calera, AL) and show it. If search icon is pressed, the Search Screen is displayed, there it's
possible to look for new cities. After the city is found, if pressed, it should appear in the main
screen that now should be horizontally scrollable because now it contains more than one city.  
If delete button is pressed, current city is deleted.

Radar button is shown but no feature is implemented.

App developed in Android Studio Arctic Fox | 2020.3.1 Patch 2

## Requires Java 11

## Target SDK: 30

## Compile SDK: 31

### User Interface was created using Jetpack Compose

The app is divided in 4 modules:

## domain:

Module containing the core domain model of the app including entities, value objects and
company-wide use cases

## infrastructure:

Module containing infrastructure support for domain including local database and remote API
communication

## di:

Module containing internal dependency tree using Koin

## app:

Actual Android app including icons, MainActivity, Application specific use cases and Composable
functions that makes the app user friendly and visually appealing.

Each module contains a set of tests for checking internal funcionality  
Dependency versions are stored in versions.gradle file

# Architecture

For the app I implemented a Layered architecture highly inspired in Clean Architecture by Robert C.
Martin.  
It's divided in core domain, infrastructure, and app.
In domain layer it's defined app entities and its interactions with storage resources as abstractions, also contains Company-wide Use cases that supplies app with its core features.  
Architecture layer is in charge of supplying domain layer with actual storage implementations and handling all the communication with external resources (out from the app domain itself)
The UI is implemented using a MVVM approach leveraging Android Architecture components to enhance development ant app result, performance and stability.


App icon attribution:
<div>Icons made by <a href="https://www.flaticon.com/authors/those-icons" title="Those Icons">Those Icons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>