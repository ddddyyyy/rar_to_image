package com.mdy.main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	File f_1;
	File f_2;
	File f_3;
	File f;
	double _count=0;
	
	class ThreadA implements Runnable{
		public void run() {
			do{
				progressBar.setValue((int) _count);;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while(_count!=100);
		}
		
	}
	class ThreadB implements Runnable{
		public void run() {
			FileOutputStream fos = null;
			FileInputStream fis_1=null;
			FileInputStream fis_2=null;
			 try {
				fis_1 = new FileInputStream(f_1);
				 fis_2 = new FileInputStream(f_2);
				 fos = new FileOutputStream(f_3);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			byte[] b= new byte[1024];
			int count=0;
			long n=1;
			try {
				
				while((count=fis_1.read(b))!=-1){
					fos.write(b,0,count);
					_count=(n++*1024*1.0/(f_1.length()+f_2.length()))*100*1.0;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				try {
					while((count=fis_2.read(b))!=-1){
						fos.write(b,0,count);
						_count=(((n++)*1024*1.0/(f_1.length()+f_2.length()))*100*1.0);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					fis_1.close();
					fis_2.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(new java.io.File("E:\\"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "文件转化成功！！");
		}
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)((screensize.getWidth()-300)/3) , (int)((screensize.getHeight()-450)/3 ), 450, 300);
		setResizable(false);
		
		JMenuBar menuBar_2 = new JMenuBar();
		setJMenuBar(menuBar_2);
		
		JMenu menu = new JMenu("\u6587\u4EF6");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar_2.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6253\u5F00");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser("E:");
				int i = jfc.showOpenDialog(contentPane);
				if(i==JFileChooser.APPROVE_OPTION){
					try {
						Desktop.getDesktop().open(jfc.getSelectedFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		menu.add(menuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u65B0\u5EFA\u7A97\u53E3");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
			}
		});
		menu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(84, 22, 213, 24);
		panel_1.add(textArea);
		
		JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser("E:");
				FileFilter filter = new FileNameExtensionFilter("图片文件（JPG/GIF）", "JPG","GIF","JPEG");
				jfc.setFileFilter(filter);
				int i = jfc.showOpenDialog(contentPane);
				if (i==JFileChooser.APPROVE_OPTION){
					f = jfc.getSelectedFile();
					textArea.setText(f.getAbsolutePath());
				}
			}
		});
		button.setBounds(307, 22, 93, 23);
		panel_1.add(button);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(84, 69, 213, 24);
		panel_1.add(textArea_1);
		
		JButton button_1 = new JButton("\u9009\u62E9\u6587\u4EF6");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser("E:");
				FileFilter filter = new FileNameExtensionFilter("压缩包文件（ZIP/RAR）", "ZIP","RAR");
				jfc.setFileFilter(filter);
				int i = jfc.showOpenDialog(contentPane);
				if (i==JFileChooser.APPROVE_OPTION){
					f = jfc.getSelectedFile();
					textArea_1.setText(f.getAbsolutePath());
				}
			}
		});
		button_1.setBounds(307, 69, 93, 23);
		panel_1.add(button_1);
		
		JLabel label = new JLabel("\u56FE\u7247");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(20, 26, 54, 15);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("\u538B\u7F29\u5305");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(20, 73, 54, 15);
		panel_1.add(label_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		progressBar = new JProgressBar(0,100);
		progressBar.setStringPainted(true);
		progressBar.setBounds(85, 60, 316, 23);
		panel.add(progressBar);
		
		JLabel label_2 = new JLabel("\u6587\u4EF6\u540D");
		label_2.setBounds(21, 15, 54, 15);
		panel.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setVerticalAlignment(SwingConstants.TOP);
		JButton btnNewButton = new JButton("压缩");
		btnNewButton.setBounds(308, 12, 93, 23);
		panel.add(btnNewButton);
		
		JLabel label_3 = new JLabel("\u538B\u7F29\u8FDB\u5EA6");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(21, 62, 54, 15);
		panel.add(label_3);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(85, 11, 211, 24);
		panel.add(textArea_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n=JOptionPane.showConfirmDialog(null, "是否确认压缩？","确认窗口",JOptionPane.YES_NO_CANCEL_OPTION);
				if(n==JOptionPane.YES_OPTION){
					if (textArea.getText().isEmpty()||textArea_1.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "清选择文件");
					}
					else{
						f_1 = new File(textArea.getText());
						f_2 = new File(textArea_1.getText());
						if(!textArea_2.getText().isEmpty())
						f_3 = new File("E:",textArea_2.getText()+".jpg");
						else{
							JOptionPane.showMessageDialog(null, "请输入文件名称！！！！","error",JOptionPane.ERROR_MESSAGE);
							return ;
						}
						if(!f_3.exists())
							try {
								if(!f_3.createNewFile()){
									JOptionPane.showMessageDialog(null, "文件创建失败哦");
									return;
								}
							} catch (HeadlessException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						if(f_1.exists()&&f_2.exists()){//文件操作
							new Thread(new ThreadA()).start();
							new Thread(new ThreadB()).start();
						}
						else{
							if(!f_1.exists())
							JOptionPane.showMessageDialog(null, "图片文件不存在","error",JOptionPane.ERROR_MESSAGE);
							else
							JOptionPane.showMessageDialog(null, "压缩包文件不存在","error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				else
					return;
				}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
