import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WC {

	public static void main(String[] args) throws IOException{
		// TODO �Զ����ɵķ������
		while(true) {
		System.out.println("ָ��˵����");
		System.out.println("wc.exe -c  �����ļ� file.c ���ַ�����");
		System.out.println("wc.exe -w  �����ļ� file.c �Ĵʵ���Ŀ ");
		System.out.println("wc.exe -l  �����ļ� file.c ������");
		System.out.println("wc.exe -a  �����ļ� file.c �Ŀ��У������У�ע����");
		System.out.println("����ָ�");
     
 
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
			System.out.println("ָ��������������룺");
			break;
		}
 
	}
	}
}
