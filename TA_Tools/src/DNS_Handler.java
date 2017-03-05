import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DNS_Handler implements Handler{
	
	@Override
	  public void readFromFile(String theFile) {
	    	
		  System.out.println('{');
		 
        // This will reference one line at a time
        try {
			List<String> lines = Files.readAllLines(Paths.get(theFile), StandardCharsets.UTF_8);
			processFile(lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			 doAction(temp[i]);
			 
			 if(i != temp.length - 1)
			 {
				System.out.print("," + "\n"); 
			 }
			 else
			 {
				System.out.print("\n" + "}");
			 }
		
		  }
		
	}


	@Override
	public void doAction(String record) {
		// TODO Auto-generated method stub
		String[] temp;
		 
		  /* delimiter */
		  String delimiter = ",";
		  /* given string will be split by the argument delimiter provided. */
		  temp = record.split(delimiter);
		
		  for(int i =0; i < temp.length ; i++)
		  {
			  System.out.print("\"" + temp[i].trim() + "\"" );
			  if(i != temp.length -1 )
			  {
				  System.out.print(": ");
			  }
		  }
		 
		
	}

	

	

	
	
}
