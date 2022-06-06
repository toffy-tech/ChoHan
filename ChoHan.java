package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ChoHan extends JFrame implements ActionListener {

 private static final long serialVersionUID = 1L;

 final int MAX = 6; //サイコロの目の数
 
 Timer timer = new Timer(10, this); //タイマーはできるだけ早くしている

 JLabel label= new JLabel("”回す”を押すとサイコロを回すことができるよ",JLabel.CENTER);
 JButton[] bt = {new JButton("回す"),new JButton("止める")}; 
 JButton button1 = new JButton(new ImageIcon("Cho.jpg"));
 JButton button2 = new JButton(new ImageIcon("Han.jpg"));
 JLabel saikoro = new JLabel(new ImageIcon("saikoro1.jpg"),JLabel.CENTER);
 JLabel result = new JLabel(new ImageIcon("kekka.png"),JLabel.CENTER);
 ImageIcon[] saikoro_icon = new ImageIcon[MAX];
 
 int iCount = 0;   
 //iCountはサイコロの目に使う数字
 
 int number=1;    
 //numberはiCountの値を変更するためのもの	

 ChoHan(String title) {
  super(title);
  timer.setActionCommand("timer");
  setBounds(200, 200, 650, 400);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  for(int i = 0;i < saikoro_icon.length; i++)
	   saikoro_icon[i] = new ImageIcon("saikoro" + (i+1)+ ".jpg");
  //サイコロの画像を配列として、設定する
  
  add("North",label);
  add("East",result);
  add("West",saikoro);
  JPanel x = new JPanel();
  button1.addActionListener(this);
  button2.addActionListener(this);
  x.add(button1);
  x.add(button2);
  add("Center",x);
  //変数xにパネルを設定、丁・半のボタンをまとめてCenterに配置する
  
  JPanel p = new JPanel();
  for (int i = 0; i < bt.length;i++) {
   bt[i].addActionListener(this);
   p.add(bt[i]);
  }
  add("South",p);
  //変数xにパネルを設定、回す・止めるのボタンをまとめてSouthに配置する
  setVisible(true);
 }

 public static void main(String[] args) {
  new ChoHan("丁半ゲーム");
 }

 public void actionPerformed(ActionEvent e) {

  String cmd = e.getActionCommand();
  // setIconを使い，ラベル「saikoro」に表示される画像をsaikoro_icon[0]に設定する
  saikoro.setIcon(saikoro_icon[0]);

  //Timerのイベント処理 
  if(cmd.equals("timer")) {
   iCount+=number;   // iCountを増減させる
   if(iCount==saikoro_icon.length-1 ) number = -1;
   if(iCount==0 ) number = 1;
  // iCountが5になると、数値が下がっていく。
  // iCountが0になると、数値が上がっていく。

  // setIconを使って，ラベル「saikoro」に 画像 saikoro_icon[iCount] が表示されるよう設定
   saikoro.setIcon(saikoro_icon[iCount]);
  }

  //ボタンのイベント処理 
  // ボタン0（スタート）が押されて，タイマーが動いていないとき
  if(e.getSource() == bt[0] && !timer.isRunning()) {
	   label.setText("”止める”を押すとさいころを止められるよ");
	   result.setIcon(new ImageIcon("ouen.jpg"));
	   timer.start();
  }
  // ボタン1（ストップ）が押されて，タイマーが動いているとき
  else if(e.getSource() == bt[1] && timer.isRunning()) {
	  saikoro.setIcon(new ImageIcon("saikoro0.jpg"));
	  timer.stop();
	  label.setText("丁か半か");
  }
  if(e.getSource() == button1 && iCount==0) { //「丁」を押して、サイコロの目が1だった場合
	  saikoro.setIcon(new ImageIcon("saikoro1.jpg"));
	  result.setIcon(new ImageIcon("ChoLose.jpg"));
  }
  else if(e.getSource() == button2 && iCount==0) { //「半」を押して、サイコロの目が1だった場合
	  saikoro.setIcon(new ImageIcon("saikoro1.jpg"));
	  result.setIcon(new ImageIcon("HanWin.jpg"));
  }
  else if(e.getSource() == button1 && (iCount+1)%2==0) { //「丁」を押して、サイコロの目が偶数だった場合
	  saikoro.setIcon(new ImageIcon("saikoro" + (iCount+1)+ ".jpg"));
	  result.setIcon(new ImageIcon("ChoWin.jpg"));
  }
  else if(e.getSource() == button1 && (iCount+1)%2==1) { //「丁」を押して、サイコロの目が奇数だった場合
	  saikoro.setIcon(new ImageIcon("saikoro" + (iCount+1)+ ".jpg"));
	  result.setIcon(new ImageIcon("ChoLose.jpg"));
  }
  else if(e.getSource() == button2 && (iCount+1)%2==1) { //「半」を押して、サイコロの目が奇数だった場合
	  saikoro.setIcon(new ImageIcon("saikoro" + (iCount+1)+ ".jpg"));
	  result.setIcon(new ImageIcon("HanWin.jpg"));
  }
  else if(e.getSource() == button2 && (iCount+1)%2==0) { //「半」を押して、サイコロの目が偶数だった場合
	  saikoro.setIcon(new ImageIcon("saikoro" + (iCount+1)+ ".jpg"));
	  result.setIcon(new ImageIcon("HanLose.jpg"));
  }
 }
}