import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WC {

	public static void main(String[] args) throws IOException{
		// TODO 自动生成的方法存根
		while(true) {
		System.out.println("指令说明：");
		System.out.println("wc.exe -c  返回文件 file.c 的字符数：");
		System.out.println("wc.exe -w  返回文件 file.c 的词的数目 ");
		System.out.println("wc.exe -l  返回文件 file.c 的行数");
		System.out.println("wc.exe -a  返回文件 file.c 的空行，代码行，注释行");
		System.out.println("输入指令：");
     
 
    	Scanner input=new Scanner(System.in);
    	String commend=input.nextLine();
    	switch (commend) {
		case "-l":
			Linecount line = new Linecount();
			break;
		case "-c":
			 Charcount ch = new Charcount();
			break;
		case "-w":
			 Wordcount wc = new Wordcount();
			 break;
		case "-a":
			Complex cp = new Complex();
			break;
		default:
			System.out.println("指令错误，请重新输入：");
			break;
		}
 
	}
	}
}
