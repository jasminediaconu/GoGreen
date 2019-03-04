# Task and Issue distribution SPRINT 3

## `2nd DEMO DEADLINE 18th March: full workflow "Eating a vegetarian meal"`

* `Click button to register meal, send a request to the server, store it and persists it.`
* `The full workflow of "Eating a vegetarian meal" is implemented`
* `User clicks on a button to indicate that they bought a vegetarian meal`
* `The server stores this data in a file or database`
* `On closing the client and reopening it, it is able to request the data from the server and


## Agenda (Jasmine):
* popup when clicked on username, display logout connect to login screen
* Agenda, separation between the days, hardcoded activities
* Cross button, you can delete the activity

## Popup windows for plus button and functionality (Giuliano):
* make plus button work with the meal
* then transport, energy
* Create Activity Class with the help of wout
* Item class with arraylist as example items

## Backend and General (Wout):
* Instead of storing an activityID it should store uuID(Unique...identifier)
* Sessions properly work
* We have data persistence, when you close and open app we have the data
* First time signup should go to profile, from 2nd time onwards it goes to the welcome message from jasmin
* Popup if trying to select another window (leaderboard,overview etc.) when profile is not complete
* All the requests for creating activities
* Mockito
* Continous Integration

## Profile (Jan Willem)
* Link names 
* dropdown menu with types of cars
* 2 dropdown menus cartype and emmission type: electric, gas
* Add solar panel, when added in activity changes in profile
* Change of profile picture possible
* next to username display email.

## Overview (Svetoslav)
* Overview of the graph of cO2 saved
* dropdown menu: day, week, month
* Total CO2 saved
* Function to add together 
* Weekly Tasks
* Make a new table in the database for daily co2 saved

## Leaderboard (Mandy):
* Search bar, you can look up somebodies profile, if there is no user show --> no user
* Global list, ordered according to Highest CO2 reduction, follow button next to the person, if followed there is an unfollow button instead
* Following list, show only the people you follow, show their CO2 reduction, if already followed, unfollow button shows up
* Use Wout's function to populate the leaderboard.
* Dropdown menu with countries

## Mixed Tasks (Gino):
* Remember me functionality
* Forgot password functionality
* when trying to signup, already existing username dispaly red error message, username already exists
* invalid password red, internet connection
* Terms of service
* Privacy policy