import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
	
      Complex() throws IOException{
    	  System.out.println("����·����");
    	  Scanner a = new Scanner(System.in);
    	  String path = a.nextLine();
    	  BufferedReader fis = new BufferedReader(new FileReader(path));
    	  int spacecount = 0;
    	  int notecount = 0;
    	  int codecount = 0;
    	  boolean state = false;
    	  String c;
    	  while((c=fis.readLine())!=null) {
    			 if(c.contains("/*")) {   //����ע�Ϳ�ʼ���
    				 notecount++;
    				 state = true;
    			 }
    			 else if(state) {
        			 notecount++;
    				 if(c.contains("*/")) {  //����ע�ͽ������
    				 state = false;}
    			 }
    			 else if(c.contains("//")) {  //����ע�ͱ��
    				 notecount++;
    			 }
    			 else if(c.trim().length()>1) {  //�ж�Ϊ����������
    				 codecount++;
    			 }
    			 else {spacecount++;}
    		 }
    	  
    	  fis.close();
    	  System.out.println("�հ��У�"+spacecount);
    	  System.out.println("ע���У�"+notecount);
    	  System.out.println("�����У�"+codecount);
      }
}
