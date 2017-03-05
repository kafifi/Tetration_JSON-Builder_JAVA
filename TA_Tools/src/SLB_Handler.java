import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SLB_Handler implements Handler{
	
	static int  nat = 0;
	static int  backend = 0;
	static int total_backend = 0;
	static int total_nat = 0;

	@Override
	public void readFromFile(String theFile) {
    	
		  System.out.println('{');
		  System.out.print('\"');
		  System.out.print("configs");
		  System.out.print('\"');
		  System.out.print(": ");
		  System.out.println('[');
		 


    // This will reference one line at a time
    try {
			List<String> lines = Files.readAllLines(Paths.get(theFile), StandardCharsets.UTF_8);
			processFile(lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
			 
		}
    
    
}
	


	private void decide(String string) {
		// TODO Auto-generated method stub
		if (string.contains(":"))
		{
			if(backend ==0)
			{
				 System.out.print("\"backends\"" + ": " + "[");
				 System.out.print("\n");
				 backend++;
			}
			 System.out.print("{");
			backendProcessing(string);
			 System.out.print( "}");

		}
		else if (string.contains("/"))
		{
			natPoolProcesing(string);
		}
		
		
	}

	private void natPoolProcesing(String string) {
		// TODO Auto-generated method stub
		
		if (nat == 0)
		{
			 System.out.print("\"nat_subnet_pool\"" + ": " +  "[");
			 System.out.print("\n");
			 nat++;
		}
		System.out.print("\"" + string + "\"");
		
		
	}

	private void backendProcessing(String string) {
		// TODO Auto-generated method stub
		
		
		String[] temp;
		 
		  /* delimiter */
		  String delimiter = ":";
		  /* given string will be split by the argument delimiter provided. */
		  temp = string.split(delimiter);
		  for(int i =0; i < temp.length ; i++)
		  {
			  if(i == 0)
			  {
				  System.out.print("\n");
				  System.out.print("\"backend_ip\"" + ": "+ "\"" + temp[i].trim() + "\"" + ",");
				  System.out.print("\n");
				  
			  }
			  else if(i == 1) 
			  {
				  System.out.print("\"backend_port\"" + ": " + temp[i].trim() );
				  System.out.print("\n");
				  
			  }
		  }
	}




	@Override
	public void processFile(List<String> lines) {
		// TODO Auto-generated method stub
	String mem = "";
		
		for (String name : lines)
		{
		    mem = mem + name;
		}
		
		
		String[] temp;
		 
		  /* delimiter */
		  String delimiter = ";";
		  /* given string will be split by the argument delimiter provided. */
		  temp = mem.split(delimiter);
		  /* print substrings */
		  for(int i =0; i < temp.length ; i++)
		  {
			  System.out.print("{");
			 doAction(temp[i]);
			 
			 if(i != temp.length - 1)
			 {
				 System.out.print("\n" + "}");
				System.out.print("," + "\n"); 
			 }
			 else
			 {
				System.out.print("\n" + "}");
				System.out.print("\n" + "]");
				System.out.print("\n" + "}");
			 }

		
		  }
		
		
	}




	@Override
	public void doAction(String record) {
		// TODO Auto-generated method stub
		total_backend = 0;
		total_nat = 0;
		
		String[] temp;
		 
		  /* delimiter */
		  String delimiter = ",";
		  /* given string will be split by the argument delimiter provided. */
		  temp = record.split(delimiter);
		  
		  //Count backend & nat
		  for(int i =0; i < temp.length ; i++)
		  {
			  if (temp[i].contains(":"))
			  {
				  total_backend++;
			  }
			  
			  else
				  if (temp[i].contains("/"))
				  {
					  total_nat++;
				  }
		  }
		  
		
		  for(int i =0; i < temp.length ; i++)
		  {
			  if(i == 0) //temp.length -1
			  {
			  System.out.print("\n");
			  System.out.print("\"name\"" + ": "+ "\"" + temp[i].trim() + "\"" + ",");
			  System.out.print("\n");
			  }
			  else if(i == 1 )
			  {
				  System.out.print("\"vip\"" + ": "+ "\"" + temp[i].trim() + "\"" + ",");
				 System.out.print("\n");
			  }
			  else if(i ==2)
			  {
				  System.out.print("\"vip_port\"" + ": " + temp[i].trim() + ",");
					 System.out.print("\n");
			  }
			  else if(i ==3)
			  {
				  System.out.print("\"protocol\"" + ": " + temp[i].trim() + ",");
					 System.out.print("\n");
			  }

			  else if(i >= 4 && i!= temp.length -1)
			  {
				  decide(temp[i]);
				  
				  if(i == total_backend + 3) {
					  System.out.print("\n");
					  System.out.println("],");
					  
				  }
				  else 
				  {

						System.out.print(",");
					    System.out.print("\n");
				  }

				  
			  }
			  else if(i > 4 && i  == temp.length -1)
			  {
				  decide(temp[i]);
				  System.out.print("\n");
				  System.out.print("]");

			  } 
			
			
		  }
		  
		  nat =0;
		  backend=0;

		
	}


}

