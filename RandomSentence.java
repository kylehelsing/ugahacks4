import java.util.Scanner;
import java.io.File;

public class RandomSentence{
    public static void main(String[] args){
	File adjFile = new File("adjectives.txt");
	File nounFile = new File("nouns.txt");
	Scanner input = new Scanner(System.in);
	boolean again = false;

	
	try{		    
	    do{
		again = false;
		Scanner adjFinder = new Scanner(adjFile);
		Scanner nounFinder = new Scanner(nounFile);
		
		int randAdj = (int)(Math.random() * 998);	    
		int randNoun = (int)(Math.random() * 998);
		
		for(int i = 0; i < randAdj; i++){
		    adjFinder.nextLine();
		} //for
		String adj = adjFinder.next();
		String adj2 = adjFinder.next();
		for(int i = 0; i < randNoun; i++){
		    nounFinder.nextLine();
		} //for
		String noun = nounFinder.next();
		String noun2 = nounFinder.next();
		System.out.println(adj + " " + noun + ": " + adj2 + " for your " + noun2);

		System.out.print("Do you want another idea? ");
		String next =  input.next();
		if(next.equalsIgnoreCase("yes") || next.equalsIgnoreCase("y")) 
		    again = true;
		
		adjFinder.close();
		nounFinder.close();
	    } while(again);
	    
	}
	catch(Exception e){
	    e.printStackTrace();
	} //catch
    } //main
}
