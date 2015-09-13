# Journal #


## <u> Week #1 9/29 - 10/6 </u> ##

**The progress you made (once you start coding, mention the revisions for that week)**

We have made our front page which include two buttons called "request driver" and "request passenger". If we were to click on request driver it would link to our second page which has two edit text box to type in to and from address. We found tutorials showing us how to enable GPS setting on the phone. We have created Google map view with a marker indicator. We did extensive research about finding the current location of a user and we'll be implementing the following week.

**The problems you have encounter:**

  1. We tried to created buttons from the form widget design tool but we have trouble positioning the button at certain location.
  1. We have trouble assigning the button to an event handler which would carry out a certain operation for that button.
  1. We have trouble setting up the phone as a deployment.

**Lesson learned**

  1. We learned that instead of using LinearLayout, if we used RelativeLayout, it's easier for us to position our buttons using the widget design tool.
  1. We learned for every button we have to assign an onClick action, the name of the onClick action has to have the same name as the name of the even handler function.
  1. We learned that in order for us to deploy our project to our android phone, we first have to install the driver at our desktop.

**What you plan to do the following week**

  1. Enable GPS
  1. implement the third page
  1. implement the find current location
  1. implement the find nearby taxi by hard coding it into the program.
  1. implement the route calculation using Google API.



## <u> Week #2 10/6 - 10/13 </u> ##

**The progress you made (once you start coding, mention the revisions for that week)**

We setup Apache Tomcat Server on port 8080.  We create a servlet called "TaxiCenterController".  This servlet will accept the following request parameters via GET or POST method.

  1. lat (for latitude coordinate) ie. lat=12.34
  1. long (for longitude coordinate) ie. long=100.322
  1. tid (for taxi driver login id) ie. tid=joe22
  1. action (for either updating the driver's location information or return the driver info as a json object). Action includes
    * action=set
    * action=json
    * action=html

The servlet will process the request passed from either the mobile device or any java stand alone application.  It will saved these coordinate information corresponding to the appropriate taxi driver into memory.

We have also created a map find the current location using GPS. We used ItemizedOverlay to implement our markers because we want to implement several markers for different drivers. With the ability of creating different markers at different location, we could find the GPS coordinate of different taxi drivers and create a marker.

We have implement the third page of our design which included two buttons called "Cab directory" and "Find nearby taxi" and we also comment and rename variables so it make it easier for us to maintain in the future.


**The problems you have encounter:**

  1. We cannot get the servlet to execute.  It returns page not not found.  We have uncomment some codes in the web.xml for servlet execution.
  1. The google map does not display the current location of the mobile device accurately.  It is in some unknown location on the map.
  1. We have problems of setting up a json object to be returned from the servlet to the mobile device.  We didn't know how to setup the correct string values pair to be returned as a json object.
  1. We were not able to implement the route calculation using the google API due to our struggle to setup
  1. We first used the zoom in streetView to show the map but the map came up looking very funny with boxes and x mark but using trafficView solve the problem
  1. The first thing we used to implement the marker was to use the overlay class. We have to change it because it does not support more than one marker


**Lesson learned**

  1. We learned how to setup and configure Apache Tomcat web server on our local computer.  We learned how to modify the server.xml to listen to different port.  Default is port 8080.  We learned how to modify the web.xml file to enable servlet execution.
  1. We learned how to deploy the java stand alone application onto the web server by exporting war file.
  1. We learned how to write a servlet program to handle and process http request to update driver GPS location.
  1. We learned how to put different marker on the google map.
  1. We learned that zooming in too much causes the streetView to look very funny
  1. We learned extending ItemizedOverlay have the ability to defined multiple markers at multiple location.
  1. we learned that onTap of the marker, we could display information of the driver.

**What you plan to do the following week**

  1. Make http request to get the available driver location
  1. Parse the json objects to extract taxi driver info
  1. take driver info and plot it into the map
  1. Implement enable GPS checkbox
  1. Convert address to coordinate and vice versa
  1. Debug markers on map
  1. Route calculation
  1. Implement cab directory
  1. Implement subdirectory of cab directory


## <u> Week #3 10/13 - 10/20 </u> ##

**The progress you made (once you start coding, mention the revisions for that week)**

  1. Remove the bug of where the marker was overlapping
  1. Able to pass multiple data between activities
  1. Able to convert address to coordinate to plot the map
  1. Implement to use the Network provider or GPS provider
  1. Implemented the cab directory
  1. We combined the two projects together, which is the map and testcab application. Now we can type in the address to show in marker
  1. Enabled GPS and implement into the project
  1. Move Json object to Project

**The problems you have encounter:**
  1. We did not know that if we were to combing the map with the taxicab project we need a new MAP API key. The key is only valid for the specify project. Which is very troublesome.
  1. I made some update to the taxicab project and then accidentally delete the taxicab project because i was trying to combine the map project together. I end up having  to import the project from SVN and re-update the changes and commit it back on the svn.
  1. We tried to put the Vector of coordinate points into a loop and add it to the marker but it doesn't work.
  1. We tried to pass multiple data between activities but it took us a while because we made it mistake on naming the id of the edittext in the layout of XML.
  1. Trouble animating two points if it is too far.

**Lesson learned**
  1. By setting the update rate of location request either 5 secs or 5 meter instead of 0,0 would eliminate the overlapping marker. We could also implement deleting the last marker but setting the marker to 5,5 is easier
  1. We also learned that the bug on our address.xml where we switch the name of from edittext to to edittext which gave us inconsistent result.
  1. We learned that we could convert address to coordinate and plot multiple coordinate on the map and vice versa.

**What you plan to do the following week**
  1. Route calculation
  1. Implement cab directory
  1. Implement subdirectory of cab directory
  1. Parse Json
  1. able to use a loop to get the points to plot it in the map
  1. able to calculate the maximum distance where user could see in a map

## <u> Week #4 10/20 - 10/27 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. We have implement the cab directory that extends ListActivity.
  1. We used the onListItemClick to check for the array of string value to determined which intent to call.
  1. After pressing on a taxi company, we create a new intent that has a call button that allow user to call the cab company to request a taxi cab
  1. we created a call button to call the cab
  1. We were able to connect to our tom cat server to get the coordinate from the server and plot it in the map.


**The problems you have encounter:**
  1. The program force close when i tried to extends ListActivity
  1. Forgot to add the activity to Activity Manifest
  1. we try to calculate distance between two points but it is very inaccurate
  1. We had a problem connecting to our server in our application because we are in the same network. Instead connecting to localhost we have to be specifiy our ip address and be at the same network.
  1. The coordinate came out at the wrong spot because we didn't mutliply it by 1E6

**Lesson learned**
  1. Always remember to add activity to Manifest when creating a new activity
  1. Need to add andriod:id/list in order to extend ListActity
  1. Extending ListActivity is alot easier than extending Activity to implement ListView. The code is more readable if we were to extend ListActivity.

**What you plan to do the following week**
  1. Set up Google App Engine
  1. swap the map to the driver UI
  1. Instead of passenger that will see available driver we would have to have robots that acts as an passengers that let driver see where's the available passengers
  1. Passenger is able to request for driver providing information. The passenger data is send to the server.(fake the data in server)
  1. After passenger made request they are able to see a confirmation page.
  1. The driver is able to see the map of where customers that had made requests.
  1. The driver is accept requests.



## <u> Week #5-6 10/27 - 11/10 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. Set up the google app engine account to host the server side for process the taxi request
  1. Set up the data structure and storage to keep track and manage taxi driver and passenger information.
  1. Write the log in authentication procedure to verify user
  1. Create a web based front end to add new driver and add new request for testing purpose
  1. Set up a request page for users where user could enter information in order to request nearby driver
  1. Set up a confirmation page after user requested a driver
  1. Set up a log in page where user could log in
  1. Set up a open request page which it connected to the google app engine server to get all the open request for driver to view
  1. Set up a my order page where driver could see the order that he has made.

  * he problems you have encounter:**1. encounter the google map does not work anymore and to replace it we use list instead because of time constraint
  1. Struggling on how to set up the database back end
  1. running into trouble of saving the data into the database
  1. making sql query with no result exception
  1. trying to create the servlet to manage the driver and the taxi request having the mapping result in http 405 exception**

**Lesson Learned**
  1. We learned google app engine.
  1. We learned using listactivity
  1. We learned how to map java object to database using persistence model


**What you plan to do the following week**
  1. Set up preference for phone passenger and driver side
  1. re-do the database mapping using the new preferences schema design
  1. find current location of driver and passenger
  1. convert current location of passenger to address and fill into the edittext
  1. Setup the registration and save info into preferences
  1. create sql filtering to match driver and passenger preferences
  1. Implement server side accepting request for the driver
  1. implement client and server side competing request (handling problem for simultaneous accepting request submission from both taxi drivers

## <u> Week #7 11/10 - 11/17 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. Created both perferences for driver and passenger
  1. Send driver perferences and passenger perferences to server
  1. Get GPS location from driver and passenger
  1. convert current GPS coordinate to street address
  1. Able to send http request to request for a driver
  1. From the driver side, i was able to retrieve the passenger order from the server
  1. From the driver side, I was able to retrieve my order from the server
  1. caculated the distance between point a to point b
  1. Able to accept passenger order

**The problems you have encounter:**
  1. There was a problem convert coordinate to address. I named the class called Address and to convert, I need to use geocoder which return an array of List

&lt;address&gt;

 and the compiler didn't recoginze which address i am talking about.
  1. In the FindBy class, I turn on the GPS but it take time to load until i could convert the point to address. I have to wait for the GPS to finish to get the address before preceeding further, so i set a flag to prohibit to continue unless i have my current location
**lesson learned**
  1. Google supported mutil-thread
  1. I learned to pass value to HTTP and connect to the server to get result back
  1. I learned how to make perferences and store it into the phone
**What you plan to do the following week**
  1. Filter cab request by location and payment option
  1. Get estimated arrival time
  1. cancel requests for my order as well as passenger request order
  1. create javadoc
  1. clean up the UI.

## <u> Week #8 11/17 - 12/1 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. implemented the cancel button for the driver and passenger so they could cancel requests
  1. clean up the UI and design it so it looks better
  1. Implementing the confirmed button so after the driver is done with the request they are able to confirmed the request and the request would disappear in his/her order page
  1. inserted drawing into the UI
  1. implement the call button for both the passenger and driver
  1. Clean up the cab directory
  1. We have got the estimated arrival time to display to the passenger

**The problems you have encounter:**
  1. We have encounter an error confirming request because of a invalid URL
**lesson learned**
  1. Learned how to design the UI and insert images to the android

**What you plan to do the following week**
  1. Create javadoc
  1. create a flow chart for the classes that we have made
  1. set up eclipse for our laptops in order to present in class
  1. set up the emulator and test emulator so we could present in the class


## <u> Week #5-6 10/27 - 11/10 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. Set up the google app engine account to host the server side for process the taxi request
  1. Set up the data structure and storage to keep track and manage taxi driver and passenger information.
  1. Write the log in authentication procedure to verify user
  1. Create a web based front end to add new driver and add new request for testing purpose
  1. Set up a request page for users where user could enter information in order to request nearby driver
  1. Set up a confirmation page after user requested a driver
  1. Set up a log in page where user could log in
  1. Set up a open request page which it connected to the google app engine server to get all the open request for driver to view
  1. Set up a my order page where driver could see the order that he has made.

  * he problems you have encounter:**1. encounter the google map does not work anymore and to replace it we use list instead because of time constraint
  1. Struggling on how to set up the database back end
  1. running into trouble of saving the data into the database
  1. making sql query with no result exception
  1. trying to create the servlet to manage the driver and the taxi request having the mapping result in http 405 exception**

**Lesson Learned**
  1. We learned google app engine.
  1. We learned using listactivity
  1. We learned how to map java object to database using persistence model


**What you plan to do the following week**
  1. Set up preference for phone passenger and driver side
  1. re-do the database mapping using the new preferences schema design
  1. find current location of driver and passenger
  1. convert current location of passenger to address and fill into the edittext
  1. Setup the registration and save info into preferences
  1. create sql filtering to match driver and passenger preferences
  1. Implement server side accepting request for the driver
  1. implement client and server side competing request (handling problem for simultaneous accepting request submission from both taxi drivers

## <u> Week #7 11/10 - 11/17 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. Created both perferences for driver and passenger
  1. Send driver perferences and passenger perferences to server
  1. Get GPS location from driver and passenger
  1. convert current GPS coordinate to street address
  1. Able to send http request to request for a driver
  1. From the driver side, i was able to retrieve the passenger order from the server
  1. From the driver side, I was able to retrieve my order from the server
  1. caculated the distance between point a to point b
  1. Able to accept passenger order

**The problems you have encounter:**
  1. There was a problem convert coordinate to address. I named the class called Address and to convert, I need to use geocoder which return an array of List

&lt;address&gt;

 and the compiler didn't recoginze which address i am talking about.
  1. In the FindBy class, I turn on the GPS but it take time to load until i could convert the point to address. I have to wait for the GPS to finish to get the address before preceeding further, so i set a flag to prohibit to continue unless i have my current location
**lesson learned**
  1. Google supported mutil-thread
  1. I learned to pass value to HTTP and connect to the server to get result back
  1. I learned how to make perferences and store it into the phone
**What you plan to do the following week**
  1. Filter cab request by location and payment option
  1. Get estimated arrival time
  1. cancel requests for my order as well as passenger request order
  1. create javadoc
  1. clean up the UI.

## <u> Week #8 11/17 - 12/1 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. implemented the cancel button for the driver and passenger so they could cancel requests
  1. clean up the UI and design it so it looks better
  1. Implementing the confirmed button so after the driver is done with the request they are able to confirmed the request and the request would disappear in his/her order page
  1. inserted drawing into the UI
  1. implement the call button for both the passenger and driver
  1. Clean up the cab directory
  1. We have got the estimated arrival time to display to the passenger

**The problems you have encounter:**
  1. We have encounter an error confirming request because of a invalid URL
**lesson learned**
  1. Learned how to design the UI and insert images to the android

**What you plan to do the following week**
  1. Create javadoc
  1. create a flow chart for the classes that we have made
  1. set up eclipse for our laptops in order to present in class
  1. set up the emulator and test emulator so we could present in the class



## <u> Week #9 12/1 - 12/8 </u> ##
**The progress you made (once you start coding, mention the revisions for that week)**
  1. we have created javadoc for all our classes
  1. we have created a flow chart that explain the flow of the classes from one page to another
  1. we have clean up everything in our svn so it's easier to navigate
  1. we have included link to the wiki page so it's easier to navigate
  1. we have done a powerpoint for the presentation
  1. we practice our presentation
  1. we setup our laptop and emulator to prepare for the presentation
**The problems you have encounter:**
  1. it was pretty confusing as to importing the project to our newly install laptop. The project could not identify the R.java
  1. came across problem with the emulator because it was too slow on my netbook. I had to install eclipse and android sdk to my faster laptop

**lesson learned**
  1. I could save state on my emulator so when the presentation is ready I'm able to fire it up in a shorter amount of time

**What you plan to do the following week**
  1. STUDY FOR FINALS!





