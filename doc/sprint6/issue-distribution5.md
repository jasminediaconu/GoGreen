# Task and Issue distribution SPRINT 6

## `3rd DEMO DEADLINE 1st April, week 8: Final Feedback of TA"`

* `4 of the 6 minimal features from Food, Transportation and Energy have been implemented (0.4)`
* `The full workflow for "Track the CO2 that you save and compare to your friends" is implemented`
* `You are able to have an overview of the CO2 that you have saved. (0.2)`
* `You are able to have an overview of the CO2 that others have saved. (0.2)`
* week 8 thursday draft of final report, week 9 monday Lab Trial run for presentation

## TO DO General for next demo:
* When activity is applied it should show up immediately on the agenda
* Everybody should have checkstyle plugin, know how to autoformat and manual format.
* Start cleaning up fully functional classes according to checkstyle
* All current branches should be merged and functional in develop
* `week 8 thursday draft of final report, week 9 monday Lab Trial run for presentation`
* `TEST COVERAGE`Preferable 95%, min 80%

## Testing:

* Divide AgendaController into helper class and test it - Mandy: getRowCount, round, activityMap functions

## Overview page, badges & achievements (Jasmine):

* Connect the badges with the serverrequest, to retrieve them from the database
* Open popup on hover
* Make badges more distinct when active/inactive, black and white or opacity
* Loading them from the list, checking if they are achieved


* Click on the badge, show up a popup box, say title, what the user has to do to achieve it and show progress bar.
* Make them go vertical 6 in a row.
* AchievementController class not same the same as FXML controller
* Call an updating function depending on what activity you added to the agenda
* To update the progress bar in the achievements


FROM LAST WEEK:

* Work on the overview page, implement badges(achievements)
* Make a scrollpane
* Come up with realistic badges, achievements
* Badges should be stored locally
* Show up some title


## Popup windows for plus button and functionality (Giuliano):






* Fix applyButton method in AgendaController to add energy and transport to the agenda when clicked apply
* Make applyButton generic
* Check if solarpanel, leds, lowered temperate already in agenda for that day if not take it from the database and add it to the day.
* lower temperature
* solarpanels
* main.clientuser.getsolarpanels and implement in show agendaactivities function
* main.clientuser.getleds and implement in show agendaactivities function
* add unit to Text km if g if food - 
* Ask on mattermost java app, if not able to contribute that much


FROM LAST WEEK:

* Mockito Achievement, Item, Activity

## Backend and General (Wout):

STILL TO BE DONE:

* Change the boolean to numbers, rewrite requests
* Link CO2 saved from Agenda to show under ProfileScreen - data persistence
* Profile picture persistence

Done:
* Achievement request
* Make a follow request
* Link CO2 saved from Agenda to show under ProfileScreen - data persistence
* Make a request to make the profile picture persistentMake an in id for the image, client sends it to the server then send it back when user logs in
* Change database to be able to receive the number of solar panels and leds from the profile

FROM LAST WEEK:

* Make the request specific for username, failed username and email
* Store the profile icon on the server
* Help Gino with the mail responses, forgot password
* Update the agenda screen, removing adding instand when communication with the server
* Tests
* Tests for the database
* Figure out tests for server and database communication
* Request to follow a person

## Profile (Jan Willem)

* Update the @username on welcomeScreen to the actual username
* Updating the agendaScreen when an activity is added without having to restart the application
* Option textfield to put the number of solar panels and leds on the profile
* Call the function to make the profile picture load from database

FROM LAST WEEK:

* Linking between profile and agenda Wout & Jan-Willem
* Miscellanous items for the activities, using a cup at the coffeshop, which hep reduce CO2
* Sprint review
* TEST for your code with is non GUI to reach test coverage pref 95%, min 80%

EXTRA: Multithreaded application

## Overview (Svetoslav)

* Finish the overview this sprint
* Help him with the ComboBox
* Do the algorithm for the graph


MUST HAVE:

* Make new logo

* Overview graph part, part of gamification bonus points
* In the serverRequest class there is getactivities
* Get the activity list
* Retrieve activities

FROM LAST SRINT:
* Overview of the graph of cO2 saved
* dropdown menu: week, month, per year
* Total CO2 saved
* Function to add together
* Weekly Tasks
* Make a new table in the database for daily co2 saved
* Analyse list of activities and make a graph out of those

## Leaderboard (Mandy):

PRIORITY:

* The follow button should follow the user
* Should be able to unfollow user
* You cannot follow yourself
* Search bar, you can look up somebodies profile, if there is no user show --> no user
* Make a follow button
* Change Global to Following Pane

MUST HAVE:	
* Follow and compare CO2 to the users	
* Global and following to be populated with users
* In global all the users which are in the database should show up, show CO2 next to their names and show the follow button.

EXTRAS:

* follow button next to the person, if followed there is an unfollow button instead
* Following list, show only the people you follow, show their CO2 reduction, if already followed, unfollow button shows up
* Use Wout's function to populate the leaderboard.
* Dropdown menu with countries


## Mixed Tasks (Gino): MUST HAVE:

FIRST:

* There is a great readme (0.1)
* Working on achievements with Jasmine, communicate and divide tasks
* If you would like to contribute clone down the repo
* If you would like to use our application just download the executable and run it on your computer
* Gifs
* Look up the report, decide what is individual and what has to be done by 1 specific person.

THEN:

* Remember me functionality (Needs more time, needs to consult with Wout)
* Forgot password functionality (Consult with Wout)
* when trying to signup, already existing username dispaly red error message, username already exists
* invalid password red, internet connection
* Terms of service
* Privacy policy/Terms and conditions nicely done