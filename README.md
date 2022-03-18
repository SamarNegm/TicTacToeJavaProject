[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)[![forthebadge](https://forthebadge.com/images/badges/uses-brains.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/powered-by-black-magic.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/makes-people-smile.svg)](https://forthebadge.com)


# **Tic Tac Toe**

Tic-tac-toe has been a part of almost everyone’s childhood.
While the game of tic-tac-toe is really old, dating back to ancient Egyptians at around 1300 B.C., the underlying principles of the game have endured the test of time. The game seems simple enough. Two opponents, one using X and one using O, use a 3 x 3 grid to mark their symbols. The first one to get all three of his/her symbols in a row, whether it’s diagonal, horizontal, or vertical, wins the game.

---
## Overview

![single player demo](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/login.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/welcime.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/start.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/new%20game.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/difficulty.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/play%20with%20computer.png)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/invite.PNG)
![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/playing.PNG)

This project is a simple Tic-Tac-Toe online game developed as a final project for the java course.
The game is developed using java and javafx utilizing socket programming, multi-threading
and JSON data transmission to allow for a multiplayer online game with other features such as ingame Chat and much more.

---
## Table of Contents

<!-- TOC -->
- [Main Features](https://github.com/SamarNegm/TicTacToeJavaProject/edit/master/README.md#%EF%B8%8F-main-features)
- [Game Features](https://github.com/SamarNegm/TicTacToeJavaProject/edit/master/README.md#-game-features)
- [Demo](https://github.com/SamarNegm/TicTacToeJavaProject/edit/master/README.md#-demo)
- [How To Use](https://github.com/SamarNegm/TicTacToeJavaProject/edit/master/README.md#-how-to-use)
    - [Database Setup](#database-setup)
    - [Run The Server](#run-the-server)
    - [Run The Client](#run-the-client)
- [Dependencies](#dependencies)
- [Limitations](#limitations)
- [Possible Improvements](#possible-improvements)
- [About Us](https://github.com/SamarNegm/TicTacToeJavaProject/edit/master/README.md#-about-us)

<!-- /TOC -->



---
## 🕹️ Main Features

### Server-Side

- Live list of players status and score.
- Load and logs monitoring through a simple GUI.
- Start and stop the server with a simple click of a button.
- Passwords are encrypted before being saved in the database.

### Client-Side

- Play in single mode vs. computer in two levels of difficulty
- A live list of the status and score of other players in the game.
- Save the game to continue playing it later.
- User-friendly GUI.

---
## 💡 Game-Features

- A score metrics for each player that is calculated upon winning or losing.
- Play with your friends in multiplayer mode (online).
- Playing again with the same player.
- Quit the game in the middle, but it will result in loosing the game.

---
## 🎮 Demo

### Server-side Interface

![server gui](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/server.png)

You can start and stop the server from the GUI, as well as view the server logs, the database and a live players list.

### Multiplayer Mode

![multiplayer gif](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/new%20game.png)
![]![](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/playing.PNG)


### Saving and Loading

![save gif](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/saved.PNG)

The players can save the game to continue playing at another time.

For the full demo video refer to the following link: [Demo Video](https://github.com/SamarNegm/TicTacToeJavaProject/blob/master/Demo/tic-tac-toe.mkv)
---
## 💻 How To Use

Clone the project in your working directory.

```bash
https://github.com/SamarNegm/TicTacToeJavaProject.git
```

or download the zipped file and unzip it in your working directory.


### Database Setup

- Import SQL schema file in any MySQL Server ( <u>Recommended: phpMyAdmin</u> ) or implement SQl statements manually in mySQL Shell as described in tictactoedb.sql in [DBSchema directory.](https://github.com/SamarNegm/TicTacToeJavaProject/tree/master/ServerSide/Database%20Schema)
- Edit DBconfig.java file in  [Database Package ](https://github.com/SamarNegm/TicTacToeJavaProject/tree/master/ServerSide/src/database) to fill your database username ,password ,port number ,and database server url .

### Run The Server

Using File Explorer : Navigate to the ServerSide folder then inside dist folder double click ServerSide.jar



⚠️**Note that the server and the client run on port 7777.**

### Run The Client

Using File Explorer : Navigate to the ClientSide folder then inside dist folder double click ClientSide.jar

Using the Terminal : Navigate to the [ClientSide/dist](https://github.com/SamarNegm/TicTacToeJavaProject/tree/master/ClientSide/dist) directory and run the following command:

```bash
java -jar ClientSide.jar
```

Using The executable file: Double-click the ClientSide.jar file found in the [bin directory](https://github.com/SamarNegm/TicTacToeJavaProject/tree/master/ClientSide/bin)

---
## 📋 Dependencies

* [MySQL](https://www.mysql.com/)
* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html/)
* [Json-simple library](https://github.com/fangyidong/json-simple)
* [mysql-connector](https://dev.mysql.com/downloads/connector/j/)


---
## 🚫 Limitations

- A potential shortcoming could appear when the database goes down while the server is running.
- The reliability of the Client-Server communication is about 90%; sometimes the requests from the client are not caught by the server.

---
## 📈 Possible Improvements

- Implement a notification system to notify players about others signning in/out.
- To allow the player to share his game result on his social media accounts.
- - Live chat
---
## 👨‍💻 About Us

We are a team of software enginnering students at ITI intake 42, Menofia branch, Open-source application track.

### 📞 Contacts

You can find us on:


#### GitHub

- [Samar Negm](https://github.com/SamarNegm)
- [Abdelfattah Omar](https://github.com/AbdelfattahOmar)
- [Marwa Ezzaat](https://github.com/MarwaEzzatMo)
- [Mohamed Shehata](https://github.com/Mohamed-Shhata)
