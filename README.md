XumakWeatherData

Xumak challenge app to show weather data from allweather.docs.apiary.io API

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

Actual Android app including icons, MainActivity and Composable functions that makes the app user
friendly and visually appealing.

Each module contains a set of tests for checking internal funcionality  
Dependency versions are stored in versions.gradle file

App icon attribution:
<div>Icons made by <a href="https://www.flaticon.com/authors/those-icons" title="Those Icons">Those Icons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>