/*Radhika Banerjea
 * September 28, 2021
 * Using this code the user can choose to:
 * a. Give a message to be encoded and the key to be used
	b. Give a message to be decoded and the key to be used
	c. Give an encoded message. The output will be the 26 possible decoded messages
	d. Give an encoded message, the output will be the best guessed decoded message
	e. Exit the program
	and the program will do the option that the user selected.
 * 
 */

import java.io.*;

//importing exception packages to use for throwing exceptions.
import exceptionbasics.EmptyStringException;
import exceptionbasics.IntegerOutOfRangeException;
public class ExceptionsAssignment {
	
	 //array that will hold all the different decoded options
	static String [] options = new String[26];
	//variable used for the key
	static int key = 0;
	//used for the while loops in try catch statements
	static boolean c = true;
	//used to hold the char Array versions of the user's input
	static char[]sentence;
	
	public static void main(String[] args ) throws IOException{
		//declaring buffered reader variable
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
		
		//variable used for the user's input.
		String input = " ";
		
		
		while(!input.equals("e")) {
			//getting menu item user wants, setting boolean to true
			System.out.println("Type the letter of the option that you would like. " + "\n");menu();
			input = reader.readLine();
			c = true;
			
			switch (input) {
				
				case "a","A":
					//asks for the key and ensures the input is within the acceptable range.
					System.out.println("What is the key?");					
					while (c) {
						try 
						{ 
							key = Integer.parseInt(reader.readLine());
							if (key>25||key<0) {
								//throws exception if it is out of the range
								throw new IntegerOutOfRangeException();
							}
							c = false;
						}  
						catch (NumberFormatException e)  
						{ 
							System.out.println("not a valid integer"); 
						} 
						catch (IntegerOutOfRangeException e)  
						{ 
							System.out.println("Please enter an integer between 0 and 25."); 
						} 
						
					}
					System.out.println("Tell me what you want me to encode.");
					
					//sets boolean c to true again and checks for if the reader's input of the sentence to encode is not empty.
					c = true;
					while (c) {
						try 
						{ 
							input = reader.readLine();
							if (input.equals("")) {
								//throws exception if it is out of the range

								throw new EmptyStringException();
							}
							c = false;
						}  
						catch (EmptyStringException e)  
						{ 
							System.out.println("Your input must contain something. Try again."); 
						} 
						
					}
					//converts input to char array, goes to encode method for the correct encoded version.
					sentence = input.toCharArray();
					System.out.println("The encripted form is: "); encode(sentence,key);
			
				
				break;	
				
				case "b","B":
					//asks for the key and ensures the input is within the acceptable range.
					System.out.println("What is the key?");
					while (c) {
						try 
						{ 
							key = Integer.parseInt(reader.readLine());
							if (key>25||key<0) {	
								//throws exception if it is out of the range

								throw new IntegerOutOfRangeException();
							}
							c = false;
						}  
						catch (NumberFormatException e)  
						{ 
							System.out.println("not a valid integer"); 
						} 
						catch (IntegerOutOfRangeException e)  
						{ 
							System.out.println("Please enter an integer between 0 and 25."); 
						} 
						
					}
					System.out.println("Tell me what you want me to decode.");
					//sets boolean c to true again and checks for if the reader's input of the sentence to decode is not empty.
					c = true;
					while (c) {
						try 
						{ 
							input = reader.readLine();
							if (input.equals("")) {
								throw new EmptyStringException();
							}
							c = false;
						}  
						catch (EmptyStringException e)  
						{ 
							System.out.println("Your input must contain something. Try again."); 
						} 
						
					}					
					//converts input to char array, goes to decode method for the correct decoded version.
					sentence = input.toCharArray();
					System.out.println("The encripted form is: " + decode(sentence,key)); 

				break;	
	
				case "c","C":
					System.out.println("Tell me what you want me to decode.");
				//sets boolean c to true again and checks for if the reader's input of the sentence to decode is not empty.	
				c = true;
					while (c) {
						try 
						{ 
							input = reader.readLine();
							if (input.equals("")) {
								throw new EmptyStringException();
							}
							c = false;
						}  
						catch (EmptyStringException e)  
						{ 
							System.out.println("Your input must contain something. Try again."); 
						} 
						
					}
					//converts input to char array, goes to breakCode method for all of the encription possibilities.
					sentence = input.toCharArray();
					System.out.println("All the encription possibilities are: " ); 
					options = breakCode(sentence);
					//prints the returned array from breakCode.
					for (int i = 0; i<options.length; i++) {
						System.out.println(options[i]);
					}
				
				break;
				
				case "d","D":
					System.out.println("Tell me what you want me to decode.");
				//sets boolean c to true again and checks for if the reader's input of the sentence to decode is not empty.	
				c = true;
					while (c) {
						try 
						{ 
							input = reader.readLine();
							if (input.equals("")) {
								throw new EmptyStringException();
							}
							c = false;
						}  
						catch (EmptyStringException e)  
						{ 
							System.out.println("Your input must contain something. Try again."); 
						} 
						
					}
					//goes to attempt method for the hopefully correct encoded version.
					System.out.println("The presumed encripted form is: "); attempt(input);
					
				break;	

				case "e","E":
					//exits the program if it is e
					System.out.println("bye");
				
			 	break;
			 	
			 	default:
			 		//checks for non menu item inputted.
			 		System.out.println("Please enter a valid option from the menu. Try again.");
			 	break;

			}	
		}
	}
	
	//this method prints out the menu options.
	public static void menu() {
		System.out.println("a. Give a message to be encoded and the key to be used\r\n"
				+ "b. Give a message to be decoded and the key to be used\r\n"
				+ "c. Give an encoded message. The output will be the 26 possible decoded messages\r\n"
				+ "d. Give an encoded message, the output will be the best guessed decoded message\r\n"
				+ "e. Exit the program");
	}
	
	/*This method takes the sentence char Array made from the users input and goes through the ASCII codes for all the
	 * characters that are the alphabet uppercase and lower case. Then the key is added to those ASCII code numbers
	 * to shift the characters for the Caesar cypher. If the character goes past z when the key is added, then 26 is 
	 * subtracted from that number to loop it back to the beginning of the alphabet.
	 * The encripted version is then printed.
	 * 
	 */
	public static void encode(char [] sentence, int key) {
		for (int i=0;i<sentence.length;i++) {
			if (sentence[i]<=90 &&sentence[i]>=65) {
				sentence [i] = (char)(sentence [i] + key);
				if (sentence [i]>90) {
					sentence [i] = (char)(sentence [i] - 26);
				}
			}
			else if (sentence[i]<=122 &&sentence[i]>=97) {
				sentence [i] = (char)(sentence [i] + key);
				if (sentence [i]>122) {
					sentence [i] = (char)(sentence [i] - 26);
				}
			}
			System.out.print(sentence [i]);
		}
		System.out.println();
	}
	
	/*This method takes the sentence char Array made from the users input and goes through the ASCII codes for all the
	 * characters that are the alphabet uppercase and lower case. Then the key is subtracted to those ASCII code numbers
	 * to shift the characters for the Caesar cypher. If the character goes past z when the key is added, then 26 is 
	 * added to that number to loop it back to the end of the alphabet.
	 * The decoded version is then returned as a string so that it can be used in the next method.
	 * 
	 */
	public static String decode(char [] sentence, int key) {
		//char [] sent = new char[sentence.length];
		for (int i=0;i<sentence.length;i++) {
			if (sentence[i]<=90 &&sentence[i]>=65) {
				sentence [i] = (char)(sentence [i] - key);
				if (sentence [i]<65) {
				sentence [i] = (char)(sentence [i] + 26);
				}
			}
			else if (sentence[i]<=122 &&sentence[i]>=97) {
				sentence [i] = (char)(sentence [i] - key);
				if (sentence [i]<97) {
				sentence [i] = (char)(sentence [i] + 26);
				}
			}
			
		}
		//string that combines the values of the char array sentence into one string.
		String line = new String(sentence);
		return line;
		
	}
	/*This method takes the sentence char Array made from the users input. It runs 26 times. on the time that is zero,
	 * it just takes the value of i in the for loop for the key. But from 1-25, it just takes one as the key
	 * because sentence keeps replacing itself in the decode method.
	 * It saves all of the different ways that
	 * the sentence could be decoded and saves it in the array options. It then returns options.
	 */
	public static String[] breakCode(char [] sentence) {
		for (int i = 0;i<26;i++) {
			switch (i) {
				case 0:
					options[i] = decode(sentence,i);
				break;
				
				default:
					options [i] = decode(sentence,1);
			}
		}
		
		return options;
	}
	
	/*Method tries to decode the sentence the user inputs and tries to choose the best decoded message from the returned
	 * array in the breakCode method.
	 */
	public static boolean attempt(String input) {
		//converting input to char array and finding all the possible decoded options.
		sentence = input.toCharArray();  
		options = breakCode(sentence);
	
		//boolean used to exit the method when needed
		boolean a = true;
		//for loop that cycles from 0 to 26 so that it cycles through all the encription options
		for(int i = 0;i<26;i++) {
			//splits the options into individual words so that can be analyzed
			String[] tokens = options[i].split(" ");			
			//for loop cycles through the length of the tokens array 
			for (int j = 0;j<tokens.length;j++) {
				//checks for some of the most common english words being present in one of the options, then printing that out
				//It exclues 'a' so if a single letter word begins a sentence it won't automatically choose the option with A in the beginning
				if (tokens[j].equalsIgnoreCase("and")||tokens[j].equalsIgnoreCase("the")||tokens[j].equalsIgnoreCase("of")||tokens[j].equalsIgnoreCase("it")||tokens[j].equalsIgnoreCase("that")||tokens[j].equalsIgnoreCase("you")||tokens[j].equalsIgnoreCase("to")||tokens[j].equalsIgnoreCase("in")) {
					System.out.println(options[i]);
					return a;
				
				}
				//if there aren'ts any common english words then it checks whether fifty-eight percent of the letters are consonants
				//because that is the minimum ratio of consonants to vowels in a sentence usually.
				else if (i==25 && j==(tokens.length-1)) {
					for (int h = 0;h<26;h++) {
						char [] broken = options[h].toCharArray();
						//variables that keep track of the number of consonants and vowels.
						double v = 0;
						double c = 0;
		
						for (int r = 0; r<broken.length; r++) {
							switch(broken[r]) {
							//adds to v if its a vowel, adds to c if its a consonant.	
							case 'A','a','E','e','I','i','O','o','U','u':
									v++;
								break;
								case 'B','b','C','c','D','d','F','f','G','g','H','h','J','j','K','k','L','l','M','m','N','n','P','p','Q','q','R','r','S','s','T','t','V','v','W','w','X','x','Y','y','Z','z':
									c++;

								break;
							}
						}
						//does the math for if the ratio is 0.58, and prints out the option that is greater than 0.58.
						double x = c/(v+c);
						if (x>=0.58) {
							System.out.println(options[h]);
							return a;
						}
					}
					
				}
			}
		}
	
		return a;
	}
	
		
	}
