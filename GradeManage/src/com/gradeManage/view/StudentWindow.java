package com.gradeManage.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.gradeManage.controller.DBConnection;
import com.gradeManage.controller.FileDaoImpl;
import com.gradeManage.controller.StudentDaoImpl;
import com.gradeManage.entities.Client;



public class StudentWindow  implements Runnable{
	
		private JFrame frame;
		private JLabel t = new JLabel();
		
		public StudentWindow(String userName, String passWord) {
			
			frame = new JFrame();
			//���ù۸�
			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			SwingUtilities.updateComponentTreeUI(frame);
			
			
			//���ý��泤�Ϳ�
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width*2/3+30;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
			//���ó�ʼ������λ��
			int width = Toolkit.getDefaultToolkit().getScreenSize().width/6;
			int height = Toolkit.getDefaultToolkit().getScreenSize().height/6;
			frame.setBounds(width,height,screenWidth,screenHeight);
			frame.setTitle("ѧ��ƽ̨");
			frame.setLayout(null);
			
			
			//�½����
			JMenuBar navigator = new JMenuBar();
			navigator.setBackground(Color.WHITE);
			navigator.setBounds(0, 0, screenWidth, 20);
			JPanel panel = new JPanel();
			panel.setBounds(0,22,180,screenHeight);
//			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			JPanel panel2 = new JPanel();
			panel2.setLayout(new java.awt.CardLayout());
			panel2.setBounds(185,22,530,screenHeight);
			panel2.setBackground(Color.WHITE);
			JPanel panel3 = new JPanel();
			
			panel3.setBounds(720,22,370,screenHeight);
			panel3.setBackground(Color.WHITE);
			panel3.setLayout(null);
			
			//�����3�������
			LocalDate date = LocalDate.now();//��ȡ��ǰʱ��
			int month = date.getMonthValue();//��ȡ��ǰ�·�
			int today = date.getDayOfMonth();//��ȡ��������
			date = date.minusDays(today-1);
			DayOfWeek weekday = date.getDayOfWeek();//����dayofweek�����getValue������ȡ���ڼ�
			int value = weekday.getValue()-1;
			
			String[] colNames = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
			
			Object[][] calendar = new Object[6][7];
			int i = 0;
				while(date.getMonthValue() == month) {
					calendar[i][value] = date.getDayOfMonth();
					if(date.getDayOfMonth() == today) {
						calendar[i][value] = date.getDayOfMonth()+"*";
					}
					value++;
					date = date.plusDays(1);
					if(date.getDayOfWeek().getValue() == 1) {
						i++;
						value = 0;
					}
			}
			
				
			JTable table = new JTable(calendar,colNames);
			JScrollPane pane = new JScrollPane(table);
//			pane.setLayout(null);
			pane.setBackground(Color.WHITE);
			table.updateUI();
			pane.setBounds(0,25,200,125);
			panel3.add(pane);
			panel3.updateUI();
			
			
			//���3����ļ��б�
			JLabel ifo = new JLabel("��������");
			Font font = new Font("����",Font.BOLD,20);
			ifo.setFont(font);
			ifo.setBounds(60, 160,100,50);
			panel3.add(ifo);
			JPanel panel4 = new JPanel();
			ResultSet rs2 = FileDaoImpl.getFile();
			try {
				while(rs2.next()) {
					JLabel file = new JLabel(rs2.getString("file_Names")+"          ");
					file.setToolTipText("�������");
					file.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(java.awt.event.MouseEvent e) {
							// TODO Auto-generated method stub
							super.mouseClicked(e);
							int choose = JOptionPane.showConfirmDialog(null,"����"+file.getText()+"?");
							if(JOptionPane.YES_OPTION == choose) {
								String f = FileDaoImpl.findFile(file.getText());
								JFileChooser fileChooser = new JFileChooser();
								fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								fileChooser.showSaveDialog(new JLabel("����"));
								File sFile = fileChooser.getSelectedFile();
								File saveFile = new File(sFile.getName()+file.getText());
								try {
									FileOutputStream fos = new FileOutputStream(saveFile);
									Client client = new Client();
									try {
										client.getDos().writeUTF("����");
										client.getDos().writeUTF(f);
										byte[] buffer = new byte[1024*5];
										int len = 0;
										while(( len = client.getDis().read(buffer,0,buffer.length))!=-1) {
											fos.write(buffer,0,len);
											System.out.println("%");
											fos.flush();
										}
										JOptionPane.showMessageDialog(null,"���سɹ�");
										fos.close();
										client.getClient().close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
//										e1.printStackTrace();
										JOptionPane.showMessageDialog(null, "������δ����");
									}
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					});
					panel4.add(file);
				}
				panel4.setBounds(0,200, 200, 200);
				panel4.setBackground(Color.WHITE);
				JScrollPane jsp = new JScrollPane(panel4);
				panel3.add(jsp);
				panel3.add(panel4);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			
			
			//�½���ǩ
			JLabel uname = new JLabel();
			JLabel exit = new JLabel("�˳�");
			JLabel bg = new JLabel(new ImageIcon("bg1.jpg"));
			bg.setBounds(185,22,530,screenHeight);
			panel.setLayout(null);
			t.setBounds(20,0,150,20);
			panel3.add(t);
			
			
			
			//���ɵ�����
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("��������");
			DefaultMutableTreeNode studGrade = new DefaultMutableTreeNode("�ɼ���ѯ");
			DefaultMutableTreeNode teac = new DefaultMutableTreeNode("��ʦ������Ϣ");
			DefaultMutableTreeNode teacIfo = new DefaultMutableTreeNode("������Ϣ��ѯ");
			teac.add(teacIfo);
			DefaultMutableTreeNode stud = new DefaultMutableTreeNode("ѧ��������Ϣ");
			DefaultMutableTreeNode studIfo = new DefaultMutableTreeNode("������Ϣ��ѯ");
			stud.add(studIfo);
			stud.add(studGrade);
			DefaultMutableTreeNode grade = new DefaultMutableTreeNode("�ɼ�����");
			DefaultMutableTreeNode gradeStatics = new DefaultMutableTreeNode("�ɼ�ͳ��");
			DefaultMutableTreeNode gradeIn = new DefaultMutableTreeNode("�ɼ�¼��");
			DefaultMutableTreeNode gradeModify = new DefaultMutableTreeNode("�ɼ��޸�");
			DefaultMutableTreeNode pwdModify = new DefaultMutableTreeNode("�޸�����");
			grade.add(gradeModify);
			grade.add(gradeIn);
			grade.add(gradeStatics);
			grade.add(studGrade);
			stud.add(pwdModify);
			root.add(teac);
			root.add(stud);
			root.add(grade);
			JTree tree = new JTree(root);
			tree.setOpaque(false);
			
			tree.setBounds(0,0,180, 150);
			
			//��ȡѧ������
			Connection conn = DBConnection.getConnection();
			try {
				Statement  stmt = conn.createStatement();
				String sql = "select StuName from student where userName="+userName;
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				String name = rs.getString("StuName");
				uname.setText("��ӭ��,"+name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			navigator.setLayout(null);
			navigator.add(uname);
			navigator.add(exit);
			uname.setBounds(screenWidth-150, 0, 100, 20);
			exit.setBounds(screenWidth-50,0,40,20);
			exit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					frame.dispose();
					new LoginWindow();	
				}
			});
			
			
			//�����Ӧ
			tree.addTreeSelectionListener(new TreeSelectionListener() {

				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO Auto-generated method stub
					TreePath path = tree.getSelectionPath();
					if(path == null)
						return;
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
					if(selectedNode.equals(teacIfo)) {//ѡ�н�ʦ������Ϣ
							JOptionPane.showMessageDialog(null,"�ù�����δ����!");
					}
					if(selectedNode.equals(studIfo)) {//ѡ��ѧ��������Ϣ
						JPanel panel = new JPanel();
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						ResultSet rs = StudentDaoImpl.studentIfo(userName);
						try {
							while(rs.next()) {
								
								Font font = new Font("����", 10, 25);
								JLabel title = new JLabel("ѧ��������Ϣ");
								title.setFont(font);
								JLabel stuId = new JLabel("ѧ��               "+rs.getString("StuId"));
								JLabel stuName = new JLabel("����               "+rs.getString("StuName"));
								JLabel stuSex = new JLabel("�Ա�               "+rs.getString("StuSex"));
								JLabel stuBirth = new JLabel("����               "+rs.getString("Birthday"));
								JLabel stuPhone = new JLabel("��ϵ��ʽ           "+rs.getString("Phone"));
								JLabel stuAddress = new JLabel("��ͥ��ַ           "+rs.getString("Address"));
								JLabel stuClass = new JLabel("�༶               "+rs.getString("class_Name"));
								JLabel stuDepar = new JLabel("����Ժϵ           "+rs.getString("Depar_name"));
								
								title.setBounds(0, 0, 150, 60);
								stuId.setBounds(10,50,200,40);
								stuName.setBounds(10,80,200,40);
								stuSex.setBounds(10,110,200,40);
								stuBirth.setBounds(10,140,250,40);
								stuPhone.setBounds(10,170,200,40);
								stuAddress.setBounds(10,200,300,40);
								stuClass.setBounds(10,230,300,40);
								stuDepar.setBounds(10,260,200,40);
								
								panel.add(title);
								panel.add(stuId);
								panel.add(stuName);
								panel.add(stuSex);
								panel.add(stuBirth);
								panel.add(stuPhone);
								panel.add(stuAddress);
								panel.add(stuClass);
								panel.add(stuDepar);
								panel2.removeAll();
								panel.add(bg);
								panel2.add(panel);			
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if(selectedNode.equals(pwdModify)) {
						JFrame jf = new JFrame();
						jf.setTitle("�޸�����");
						jf.setBounds(width+200, height+100, 400, 300);
						JPanel pl = new JPanel();
						JLabel oldPwd = new JLabel("������:");
						JLabel newPwd = new JLabel("������:");
						JTextField oldPwdText = new JTextField(15);
						JTextField newPwdText = new JTextField(15);
						JButton confirm = new JButton("ȷ��");
						JButton reset = new JButton("����");
						pl.setLayout(null);
						oldPwd.setBounds(120,50,50,20);
						newPwd.setBounds(120,80,50,20);
						oldPwdText.setBounds(200,50,120,20);
						newPwdText.setBounds(200,80,120,20);
						confirm.setBounds(140,140,60,30);
						reset.setBounds(220,140,60,30);
						reset.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								// TODO Auto-generated method stub
								super.mouseClicked(e);
								oldPwdText.setText("");
								newPwdText.setText("");
							}
						});
						confirm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								// TODO Auto-generated method stub
								super.mouseClicked(e);
								try {
									Statement stmt = conn.createStatement();
									String sql = "select passWord from teacher where userName='"+userName+"'";
									ResultSet rs = stmt.executeQuery(sql);
									if(rs.next()) {
										if(!rs.getString("passWord").equals(oldPwdText.getText().trim().toString())) {
											JOptionPane.showMessageDialog(null, "���������!");
										}else {
											String sql2 = "update teacher set password='"+newPwdText.getText()+"' where userName='"+userName+"'";
											stmt.executeUpdate(sql2);
											JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
											jf.setVisible(false);
										}
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						
						pl.add(oldPwd);
						pl.add(newPwd);
						pl.add(oldPwdText);
						pl.add(newPwdText);
						pl.add(confirm);
						pl.add(reset);
						jf.add(pl);
						jf.setBackground(Color.WHITE);
						jf.setResizable(false);
						jf.setVisible(true);
						
					}
					
					
					if(selectedNode.equals(studGrade)) {//ѡ�гɼ���ѯ
						JPanel panel = new JPanel();
						
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						ResultSet rs = StudentDaoImpl.studentGrade(userName);
						DefaultTableModel model = new DefaultTableModel(){	//���õ�Ԫ�񲻿ɱ༭
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public boolean isCellEditable(int col, int row) 
							{
								return false;
							};
						
						};
						JTable table = new JTable();
						
						table.setModel(model);
						
						String[] columnNames = {"ѧ��","����","�γ�","�ɼ�"};
						String[] row = new String[4];
						for(int i = 0; i < 4;i++) {
							model.addColumn(columnNames[i]);
						}
						model.addRow(columnNames);
						try {
							while(rs.next()) {
								for(int i = 0; i < 4;i++) {
									row[i] = rs.getString(i+1);
								}
								model.addRow(row);
							}
							int avg = 0;
							for(int i = 1; i <= model.getRowCount()-1;i++) {
								avg += Integer.valueOf((String) model.getValueAt(i,3));
							}
							if(model.getRowCount()-1 != 0) {
								avg /= model.getRowCount()-1;
							}
							JLabel avgGrade = new JLabel("ƽ���ɼ�:"+avg);
							table.setBounds(70, 50, 400, 200);
							avgGrade.setBounds(100,300,200,30);
							table.updateUI();
							panel.removeAll();
							panel.add(avgGrade);
							panel.add(table);
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						panel2.removeAll();	
						panel.add(bg);
						panel2.add(panel);
					}
					
					
					if(selectedNode.equals(gradeStatics)) {  //�ɼ�ͳ�ƣ������ʣ���������
						JOptionPane.showMessageDialog(null,"Ȩ�޲���!");
					}
					
					//ѡ��ɼ�¼��
					if(selectedNode.equals(gradeIn)) {
						JOptionPane.showMessageDialog(null,"Ȩ�޲���!");
					}
					
					if(selectedNode.equals(gradeModify)) {//�ɼ��޸�
						JOptionPane.showMessageDialog(null,"Ȩ�޲���!");
					}
				}
			});
			
			
			//������
			panel.add(tree);
			frame.add(navigator);
			frame.add(panel);
			frame.add(panel2);
			panel2.add(bg);
			frame.add(panel3);
			
			//���ô���ɼ�l
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				Date ti = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				System.out.println(sdf.format(ti));
				t.setText(sdf.format(ti));
				}
		}
}
