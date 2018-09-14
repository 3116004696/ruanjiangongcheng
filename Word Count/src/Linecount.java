import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public  class Linecount {
	Linecount() throws IOException {
    System.out.println("输入路径：");
	Scanner input=new Scanner(System.in);
	String path=input.nextLine();
	BufferedReader fis =new BufferedReader(new FileReader(path));
	int linecount=0;
	while(fis.readLine()!=null) {  //当前行不为空时，行数+1
		linecount++;
		}
	System.out.println("行数："+linecount);
	fis.close();

}
}