# Points Tracking REST API
Author: Shufan Sun 

Creation Date: 2023.9.28

This is a simple REST API implemented with Springboot DAO structure using Java.

Its purpose is to track points earned and spent by users. Users can add points, spend points, and check their point balances.

## Table of Contents
- [Getting Started](#getting-started)

## Getting Started

### Prerequisites
- Java 1.8 or higher
- Spring Boot
- Maven download and configuration
- MySQL database
- Postman


### Installation

1. Clone this repository to your local repository
   ```bash
   https://github.com/ShufanSun/FetchServer.git
2. Open the project folder FetchServer from your editor
  
**Note:** For this project I used IntelliJ

### Environment Configuration

#### Maven Setup ####
Go to `File->Settings->Build, Execution, Deployment->Build Tools->Maven`, your configuration should look 
like the image shown below. You might need to modify some of your system's environment settings.

![](/src/images/Maven.png)

#### Server Ports Setup ####

In order for the web server to work, we need to connect it to the server. For my project I used XAMPP Control Panel as 
the way to manipulate the servers. For this project, I reconfigured the datasource to be fetched from `localhost:3306` 
through editing the `my.ini` file through `Config` action for `MySQL` module. Edit the port numbers to `port=3306`
in `my.ini`, then close the file.

Click the `Start` action button on XAMPP for `Apache`,`MySQL`, and `Tomcat`. If your servers succeed in running, you should now see
something like the below image:

![](/src/images/serverConnected.png)
**Note:** Make sure thre's no other things running on your 8000 port or else you can just go to `application.properties` file to change the `server.port`.

### Database check ###
Now we've configured the environments successfully, let's proceed to setting up database and testing systems.

For this project I used Myphpadmin for checking the outputs.

You can try typing `http://localhost/phpmyadmin/` in your browser, and a page without errors showing your database should show. 


## Tasks Accomplished

### 1. Add points the user get from each payer

Open Postman, and choose `POST` from the dropdown. Type in `localhost:8000/add` in the search bar.

On the row below, go to the `Body` tag under the searching bar, and then change the rightmost dropdown on the row below `Body`
 tag to `JSON`. In the coding area below the row below, input JSON objects like the below example.
`{
"payer" : "DANNON",
"points" : 5000,
"timestamp" : "2020-11-02T14:00:00Z"
} `
Then click the `Send` button on the upper right side of Postman page. If you see a status code of `200` below, the points are added successfully.

You can try refresh the adphpadmin page and you shall see a directory called `pointsss` created with a Table called `pointsTable`, there we have the database you just updated.

### 2. Statistics of Points Transacted

Now type in `localhost:8000/spend` in the search bar.

On the row below, input JSON objects like the below example.
`{"points" : 5000}`
Then click the `Send` button on the upper right side of Postman page. If you see a status code of `200` below, the points are subtracted from user account successfully for the item.
If you see a status code of `400` and a message saying the user doesn’t have enough points below
, the points are not successfully subtracted from user account successfully for the item.


You can refresh the phpadmin page and can check the point stats for the user.


### 3. Get Point Balance
Choose `GET` from the dropdown. Type in `localhost:8000/balance` in the search bar.

Then click the `Send` button on the upper right side of Postman page. You will see Respond Body showing all the data in JSON format
You can try refresh the adphpadmin page and check whether the infomation match exactly

### End of Documentation #


