import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public  class Linecount {
	Linecount() throws IOException {
    System.out.println("����·����");
	Scanner input=new Scanner(System.in);
	String path=input.nextLine();
	BufferedReader fis =new BufferedReader(new FileReader(path));
	int linecount=0;
	while(fis.readLine()!=null) {  //��ǰ�в�Ϊ��ʱ������+1
		linecount++;
		}
	System.out.println("������"+linecount);
	fis.close();

}
}