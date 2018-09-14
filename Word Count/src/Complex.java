import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
	
      Complex() throws IOException{
    	  System.out.println("输入路径：");
    	  Scanner a = new Scanner(System.in);
    	  String path = a.nextLine();
    	  BufferedReader fis = new BufferedReader(new FileReader(path));
    	  int spacecount = 0;
    	  int notecount = 0;
    	  int codecount = 0;
    	  boolean state = false;
    	  String c;
    	  while((c=fis.readLine())!=null) {
    			 if(c.contains("/*")) {
    				 notecount++;
    				 state = true;
    			 }
    			 else if(state) {
        			 notecount++;
    				 if(c.contains("*/")) {
    				 state = false;}
    			 }
    			 else if(c.contains("//")) {
    				 notecount++;
    			 }
    			 else if(c.trim().length()>1) {
    				 codecount++;
    			 }
    			 else {spacecount++;}
    		 }
    	  
    	  fis.close();
    	  System.out.println("空白行："+spacecount);
    	  System.out.println("注释行："+notecount);
    	  System.out.println("代码行："+codecount);
      }
}
