import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wordcount {
	String REGEX ="[a-zA-Z]+\\b";   //�ж�Ϊ���ʵ�������ʽ����
      Wordcount() throws IOException {
	    System.out.println("����·����");
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		BufferedReader fis =new BufferedReader(new FileReader(path));
		int wordcount =0;
		String w;
		Pattern p =Pattern.compile(REGEX);
		while((w=fis.readLine()) != null) {
			Matcher m =p.matcher(w);
			while(m.find())   //���ҵ���������������ʱ������+1
				   wordcount ++;
		}
        
        System.out.println("��������"+wordcount);
        fis.close();
}
}