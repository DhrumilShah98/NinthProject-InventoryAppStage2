# Udacity and Google India Scholarship Program
## Android Basics Nanodegree Project 9 "Inventory App, Stage 2"
## Name of App - "Books Box"

<b>PROJECT SPECIFICATION</b>

1.  The app contains activities and/or fragments for the user to:

     - Add Inventory
     - See Product Details
     - Edit Product Details
     - See a list of all inventory from a Main Activity<br><br>
     Multiple actions listed above can be combined in a single activity.<br>
     The user navigates between the activities and/or fragments using one or more of the following navigation patterns 
     - Navigation Drawer, View Pager, Up/Back Navigation, or Intents.

2.  In the Main Activity/Fragment, each list item displays the Product Name, Price, and Quantity.

    Each list item also contains a SaleButton that reduces the total quantity of that particular product by one 
    (include logic so that no negative quantities are displayed).

3.  The Product Detail Layout displays the Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number 
    that's stored in the database.

    The Product Detail Layout also contains buttons that increase and decrease the available quantity displayed.

    Add a check in the code to ensure that no negative quantities display (zero is the lowest amount).   

    The Product Detail Layout contains a button to delete the product record entirely. 

    The Product Detail Layout contains a button to order from the supplier. 
    In other words, there exists a button to contains a button for the user to contact the supplier 
    via an intent to a phone app using the Supplier Phone Number stored in the database.

    Note: Including the Product Image is an optional feature. 
    The functionality to pick a picture from the gallery is beyond the scope of this program, 
    but students who are interested in learning this functionality may implement it.

4.  When there is no information to display in the database,
    the layout displays a TextView with instructions on how to populate the database 
    (e.g. what should be entered in the field, which fields are required).

5.  The code runs without errors. 
    For example, when user inputs product information (quantity, price, name), instead of erroring out,
    the app includes logic to validate that no null values are accepted.
    If a null value is inputted, add a Toast that prompts the user to input the correct information before they can continue.

6.  The Main Activity displaying the list of
    current inventory contains a ListView that populates with the current products stored in the table.
    
7.  The Main Activity contains an Add Product Button prompts the user for product information 
    and supplier information which are then properly stored in the table.

    Before the information is added to the table, it must be validated -
    In particular, empty product information is not accepted. 
    If user inputs invalid product information (name, price, quantity, supplier name, supplier phone number),
    instead of erroring out, the app includes logic to validate that no null values are accepted.
    If a null value is inputted, add a Toast that prompts the user to input the correct information before they can continue.
   
8.  When a user clicks on a List Item from the Main Activity, it opens up the detail screen for the correct product.

9.  In the Detail View for each item, there are Buttons that correctly increase or decrease the quantity for the correct product.

    Add a check in the code to ensure that no negative quantities display (zero is the lowest amount).

    The student may also add input for how much to increase or decrease the quantity by if not using the default of 1.

10. In the Detail Layout, there is a Delete Button that prompts the user for confirmation and,
    if confirmed, deletes the product record entirely and sends the user back to the main activity.
    
    
<b>About Books Box</b>

User can 
  - Add Book details(Product Name, Product Price, Product Quantity, Supplier Name, Supplier Contact Number) 
    and successful addition of book will display product name, product price and product quantity on main screen.
  - Edit(Update) added books details
  - Delete single book at a time as well as all the books at same time.
  - Contact supplier using supplier phone number.
  - Can decrease the product quantity from main screen using "Sale" button attached to each book.
  
NOTE: All fields are mandatory and must be populated by user.

## Books Box App Images
<img src = "BooksBoxImages/1.png" width="300px" height="550px"> <img src = "BooksBoxImages/2.png" width="300px" height="550px">
<img src = "BooksBoxImages/4.png" width="300px" height="550px"> <img src = "BooksBoxImages/5.png" width="300px" height="550px"> 
<img src = "BooksBoxImages/7.png" width="300px" height="550px"> <img src = "BooksBoxImages/8.png" width="300px" height="550px">
<img src = "BooksBoxImages/10.png" width="300px" height="550px"> <img src = "BooksBoxImages/11.png" width="300px" height="550px">

Check out more images in BooksBoxImages folder.
