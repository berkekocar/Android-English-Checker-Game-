# Android-English-Checker-Game
I created an English checker mobile game with Custom View via Google's Android SDK. 
Notes about the program before start. 

-If the project has an execution error it might be because of the gradle sync. These kind of case it is better to rebuild the project then run it. 
- For selecting starts with player 1, there is a bug if you select the item sometimes it does not allow you to deselect. 
- For capture it is necessary to press on to the enemy checker. 

*****************************************************************
1.Project Overview
In this assignment, the given task was to build a Draughts Game using Android Development
Platform and its SDK. The project has developed in Java and marked up by XML; there is no
database integration; however, Android’s Custom View feature has used in that assignment
correctly. The version control was made via — Git version control. 6 commits represent the
brackets, details are noted in these brackets.
In this paper, in the following part Classes and Methods are explained; after that, every data
structure and technical design are explained, and finally, in the last part design decisions
made for improving the UI is going to explain.
2. Classes & Methods
Java creates the functionality of the application. Classes and methods that I used in my
project are as follows.
1) MainActivity.class
This class is fundamentally responsible for handling the first activity. This class has mainly
onCreate method responsible for showing the app when the app first started. Additionally,
button and menu items have created in that method that enables users to have a chance to
restart the activity and customize the activity.
a. onCreate Method:
In the onCreate method, I defined the Menu Item and Button at the beginning of the
program. Also, it has a click listener for the reset button and menu item for regular click.
I created two-player objects from the Player Class that is responsible for showing the turn of
the player, and it counts how many checkers in the game then it shows it on the Text View.
In the following, I defined the menu items and reset button; then I add Action Listener to
these buttons. In the resource files, I created a menu folder is responsible for showing the
menu option in the top right and Add Activity item has added in it. After creating that
button, it is necessary to use menu inflator to give the functionality to this button.
onCreateOptionsMenu is responsible for creating the menu item, and the
onOptionsItemSelected method is used for listing the actions on that button. In the
onOptionsItemSelected method, there is an onMenuItemClick method that shows a simple
input dialogue that is going to detail in the following. Simple input dialogue is used primarily
as an alert in this case, and for building this alert, builder function has used. Positive and
adverse reaction on this case, there are two different buttons has built by the builder, and
there is a listener responsible for handling the clicks in these buttons. Positive onClick
method into the simple input dialogue saves the text from the edited text area to database
via save method; however, in negative button, there is not any database process. Toast
messages used to notify the user.
In the XML part, I used a Relative Layout that keeps the button, and Text View elements at
the beginning of the application. Bellow of my Relative Layout, I created another Relative
Layout that has a Custom View on it. One of the requirements in the second Bracket was the
use of dynamic sizing instead of using fixed sizing. I used Display Metrics class to get the
width and height of the screen size then set I set it the Custom View’s size. I only used a
width that I got a square layout for Custom View. Also, I linked them with the onMeasure
method for using in my Custom View. This part is going to discuss more detailed in the
Custom View class.
2) CustomView.class
This classes essential responsibility is to create the Custom View and link with the logic in the
Player class. This class has a direct relationship with all other classes, and it is handling the
part of the screen events. I created some variables for defining the number of items and
their attributes, such as colour. Also, touching event elements are defined at the beginning
of this class. This class is extending from the View object that gets most of the attributes
from it. Also, it is necessary to create default constructor for the class that takes in a context
then another constructor that takes in a context and a list of attributes that were set
through XML and final constructor that take in a context, attribute set and a default style in
case the view is to be styled in a certain way. In the following, we need to call the init
method for sharing with all constructors.
a. Init Method:
In the init method, objects have called and defined. Firstly, the colour object was initialized
and set with RGB formatted values. Also, from the Click Event class, Event_Nothing has
called for resetting the application that can directly access from that class. This event is
going to reset the whole event at any time. At the end of this method, touch activities have
initialized with arrays has 16 elements in it. This is going to determine how many pointers
the device handle accurately, and the given value is overkill value for mobile devices.
b. onDraw Method:
This method is responsible for rendering operation in the Custom View and activity into the
screen. Before drawing, this method has a Canvas object that we can use for drawing
operations. Also, we need to set the screen size dynamic in that method from the
onMeasure method. There are two canvas objects; in this case, these are circular and square
objects. We must have the custom view’s width and height depending on the device, so
onMeasure methods returns have set to our custom view values. This part will be detailed in
the following method. After setting these variables, we must divide it by eight for finding the
length of the square. Also, for checkers, we need to figure out the radius that set half of the
length of one square in this case. Two loops used for creating the layout of our game and
counter is dividing the squares that change the colour and, the colour object comes from the
init method that already initialized. Moreover, in the second loop, setting actions have set
with conditions; this is initializing when the touch interaction occurs from the onTouchEvent.
c. onMeasure Method:
This method is responsible for measuring the screen width and height with Measure Spec
object. This object gets the size of the screen, and we can pass the value to our original
width and height. For forcing it to pass the dimension values to a method and return it.
d. onTouchEvent Method:
Touching events and interactions are occurring in this method. There are two states, in this
case, the first one is ACTION.DOWN another one is ACTION.UP. Before the start, we need to
check these statements for getting input from the user. ACTION_DOWN indicates if the
user's finger is on the screen and its location. Also, we create a pointer ID variable for taking
its initial pointer. In the second case, which is ACTION_UP is responsible for indicating if the
user removed hand from the screen, and it ends the touch interaction. Inside of the checking
the conditions I called the events that are responsible for selecting, moving and capturing.
Mostly the event gets the coordinates of the touch interaction and creates an action after.
Event’s logic has detailed in Event Class that connected to the Player class. If the move
location is set, we need to render the view again, that is the reason we used invalidate at the
end of the events. Events are discussed in Click Event class. This part gets its logic from
Player class that decisions are made by Boolean array; it will be discussed more detailed in
that class as well.
e. Reset Method:
This method resets the game. If it calls, this method will automatically call the init method in
it. That init method calls the Event type nothing that initializes everything from the
beginning.
3) ClickEvent.class
Enumerations serve the purpose of representing a group of named constants in an in this
case. There are 7 events that stored in another class; Event Nothing is responsible for select
and deselect, Event Wrong Piece is responsible for checking the select area that checked buy
Boolean counter, Event Select Piece is responsible for operations after select, Event Move is
responsible for moving if there is no checker in the targeted area, move piece and ask to
continuo is checks if there are multiple checkers that we can eat. Finally, Event Eat Piece is
responsible for capture events. Also, this class keeps the last event and index in it.
4) Piece.class
This class creates the checkers and status during the game. It keeps its turn, type and
selection. Also, if the piece is king that changes the return type in it, this class has a default
constructor which initialized these variables logic false in the beginning. Moreover, set Piece
has created for changing the select status by manually in the Custom View class.
5) Player.class
This class is an object responsible for whole the logic between the game and Events are
directly connected to that class. In the beginning, I created an array has two elements type
in that object represents the player 1 and player 2.
a. endPlayerTurn Method:
This method checks the player turn and makes the transition between two players in type
Boolean. Also, it controls highlight the checkers in the select event.
b. getEnemyPlayer:
This method controls and defines the checker's belongings, starting with the first player.
get current Player
This method controls and defines the checker's belongings, starting with the second player.
c. checkHighlights:
This method checks the checker if it is selected or not.
d. selectPiece & unselectPiece:
This method is responsible for selecting and unselecting the piece.
e. pieceActionTo:
This method controls the move action and possible captures in the capture event.
f. movePiece:
This method is responsible for moving and deletes the previous location.
g. hasPieceAtIndex:
This method checks the checkers if there is one in the given index.
h. initPlayerPiece:
Finally, this method initializes the pieces and stores them into an array as a matrix logic.
3. Data Structures and Algorithms in the Project:
Array's and multidimensional arrays are heavily used during the development of the logic.
Firstly, I created an object called a Player that keeps the attributes and most importantly, the
turn in to it. I used a boolean type array has two elements in it. This array keeps the users
turn and changes its status into it. Also, during to project, I needed to create a matrix that
represents the specific locations. In Custom View, there was no index logic, so I needed to
get X and Y values from the touch event and convert that to the index. For keeping the X-axis
and Y-axis values, I used a multidimensional array that saves the values instantaneously.
Instead of using integer or double array, I created an object called Piece and that set in a
multidimensional array. Piece check type is boolean, so we only needed to see if it is false or
accurate in its attributes. Also, that Piece object keeps the checker's type, if that checker is
king the king status turns true that gets more abilities in the Player class. Lastly, I keep the
colour attributes into an array has three elements which represent the RGB values. The
reason for using arrays instead of Array Lists was I kept everything in Boolean logic then
everything was about if and else statements.
4. UI Decisions:
The UI is designed to present a user-friendly environment for the user to facilitate the usage
of the application. During the development of the interface, XML used. Thanks to Android's
measure method, pages become responsive, which means that they change their form
automatically according to the device windows size. In the UI, the app has two Text View for
showing how many checkers has remained during the game. In the beginning, there are
twelve checkers, and it counts down when the checker captures. Also, there is a button
responsible for resetting the application on the top right corner. For having a customized
view during the game, you can change the board and stones colour with the Settings menu
under the menu inflater. I decided to put the list on the top right that independent from the
application so, I gained more space from the screen. The boar consist of green and light
yellow colours. According to the design perspective, green is a calming natural colour. Plus,
shades of green are related to balance and harmony. From a colour psychology perspective,
it is the great balancer of the heart and the emotions, creating equilibrium between the
head and the heart. Yellow colour has harmony with the green colour so, I decided to use it
that fitted best with it. In the other hand, I used the black and red colour that is the standard
colour for most strategy games. Also, if you pick the checker, the colour turns into blue.
While drawing the checkers, I decided to use a full square size and fit it entirely instead of
using a smaller radius. With this checkers are more significant, and the player can easily fit
their finger on to it. The tables and buttons are designed proportionally so that it presents a
proper view of the user. No unnecessary information is used. On the other hand, complexity
is avoided while designing the pages. In addition, all type of devices such as tablets and
mobile phones are considered while placing each element on the pages and, I tested them in
both platforms physically.
