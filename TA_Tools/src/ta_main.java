import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ta_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date = new Date();

		
		List<String> options = new ArrayList<String>();
		options.add(TA_REF.SLB);
		options.add(TA_REF.DNS);
		options.add(TA_REF.RTE);
		options.add(TA_REF.CMDB);
		
		if (args.length != 2)
		{
			userError(options, TA_REF.INVALID_ARGS );
			
		}
		else if(args.length == 2 && options.contains(args[0]))
		{
			
			if ( args[0].equals(TA_REF.CMDB))
			{
				System.out.println("Executing: " + args[0] + " at: " + date.toString()+ "\n");
				long startTime = System.nanoTime();
				CMDB_Handler cmdb_call = new CMDB_Handler();
				cmdb_call.readFromFile(args[1]);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				double seconds = ((double)duration / 1000000000);
				System.out.println("Execution time: " + new DecimalFormat("#.##########").format(seconds) + " Seconds");
				System.out.println("\n");
			}
			else if (args[0].equals(TA_REF.DNS))
			{
				System.out.println("Executing: " + args[0] + " at: " + date.toString()+ "\n");
				long startTime = System.nanoTime();
				DNS_Handler dns_call = new DNS_Handler();
				dns_call.readFromFile(args[1]);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				double seconds = ((double)duration / 1000000000);
				System.out.println("Execution time: " + new DecimalFormat("#.##########").format(seconds) + " Seconds");
				System.out.println("\n");
			}
			else if (args[0].equals(TA_REF.RTE))
			{
				System.out.println("Executing: " + args[0] + " at: " + date.toString()+ "\n");
				long startTime = System.nanoTime();
				RTE_Handler rte_call = new RTE_Handler();
				rte_call.readFromFile(args[1]);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				double seconds = ((double)duration / 1000000000);
				System.out.println("Execution time: " + new DecimalFormat("#.##########").format(seconds) + " Seconds");
				System.out.println("\n");
			}
			else if (args[0].equals(TA_REF.SLB))
			{
				System.out.println("Executing: " + args[0] + " at: " + date.toString()+ "\n");
				long startTime = System.nanoTime();
				SLB_Handler slb_call = new SLB_Handler();
				slb_call.readFromFile(args[1]);
				System.out.println("\n");
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				double seconds = ((double)duration / 1000000000);
				System.out.println("Execution time: " + new DecimalFormat("#.##########").format(seconds) + " Seconds");
				System.out.println("\n");
			}
		}
		
		else 
		{
			userError(options, TA_REF.INVALID_OPTION);
		}





	}

	private static void userError(List<String> options, String error) {
		// TODO Auto-generated method stub
		System.out.println(error);
		System.out.println(TA_REF.SYNTAX);
		System.out.print("	options ");

		
		for (String option : options)
		{
			System.out.print("<" + option + "> ");
			
		}
		
		System.out.print("\n");
		System.out.println(TA_REF.EXAMPLE);
		
	}


}
