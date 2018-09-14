import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wordcount {
	String REGEX ="[a-zA-Z]+\\b";   //判定为单词的正则表达式条件
      Wordcount() throws IOException {
	    System.out.println("输入路径：");
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		BufferedReader fis =new BufferedReader(new FileReader(path));
		int wordcount =0;
		String w;
		Pattern p =Pattern.compile(REGEX);
		while((w=fis.readLine()) != null) {
			Matcher m =p.matcher(w);
			while(m.find())   //当找到符合条件的内容时单词数+1
				   wordcount ++;
		}
        
        System.out.println("单词数："+wordcount);
        fis.close();
}
}