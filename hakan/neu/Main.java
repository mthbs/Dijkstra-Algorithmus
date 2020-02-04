
public class Main {
	public static void main(String[] args){
		for(String inputFileGraphml: args)
		{
			Graphmlinfo gi = new Graphmlinfo();
			
			System.out.println("\nJAVA PROJECT - COMMUNICATION NETWORK ANALYSIS");
			System.out.print("GROUPMEMBERS: CHAKAN, LUKA, MAXIM, MOUAD\n\n");
			gi.graphmlReader(inputFileGraphml);
		}
	}
}