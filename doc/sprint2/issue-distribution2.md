# Task and Issue distribution SPRINT 2

## `2nd DEMO DEADLINE 18th March: full workflow flow "Eating a vegetarian meal" (click button to register meal, send a request to the server, store it and persists it. :
* * The full workflow of "Eating a vegetarian meal" is implemented
* User clicks on a button to indicate that they bought a vegetarian meal
* The server stores this data in a file or database
* On closing the client and reopening it, it is able to request the data from the server and show it to the user`

### Login Screen: (Giuliano)
* A mockup login screen which lets you in if you enter a specific username and password.
* Designed in Javafx using the JFoenix library
* Close windows after successful login
* Not focusing on looks,  Try to make it look like the WE ARE DARK log in screen.
* Window size: XGA = 1024 x 768 pixels.
* Make window non re-sizable
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)
* (EXTRA) test

### Main Screen/Agenda: (Jasmine)
* Window size: XGA = 1024 x 768 pixels.
* Not focusing on looks but try to make it look like JASMINE's design on DISCORD
* Designed in Javafx using the JFoenix library
* Make logout button work, it should just close the window
* Minimizing side bar button working
* A window popup when clicked on username/settings
* Make window non re-sizable
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)
* (EXTRA) Make the buttons clickable (when a button is clicked it will be highlighted and the active screen will change)
* (EXTRA) Implement Mandy's design

### Server: (Wout)
* Working client and server communication
* Proper hashing
* Accept multiple clients
* Basic profile setup for the server-side for every user...
* At boot-up have some server setting loading up; naming, server-profile
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)

### Design the rest of the screens: (Mandy)
* Designing a theme for the application, consistent color scheme, fonts: minimalistic, modern, green, white, brown
* Design each screen of the application: (user profile, agenda, leaderboard etc.)
* How we could actually implement the design into Javafx using the JFoenix library
* Material.io for inspiration and ideas
* Window size: XGA = 1024 x 768 pixels.
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)

### User Profile: (Jan Willem)
* Window size: XGA = 1024 x 768 pixels.
* Make window non re-sizable
* What kind of data-structures should we use?
* (EXTRA) Please make new bullet-points for any other feature you worked on
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)

### CO2 emmission integration: (Gino)
* Inventory/database off all the items possible
* Should we use a WEB API/database or just locally stored database
* How to distribute CO2 scores in the system.
* (EXTRA) Please make new bullet-points for any other feature you worked on
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)

### Database of users (Svetoslav)
* Should contain:

	* user_ID
	* Totalamount of CO2saved
	* CO2 per activity
	* hashed_password

* (EXTRA) Please make new bullet-points for any other feature you worked on 
* Don't forget to write tests for the code (Tools: mokito being used for at least 5 different test classes, ECLemma in Eclipse min 80% test coverage need 95% for max score)
