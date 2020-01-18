
public class Main {
	public static void main(String[] args){
		for(String inputFileGraphml: args)
		{
			System.out.println("\nJAVA PROJECT - COMMUNICATION NETWORK ANALYSIS");
			System.out.print("GROUPMEMBERS: CHAKAN, LUKA, MAXIM, MOUAD\n\n");
			Project p = new Project();
			p.graphmlAnalysis(inputFileGraphml);
		}
	}
}
