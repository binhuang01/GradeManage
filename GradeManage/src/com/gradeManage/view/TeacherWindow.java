package com.gradeManage.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
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
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.gradeManage.controller.DBConnection;
import com.gradeManage.controller.FileDaoImpl;
import com.gradeManage.controller.TeacherDaoImpl;
import com.gradeManage.entities.Client;


public class TeacherWindow implements Runnable{
	
		private JFrame frame;
		private String hGrade;
		private String lGrade;
		private JLabel t = new JLabel();
		
		public TeacherWindow(String userName, String passWord) {
			
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
			frame.setTitle("��ʦƽ̨");
			frame.setLayout(null);
			
			
			//�½����
			JMenuBar navigator = new JMenuBar();
			navigator.setBackground(Color.WHITE);
			navigator.setBounds(0, 0, screenWidth, 20);
			JPanel panel = new JPanel();
			panel.setBounds(0,22,180,screenHeight);
			panel.setBackground(Color.WHITE);
			JPanel panel2 = new JPanel();
			panel2.setLayout(new java.awt.CardLayout());
			panel2.setBounds(185,22,530,screenHeight);
			panel2.setBackground(Color.WHITE);
			JPanel panel3 = new JPanel();
			panel3.setBounds(720,22,370,screenHeight);
			panel3.setBackground(Color.WHITE);
			panel3.setLayout(null);
			JLabel bg = new JLabel(new ImageIcon("bg1.jpg"));
			bg.setBounds(185,22,530,screenHeight);
			JLabel time = new JLabel();
			JLabel ifo = new JLabel("��������");
			
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
			panel.add(time);
			Font font = new Font("����",Font.BOLD,20);
			ifo.setFont(font);
			ifo.setBounds(60, 160,100,50);
			panel3.add(ifo);
			
			//���3����ļ��б�
			JPanel panel4 = new JPanel();
//			panel4.setLayout(new BorderLayout());
			ResultSet rs2 = FileDaoImpl.getFile();
			try {
				while(rs2.next()) {
					JLabel file = new JLabel(rs2.getString("file_Names")+"     ");
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
										e1.printStackTrace();
									}
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					});
					panel4.add(file,BorderLayout.NORTH);
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
			teac.add(pwdModify);
			root.add(teac);
			root.add(stud);
			root.add(grade);
			JTree tree = new JTree(root);
			
			//��ȡ��ʦ����
			Connection conn = DBConnection.getConnection();
			try {
				Statement  stmt = conn.createStatement();
				String sql = "select TeacName from teacher where userName="+userName;
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				String name = rs.getString("TeacName");
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
			t.setBounds(20,0,150,20);
			panel3.add(t);
			
			//�����Ӧ
			tree.addTreeSelectionListener(new TreeSelectionListener() {

				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO Auto-generated method stub
					TreePath path = tree.getSelectionPath();
					if(path == null)
						return;
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
					if(selectedNode.equals(teacIfo)) {//ѡ�и�����Ϣ
							JPanel panel = new JPanel();
							panel.setLayout(null);
							panel.setBackground(Color.WHITE);
							ResultSet rs = TeacherDaoImpl.teacherIfo(userName);
							try {
								while(rs.next()) {
									
									Font font = new Font("����", 10, 25);
									JLabel title = new JLabel("��ʦ������Ϣ");
									title.setFont(font);
									JLabel teacId = new JLabel("ְ����          "+rs.getString("teacId"));
									JLabel teacName = new JLabel("����            "+rs.getString("teacName"));
									JLabel teacSex = new JLabel("�Ա�            "+rs.getString("teacSex"));
									JLabel teacBirth = new JLabel("����            "+rs.getString("Birthday"));
									JLabel teacPost = new JLabel("ְ��            "+rs.getString("techPost"));
									JLabel teacDepar = new JLabel("����Ժϵ         "+rs.getString("depar_id"));
									
									title.setBounds(0, 0, 150, 60);
									teacId.setBounds(10,50,200,40);
									teacName.setBounds(10,80,200,40);
									teacSex.setBounds(10,110,200,40);
									teacBirth.setBounds(10,140,250,40);
									teacPost.setBounds(10,170,200,40);
									teacDepar.setBounds(10,200,200,40);
									
									panel.add(title);
									panel.add(teacId);
									panel.add(teacName);
									panel.add(teacSex);
									panel.add(teacBirth);
									panel.add(teacPost);
									panel.add(teacDepar);
									panel.add(bg);
									panel2.removeAll();
									panel2.add(panel);			
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					
					if(selectedNode.equals(pwdModify)) {//�޸�����
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
						JLabel classes = new JLabel("�༶");
						JLabel courses = new JLabel("�γ�");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton confirm = new JButton("��ѯ");
						JButton high = new JButton("��߷�");
						JButton low = new JButton("��ͷ�");
						
						classes.setBounds(80, 20, 50, 20);
						selectClazz.setBounds(110, 20, 120, 20);
						courses.setBounds(240, 20, 50, 20);
						selectCourse.setBounds(270, 20, 80, 20);
						confirm.setBounds(370, 20, 70, 20);
						high.setBounds(180, 350,70,20);
						low.setBounds(300,350,70,20);
						panel.add(classes);
						panel.add(selectClazz);
						panel.add(courses);
						panel.add(selectCourse);
						panel.add(confirm);
						panel.add(bg);
						
						try {
							Statement stmt = conn.createStatement();
							String sql = "select Class_name from class where Director="+userName;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								selectClazz.addItem(rs.getString("CLass_Name"));
							}
							String sql2 = "select Course_name from course where Course_id in"+"(select Course_id from courseteacher where Class_id="+
									"(select Class_id from class where class_name="+"'"+(String)selectClazz.getSelectedItem()+"'"+"))";
							ResultSet rs2 = stmt.executeQuery(sql2);
							while(rs2.next()) {
								selectCourse.addItem(rs2.getString("Course_Name"));
							}
							
							confirm.addMouseListener((new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									
									String classes =selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String[] columnNames = {"ѧ��","����","�Ա�","�༶","�γ�","�ɼ�"};
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
									//���ñ��͸��
									table.setOpaque(false);
									DefaultTableCellRenderer render = new DefaultTableCellRenderer();
									render.setOpaque(false);
									table.setDefaultRenderer(Object.class, render);
									
					
									int tmp = 0;
									int tmp2 = 100;
									try {
										Statement stmt = conn.createStatement();
										String sql = "select stuId,StuName,StuSex,class_name,course_name,Grade "+
												"from student inner JOIN class on student.Class_id=class.Class_id and class_name='"+classes+"' INNER JOIN course on course_Name ='"+course+"' LEFT JOIN studentgrade on  studentgrade.Stu_id=student.StuId and studentgrade.Course_id = course.Course_id";
										ResultSet rs = stmt.executeQuery(sql);
										String[] row = new String[6];
										if(rs.isBeforeFirst()==rs.isAfterLast()) {
//											
										}else {
										for(int i = 0; i < 6;i++) {
											model.addColumn(columnNames[i]);
										}
										model.addRow(columnNames);
							
										while(rs.next()) {
											for(int i = 0; i < 6;i++) {
												row[i] = rs.getString(i+1);
											}
											if(row[5]!=null) {
												if(Integer.valueOf(row[5]) > tmp) {
													tmp = Integer.valueOf(row[5]);
													StringBuilder highGrade = new StringBuilder();
													for(int i = 0; i < 6;i++)
														highGrade.append(row[i]+",");
													hGrade = highGrade.toString();
												}
												if(Integer.valueOf(row[5]) < tmp2) {
													tmp2 = Integer.valueOf(row[5]);
													StringBuilder lowGrade = new StringBuilder();
													for(int i = 0; i < 6;i++)
														lowGrade.append(row[i]+",");
													lGrade = lowGrade.toString();
												}
											}
											model.addRow(row);
										}
										
										high.addMouseListener(new MouseAdapter() {//��ѯ��߷�
											@Override
											public void mouseClicked(java.awt.event.MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												String[] highIfo = hGrade.split(",");
												JLabel ifo = new JLabel("ѧ��:"+highIfo[0]+" "+"����:"+highIfo[1]+" "+"�Ա�:"+highIfo[2]+" "+"�༶:"+highIfo[3]+" "+"�γ�:"+highIfo[4]+" "+"�ɼ�:"+highIfo[5]+" ");
												JOptionPane.showMessageDialog(null,ifo.getText());
											}
										});
										
										low.addMouseListener(new MouseAdapter() {//��ѯ��ͷ�
											@Override
											public void mouseClicked(java.awt.event.MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												String[] lowIfo = lGrade.split(",");
												JLabel ifo = new JLabel("ѧ��:"+lowIfo[0]+" "+"����:"+lowIfo[1]+" "+"�Ա�:"+lowIfo[2]+" "+"�༶:"+lowIfo[3]+" "+"�γ�:"+lowIfo[4]+" "+"�ɼ�:"+lowIfo[5]+" ");
												JOptionPane.showMessageDialog(null,ifo.getText());
											}
										});
										
										rs.close();
										table.setBounds(20,50, 500, 300);
										table.repaint();
										table.updateUI();
										panel.removeAll();
//										panel.add(classes);
										panel.add(selectClazz);
										panel.add(courses);
										panel.add(selectCourse);
										panel.add(confirm);
										panel.add(high);
										panel.add(low);
										panel.add(table);
										panel.add(bg);
										panel.updateUI();
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							}));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						panel2.removeAll();	
						panel2.add(panel);
					}
					
					
					if(selectedNode.equals(gradeStatics)) {  //�ɼ�ͳ�ƣ������ʣ���������
						//�½���塣��ǩ
						JPanel panel = new JPanel();
						JLabel clazz1 = new JLabel("�༶");
						JLabel course1 = new JLabel("�γ�");
						JLabel select1 = new JLabel("ѡ��");
						JComboBox<String> selectItem = new JComboBox<String>();
						JButton find = new JButton("��ѯ");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JComboBox<String> selectCourse = new JComboBox<String>();
						
						//�������
						panel.setBackground(Color.WHITE);
						panel.setLayout(null);
						
						//����λ��
						clazz1.setBounds(20, 20, 30, 20);
						selectClazz.setBounds(60, 20, 90, 20);
						course1.setBounds(160, 20, 30, 20);
						selectCourse.setBounds(200,20,80,20);
						select1.setBounds(290,20,30,20);
						selectItem.setBounds(330, 20, 60, 20);
						find.setBounds(400, 20, 80, 20);
						
						panel.add(clazz1);
						panel.add(selectClazz);
						panel.add(course1);
						panel.add(selectCourse);
						panel.add(select1);
						panel.add(selectItem);
						panel.add(find);
						panel.add(bg);
						
						//���ѡ��
						selectItem.addItem("������");
						selectItem.addItem("������");
						Statement stmt;
						try {
							stmt = conn.createStatement();
							String sql = "select Class_name from class where Director="+userName;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								selectClazz.addItem(rs.getString("CLass_Name"));
							}
							String sql2 = "select Course_name from course where Course_id in"+"(select Course_id from courseteacher where Class_id="+
									"(select Class_id from class where class_name="+"'"+(String)selectClazz.getSelectedItem()+"'"+"))";
							ResultSet rs2 = stmt.executeQuery(sql2);
							while(rs2.next()) {
								selectCourse.addItem(rs2.getString("Course_Name"));
							}
							find.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									String clazz = selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String select = selectItem.getSelectedItem().toString();
									String[] columnNames = {"ѧ��","����","�Ա�","�༶","�γ�","�ɼ�"};
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
									//���ñ��͸��
									table.setOpaque(false);
									DefaultTableCellRenderer render = new DefaultTableCellRenderer();
									render.setOpaque(false);
									table.setDefaultRenderer(Object.class, render);
									try {
										Statement stmt = conn.createStatement();
										String sql = "";
										if(select.equals("������")) {
										 sql = "select stuId,StuName,StuSex,class_name,course_name,Grade " + 
													"from student,class,studentgrade,course " + 
													"where student.Class_id=class.Class_id  and class_name='"+clazz+"' and course.Course_id=studentgrade.Course_id and course_Name ='"+course+"' and student.StuId = studentgrade.Stu_id and grade >= 90";
										}
										if(select.equals("������")) {
											 sql = "select stuId,StuName,StuSex,class_name,course_name,Grade " + 
														"from student,class,studentgrade,course " + 
														"where student.Class_id=class.Class_id  and class_name='"+clazz+"' and course.Course_id=studentgrade.Course_id and course_Name ='"+course+"' and student.StuId = studentgrade.Stu_id and grade >= 60";
										}
										ResultSet rs = stmt.executeQuery(sql);
										String[] row = new String[6];
										for(int i = 0; i < 6;i++) {
											model.addColumn(columnNames[i]);
										}
										model.addRow(columnNames);
										while(rs.next()) {
											for(int i = 0; i < 6;i++) {
												row[i] = rs.getString(i+1);
											}
											model.addRow(row);
										}
										rs.close();
										table.setBounds(20,50, 500, 300);
										panel.removeAll();
										if(select.equals("������")) {
											String sql2 = "select count(stuId) from student,class where student.Class_id=class.Class_id and Class_Name='"+clazz+"'";
											ResultSet rs2 = stmt.executeQuery(sql2);
											double m = 0;
											if(rs2.next()) {
												m = Integer.valueOf(rs2.getString(1));
//												System.out.println(rs2.getString(1));
											}else {
												m = 1;
											}
											double n = table.getRowCount()-1;
//											System.out.println(n);
											JLabel execllent = new JLabel("������:"+n/m*100+"%");
											execllent.setBounds(100, 400, 100, 20);
											panel.add(execllent);
										}else {
											String sql2 = "select count(stuId) from student,class where student.Class_id=class.Class_id and Class_Name='"+clazz+"'";
											ResultSet rs2 = stmt.executeQuery(sql2);
											double m = 0;
											if(rs2.next()) {
												m = Integer.valueOf(rs2.getString(1));
//												System.out.println(rs2.getString(1));
											}else {
												m = 1;
											}
											double n = table.getRowCount()-1;
											JLabel good = new JLabel("������:"+n/m*100+"%");
											good.setBounds(100, 400, 100, 20);
											panel.add(good);
										}
										panel.add(clazz1);
										panel.add(selectClazz);
										panel.add(course1);
										panel.add(selectCourse);
										panel.add(select1);
										panel.add(selectItem);
										panel.add(find);
										panel.add(table);
										panel.add(bg);
										panel.updateUI();
										panel2.removeAll();
										panel2.add(panel);
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
								}
							});
						}catch(SQLException e1){
							e1.printStackTrace();
						}
						panel2.removeAll();
						panel2.add(panel);
					}
					
					//ѡ��ɼ�¼��
					if(selectedNode.equals(gradeIn)) {
						JPanel panel = new JPanel();
						JLabel clazz = new JLabel("�༶");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JLabel courses = new JLabel("�γ�");
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton output = new JButton("��������");
						JButton update = new JButton("¼��");
						JButton reset = new JButton("����");
						
						//���ò���
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						
						//���ñ�ǩ��ѡ���λ��
						clazz.setBounds(20, 20, 30, 20);
						selectClazz.setBounds(60, 20, 100, 20);
						courses.setBounds(170, 20, 30, 20);
						selectCourse.setBounds(200,20,100,20);
						output.setBounds(320,20,100,20);
						reset.setBounds(180,400,100,20);
						update.setBounds(320,400,100,20);
						
						//��ӵ����
						panel.add(clazz);
						panel.add(selectClazz);
						panel.add(courses);
						panel.add(selectCourse);
						panel.add(output);
						panel.add(bg);
						
						try {
							Statement stmt = conn.createStatement();
							String sql = "select Class_name from class where Director="+userName;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								selectClazz.addItem(rs.getString("CLass_Name"));
							}
							String sql2 = "select Course_name from course where Course_id in"+"(select Course_id from courseteacher where Class_id="+
									"(select Class_id from class where class_name="+"'"+(String)selectClazz.getSelectedItem()+"'"+"))";
							ResultSet rs2 = stmt.executeQuery(sql2);
							while(rs2.next()) {
								selectCourse.addItem(rs2.getString("Course_Name"));
							}
							output.addMouseListener(new MouseAdapter() { //����ѧ������
								 @Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									String classes =selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String[] columnNames = {"ѧ��","����","�Ա�","�༶","�γ�","�ɼ�"};
									DefaultTableModel model = new DefaultTableModel();
									JTable table = new JTable();
									table.setModel(model);
									//����table͸��
									table.setOpaque(false);
									DefaultTableCellRenderer render = new DefaultTableCellRenderer();
									render.setOpaque(false);
									table.setDefaultRenderer(Object.class, render);
									
									try {
										Statement stmt = conn.createStatement();
										String sql = "select stuId,StuName,StuSex,class_name,course_name,Grade "+
												"from student inner JOIN class on student.Class_id=class.Class_id and class_name='"+classes+"' INNER JOIN course on course_Name ='"+course+"' LEFT JOIN studentgrade on  studentgrade.Stu_id=student.StuId and studentgrade.Course_id = course.Course_id";
										ResultSet rs = stmt.executeQuery(sql);
										String[] row = new String[6];
										for(int i = 0; i < 6;i++) {
											model.addColumn(columnNames[i]);
										}
										model.addRow(columnNames);
										while(rs.next()) {
											for(int i = 0; i < 6;i++) {
												row[i] = rs.getString(i+1);
											}
											model.addRow(row);
										}
										
										
										update.addMouseListener(new MouseAdapter() {//¼��ѧ���ɼ�
											
											
											@Override
											public void mouseClicked(MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												
												for(int i = 1; i <= table.getRowCount()-1;i++) {
													String stuId = (String)table.getValueAt(i,0);
//													System.out.println(stuId);
													String grade = (String)table.getValueAt(i,5);
//													System.out.println(grade);
													String courseName = (String)table.getValueAt(i,4);
													if(grade != null && !grade.equals("")) {
														String sql = "select course_id from course where Course_name='"+courseName+"'";
														try {
															ResultSet rs = stmt.executeQuery(sql);
															String course_id = "";
															if(rs.next()) {
															course_id = rs.getString(1);
//															System.out.println(course_id);
															}
															String sql3 = "select * from studentgrade where stu_id='"+stuId+"' and course_id='"+course_id+"'";
															rs = stmt.executeQuery(sql3);
															if(rs.next()) {
																String sql4 = "update studentgrade set grade = '"+grade+"' where stu_id='"+stuId+"' and course_id='"+course_id+"'";
																stmt.executeUpdate(sql4);
															}else{
																String sql2 = "insert into studentgrade values('"+stuId+"','"+course_id+"','"+grade+"')";
																stmt.executeUpdate(sql2);
															}
														} catch (SQLException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
													}else {
														continue;
													}
												}
												JOptionPane.showMessageDialog(null, "�ɼ�¼��ɹ�");
											}
											
										});
										
										reset.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												for(int i = 1;i <= model.getRowCount()-1;i++) {
													model.setValueAt("",i,5);
												}
											}
										});
										
										rs.close();
										table.setBounds(20,50, 500, 300);
										table.repaint();
										table.updateUI();
										panel.removeAll();
										panel.add(clazz);
										panel.add(selectClazz);
										panel.add(courses);
										panel.add(selectCourse);
										panel.add(output);
										panel.add(table);
										panel.add(reset);
										panel.add(update);
										panel.add(bg);
										panel.updateUI();
										panel2.removeAll();
										panel2.add(panel);
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}
									
								}
							});
						}catch(SQLException e1) {
										e1.printStackTrace();
									}
									panel2.removeAll();
									panel2.add(panel);
					}
					
					if(selectedNode.equals(gradeModify)) {//ѡ��ɼ��޸�
						JPanel panel = new JPanel();
						JLabel clazz = new JLabel("�༶");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JLabel courses = new JLabel("�γ�");
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton output = new JButton("��������");
						JButton update = new JButton("�޸�");
						
						
						//���ò���
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						
						//���ñ�ǩ��ѡ���λ��
						clazz.setBounds(20, 20, 30, 20);
						selectClazz.setBounds(60, 20, 100, 20);
						courses.setBounds(170, 20, 30, 20);
						selectCourse.setBounds(200,20,100,20);
						output.setBounds(320,20,100,20);
						update.setBounds(220,400,100,20);
						
						//��ӵ����
						panel.add(clazz);
						panel.add(selectClazz);
						panel.add(courses);
						panel.add(selectCourse);
						panel.add(output);
						panel.add(update);
						panel.add(bg);
						
						try {
							Statement stmt = conn.createStatement();
							String sql = "select Class_name from class where Director="+userName;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								selectClazz.addItem(rs.getString("CLass_Name"));
							}
							String sql2 = "select Course_name from course where Course_id in"+"(select Course_id from courseteacher where Class_id="+
									"(select Class_id from class where class_name="+"'"+(String)selectClazz.getSelectedItem()+"'"+"))";
							ResultSet rs2 = stmt.executeQuery(sql2);
							while(rs2.next()) {
								selectCourse.addItem(rs2.getString("Course_Name"));
							}
							output.addMouseListener(new MouseAdapter() { //����ѧ������
								 @Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									String classes =selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String[] columnNames = {"ѧ��","����","�Ա�","�༶","�γ�","�ɼ�"};
									DefaultTableModel model = new DefaultTableModel();
									JTable table = new JTable();
									table.setModel(model);
									table.setOpaque(false);
									DefaultTableCellRenderer render = new DefaultTableCellRenderer();
									render.setOpaque(false);
									table.setDefaultRenderer(Object.class, render);
									
									try {
										Statement stmt = conn.createStatement();
										String sql = "select stuId,StuName,StuSex,class_name,course_name,Grade "+
												"from student inner JOIN class on student.Class_id=class.Class_id and class_name='"+classes+"' INNER JOIN course on course_Name ='"+course+"' LEFT JOIN studentgrade on  studentgrade.Stu_id=student.StuId and studentgrade.Course_id = course.Course_id";
										ResultSet rs = stmt.executeQuery(sql);
										String[] row = new String[6];
										for(int i = 0; i < 6;i++) {
											model.addColumn(columnNames[i]);
										}
										model.addRow(columnNames);
										while(rs.next()) {
											for(int i = 0; i < 6;i++) {
												row[i] = rs.getString(i+1);
											}
											model.addRow(row);
										}
										panel.add(update);
										
										update.addMouseListener(new MouseAdapter() {//¼��ѧ���ɼ�
											
											
											@Override
											public void mouseClicked(MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												
												for(int i = 1; i <= table.getRowCount()-1;i++) {
													String stuId = (String)table.getValueAt(i,0);
													String grade = (String)table.getValueAt(i,5);
//													System.out.println(grade);
													String courseName = (String)table.getValueAt(i,4);
													if(grade != null && !grade.equals("")) {
														String sql = "select course_id from course where Course_name='"+courseName+"'";
														try {
															ResultSet rs = stmt.executeQuery(sql);
															String course_id = "";
															if(rs.next()) {
															course_id = rs.getString(1);
//															System.out.println(course_id);
															}
															String sql3 = "select * from studentgrade where stu_id='"+stuId+"' and course_id='"+course_id+"'";
															rs = stmt.executeQuery(sql3);
															if(rs.next()) {
																String sql4 = "update studentgrade set grade = '"+grade+"' where stu_id='"+stuId+"' and course_id='"+course_id+"'";
																stmt.executeUpdate(sql4);
															}else{
																String sql2 = "insert into studentgrade values('"+stuId+"','"+course_id+"','"+grade+"')";
																stmt.executeUpdate(sql2);
															}
														} catch (SQLException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
													}else {
														continue;
													}
												}
												javax.swing.JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
											}
											
										});
										
										
										
										rs.close();
										table.setBounds(20,50, 500, 300);
										
										table.repaint();
										table.updateUI();
										panel.removeAll();
										table.setOpaque(false);
										panel.add(clazz);
										panel.add(selectClazz);
										panel.add(courses);
										panel.add(selectCourse);
										panel.add(output);
										panel.add(update);
										panel.add(table);
										panel.add(bg);
										panel.add(update);
										
										panel.updateUI();
										panel2.removeAll();
										panel2.add(panel);
										
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}
									
								}
							});
						}catch(SQLException e1) {
										e1.printStackTrace();
									}
									panel2.removeAll();
									panel2.add(panel);
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
			
			//�ļ��ϴ�
			JButton upload = new JButton("�ϴ��ļ�");
			upload.setBounds(50, 400, 100, 30);
			panel3.add(upload);
			upload.addActionListener(new java.awt.event.ActionListener() {
				private FileInputStream fis;

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();//�ļ�ѡ����
						jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
						jfc.showDialog(new JLabel(),"�ϴ��ļ�");
						File file = jfc.getSelectedFile();
						fis = new FileInputStream(file);
						byte[] buffer = new byte[1024*5];
						int len = 0;
						Client client = new Client();
						client.getDos().writeUTF("�ϴ�");
						client.getDos().writeUTF(file.getName());
						while((len = fis.read(buffer,0,buffer.length))!=-1) {
							client.getDos().write(buffer,0,len);
							client.getDos().flush();
						}
						fis.close();
						client.getClient().shutdownOutput();
						JOptionPane.showMessageDialog(null,client.getDis().readUTF());
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
			Date ti = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(sdf.format(ti));
			t.setText(sdf.format(ti));
			}
		}
}
