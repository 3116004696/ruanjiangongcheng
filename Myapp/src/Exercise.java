

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Exercise extends JFrame implements ActionListener{
       JLabel welcome=new JLabel("Weclome Back!",JLabel.CENTER);
       JLabel none1=new JLabel("                             ");
       JLabel none2=new JLabel("                             ");
       JTextArea write=new JTextArea(60,60);
       JScrollPane sp=new JScrollPane(write);
       JButton begin;
       JButton check;
       JButton confirm;
       Color color=new Color(0,0,0);
   Exercise(){
	   setTitle("Exercise");
	   setBounds(0,0,1400,900);
	   Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	   setLocation((screen.width-1400)/2,(screen.height-900)/2);
	   JPanel p=new JPanel();
	   this.add(p);
	   p.setBackground(Color.DARK_GRAY);
	   p.setLayout(new BorderLayout());
	   begin=new JButton("开始答题");
	   check=new JButton("参考答案");
	   confirm=new JButton("提交");
	   JPanel p1=new JPanel();
	   p1.setLayout(new GridLayout(1,3,200,0));
	   p1.add(begin);
	   p1.add(confirm);
	   p1.add(check);
	   JMenuBar mb=new JMenuBar();
	   setJMenuBar(mb);
	   JMenu m=new JMenu("开始");
	   mb.add(m);
	   JMenuItem m1=new JMenuItem("新建");
	   JMenuItem m2=new JMenuItem("保存");
	   JMenuItem m3=new JMenuItem("打开");
	   JMenuItem m4=new JMenuItem("退出");
	   m.setFont(new Font("幼圆",Font.BOLD,35));
	   m1.setFont(new Font("幼圆",Font.BOLD,25));
	   m2.setFont(new Font("幼圆",Font.BOLD,25));
	   m3.setFont(new Font("幼圆",Font.BOLD,25));
	   m4.setFont(new Font("幼圆",Font.BOLD,25));
	   m.add(m1);
	   m.add(m2);
	   m.add(m3);
	   m.add(m4);
	   JMenu help=new JMenu("帮助");
	   help.setFont(new Font("幼圆",Font.BOLD,35));
	   mb.add(help);
	   JMenu about=new JMenu("关于");
	   about.setFont(new Font("幼圆",Font.BOLD,35));
	   JMenu setting=new JMenu("设置");
	   setting.setFont(new Font("幼圆",Font.BOLD,35));
	   JMenuItem m5=new JMenuItem("字体颜色");
	   JMenuItem m6=new JMenuItem("文本颜色");
	   m5.setFont(new Font("幼圆",Font.BOLD,25));
	   m6.setFont(new Font("幼圆",Font.BOLD,25));
	   setting.add(m5);
	   setting.add(m6);
	   mb.add(about);
	   mb.add(setting);
	   JPopupMenu po=new JPopupMenu();
	   write.add(po);
	   welcome.setFont(new Font("Script MT Bold",Font.BOLD,50));
	   welcome.setForeground(Color.WHITE);
	   p.add(welcome,BorderLayout.NORTH);
	   p.add(sp,BorderLayout.CENTER);
	   p.add(none1,BorderLayout.WEST);
	   p.add(none2,BorderLayout.EAST);
	   p.add(p1,BorderLayout.SOUTH);
	   m1.addActionListener(this);
	   m2.addActionListener(this);
	   m3.addActionListener(this);
	   m4.addActionListener(this);
	   m5.addActionListener(this);
	   m6.addActionListener(this);
	   begin.addActionListener(this);
	   check.addActionListener(this);
	   confirm.addActionListener(this);
	   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   this.setVisible(true);
   }
   public void actionPerformed(ActionEvent e){
	   if(e.getActionCommand()=="清空") {
		   write.setText("");
	   }
	   else if(e.getActionCommand()=="保存") {
		   FileDialog save=new FileDialog(this,"save",FileDialog.SAVE);
		   save.setVisible(true);
		   try {
		   String savefile=save.getDirectory()+save.getFile();
		   BufferedReader in=new BufferedReader(new StringReader(write.getText()));
		   BufferedWriter fos=new BufferedWriter(new FileWriter(savefile));
		   String str;
		   while((str=in.readLine())!=null) {
			   fos.write(str);
			   fos.newLine();
		   }
		   fos.flush();
		   fos.close();
		   }catch(IOException ioe) {
			   System.out.println("ERROR");
		   }
	   }
	   else if(e.getActionCommand()=="打开") {
		   FileDialog open=new FileDialog(this,"open",FileDialog.LOAD);
		   open.setVisible(true);
		   try {
			   String openfile=open.getDirectory()+open.getFile();
			   BufferedReader fis=new BufferedReader(new FileReader(openfile));
			   String s;
			   while((s=fis.readLine())!=null) {
				   write.append(s);
				   write.append("\n");
			   }
			   fis.close();
		   }catch(IOException ioe) {
			   System.out.println("ERROR");
		   }
	   }
	   else if(e.getActionCommand()=="退出") {
	   dispose();}
	   else if(e.getSource()==begin) {//答题
		   write.setText("");
		   write.setFont(new Font("Tekton Pro",Font.BOLD,40));
		   try {
			   BufferedReader fis=new BufferedReader(new FileReader(".\\test.txt"));
			   String s;
			   while((s=fis.readLine())!=null) {
				   write.append(s);
				   write.append("\n");
			   }
			   fis.close();
		   }catch(IOException ioe) {
			   System.out.println("ERROR");
		   }
	   }
	   else if(e.getSource()==check) { //参考答案
		   write.setFont(new Font("Tekton Pro",Font.BOLD,40));
		   try {
			   BufferedReader fis=new BufferedReader(new FileReader(".\\answer.txt"));
			   String s;
			   while((s=fis.readLine())!=null) {
				   write.append(s);
				   write.append("\n");
			   }
			   fis.close();
		   }catch(IOException ioe) {
			   System.out.println("ERROR");
		   }
	   }
	   else if(e.getSource()==confirm) {//提交
		   try {
			   BufferedReader fis=new BufferedReader(new FileReader(".\\answer.txt"));
			   BufferedReader in=new BufferedReader(new StringReader(write.getText()));
			   String s;
			   String comp;
			   int correct=0;
			   int wrong=0;
			   int col=write.getLineCount()-1;
			   int testnum=1;
			   int i=0;
			   int j=0;
			   int[] correctnum=new int[col];
			   int[] wrongnum=new int[col];
			   while((s=fis.readLine())!=null&&(comp=in.readLine())!=null) {
				   if(s.equals(comp)) {
					   correctnum[i]=testnum;
					   correct++;
					   i++;
				   }
				   else {
					   wrongnum[j]=testnum;
					   wrong++;
					   j++;
				   }
				   testnum++;
			   }
			   write.append("\n\nCorrect: "+correct);
			   write.append(" (");
			   for(int k=0;k<col;k++) {
				   if(correctnum[k]!=0)
				   write.append(" "+correctnum[k]+" ");
			   }
			   write.append(")\n");
			   write.append("Wrong: "+wrong);
			   write.append(" (");
			   for(int k=0;k<col;k++) {
				   if(wrongnum[k]!=0)
				   write.append(" "+wrongnum[k]+" ");
			   }
			   write.append(")\n\n");
			   fis.close();
			   in.close();
		   }catch(IOException ioe) {
			   System.out.println("ERROR");
		   }
	   }
	   else if(e.getActionCommand()=="字体颜色") {
			color= JColorChooser.showDialog(this,"选择颜色",color);
			if (color==null ) color=Color.BLACK; 
			write.setForeground(color);
	   }
	   else if(e.getActionCommand()=="文本颜色") {
			color= JColorChooser.showDialog(this,"选择颜色",color);
			if (color==null ) color=Color.WHITE; 
			write.setBackground(color);
	   }
   }
}