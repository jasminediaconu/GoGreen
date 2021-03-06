# Task and Issue distribution 4 for SPRINT 5


## `2nd DEMO DEADLINE 18th March: full workflow "Eating a vegetarian meal"`


* `Click button to register meal, send a request to the server, store it and persists it.`
* `The full workflow of "Eating a vegetarian meal" is implemented`
* `User clicks on a button to indicate that they bought a vegetarian meal`
* `The server stores this data in a file or database`
* `On closing the client and reopening it, it is able to request the data from the server and shot it to the user`


## TO DO General for next demo:
* When activity is applied it should show up immediately on the agenda
* Everybody should know JUnit 5 tests and mockito
* Everybody should have checkstyle plugin, know how to autoformat and manual format. 
* Start cleaning up fully functional classes according to checkstyle
* All current branches should be merged and functional in develop
* `TEST COVERAGE`Preferable 95%, min 80%

## Testing:

* Tests for database and server requests, serverApp coverage - Wout
* Activity, Item, Achievement, User, ClientUser classes with Mockito - Giuliano
* LoginRequest, SignUpRequest - Jan Willem
* User, ClientUser, Item, Activity - Gino
* Divide AgendaController into helper class and test it - Mandy
* The function in ActivityController needs to have a helper class, selectDomain method - Wout


* Can ImageChoose.class be tested?



## Overview page, badges & achievements (Jasmine):
* Work on the overview page, implement badges(achievements)
* Make a scrollpane
* Come up with realistic badges, achievements
* Badges should be stored locally
* Show up some title


## Popup windows for plus button and functionality (Giuliano):

* Extra miscellanous plus button to be filled with items from Jan-Willem
* Check function foodchoices filled with lambda
* Do the same for transport and energy options


## Backend and General (Wout):

* Make the request specific for username, failed username and email
* Store the profile icon on the server
* Help Gino with the mail responses, forgot password
* Update the agenda screen, removing adding instand when communication with the server
* Tests
* Tests for the database
* Figure out tests for server and database communication
* Request to follow a person


## Profile (Jan Willem)

MUST HAVE:
* Linking between profile and agenda Wout & Jan-Willem
* Miscellanous items for the activities, using a cup at the coffeshop, which hep reduce CO2
* Sprint review
* TEST for your code with is non GUI to reach test coverage pref 95%, min 80%

EXTRA: Multithreaded application


## Overview (Svetoslav)

MUST HAVE:
* Overview graph part, part of gamification bonus points

FROM LAST SRINT:
* Overview of the graph of cO2 saved
* dropdown menu: week, month, per year
* Total CO2 saved
* Function to add together
* Weekly Tasks
* Make a new table in the database for daily co2 saved
* Analyse list of activities and make a graph out of those


## Leaderboard (Mandy):

MUST HAVE:

* Follow and compare CO2 to the users, 
* Global and following to be populated with users
* In global all the users which are in the database should show up, show CO2 next to their names and show the follow button.

EXTRAS:
* Search bar, you can look up somebodies profile, if there is no user show --> no user
* Global list, ordered according to Highest CO2 reduction, follow button next to the person, if followed there is an unfollow button instead
* Following list, show only the people you follow, show their CO2 reduction, if already followed, unfollow button shows up
* Use Wout's function to populate the leaderboard.
* Dropdown menu with countries

## Mixed Tasks (Gino):

MUST HAVE:

* Tests PRIORITY

* Remember me functionality (Needs more time, needs to consult with Wout)
* Forgot password functionality (Consult with Wout)
* when trying to signup, already existing username dispaly red error message, username already exists 
* invalid password red, internet connection 
* Terms of service 
* Privacy policy