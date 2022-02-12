/*
Nathan Pham
Java Assignment 4
Sunday 12/12/2021
JDK Version 16
Honor Code:
"I have neither given nor received unauthorized aid in completing this work,
nor have I presented someone else's work as my own."

For this assignment 
- Utilize Files to read and copy products / Also write in a separate file for users and have the system recognize previous users
- GUI
GroceryStore.V.4
 */


import java.util.Scanner; // we need to import this in order to get the user's input
import java.io.BufferedReader;
import java.io.*;
import java.lang.StringBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.io.FileWriter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;  


public class GroceryStore extends JFrame{ // Setting up the Java file
	
    
	static itemList [] productDetails;
	
    // creating an int value to get the product ID
    public int number, count = 0; // number is the amount of products that the user wants
    						// count is the total amount of items
    public double total = 0, salesTaxes = 0.06;
    public final double fullPaymentDiscount = 0.95;
    // salesTaxes is 6% and fullPaymentDiscount is a 5% discount, labeled final so it cannot be changed
    // create total for total price in terms of double to be precise in 2 decimals
    
    // countItem is to keep track of amount of items
    public int [] countItem = new int[15]; //
    /* an int array that contains the following list with initial variables set to zero
    This is equal to this below
    countApple = 0, countPear = 0, countBanana = 0, countOrange = 0, countGrape = 0,  
    countPizza = 0;, countWater = 0, countPotato = 0, countTuna = 0, countShark = 0, etc; 
    creating a count to keep track of how many products that the user wants*/
    
    
    public int plans; // the plans variable doesn't need to be included in the constructor
    
    public int id = 1; // setting up some conditions
    
    public int invoice;
    
    // These variables are used for adding a new product
    // These String values will be used to implement the new items
    public String newID, newProduct = "", newPrice, newDetail = "";
    
    public int idCount = 10;
    public boolean underLimit = true;
    public double newCost;
    static String name = "";
    
    // GUI base variables that are needed to setup the UI
    
    static JButton Button[] = new JButton[10];
    static JLabel productLabel[] = new JLabel[10];
    static JButton nameButton = new JButton();
    static JButton checkOutButton = new JButton();
    static JButton fullPayoutButton = new JButton();
    static JButton installmentButton = new JButton();
    static JButton saveInvoiceButton = new JButton();
    static JButton showInvoiceButton = new JButton();
    JLabel title, insertFullName, insertAmount;
    JFrame frame;
    Container contentPane;
    private static JTextField textFullname, textAmount;
    static String fullName, tempAmount;
    static JTextArea displayUI;
    
    static boolean choice1 = true, choice2 = true; 
    // Ex: if the user chooses installment, they cannot click installment or fullpayment button
    
    // files
    
    static int n = 0, c = 0, p = 0, q = 0;
    static double tempSalesTax, tempTotal, tempSubTotal;
    static String line, tempLine;
    static StringBuilder appendLine = new StringBuilder ("");
    static StringBuilder appendInvoiceLine = new StringBuilder ("");
    static StringBuilder payment = new StringBuilder ("");
    static BufferedReader userFile;
    static BufferedWriter invoiceWriter;
    static BufferedWriter userFileWrite = null;
    static BufferedWriter appending = null;
    
	static void welcomeStatement() // static vs public methods, static can be called by itself, meaning the method itself
	{							   // For public methods, it requires an object to be called
		// ------------ Welcoming the User -----------------
	      
	      System.out.println("\t=========================");
	      System.out.println("\t|                       |");
	      System.out.println("\t|        Welcome        |");
	      System.out.println("\t|       to Walkart      |");
	      System.out.println("\t|       Version 4.0     |");
	      System.out.println("\t|                       |");
	      System.out.println("\t=========================");
	      // display output in system terminal
	}
 
    public static boolean checkIfNegative(int count) // this method checks if the count (amount) is negative because you can't have -1 apples
    {
    	if (count < 0)
    	{
    		return true; // boolean method returns true
    	}
    	else
    	{
    		return false; // boolean method returns false
    	}
    }
    
    public static void printMenuDetails() // simple code to system output
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nFood list (please enter the number code to order the listed item, ex: 45): \n");
        System.out.println("SALE PRICE! A 10%% DISCOUNT IS APPLIED TO ANY 10 SAME ITEM!");
        System.out.printf("\n%s\t%s\t\t%s\t\t%s", "ID", "Product name", "Price", "Description"); 
    }
  
    public static void main(String [] args) throws IOException //starting up the program
    { 
    	GroceryStore menu = new GroceryStore();
    	menu.startFrame(); // creating GUI
    	welcomeStatement();
    	
      // creating object called store to access variables from GroceryStore
      GroceryStore store = new GroceryStore();
      
      Scanner myObj = new Scanner(System.in); // scanning for next user input
      Scanner itemFile = new Scanner(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\item_list.txt"));
      // Opening the file base on its exact location
      
      // these variable are for implementing new products into the system
      int k = 0, j = 0, l = 0, m = 0; 
      String copy = ""; // this variable is used for 
      
      // Initalizing the array of objects, this will then go to class itemList where it arranges
      // its variables
      
      
      String [] listOfStrings = new String[40];
      String [] stringHolder = new String[1000];
      productDetails = new itemList[10]; // max size of item list is 10
      itemList [] productPrice = new itemList[10];
      productDetails = new itemList[19];
      
      // ============================ ITEMS FILE =====================================
      
      try
      {
		  while(itemFile.hasNextLine()) // read the entire file until there is nothing (null)
		  {
			copy = itemFile.nextLine();  // copy contents
			listOfStrings[k] = copy; // save contents into array
			if(k % 4 == 3) // checks if the file has read the line 4 times
			{
				// implement new items based on the file
				productDetails[0+l] = new itemList(listOfStrings[0+m], listOfStrings[1+m], listOfStrings[2+m], listOfStrings[3+m]);
				l++; // next item
				m = m + 4; // every 4 strings is a new item
			}
			k++;
		  }
		  
		  itemFile.close();
      }
      catch(Exception e)
      {
    	  System.out.println("\nFile does not exist!\n");
      }
      // close the file

    	  
      // ========================== Date =========================
      
      DateTimeFormatter recordDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // date is set up like this
      LocalDateTime now = LocalDateTime.now();  // get time and date
      System.out.println(recordDate.format(now));  // print it into the system
      
      // ============================ HARD CODED ITEMS =====================================
      
      productPrice[0] = new itemList(0.99); 
      productPrice[1] = new itemList(0.50); 
      productPrice[2] = new itemList(0.57); 
      productPrice[3] = new itemList(1.20);
      productPrice[4] = new itemList(2.99);
      productPrice[5] = new itemList(10.99);
      productPrice[6] = new itemList(0.99);
      productPrice[7] = new itemList(0.99);
      productPrice[8] = new itemList(21.99);
      productPrice[9] = new itemList(799.99);  
      
      // =========================== Open file with button  ===================================
      
       
      nameButton.addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  try {
				userFile = new BufferedReader(new FileReader("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt"));
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} 
    	      
    	      try {
				userFileWrite	= new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt"),true));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
    		  fullName = textFullname.getText().toString(); // get text from JTextField box, any input is used as a name
    	      
    		  
    		  try
    	      {
    		      while((line = userFile.readLine()) != null) // read throughout file
    		      {
    		    	  stringHolder[n] = line;
    		    	  if(q == 1) //print only the transaction date of said user
    		    	  {
    		    		  // appendLine is a string Builder so that I can append in a GUI
    		    		  appendLine.append(stringHolder[p+1] + "\n"); 
    		    		  q--;
    		    	  }
    		    	  if(line.equals(fullName)) // finding user
    		    	  {
    		    		  
    		    		  p = n; 
    		    		  q++;
    		    		  c++;
    		    	  }
    		    	  n++;
    		      }
    		      
    		      if (c == 0) // checks if it is a new user
    		      { // display a welcome user message
    		    	  displayUI.setText("Welcome NEW User");
    		      }
    		      else
    		      {
    		    	  // display in GUI for returning user and their transaction history base on file
    		    	  displayUI.setText("Welcome RETURNING USER \n" + appendLine.toString());
    		      }
    	      }
    	      catch(Exception e1)
    	      {
    	    	  displayUI.setText("\nFile does not exist!\n");
    	      }
    		     
    	      try { // try closing, if not, give exception error into system
				userFile.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	      try {
				userFileWrite.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	  } 
  		});
    
      // ======================== Button actions ====================
      
      // ========== Apple =============
    Button[0].addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  
  		tempAmount = textAmount.getText().toString(); // get text
  		try 
  			{ // utilizing try and catch to check if the input is correct instead of using if and else
  				store.number = Integer.parseInt(tempAmount);
  				// converts string to an int
  			}  								  		 // basically checks if the String is an Integer
		catch (NumberFormatException ne) 
      		{ // skips the exception error in the system output
				displayUI.setText("Error, input is not a number, please try again!");
	        }
  		
  		if(store.number > 0 || store.number < 0) {
	  		store.countItem[0] += store.number; // you can add or remove, just simply put a minus sign to remove
	  		if(store.number > 0)
		  		{
	  				displayUI.setText("Added " + store.number + " Apples"); 
		  		}
	  		else if(store.number < 0)
	  		{
	  			displayUI.setText("Removed " + store.number + " Apples"); 
	  		}
	  		
	        // tells the user that the system added the product to their list
	        if(store.countItem[0] < 0)
			  {
	        	  displayUI.setText("You cannot have a negative amount of Apples");
		      	  store.countItem[0] = 0;
				  // reset amount to 0 if the user had negative amount of said product
			  }
  		}
        else {
        	displayUI.setText("Try Again"); // incase there were 0 inputs
        }

  	           } 
		});
      
    // ========== Pear =============
    Button[1].addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		tempAmount = textAmount.getText().toString(); // get text
    		try 
    			{ // utilizing try and catch to check if the input is correct instead of using if and else
    				store.number = Integer.parseInt(tempAmount); // converts string to an int
    			}  								  		 // basically checks if the String is an Integer
  		catch (NumberFormatException ne) 
        		{ // skips the exception error in the system output
  				displayUI.setText("Error, input is not a number, please try again!");
  	        }
    		
    		if(store.number > 0 || store.number < 0) {
  	  		store.countItem[1] += store.number; // you can add or remove, just simply put a minus sign to remove
  	  		if(store.number > 0)
  		  		{
  	  				displayUI.setText("Added " + store.number + " Pears"); 
  		  		}
  	  		else if(store.number < 0)
  	  		{
  	  			displayUI.setText("Removed " + store.number + " Pears"); 
  	  		}
  	  		
  	        // tells the user that the system added the product to their list
  	        if(store.countItem[1] < 0)
  			  {
  	        	  displayUI.setText("You cannot have a negative amount of Pears");
  		      	  store.countItem[1] = 0;
  				  // reset amount to 0 if the user had negative amount of said product
  			  }
    		}
          else {
          	displayUI.setText("Try Again"); // incase there were 0 inputs
          }
    	           } 
  		});
    
    // ========== Banana =============
    Button[2].addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		tempAmount = textAmount.getText().toString(); // get text
    		try 
    			{ // utilizing try and catch to check if the input is correct instead of using if and else
    				store.number = Integer.parseInt(tempAmount); // converts string to an int
    			}  								  		 // basically checks if the String is an Integer
  		catch (NumberFormatException ne) 
        		{ // skips the exception error in the system output
  				displayUI.setText("Error, input is not a number, please try again!");
  	        }
    		
    		if(store.number > 0 || store.number < 0) {
  	  		store.countItem[2] += store.number; // you can add or remove, just simply put a minus sign to remove
  	  		if(store.number > 0)
  		  		{
  	  				displayUI.setText("Added " + store.number + " Bananas"); 
  		  		}
  	  		else if(store.number < 0)
  	  		{
  	  			displayUI.setText("Removed " + store.number + " Bananas"); 
  	  		}
  	  		
  	        // tells the user that the system added the product to their list
  	        if(store.countItem[2] < 0)
  			  {
  	        	  displayUI.setText("You cannot have a negative amount of Bananas");
  		      	  store.countItem[2] = 0;
  				  // reset amount to 0 if the user had negative amount of said product
  			  }
    		}
          else {
          	displayUI.setText("Try Again"); // incase there were 0 inputs
          }
    	           } 
  		});
    
    //======= Orange =======
    Button[3].addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  
  		tempAmount = textAmount.getText().toString(); // get text
  		try 
  			{ // utilizing try and catch to check if the input is correct instead of using if and else
  				store.number = Integer.parseInt(tempAmount); // converts string to an int
  			}  								  		 // basically checks if the String is an Integer
		catch (NumberFormatException ne) 
      		{ // skips the exception error in the system output
				displayUI.setText("Error, input is not a number, please try again!");
	        }
  		
  		if(store.number > 0 || store.number < 0) {
	  		store.countItem[3] += store.number; // you can add or remove, just simply put a minus sign to remove
	  		if(store.number > 0)
		  		{
	  				displayUI.setText("Added " + store.number + " Oranges"); 
		  		}
	  		else if(store.number < 0)
	  		{
	  			displayUI.setText("Removed " + store.number + " Oranges"); 
	  		}
	  		
	        // tells the user that the system added the product to their list
	        if(store.countItem[3] < 0)
			  {
	        	  displayUI.setText("You cannot have a negative amount of Oranges");
		      	  store.countItem[3] = 0;
				  // reset amount to 0 if the user had negative amount of said product
			  }
  		}
        else {
        	displayUI.setText("Try Again"); // incase there were 0 inputs
        }
  	           } 
		});
    
    // ======= Grape =========
    Button[4].addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		tempAmount = textAmount.getText().toString(); // get text
    		try 
    			{ // utilizing try and catch to check if the input is correct instead of using if and else
    				store.number = Integer.parseInt(tempAmount); // converts string to an int
    			}  								  		 // basically checks if the String is an Integer
  		catch (NumberFormatException ne) 
        		{ // skips the exception error in the system output
  				displayUI.setText("Error, input is not a number, please try again!");
  	        }
    		
    		if(store.number > 0 || store.number < 0) {
  	  		store.countItem[4] += store.number; // you can add or remove, just simply put a minus sign to remove
  	  		if(store.number > 0)
  		  		{
  	  				displayUI.setText("Added " + store.number + " Grapes"); 
  		  		}
  	  		else if(store.number < 0)
  	  		{
  	  			displayUI.setText("Removed " + store.number + " Grapes"); 
  	  		}
  	  		
  	        // tells the user that the system added the product to their list
  	        if(store.countItem[4] < 0)
  			  {
  	        	  displayUI.setText("You cannot have a negative amount of Grapes");
  		      	  store.countItem[4] = 0;
  				  // reset amount to 0 if the user had negative amount of said product
  			  }
    		}
          else {
          	displayUI.setText("Try Again"); // incase there were 0 inputs
          }
    	           } 
  		});
    
    // ======== Pizza ========
    Button[5].addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  
  		tempAmount = textAmount.getText().toString(); // get text
  		try 
  			{ // utilizing try and catch to check if the input is correct instead of using if and else
  				store.number = Integer.parseInt(tempAmount); // converts string to an int
  			}  								  		 // basically checks if the String is an Integer
		catch (NumberFormatException ne) 
      		{ // skips the exception error in the system output
				displayUI.setText("Error, input is not a number, please try again!");
	        }
  		
  		if(store.number > 0 || store.number < 0) {
	  		store.countItem[5] += store.number; // you can add or remove, just simply put a minus sign to remove
	  		if(store.number > 0)
		  		{
	  				displayUI.setText("Added " + store.number + " Pizzas"); 
		  		}
	  		else if(store.number < 0)
	  		{
	  			displayUI.setText("Removed " + store.number + " Pizzas"); 
	  		}
	  		
	        // tells the user that the system added the product to their list
	        if(store.countItem[5] < 0)
			  {
	        	  displayUI.setText("You cannot have a negative amount of Pizzas");
		      	  store.countItem[5] = 0;
				  // reset amount to 0 if the user had negative amount of said product
			  }
  		}
        else {
        	displayUI.setText("Try Again"); // incase there were 0 inputs
        }
  	           } 
		});
    
    // ====== Bottles of Water ======
    Button[6].addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		tempAmount = textAmount.getText().toString(); // get text
    		try 
    			{ // utilizing try and catch to check if the input is correct instead of using if and else
    				store.number = Integer.parseInt(tempAmount); // converts string to an int
    			}  								  		 // basically checks if the String is an Integer
  		catch (NumberFormatException ne) 
        		{ // skips the exception error in the system output
  				displayUI.setText("Error, input is not a number, please try again!");
  	        }
    		
    		if(store.number > 0 || store.number < 0) {
  	  		store.countItem[6] += store.number; // you can add or remove, just simply put a minus sign to remove
  	  		if(store.number > 0)
  		  		{
  	  				displayUI.setText("Added " + store.number + " Bottles of Water"); 
  		  		}
  	  		else if(store.number < 0)
  	  		{
  	  			displayUI.setText("Removed " + store.number + " Bottles of Water"); 
  	  		}
  	  		
  	        // tells the user that the system added the product to their list
  	        if(store.countItem[6] < 0)
  			  {
  	        	  displayUI.setText("You cannot have a negative amount of Bottles of Water");
  		      	  store.countItem[6] = 0;
  				  // reset amount to 0 if the user had negative amount of said product
  			  }
    		}
          else {
          	displayUI.setText("Try Again"); // incase there were 0 inputs
          }
    	           } 
  		});
    
    // ======== Potato Chip  ========
    Button[7].addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  
  		tempAmount = textAmount.getText().toString(); // get text
  		try 
  			{ // utilizing try and catch to check if the input is correct instead of using if and else
  				store.number = Integer.parseInt(tempAmount); // converts string to an int
  			}  								  		 // basically checks if the String is an Integer
		catch (NumberFormatException ne) 
      		{ // skips the exception error in the system output
				displayUI.setText("Error, input is not a number, please try again!");
	        }
  		
  		if(store.number > 0 || store.number < 0) {
	  		store.countItem[7] += store.number; // you can add or remove, just simply put a minus sign to remove
	  		if(store.number > 0)
		  		{
	  				displayUI.setText("Added " + store.number + " Potato Chips"); 
		  		}
	  		else if(store.number < 0)
	  		{
	  			displayUI.setText("Removed " + store.number + " Potato Chips"); 
	  		}
	  		
	        // tells the user that the system added the product to their list
	        if(store.countItem[7] < 0)
			  {
	        	  displayUI.setText("You cannot have a negative amount of Potato Chips");
		      	  store.countItem[7] = 0;
				  // reset amount to 0 if the user had negative amount of said product
			  }
  		}
        else {
        	displayUI.setText("Try Again"); // incase there were 0 inputs
        }
  	           } 
		});
    
    // ======== Raw Tuna =======
    Button[8].addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		tempAmount = textAmount.getText().toString(); // get text
    		try 
    			{ // utilizing try and catch to check if the input is correct instead of using if and else
    				store.number = Integer.parseInt(tempAmount); // converts string to an int
    			}  								  		 // basically checks if the String is an Integer
  		catch (NumberFormatException ne) 
        		{ // skips the exception error in the system output
  				displayUI.setText("Error, input is not a number, please try again!");
  	        }
    		
    		if(store.number > 0 || store.number < 0) {
  	  		store.countItem[8] += store.number; // you can add or remove, just simply put a minus sign to remove
  	  		if(store.number > 0)
  		  		{
  	  				displayUI.setText("Added " + store.number + " Raw Tunas"); 
  		  		}
  	  		else if(store.number < 0)
  	  		{
  	  			displayUI.setText("Removed " + store.number + " Raw Tunas"); 
  	  		}
  	  		
  	        // tells the user that the system added the product to their list
  	        if(store.countItem[8] < 0)
  			  {
  	        	  displayUI.setText("You cannot have a negative amount of Raw Tunas");
  		      	  store.countItem[8] = 0;
  				  // reset amount to 0 if the user had negative amount of said product
  			  }
    		}
          else {
          	displayUI.setText("Try Again"); // incase there were 0 inputs
          }
    	           } 
  		});
    
    // ======== Shark Fin =======
    Button[9].addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  
  		tempAmount = textAmount.getText().toString(); // get text
  		try 
  			{ // utilizing try and catch to check if the input is correct instead of using if and else
  				store.number = Integer.parseInt(tempAmount); // converts string to an int
  			}  								  		 // basically checks if the String is an Integer
		catch (NumberFormatException ne) 
      		{ // skips the exception error in the system output
				displayUI.setText("Error, input is not a number, please try again!");
	        }
  		
  		if(store.number > 0 || store.number < 0) {
	  		store.countItem[9] += store.number; // you can add or remove, just simply put a minus sign to remove
	  		if(store.number > 0)
		  		{
	  				displayUI.setText("Added " + store.number + " Shark Fins"); 
		  		}
	  		else if(store.number < 0)
	  		{	
	  			displayUI.setText("Removed " + store.number + " Shark Fins"); 
	  		}
	  		
	        // tells the user that the system added the product to their list
	        if(store.countItem[9] < 0)
			  {
	        	  displayUI.setText("You cannot have a negative amount of Shark Fins");
		      	  store.countItem[9] = 0;
				  // reset amount to 0 if the user had negative amount of said product
			  }
  		}
        else {
        	displayUI.setText("Try Again"); // incase there were 0 inputs
        }
  	           } 
		});
    
    checkOutButton.addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  
    		  for (int i = 0; i < store.countItem.length; i++) // length should be 10
    	      {
    	    	  // Remember, countItem is an int array, so most empty positions carries a value of 0
    	    	  store.count += store.countItem[i]; // adds all count 
    	      }
    		  
    		//------------------ PRINTING INVOICE -----------------
    		  appendInvoiceLine.append("\nPrinting your receipt . . . . . .");
    		  appendInvoiceLine.append("\n\n#########################################################################");
    		  appendInvoiceLine.append("\t\nWalkart\n");
    		  appendInvoiceLine.append("Customer: " + fullName); // Stating the customer's name
    		  appendInvoiceLine.append("\nIn Cart: " + store.count); // count total items in cart
    		  appendInvoiceLine.append("\nQuantity      ID    Product Name\t\tPrice\t\t"); // structure
    	      
    	      
    	      if(store.countItem[0] > 0) // 1 Apple
    	      { // if the amount of said product is at least above 0, print in invoice
    	    	  appendInvoiceLine.append(productDetails[0].printProductIntoGUI(store.countItem[0]));
    	    	  
    	    	  double totalApple = 0;
    	    	  totalApple = (0.99 * store.countItem[0]);
    	    	  if (store.countItem[0] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  	totalApple *= 0.90; // take 10% out, we are left with 90% of the full price
	    	      		appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalApple));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalApple));
	    	      	}
    	    	  
    	    	  store.total += totalApple; 
    	      }
    	      
    	      if(store.countItem[1] > 0) // 2 Pear
    	      {
    	    	  appendInvoiceLine.append(productDetails[1].printProductIntoGUI(store.countItem[1]));
    	    	  
    	    	  double totalPear = 0;
    	    	  totalPear = (0.50 * store.countItem[1]);
    	    	  if (store.countItem[1] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  	totalPear *= 0.90; // take 10% out, we are left with 90% of the full price
	    	      		appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalPear));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f", totalPear));
	    	      	}
    	    	  
    	    	  store.total += totalPear; 
    	      } 
    	      
    	      if(store.countItem[2] > 0) // 3 Banana
    	      {
    	    	  appendInvoiceLine.append(productDetails[2].printProductIntoGUI(store.countItem[2]));
    	    	  double totalBanana = 0;
    	    	  totalBanana = (0.57 * store.countItem[2]);
    	    	  if (store.countItem[2] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalBanana *= 0.90; // take 10% out, we are left with 90% of the full price
	    	      		appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalBanana));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f", totalBanana));
	    	      	}
    	    	  store.total += totalBanana; 
    	      }
    	      
    	      if(store.countItem[3] > 0) // 4 Orange
    	      {
    	    	  appendInvoiceLine.append(productDetails[3].printProductIntoGUI(store.countItem[3]));
    	    	  double totalOrange = 0;
    	    	  totalOrange = (1.20 * store.countItem[3]);
    	    	  if (store.countItem[3] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalOrange *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalOrange));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f", totalOrange));
	    	      	}
    	    	  
    	    	  store.total += totalOrange; 
    	      }
    	      
    	      if(store.countItem[4] > 0) // 5 Grape
    	      {
    	    	  appendInvoiceLine.append(productDetails[4].printProductIntoGUI(store.countItem[4]));
    	    	  double totalGrape = 0;
    	    	  totalGrape = (2.99 * store.countItem[4]);
    	    	  if (store.countItem[4] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalGrape *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalGrape));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalGrape));
	    	      	}
    	    	  
    	    	  store.total += totalGrape;
    	      }
    	      
    	      if(store.countItem[5] > 0) // 6 Pizza
    	      {
    	    	  appendInvoiceLine.append(productDetails[5].printProductIntoGUI(store.countItem[5]));
    	    	  double totalPizza = 0;
    	    	  totalPizza = (10.99 * store.countItem[5]);
    	    	  if (store.countItem[5] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalPizza *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalPizza));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalPizza));
	    	      	}
    	    	  
    	    	  store.total += totalPizza;
    	      }
    	      
    	      if(store.countItem[6] > 0) // 7 Water
    	      {
    	    	  appendInvoiceLine.append(productDetails[6].printProductIntoGUI(store.countItem[6]));
    	    	  double totalWater = 0;
    	    	  totalWater = (0.99 * store.countItem[6]);
    	    	  if (store.countItem[6] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalWater *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalWater));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalWater));
	    	      	}
    	    	  
    	    	  store.total += totalWater;
    	      }
    	      
    	      if(store.countItem[7] > 0) // 8 Potato Chip
    	      {
    	    	  appendInvoiceLine.append(productDetails[7].printProductIntoGUI(store.countItem[7]));
    	    	  double totalChip = 0;
    	    	  totalChip = (0.99 * store.countItem[7]);
    	    	  if (store.countItem[7] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalChip *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalChip));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalChip));
	    	      	}
    	    	  store.total += totalChip;
    	      }
    	      
    	      if(store.countItem[8] > 0) // 9 Raw Tuna
    	      {
    	    	  appendInvoiceLine.append(productDetails[8].printProductIntoGUI(store.countItem[8]));
    	    	  double totalTuna = 0;
    	    	  totalTuna = (21.99 * store.countItem[8]);
    	    	  if (store.countItem[8] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalTuna *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f", totalTuna));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f",totalTuna));
	    	      	}
    	    	  store.total += totalTuna;
    	      }
    	      
    	      if(store.countItem[9] > 0) // 10 Shark Fin
    	      {
    	    	  appendInvoiceLine.append(productDetails[9].printProductIntoGUI(store.countItem[9]));
    	    	  double totalFin = 0;
    	    	  totalFin = (799.99 * store.countItem[9]);
    	    	  if (store.countItem[9] >= 10) // if the said product has 10 or more
	    	      	{ // apply discount 10% discount
    	    		  totalFin *= 0.90; // take 10% out, we are left with 90% of the full price
    	    		  appendInvoiceLine.append(String.format("\nApplied 10%% discount\tTotal item price:\t$%.2f",  totalFin));
	    	      	} // %% gives the output "%" itself
	    	      else
	    	      	{
	    	    	  appendInvoiceLine.append(String.format("\n\t\t\t      Total item price: $%.2f", totalFin));
	    	      	}
    	    	  
    	    	  store.total += totalFin;
    	      }
    	      
    		
    	      appendInvoiceLine.append(String.format("\n\t\t\tSUBTOTAL: $%.2f", store.total)); // display subtotal of all items (excluding sales tax)
    	      tempSubTotal = store.total;
    	      System.out.println(tempSubTotal);
              // User is prompt to choose FUll payment or with installments
              // reorganized to fit the receipt
    	      appendInvoiceLine.append(String.format("\n\nWould you like to choose Full payment or installment plan?(1 for full payment "));
    	      appendInvoiceLine.append(String.format("\nand 0 for installment plan). Full payment offers a 5%% discount when paying full Price."));
              appendInvoiceLine.append(String.format("\nInstallment plan offers a monthly fee of 10%% of the price with sales tax, this fee will last for 1 Year.\n"));
              displayUI.setText(appendInvoiceLine.toString());
              
    	           } 
  		});
    
    // payout button
    fullPayoutButton.addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  if(choice1)
    		  {
    		  store.total *= store.fullPaymentDiscount;  
       
    		  payment.append(String.format("\n\t\t    Applied Discount Total: $%.2f", store.total));
    		  
    		  store.salesTaxes = store.salesTaxes * store.total; 
    		  tempSalesTax = store.salesTaxes;
	  		  store.total += store.salesTaxes;// adding taxes to total
	  		  tempTotal = store.total;
	  		  
    		  payment.append(String.format("\n\t\t\t    TAX: $%.2f", store.salesTaxes));
    		  payment.append(String.format("\n\t\t\t  ****TOTAL: $%.2f\n",store.total)); // tab is 8 spaces
    		  payment.append("\r\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!"); 
    		  displayUI.setText(payment.toString()); //convert to a string
    		  appendInvoiceLine.append(payment.toString()); // this will be used if the user wants to see the entire invoice in the GUI
    		  choice2 = false;
    		  }     
    		  } 
  		});
    
    installmentButton.addActionListener(new ActionListener(){ 
  	  @Override
  	   public void actionPerformed(ActionEvent e){  
  		  if(choice2)
  		  {
  		  payment.append(String.format("\nHere's your first payment for this month"));
  		  store.salesTaxes = store.salesTaxes * store.total;   
  		  tempSalesTax = store.salesTaxes;
  		  store.total += store.salesTaxes;// adding taxes to total
  		  tempTotal = store.total;
  		  
  		  payment.append(String.format("\n\t\t\t        TAX: $%-3.2f", store.salesTaxes));
  		  payment.append(String.format("\n\t\t\t    ****TOTAL: $%.2f\n",store.total*.1)); // tab is 8 spaces
  		  payment.append(String.format("\n\t         ****Installment plan in 1 year total: $%-3.2f\n",store.total*12)); // adding taxes to total
  		  payment.append("\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!"); 
  		  displayUI.setText(payment.toString());
  		  appendInvoiceLine.append(payment.toString()); // this will be used if the user wants to see the entire invoice in the GUI
  		  choice1 = false;
  		  }
  		  } 
		});

    saveInvoiceButton.addActionListener(new ActionListener(){ 
    	  @Override
    	   public void actionPerformed(ActionEvent e){  
    		  displayUI.setText("INVOICE SAVED!");
    		  try
    		  {
    		  BufferedWriter invoiceWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\savedInvoice.txt"),true));
    		  invoiceWriter.write("\r\n" + recordDate.format(now));
        	  invoiceWriter.write("\r\n\n#########################################################################");
        	  invoiceWriter.write("\r\n\tWalkart\r\n");
        	  invoiceWriter.write("Customer: " + fullName); // Stating the customer's name
        	  invoiceWriter.write("\r\nIn Cart: " + store.count); // count total items in cart
        	  invoiceWriter.write(String.format("\r\n%s\t%s\t%s\t\t%s\t\t", "Quantity", "ID", "Product name", "Price"));  // structure
              
              
              if(store.countItem[0] > 0) // 1 Apple
              { // if the amount of said product is at least above 0, print in invoice
            	  productDetails[0].writeItemFile(store.countItem[0], invoiceWriter); // this is the writing format in the file
            	  productPrice[0].invoiceDiscount(store.countItem[0], invoiceWriter); 
            	  // adding $0.99 * the amount of apples
            	  // the layout that it should display:
            	  // ID Product Name Price Description
            	  // Quantity: #
              }
              
              if(store.countItem[1] > 0) // 2 Pear
              {
            	  productDetails[1].writeItemFile(store.countItem[1], invoiceWriter);
            	  productPrice[1].invoiceDiscount(store.countItem[1], invoiceWriter); 
            	  // adding $0.50 * amount
              } 
              
              if(store.countItem[2] > 0) // 3 Banana
              {
            	  productDetails[2].writeItemFile(store.countItem[2], invoiceWriter);
            	  productPrice[2].invoiceDiscount(store.countItem[2], invoiceWriter); 
            	  // adding $0.57 * amount
              }
              
              if(store.countItem[3] > 0) // 4 Orange
              {
            	  productDetails[3].writeItemFile(store.countItem[3], invoiceWriter);
            	  productPrice[3].invoiceDiscount(store.countItem[3], invoiceWriter);
            	  // adding $1.20 * amount
              }
              
              if(store.countItem[4] > 0) // 5 Grape
              {
            	  productDetails[4].writeItemFile(store.countItem[4], invoiceWriter);
            	  productPrice[4].invoiceDiscount(store.countItem[4], invoiceWriter); 
            	  // adding $2.99
              }
              
              if(store.countItem[5] > 0) // 6 Pizza
              {
            	  productDetails[5].writeItemFile(store.countItem[5], invoiceWriter);
            	  productPrice[5].invoiceDiscount(store.countItem[5], invoiceWriter);
            	  // adding $10.99
              }
              
              if(store.countItem[6] > 0) // 7 Water
              {
            	  productDetails[6].writeItemFile(store.countItem[6], invoiceWriter);
            	  productPrice[6].invoiceDiscount(store.countItem[6], invoiceWriter);
            	  // adding $0.99
              }
              
              if(store.countItem[7] > 0) // 8 Potato Chip
              {
            	  productDetails[7].writeItemFile(store.countItem[7], invoiceWriter);
            	  productPrice[7].invoiceDiscount(store.countItem[7], invoiceWriter);
            	  // adding $0.99
              }
              
              if(store.countItem[8] > 0) // 9 Raw Tuna
              {
            	  productDetails[8].writeItemFile(store.countItem[8], invoiceWriter);
            	  productPrice[8].invoiceDiscount(store.countItem[8], invoiceWriter);
            	  // adding $21.99
              }
              
              if(store.countItem[9] > 0) // 10 Shark Fin
              {
            	  productDetails[9].writeItemFile(store.countItem[9], invoiceWriter);
            	  productPrice[9].invoiceDiscount(store.countItem[9], invoiceWriter);
            	  // adding $799.99
              }
              
              
              invoiceWriter.write(String.format("\n\t\t\t\t      SUBTOTAL: $%.2f", tempSubTotal));
              invoiceWriter.write(String.format("\n\t\t\t\t\t   TAX: $%.2f", tempSalesTax));
              invoiceWriter.write(String.format("\n\t\t\t\t     ****TOTAL: $%.2f\n",tempTotal)); // tab is 8 spaces
              invoiceWriter.write("\r\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!\r\n"); 
              invoiceWriter.close();

    	  }
    	  catch (IOException e1)
    	  {
    		  System.out.println("Error occurred");
              e1.printStackTrace();
    	  }
    		  try
    		  {
    		  appending = new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt"), true));
    		  appending.write("\r\n" + fullName + "\r\n");
    		  appending.write(String.format("$%.2f %s", store.total, recordDate.format(now))); // write on a new line	
    		  appending.close(); // close
    		  }
    		  catch (IOException e1)
        	  {
        		  System.out.println("Error occurred");
                  e1.printStackTrace();
        	  }
    	  
    	  
    	      
    		  } 
  		});
    
    showInvoiceButton.addActionListener(new ActionListener(){ 
    	  @Override
     	   public void actionPerformed(ActionEvent e){  
     		  
     		  displayUI.setText(appendInvoiceLine.toString());
     		  } 
   		});
    
    //=================== System only ================================
    
    String input; // this variable will be used for confirming whether the input is int or not
    
    // Creating a scanner object where the user can input their name, which is a string in this form
  	
  	String answer;
  	do // let's the user first input their name
    {
      System.out.println("\nPlease enter your full name:  ");
      name = myObj.nextLine();
      
	  fullName = name;
	  break;
	    	 
     
      }while(true);
      
      
      // This will run the getFirstname() method and will return with a string once the method is finished
      // name can be any symbols including numbers, because user ID exist, etc
     
      
   // ============================ FILE USERS =====================================
  	userFile = new BufferedReader(new FileReader("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt")); 
    
    userFileWrite	= new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt"),true));
      
      try
      {
	      while((line = userFile.readLine()) != null) // read throughout file
	      {

	    	  stringHolder[n] = line;
	    	  if(q == 1) //print only the transaction date of said user
	    	  {
	    		  
	    		  System.out.println(stringHolder[p+1]); 
	    		  q--;
	    	  }
	    	  if(line.equals(fullName)) // finding user
	    	  {
	    		  
	    		  p = n; 
	    		  q++;
	    		  c++;
	    	  }
	    	  n++;
	      }
	      
	      if (c == 0) // checks if it is a new user
	      {
	    	  System.out.println("Welcome NEW User");
	      }
	      else
	      {
	    	  System.out.println("Welcome returning User");
	      }
      }
      catch(Exception e)
      {
    	  System.out.println("\nFile does not exist!\n");
      }
	     
      userFile.close();
      userFileWrite.close();
      
      
      
      // ---------------- Choosing The List of Items ------------------
      
      
      while(store.id != 0) // initially set 0 to id, 0 not equal to 11 which is true and will keep running the loop until
	    { // the user types in 11;
    	  
    	printMenuDetails();
    	  
    	for (int i = 0; i < productDetails.length; i++) // runs loop 10 times
    	{
    		if (productDetails[i] == null) 
		  	{
		  		break; // will stop here if there is a null, meaning nothing in the list
		  	}
    		if(i >= 10)
    		{
    			productDetails[i].plusOneID(); // fixes printing of the id
    		}
    		
		  	productDetails[i].printList(); // prints the entire listed items for the users to read
		  	// I want my user's to see the list so that they do not need to scroll up in the output system
		  	
		  	if(i >= 10)
		  	{
		  		productDetails[i].minusOneID(); // fixes printing of the id, reverts to original
		  	}
		  	
    	}
    	System.out.print("\n0 to Pay\n"); 
    	
    	
	      input = myObj.next(); // read next String input, then the next line will convert it into an integer
			  try { // utilizing try and catch to check if the input is correct instead of using if and else
				  store.id = Integer.parseInt(input); // converts string to an int
	      }  								  		 // basically checks if the String is an Integer
			  catch (NumberFormatException ne) { // skips the exception error in the system output
	         System.out.println("Error, input is not a number, please try again!");
	         continue; // run the while loop again
	         }
			  
	        switch(store.id-1)
	        {
	        	  case -1:
		        	  System.out.println("\nLoading payout list. . .");
		          	  break;
	            case 0: // Apple $0.99
	          	  do 
	          	  { // run the code then check the conditions at the very end
	          		  System.out.println("How many Apples do you want? ");
	          		  // ask the user the amount of product that they want
	          		  	  store.number = myObj.nextInt();
		            	  store.countItem[0] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Apples"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[0] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Apples");
		                	  store.countItem[0] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[0]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break; // break to get out of the switch statements so it doesn't play the entire statements
	            case 1: // Pear $0.50
	          	  do 
	          	  {
	          		  System.out.println("How many Pears do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[1] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Pears"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[1] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Pears");
		                	  store.countItem[1] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[1]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 2: // Banana $0.57
	          	  do 
	          	  {
	          		  System.out.println("How many Bananas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[2] += store.number; // you can add or remove, just simply put a minus sign to remove
		              System.out.println("Added " + store.number + " Bananas"); 
		              // tells the user that the system added the product to their list
		                  if(store.countItem[2] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Bananas");
		                	  store.countItem[2] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[2]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 3: // Orange $1.20
	          	  do 
	          	  {
	          		  System.out.println("How many Oranges do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[3] += store.number; // you can add or remove, just simply put a minus sign to remove
		              System.out.println("Added " + store.number + " Oranges"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[3] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Oranges");
		                	  store.countItem[3] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[3]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 4: // Grape $2.99
	          	  do 
	          	  {
	          		  System.out.println("How many Grapes do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[4] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Grapes"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[4] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Grapes");
		                	  store.countItem[4] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[4]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 5: // Pizza $10.99
	          	  do 
	          	  {
	          		  System.out.println("How many Pizzas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[5] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Pizzas"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[5] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Pizzas");
		                	  store.countItem[5] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[5]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 6: // Water $0.99
	          	  do 
	          	  {
	          		  System.out.println("How many Bottle of Waters do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[6] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Bottle of Waters"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[6] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Bottle of Waters");
		                	  store.countItem[6] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[6]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 7: // Potato Chip $0.99
	          	  do 
	          	  {
	          		  System.out.println("How many Potato Chips do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[7] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Potato Chips"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[7] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Potato Chips");
		                	  store.countItem[7] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[7]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 8: // Tuna $21.99
	          	  do 
	          	  {
	          		  System.out.println("How many Raw Tunas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[8] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Raw Tunas"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[8] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Raw Japanaese Tunas");
		                	  store.countItem[8] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[8]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 9: // A pair of Shark fins $799.99
	          	  do 
	          	  {
	          		  
	          		  System.out.println("How many Pairs of Shark Fins do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
		            	  
	          		  store.countItem[9] += store.number; // you can add or remove, just simply put a minus sign to remove
		            	  
		                  System.out.println("Added " + store.number + " Pairs of Shark Fins"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[9] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Raw Japanaese Tunas");
		                	  store.countItem[9] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[9]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	        }
	    }
	
      
      for (int i = 0; i < store.countItem.length; i++) // length should be 10
      {
    	  // Remember, countItem is an int array, so most empty positions carries a value of 0
    	  store.count += store.countItem[i]; // adds all count 
      }
      // Calculating the total amount of items (how many items there are)
      
      //------------------ PRINTING INVOICE -----------------
      System.out.println("\nPrinting your receipt . . . . . .");
      
      System.out.println("\n\n#########################################################################");
      System.out.println("\tWalkart");
      System.out.println("Customer: " + fullName); // Stating the customer's name
      System.out.println("\nIn Cart: " + store.count); // count total items in cart
      System.out.printf("\n%s\t%s\t%s\t\t%s\t\t", "Quantity", "ID", "Product name", "Price");  // structure
      
      
      if(store.countItem[0] > 0) // 1 Apple
      { // if the amount of said product is at least above 0, print in invoice
    	  productDetails[0].printItem(store.countItem[0]);
    	  store.total += productPrice[0].getDiscount(store.countItem[0]); // adding $0.99 * the amount of apples
    	  // the layout that it should display:
    	  // ID Product Name Price Description
    	  // Quantity: #
      }
      
      if(store.countItem[1] > 0) // 2 Pear
      {
    	  productDetails[1].printItem(store.countItem[1]);
    	  store.total += productPrice[1].getDiscount(store.countItem[1]); // adding $0.50 * amount
      } 
      
      if(store.countItem[2] > 0) // 3 Banana
      {
    	  productDetails[2].printItem(store.countItem[2]);
    	  store.total += productPrice[2].getDiscount(store.countItem[2]); // adding $0.57 * amount
      }
      
      if(store.countItem[3] > 0) // 4 Orange
      {
    	  productDetails[3].printItem(store.countItem[3]);
    	  store.total += productPrice[3].getDiscount(store.countItem[3]); // adding $1.20 * amount
      }
      
      if(store.countItem[4] > 0) // 5 Grape
      {
    	  productDetails[4].printItem(store.countItem[4]);
    	  store.total += productPrice[4].getDiscount(store.countItem[4]); // adding $2.99
      }
      
      if(store.countItem[5] > 0) // 6 Pizza
      {
    	  productDetails[5].printItem(store.countItem[5]);
    	  store.total += productPrice[5].getDiscount(store.countItem[5]); // adding $10.99
      }
      
      if(store.countItem[6] > 0) // 7 Water
      {
    	  productDetails[6].printItem(store.countItem[6]);
    	  store.total += productPrice[6].getDiscount(store.countItem[6]); // adding $0.99
      }
      
      if(store.countItem[7] > 0) // 8 Potato Chip
      {
    	  productDetails[7].printItem(store.countItem[7]);
    	  store.total += productPrice[7].getDiscount(store.countItem[7]); // adding $0.99
      }
      
      if(store.countItem[8] > 0) // 9 Raw Tuna
      {
    	  productDetails[8].printItem(store.countItem[8]);
    	  store.total += productPrice[8].getDiscount(store.countItem[8]); // adding $21.99
      }
      
      if(store.countItem[9] > 0) // 10 Shark Fin
      {
    	  productDetails[9].printItem(store.countItem[9]);
    	  store.total += productPrice[9].getDiscount(store.countItem[9]); // adding $799.99
      }
      


      // End of last item, because this is the set limit for the store
      
      /* NOTE, how discount and sales tax work is that, discount is applied and then sales tax is applied after
      Example: Total price: $10.00
      Total price after 10% discount: $9.00
      Applied tax on total price: $9.54 
      */
      

      if (store.total != 0.0) // will not display the price IF the customer did not buy anything
      {
    	//Showing subtotal before applying sales tax
          System.out.printf("\n\t\t\t\t      SUBTOTAL: $%.2f", store.total); // display subtotal of all items (excluding sales tax)
          
          // User is prompt to choose FUll payment or with installments
          // reorganized to fit the receipt
          System.out.println(""
          		+ "\n\nWould you like to choose Full payment or installment plan? "
          		+ "\n(1 for full payment and 0 for installment plan)"
          		+ "\nFull payment offers a 5%% discount when paying full Price."
          		+ "\nInstallment plan offers a monthly fee of 10%% of the price with"
          		+ "\nsales tax, this fee will last for 1 Year.\n");
         
    	  while(true)
          {
    		  input = myObj.next();
    		  try { // utilizing try and catch to check if the input is correct instead of using if and else
    			  store.plans = Integer.parseInt(input); // converts string to an int
              }  								  		 // basically checks if the String is an Integer
    		  catch (NumberFormatException ne) { // skips the exception error in the system output
                 System.out.println("Error, input is not a number, please try again!");
                 continue; // run the while loop again
                 }
    		  
        	  if(store.plans == 1)// Fullpayment plan
        	  {                                        
        		  store.total *= store.fullPaymentDiscount;         
        		  System.out.printf("\n\t\t\tApplied Discount Total: $%.2f", store.total);
        		  break;                                                      
        	  }
        	  else if(store.plans == 0) // Installment plan, 10% of the price
        	  {						
        		  store.total *= 0.1;
        		  break;
        	  }
        	  else
        	  {
        	  System.out.println("Error... Please enter 1 or 0\n"); // Error message
        	  }
          }
    	  
    	  store.salesTaxes = store.salesTaxes * store.total; // calculating salesTaxes = 0.06 x total
          System.out.printf("\n\t\t\t\t\t   TAX: $%.2f", store.salesTaxes); //display taxes now
          // %.2f, similar in C++, it will print in that location base on the given parameter value
          store.total += store.salesTaxes;// adding taxes to total
          
          if(store.plans == 0)
          {
        	  System.out.println("\nHere's your first payment for this month");
          }
          
          System.out.printf("\n\t\t\t\t     ****TOTAL: $%.2f\n",store.total); // tab is 8 spaces
          
          if (store.plans == 0) // show installment plan full price at the end
          {
        	  System.out.printf("\n\t  ****Installment plan in 1 year total: $%.2f\n",store.total*12); // adding taxes to total
          }
          
          BufferedWriter invoiceWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\savedInvoice.txt"),true));
          
          System.out.println("Would you like to save your invoice (1 for yes or 0 for no?");
          while(true)
          {
    		  input = myObj.next();
    		  try { // utilizing try and catch to check if the input is correct instead of using if and else
    			  store.plans = Integer.parseInt(input); // converts string to an int
              }  								  		 // basically checks if the String is an Integer
    		  catch (NumberFormatException ne) 
    		  { // skips the exception error in the system output
                 System.out.println("Error, input is not a number, please try again!");
                 continue; // run the while loop again
                 }
    		  switch(store.plans)
    		  {
	          case 1:
		          {
		        	  invoiceWriter.write("\r\n\n" + recordDate.format(now));
		              
		        	  invoiceWriter.write("\r\n\n#########################################################################");
		        	  invoiceWriter.write("\r\n\tWalkart\r\n");
		        	  invoiceWriter.write("Customer: " + fullName); // Stating the customer's name
		        	  invoiceWriter.write("\r\nIn Cart: " + store.count); // count total items in cart
		        	  invoiceWriter.write(String.format("\r\n%s\t%s\t%s\t\t%s\t\t", "Quantity", "ID", "Product name", "Price"));  // structure
		              
		              
		              if(store.countItem[0] > 0) // 1 Apple
		              { // if the amount of said product is at least above 0, print in invoice
		            	  productDetails[0].writeItemFile(store.countItem[0], invoiceWriter); // this is the writing format in the file
		            	  productPrice[0].invoiceDiscount(store.countItem[0], invoiceWriter); 
		            	  // adding $0.99 * the amount of apples
		            	  // the layout that it should display:
		            	  // ID Product Name Price Description
		            	  // Quantity: #
		              }
		              
		              if(store.countItem[1] > 0) // 2 Pear
		              {
		            	  productDetails[1].writeItemFile(store.countItem[1], invoiceWriter);
		            	  productPrice[1].invoiceDiscount(store.countItem[1], invoiceWriter); 
		            	  // adding $0.50 * amount
		              } 
		              
		              if(store.countItem[2] > 0) // 3 Banana
		              {
		            	  productDetails[2].writeItemFile(store.countItem[2], invoiceWriter);
		            	  productPrice[2].invoiceDiscount(store.countItem[2], invoiceWriter); 
		            	  // adding $0.57 * amount
		              }
		              
		              if(store.countItem[3] > 0) // 4 Orange
		              {
		            	  productDetails[3].writeItemFile(store.countItem[3], invoiceWriter);
		            	  productPrice[3].invoiceDiscount(store.countItem[3], invoiceWriter);
		            	  // adding $1.20 * amount
		              }
		              
		              if(store.countItem[4] > 0) // 5 Grape
		              {
		            	  productDetails[4].writeItemFile(store.countItem[4], invoiceWriter);
		            	  productPrice[4].invoiceDiscount(store.countItem[4], invoiceWriter); 
		            	  // adding $2.99
		              }
		              
		              if(store.countItem[5] > 0) // 6 Pizza
		              {
		            	  productDetails[5].writeItemFile(store.countItem[5], invoiceWriter);
		            	  productPrice[5].invoiceDiscount(store.countItem[5], invoiceWriter);
		            	  // adding $10.99
		              }
		              
		              if(store.countItem[6] > 0) // 7 Water
		              {
		            	  productDetails[6].writeItemFile(store.countItem[6], invoiceWriter);
		            	  productPrice[6].invoiceDiscount(store.countItem[6], invoiceWriter);
		            	  // adding $0.99
		              }
		              
		              if(store.countItem[7] > 0) // 8 Potato Chip
		              {
		            	  productDetails[7].writeItemFile(store.countItem[7], invoiceWriter);
		            	  productPrice[7].invoiceDiscount(store.countItem[7], invoiceWriter);
		            	  // adding $0.99
		              }
		              
		              if(store.countItem[8] > 0) // 9 Raw Tuna
		              {
		            	  productDetails[8].writeItemFile(store.countItem[8], invoiceWriter);
		            	  productPrice[8].invoiceDiscount(store.countItem[8], invoiceWriter);
		            	  // adding $21.99
		              }
		              
		              if(store.countItem[9] > 0) // 10 Shark Fin
		              {
		            	  productDetails[9].writeItemFile(store.countItem[9], invoiceWriter);
		            	  productPrice[9].invoiceDiscount(store.countItem[9], invoiceWriter);
		            	  // adding $799.99
		              }
		              invoiceWriter.write(String.format("\n\t\t\t\t      SUBTOTAL: $%.2f", store.total));
		              invoiceWriter.write(String.format("\n\t\t\t\t\t   TAX: $%.2f", store.salesTaxes));
		              invoiceWriter.write(String.format("\n\t\t\t\t     ****TOTAL: $%.2f\n",store.total)); // tab is 8 spaces
		              invoiceWriter.write("\r\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!"); 
		              break;
		          }
	          case 0:
		          {
		        	  break;
		          }
        	  default:
	        	  {
	        		  System.out.println("ERROR, please try again");
	        		  continue;
	        	  }
	          }
    		  break;
          }
          invoiceWriter.close();

      System.out.println("\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!"); 
      // the end of the program and bids farewell to the users
     
	  appending = new BufferedWriter(new FileWriter(new File("C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\user.txt"), true));
	  appending.write("\r\n" + fullName + "\r\n");
	  appending.write(String.format("$%.2f %s\r\n\n", store.total, recordDate.format(now))); // write on a new line	
	      
  
      appending.close(); // close
      }
      
      
    }
     
    GroceryStore(){
        frame = new JFrame("Walkart 4.0");
    }
    
    public void startFrame(){
    	// this is where the app gets the images
        String iconButtons[] = {
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\apple.png", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\pear.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\banana.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\orange.jpg",
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\grape.png", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\pizza.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\water.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\chip.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\tuna.jpg", 
              "C:\\Users\\npham\\eclipse-workspace\\hello world\\src\\sharkfin.png"
           };  
        
        // for details in the GUI
        
        String productTextDetails[] = {
                "1 Apple $0.99 A red pomaceous fruit, fully ripen fruit that weighs around 0.5lbs", 
                "2 Pear $0.50 A green fruit that is sweeter than an apple, very ripe and weighs 0.4lbs", 
                "3 Banana $0.57 A bundle of 7, a long yellow fruit, weighing 1.6lbs", 
                "4 Orange $1.20 An orange fruit that is rich with vitamin C, weighing 0.6lbs",
                "5 Grape $2.99 A bag of purple grapes, it's sweet and fresh, weighing 2lbs", 
                "6 Pizza $10.99 A large cheese pizza, no additional toppings included, weighs around 3lbs", 
                "7 Bottle Water $0.99 A bottle of water, comes from the very top of Mt. Fuji, weighs 1lb", 
                "8 Potato Chip $0.99 A bag of yellow cripsy chips, a little salty but tasty, weighs 0.5lbs", 
                "9 Raw Tuna $21.99 A large raw fish that is kept nicely fresh from Japan and weighs 4lbs", 
                "10 Shark Fin $799.99 A pair of fresh shark fins that was shipped from the Pacifics, weighs 3lb"
             };  
        
        Dimension maxSize = new Dimension(20, 20);
        
        // simple String variables for a few buttons
        String confirmNameText = "Confirm Name";
        String payText = "Pay";
        String fullPayText = "Full Payment";
        String installmentText = "Installment Pay";
        String saveInvoiceText = "Save Invoice";
        String showInvoiceText = "Show Invoice";
        
        Container contentPane = frame.getContentPane(); 
        contentPane.setLayout(new FlowLayout());
        
        // Font layout fir the title
        Font font = new Font("Times New Roman", Font.PLAIN + Font.BOLD, 22);
        textFullname = new JTextField("", 20);
        textAmount = new JTextField("", 20);
        
        title = new JLabel("Welcome to Walkart Version 4.0"); // title of the java application
        
        title.setFont(font);
        contentPane.add(title);

        
        // Panels 
        JPanel productPanel = new JPanel();
        JPanel displayTextPanel = new JPanel();
        JPanel insertAmountPanel = new JPanel();
        JPanel checkOutPanel = new JPanel();
        JPanel namePanel = new JPanel();
        
        insertFullName = new JLabel ("Customer Name: ");
        insertAmount = new JLabel ("Enter Amount (negative numbers to remove) and then click the icon button, when finished: click done ");
        namePanel.add(insertFullName);
        namePanel.add(textFullname);


        insertAmountPanel.setLayout(new GridLayout(1,1, 0, 0)); // format order,  row x colums
        productPanel.setLayout(new GridLayout(10,2, 0, 0)); 
        checkOutPanel.setLayout(new GridLayout(3,2, 0, 0)); 
        displayTextPanel.setLayout(new GridLayout(1,1));
        
        
        
        nameButton = new JButton(confirmNameText);
        namePanel.add(nameButton);
        
        for(int i = 0; i < 10; i++){ // loop 10 times and give each button a certain product
           Icon productIcon = new ImageIcon(iconButtons[i]);
           Image image = ((ImageIcon) productIcon).getImage();
           Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // resizing images because the given images are huge
           productIcon = new ImageIcon(newimg);
           
           Button[i] = new JButton(productIcon); // give the button the image
           Button[i].setPreferredSize(new Dimension(20, 20)); //resizing button
          
           productPanel.add(Button[i]);
           
           productLabel[i] = new JLabel(productTextDetails[i]); // gives each button a label to the right (depending on the grid layout)
           productPanel.add(productLabel[i]);
        }

        insertAmountPanel.add(insertAmount);
        insertAmountPanel.add(textAmount);
        displayUI = new JTextArea("", 25, 80);
        displayTextPanel.add(displayUI);
        
        // Add a scroll panel to the textArea which is displayTextPanel (Invoice, printing, etc)
        JScrollPane scrollPane = new JScrollPane(displayUI);  // only works for display UI  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // verticle scroll bar
        displayTextPanel.add(scrollPane);
        
        checkOutButton = new JButton(payText);
        checkOutPanel.add(checkOutButton);
        
        fullPayoutButton = new JButton(fullPayText);
        checkOutPanel.add(fullPayoutButton);
        
        installmentButton = new JButton(installmentText);
        checkOutPanel.add(installmentButton);
        
        saveInvoiceButton = new JButton(saveInvoiceText);
        checkOutPanel.add(saveInvoiceButton);
        
        showInvoiceButton = new JButton(showInvoiceText);
        checkOutPanel.add(showInvoiceButton);
        
        // Setting each button to a color
        Button[0].setBackground(Color.RED);
        Button[1].setBackground(Color.GREEN);
        Button[2].setBackground(Color.YELLOW);
        Button[3].setBackground(Color.ORANGE);
        Button[4].setBackground(Color.MAGENTA);
        Button[5].setBackground(Color.ORANGE);
        Button[6].setBackground(Color.CYAN);
        Button[7].setBackground(Color.YELLOW);
        Button[8].setBackground(Color.PINK);
        Button[9].setBackground(Color.BLUE);

        // setting up the GUI frame
        frame.pack();
        frame.setSize(2000, 2000); // full screen, reads pixel by pixel as size. Note: I use a small computer
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
        frame.add(namePanel);
        frame.add(productPanel);  
        frame.add(insertAmountPanel);
        frame.add(displayTextPanel); 
        frame.add(checkOutPanel);
        
    }
}



class itemList {
	// these are attributes
	public String id;
	public String itemName;
	public String itemPrice;
	public String details;
	public int ids = 1;
	public double price;
	
	
	// itemList class constructor
	itemList(String id, String itemName, String itemPrice, String details)
	{   // eliminates confusion between items
		// because these will be assign into an array that contains the item
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.details = details;
	}
	
	//Overloading, but this takes in only double
	itemList(double price) 
	{
		this.price = price;
	}
	
	public void plusOneID() // fixes id
	{
		int temp; // temporary holds value
		temp = Integer.parseInt(id); // converts string to integer
		temp += 1;
		id = Integer.toString(temp); // converts integer to string
	}
	
	public void minusOneID() // fixes id
	{ // similar format as plusOnID but does subtraction
		int temp; 
		temp = Integer.parseInt(id);
		temp -= 1;
		id = Integer.toString(temp);
	}
	
	public void printList() // prints out the the list of items
    {						// this will base on what array/item
        // Product ID, Product Name, Product Price, Product Description
        System.out.printf("\n%-8s%-13s%16s\t\t%s", id, itemName, itemPrice, details);
        // remember... row is the first array and column is the second
        // printList() is finished here
    }
	
	public void printQuestionAmount() // method prints name of item base on the saved attribute of productDetails[i]
	{
		System.out.printf("\n` %s do you want? ", itemName);
		  // ask the user the amount of product that they want
	}
	
	public void printAmountAdded(int amount) // prints amount been added base on item
	{
		System.out.printf("\nAdded " + amount + " %s", itemName); 
	}
	
	public void printIncorrectAmount() // prints a statement that the user cannot go under 0 amount of items
	{
		System.out.printf("\n\nYou cannot have a negative amount of %s", itemName);
	}
	
	public void printItem(int count)
	{ // this method will print the item's descritption
		System.out.printf("\n%-8d%10s%18s\t\t%s", count, id, itemName, itemPrice);
	}
	
	public String printProductIntoGUI(int count)
	{ // this method will print the item's descritption
		String appending = String.format("\n%-8d%10s%18s%50s", count, id, itemName, itemPrice);
		return appending;
	}
	
	
	public void writeItemFile(int count, BufferedWriter invoiceWriter) throws IOException
	{ // this method will print the item's descritption
		invoiceWriter.write(String.format("\r\n%-8d%10s%18s\t\t%s", count, id, itemName, itemPrice));
	}
	
	public double getDiscount(int count)
    {
    	if (count >= 10) // if the said product has 10 or more
    	{ // apply discount 10% discount
    		price = (price * count) * 0.90; // take 10% out, we are left with 90% of the full price
    		System.out.printf("\nApplied 10%% discount\tTotal item price:\t$%.2f\n", price);
    	} // %% gives the output "%" itself
    	else
    	{
    		System.out.printf("\n\t\t\t      Total item price: $%.2f\n",price);
    	}
    	
    	// display below the item the number of items and total price of said item
    	// example: 5 Apples, Quantity: 5	Price: $4.95
    	return price; // return double value back
    }
	public void invoiceDiscount(int count, BufferedWriter invoiceWriter) throws IOException
    {
    	if (count >= 10) // if the said product has 10 or more
    	{ 
    		invoiceWriter.write(String.format("\r\nApplied 10%% discount\tTotal item price:\t$%.2f\r\n", price));
    	} // %% gives the output "%" itself
    	else
    	{
    		invoiceWriter.write(String.format("\r\n\t\t\t      Total item price: $%.2f\r\n",price));
    	}

    }
}

