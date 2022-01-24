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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptionbasics.EmptyStringException;
import exceptionbasics.IntegerOutOfRangeException;

public class Exceptions {
	
	static String [] options = new String[26];
	static int key = 0;
	static boolean c = true;
	static char[]sentence;
	
	public static void main(String[] args ) throws IOException{
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
		
		String input = " ";
		
		
		while(!input.equals("e")) {
			System.out.println("Type the letter of the option that you would like. " + "\n");menu();
			
			input = reader.readLine();
			
			c = true;
			
			switch (input) {
				
				case "a","A":
					System.out.println("What is the key?");					
					while (c) {
						try 
						{ 
							key = Integer.parseInt(reader.readLine());
							if (key>25||key<0) {
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
					sentence = input.toCharArray();
					System.out.println("The encripted form is: "); encode(sentence,key);
			
				
				break;	
				
				case "b","B":
					System.out.println("What is the key?");
					while (c) {
						try 
						{ 
							key = Integer.parseInt(reader.readLine());
							if (key>25||key<0) {
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
					sentence = input.toCharArray();
					System.out.println("The encripted form is: " + decode(sentence,key)); 

				break;	
	
				case "c","C":
					System.out.println("Tell me what you want me to decode.");
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
					sentence = input.toCharArray();
					System.out.println("All the encription possibilities are: " ); 
					options = breakCode(sentence);
					for (int i = 0; i<options.length; i++) {
						System.out.println(options[i]);
					}
				
				break;
				
				case "d","D":
					System.out.println("Tell me what you want me to decode.");
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
					System.out.println("The presumed encripted form is: "); attempt(input);
					
				break;	

				case "e","E":
					System.out.println("bye");
				
			 	break;
			 	
			 	default:
			 		System.out.println("Please enter a valid option from the menu. Try again.");
			 	break;

			}	
		}
	}
	
	
	public static void menu() {
		System.out.println("a. Give a message to be encoded and the key to be used\r\n"
				+ "b. Give a message to be decoded and the key to be used\r\n"
				+ "c. Give an encoded message. The output will be the 26 possible decoded messages\r\n"
				+ "d. Give an encoded message, the output will be the best guessed decoded message\r\n"
				+ "e. Exit the program");
	}
	
	
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
	
	
	public static String decode(char [] sentence, int key) {
		char [] sent = new char[sentence.length];
		for (int i=0;i<sentence.length;i++) {
			if (sentence[i]<=90 &&sentence[i]>=65) {
				sent [i] = (char)(sentence [i] - key);
				if (sent [i]<65) {
				sent [i] = (char)(sent [i] + 26);
				}
			}
			else if (sentence[i]<=122 &&sentence[i]>=97) {
				sent [i] = (char)(sentence [i] - key);
				if (sent [i]<97) {
				sent [i] = (char)(sent [i] + 26);
				}
			}
			
		}
		String line = new String(sent);
		return line;
		
	}
	
	public static String[] breakCode(char [] sentence) {
		for (int i = 0;i<26;i++) {
			options[i] = decode(sentence,i);
		}
		
		return options;
	}
	
	public static boolean attempt(String input) {
		sentence = input.toCharArray();  
		options = breakCode(sentence);
		boolean a = true;
		for(int i = 0;i<26;i++) {
			String[] tokens = options[i].split(" ");	
			System.out.println(options[i]);
			for (int j = 0;j<tokens.length;j++) {
				if (tokens[j].equalsIgnoreCase("and")||tokens[j].equalsIgnoreCase("the")||tokens[j].equalsIgnoreCase("I")||tokens[j].equalsIgnoreCase("a")||tokens[j].equalsIgnoreCase("it")||tokens[j].equalsIgnoreCase("that")||tokens[j].equalsIgnoreCase("you")||tokens[j].equalsIgnoreCase("to")||tokens[j].equalsIgnoreCase("in")) {
					System.out.println("in the if statement");
					System.out.println(options[i]);
					return a;
				
				}
				else if (i==25 && j==(tokens.length-1)) {
					
					return a;
					
				}
			}
		}
	
		return a;
	}
	
}
