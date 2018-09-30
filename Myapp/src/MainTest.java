import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;



//Sym 符号位
//SymNum 符号数
//Num 数字
//symbol 符号数组
//n 题目数
//r 数值范围
//list 式子列表
//formula 一条式子


public class MainTest {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		System.out.println("请输入生成题目数：-n");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println("请输入数值范围：-r");
		Scanner in = new Scanner(System.in);
		int r =in.nextInt();
		Random rand = new Random();		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Integer> answerlist = new ArrayList<Integer>();
		char[] symbol =new char[]{'+','-','*','÷'};
		File test=new File(".\\test.txt");
		test.delete();
		test.createNewFile();
		File answer=new File(".\\answer.txt");
		test.delete();
		test.createNewFile();
		for(int i=0;i<n;i++) {       //循环生成n条题目
			int SymNum = rand.nextInt(3)+1;
			int[] Num=new int[SymNum+1]; 
			String formula=new String();
			for(int j=0;j<SymNum+1;j++) {   //随机生成数字及其范围
				Num[j]=rand.nextInt(r)+1;
			}
			
			int whether=rand.nextInt(2);
			int choice=rand.nextInt(2);
			int k;
			int tag=0;
			int stop=0;
			int stop1=0;
			if(whether==0) {
				if(SymNum==3&&choice==0) { //1
					for(k=0;k<SymNum;k++) {  //符号
						int Sym =rand.nextInt(4);      //随机生成符号
						if(tag==0) {
							formula+='('+String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
						}
						else if(tag==1) {
							formula+=String.valueOf(Num[k])+')'+String.valueOf(symbol[Sym]);
						}
						else formula+=String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
						tag++;
						}
						formula+=String.valueOf(Num[SymNum]);
						tag=0;
						
				}
				else if(SymNum==3&&choice==1) { //2
					for(k=0;k<SymNum;k++) {
						int Sym =rand.nextInt(4); 
						if(tag==1) {
							formula+='('+String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
						}
						else if(tag==2) {
							formula+=String.valueOf(Num[k])+')'+String.valueOf(symbol[Sym]);
						}
						else formula+=String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
						tag++;
						}
						formula+=String.valueOf(Num[SymNum]);
						tag=0;
						
				}
				else if(SymNum==2&&choice==1) { //3
					for(k=0;k<SymNum;k++) { 
						int Sym =rand.nextInt(4); 
						if(tag==0) {
							formula+='('+String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
						}
						else {
							formula+=String.valueOf(Num[k])+')'+String.valueOf(symbol[Sym]);
						}
						tag++;
						}
						formula+=String.valueOf(Num[SymNum]);
						tag=0;
						
				}
				
				else{
					for(k=0;k<SymNum;k++) {
						int Sym =rand.nextInt(4);
						formula+=String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
					}
						formula+=String.valueOf(Num[SymNum]);
				}
			}
			
			
			if(whether==1) {
			for(k=0;k<SymNum;k++) {  
			int Sym =rand.nextInt(4);
			formula+=String.valueOf(Num[k])+String.valueOf(symbol[Sym]);
			}
			formula+=String.valueOf(Num[SymNum]);
			}
			
			if(list.contains(formula)) {//检查是否重复
	        	int len=list.size();
	        	list.remove(len-1);
	        	n=n+1;
	        	continue;
			}
			list.add(formula);
			
			//逆波兰表达式处理式子运算
			 Stack<Integer> savenumber = new Stack<Integer>(); // 保存数字
		     Stack<Character> savesymbol = new Stack<Character>(); // 保存操作符
		     int memory = 0; // 保存每一个数字
		     char[] cs = formula.toCharArray();
		     for (int k1=0;k1<cs.length;k1++) {
		         char temp = cs[k1];
		         //数字判断
		         if (Character.isDigit(cs[k1])) {
		            memory= 10*memory + Integer.parseInt(String.valueOf(cs[k1]));
		           }
		         //符号判断
		         else {
		                if (memory!= 0) {
		                    savenumber.push(memory);
		                    memory = 0;
		                }
		                if (temp=='(') {
		                    savesymbol.push(temp);
		                } else if (temp==')') {
		                	
		                	//peek 不改变栈的值(不删除栈顶的值)，pop会把栈顶的值删除。
		                	
		                    while (savesymbol.peek()!='(') { // 括号里面运算完
		                        int t = calculator(savenumber.pop(), savenumber.pop(), savesymbol.pop());
		                        savenumber.push(t);
		                    }
		                    savesymbol.pop();
		                } else if (priority(temp) > 0) {
		                    if (savesymbol.isEmpty()) { // 栈为空直接入栈
		                    	savesymbol.push(temp);
		                    } else {
		                        if (priority(savesymbol.peek())>= priority(temp)) {
		                            int t = calculator(savenumber.pop(), savenumber.pop(), savesymbol.pop());
		                            savenumber.push(t);
		                        }
		                        savesymbol.push(temp);
		                    }
		               }
		            }
		        }
		        if (memory!= 0) {
		            savenumber.push(memory);
		        }
		        while (!savesymbol.isEmpty()) {
		            int t = calculator(savenumber.pop(), savenumber.pop(), savesymbol.pop());
		            if(t==10001) {
		            	stop=1;
		            	break;
		            }
		            savenumber.push(t);
		        }
		        if(!savenumber.isEmpty()&&savenumber.peek()<0) { //负数
		        	n=n+1;
		        	int len=list.size();
		        	list.remove(len-1);
		        	continue;
	        }
		        else if(stop==1) {
		        	n=n+1;
		        	int len=list.size();
		        	list.remove(len-1);
		        	continue;
		        }
		        else if(stop1==1) {
		        	n=n+1;
		        	int len=list.size();
		        	list.remove(len-1);
		        	continue;
		        }
		        answerlist.add(savenumber.pop());
		        }
		
				BufferedWriter out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test)));
				int Titlenumber=1;
				for (String tmp : list) {
					out.write(Titlenumber+".  "+tmp+"=");
					out.newLine();
					Titlenumber++;
				}
				out.flush();
				out.close();
				
				BufferedWriter answerout=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(answer)));
				BufferedReader fis=new BufferedReader(new FileReader(".\\test.txt"));
				String testtmp;
				for (int answertmp : answerlist) {
					testtmp=fis.readLine();
					answerout.write(testtmp+answertmp);
					answerout.newLine();
				}
				answerout.flush();
				answerout.close();
		
		System.out.println("题目已生成，是否开始作答？(Y/N)");
		System.out.println("Y:开始答题");
		System.out.println("N:退出程序");
		Scanner s=new Scanner(System.in);
		String choose= s.nextLine();
		switch(choose) {
		case "Y":
			Exercise e=new Exercise();
			break;
		case "y":
			Exercise e1=new Exercise();
			break;
		case "N":
			System.exit(0);
			break;
		case "n":
			System.exit(0);
			break;
		default:
			System.exit(0);
		}
		
	 }

		    // 返回运算符的优先级
		    public static int priority(char c) {
		        if (c=='+'||c=='-') {
		            return 1;
		        } else if (c=='*'||c == '÷') {
		            return 2;
		        } else {
		            return 0;
		        }
		    }

		    public static int calculator(int m, int n, char c) {
		        int sum = 0;
		        if (c == '+') {
		            sum = n + m;
		        } else if (c == '-') {
		        	sum = n - m;
		        } else if (c == '*') {
		            sum = n * m;
		        } else if (c == '÷') {
		        	if(m==0) {
		        		sum=10001;
		        	}
		        	else sum = n / m;
		        }
		        return sum;
		    }
		}

		









/*String ss=" ";
String sss;
int[] s=new int[5];
for(int i=0;i<5;i++) {
	s[i]=i;
	ss=ss+"+"+s[i];
	}
sss=ss.substring(2, 11);
System.out.print(sss);*/