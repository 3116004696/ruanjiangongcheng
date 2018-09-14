import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Charcount {
	    String REGEX ="\\S";
	    Charcount() throws IOException {
	    System.out.println("ÊäÈëÂ·¾¶£º");
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		BufferedReader fis =new BufferedReader(new FileReader(path));
		int charcount=0; 
	    String w;
		Pattern p =Pattern.compile(REGEX);
		while((w=fis.readLine()) != null) {
			Matcher m =p.matcher(w);
			while(m.find()) 
				
			   charcount ++;
		}		
		System.out.println("×Ö·ûÊý£º"+charcount);
		fis.close();
	
}
}