package com.flipkart.client;
import java.util.*;  

public class Launcher {


    public static void main(String[] args) {
    	System.out.println("Welcome to Course Registration System! choose the Option given below!");
    	System.out.println("1. Login");
    	System.out.println("2. Registration of the Student");
    	System.out.println("3. Update Password");
    	System.out.println("4. Exit");
    	System.out.println("Enter your choice: ");
    	Scanner sc= new Scanner(System.in);
    	int choice= sc.nextInt(); 
    	int iterator;
    	do { 
    		iterator = choice;
	    	switch(choice)
	    	{
	    		case 1:
	    			System.out.println("Enter your Username: ");
		    		String name= sc.next();
		    		System.out.println("Enter your Password: ");
		    		String passwd = sc.next();
		    		System.out.println("Enter your Role(1 for student, 2 for Admin and 3 for Professor): ");
		    		int role = sc.nextInt();
		    		//System.out.println("\nLogged in Succesfully!!");
		    		if(role==1)
		    		{
		    			System.out.println("Welcome to Student Menu: ");
		    			StudentMenu stuobj = new StudentMenu();
		    			break;
		    		}
		    		else if(role==2)
		    		{
		    			System.out.println("Welcome to Admin Menu: ");
		    			AdminMenu stuobj = new AdminMenu();
		    			break;
		    		}
		    		else if(role==3)
		    		{
		    			System.out.println("Welcome to Professor Menu: ");
		    			ProfessorMenu stuobj = new ProfessorMenu();
		    			break;
		    		}
		    		else
		    		{
		    			System.out.println("Invalid Input");
		    			System.exit(0);
		    		}
		    		//break;
		    		
	    		case 2:
	    			System.out.println("Enter details for registration: ");
	    			break;
	    			
	    		case 3:
	    			System.out.println("Enter details to update password: ");
	    			break;
	    			
	    		case 4:
	    			System.exit(0);
	    			break;
	    			
	    		default:
	    			System.out.println("Invlaid Input!! Enter Again: ");
	    			choice= sc.nextInt();
	    	}
    	}while(!((iterator>=1) && (iterator<=4)));
    System.out.println("Registration Completed!!");
    }

//    public void createMainMenu() {
//
//    }
//
//    public void loginUser() {
//
//    }
//
//    public void registerStudent() {
//
//    }
//
//    public void updatePassword() {
//
//    }

}
