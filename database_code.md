# ALL TABLES FOR THE DB

#### USER LOGIN TABLE
CREATE TABLE IF NOT EXISTS user_login (
    username VARCHAR(200) PRIMARY KEY, 
    email TEXT NOT NULL, 
    password VARCHAR(50) NOT NULL, 
    userid SERIAL
);

#### USER PROFILE TABLE
CREATE TABLE IF NOT EXISTS user_profile (
    userid INTEGER PRIMARY KEY,
    countryName VARCHAR(25) DEFAULT 'global', 
    imageurl VARCHAR(50) DEFAULT 'default',
    totalCo2 FLOAT(2) DEFAULT 0,
    carType VARCHAR(25) DEFAULT 'SUV', 
    carEmissionType VARCHAR(25) DEFAULT 'Gas', 
    lastOnline DATE DEFAULT CURRENT_DATE, 
    streakLength INTEGER DEFAULT 0, 
    solarPower INTEGER DEFAULT 0, 
    leds INTEGER DEFAULT 0, 
    roomTemp INTEGER DEFAULT 21
);

#### USER FOLLOWS TABLE
CREATE TABLE IF NOT EXISTS user_follows (
    userid INTEGER NOT NULL, 
    followingid INTEGER NOT NULL
);

#### USER ACTIVITIES TABLE
CREATE TABLE IF NOT EXISTS user_activities (
    activityid SERIAL PRIMARY KEY, 
    userid INTEGER NOT NULL, 
    itemid INTEGER NOT NULL, 
    amount FLOAT(2) NOT NULL, 
    date DATE DEFAULT CURRENT_DATE
);

#### ITEM TABLE
CREATE TABLE IF NOT EXISTS items (
    itemid SERIAL PRIMARY KEY, 
    name VARCHAR(150) NOT NULL, 
    type VARCHAR(15) NOT NULL, 
    co2 FLOAT(5) NOT NULL
);

#### ACHIEVEMENTS TABLE
CREATE TABLE IF NOT EXISTS achievements (
    achievementid SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    path VARCHAR(50) NOT NULL,
    goal INTEGER DEFAULT 0
);

INSERT INTO achievements ("title", "description", "path", "goal") VALUES ('', '', '', );