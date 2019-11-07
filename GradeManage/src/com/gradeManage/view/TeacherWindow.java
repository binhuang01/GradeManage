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
			//设置观感
			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			SwingUtilities.updateComponentTreeUI(frame);
			
			
			//设置界面长和宽
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width*2/3+30;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
			//设置初始化界面位置
			int width = Toolkit.getDefaultToolkit().getScreenSize().width/6;
			int height = Toolkit.getDefaultToolkit().getScreenSize().height/6;
			frame.setBounds(width,height,screenWidth,screenHeight);
			frame.setTitle("教师平台");
			frame.setLayout(null);
			
			
			//新建面板
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
			JLabel ifo = new JLabel("资料下载");
			
			//在面板3添加日历
			LocalDate date = LocalDate.now();//获取当前时间
			int month = date.getMonthValue();//获取当前月份
			int today = date.getDayOfMonth();//获取当月天数
			date = date.minusDays(today-1);
			DayOfWeek weekday = date.getDayOfWeek();//利用dayofweek对象的getValue方法获取星期几
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
			Font font = new Font("黑体",Font.BOLD,20);
			ifo.setFont(font);
			ifo.setBounds(60, 160,100,50);
			panel3.add(ifo);
			
			//面板3添加文件列表
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
							int choose = JOptionPane.showConfirmDialog(null,"下载"+file.getText()+"?");
							if(JOptionPane.YES_OPTION == choose) {
								String f = FileDaoImpl.findFile(file.getText());
								JFileChooser fileChooser = new JFileChooser();
								fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								fileChooser.showSaveDialog(new JLabel("保存"));
								File sFile = fileChooser.getSelectedFile();
								File saveFile = new File(sFile.getName()+file.getText());
								try {
									FileOutputStream fos = new FileOutputStream(saveFile);
									Client client = new Client();
									try {
										client.getDos().writeUTF("下载");
										client.getDos().writeUTF(f);
										byte[] buffer = new byte[1024*5];
										int len = 0;
										while(( len = client.getDis().read(buffer,0,buffer.length))!=-1) {
											fos.write(buffer,0,len);
											System.out.println("%");
											fos.flush();
										}
										JOptionPane.showMessageDialog(null,"下载成功");
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
			
			
			
			//新建标签
			JLabel uname = new JLabel();
			JLabel exit = new JLabel("退出");
			
			
			
			//生成导航树
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("导航管理");
			DefaultMutableTreeNode studGrade = new DefaultMutableTreeNode("成绩查询");
			DefaultMutableTreeNode teac = new DefaultMutableTreeNode("教师个人信息");
			DefaultMutableTreeNode teacIfo = new DefaultMutableTreeNode("个人信息查询");
			teac.add(teacIfo);
			DefaultMutableTreeNode stud = new DefaultMutableTreeNode("学生个人信息");
			DefaultMutableTreeNode studIfo = new DefaultMutableTreeNode("个人信息查询");
			stud.add(studIfo);
			stud.add(studGrade);
			DefaultMutableTreeNode grade = new DefaultMutableTreeNode("成绩管理");
			DefaultMutableTreeNode gradeStatics = new DefaultMutableTreeNode("成绩统计");
			DefaultMutableTreeNode gradeIn = new DefaultMutableTreeNode("成绩录入");
			DefaultMutableTreeNode gradeModify = new DefaultMutableTreeNode("成绩修改");
			DefaultMutableTreeNode pwdModify = new DefaultMutableTreeNode("修改密码");
			grade.add(gradeModify);
			grade.add(gradeIn);
			grade.add(gradeStatics);
			grade.add(studGrade);
			teac.add(pwdModify);
			root.add(teac);
			root.add(stud);
			root.add(grade);
			JTree tree = new JTree(root);
			
			//获取教师姓名
			Connection conn = DBConnection.getConnection();
			try {
				Statement  stmt = conn.createStatement();
				String sql = "select TeacName from teacher where userName="+userName;
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				String name = rs.getString("TeacName");
				uname.setText("欢迎您,"+name);
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
			
			//添加响应
			tree.addTreeSelectionListener(new TreeSelectionListener() {

				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO Auto-generated method stub
					TreePath path = tree.getSelectionPath();
					if(path == null)
						return;
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
					if(selectedNode.equals(teacIfo)) {//选中个人信息
							JPanel panel = new JPanel();
							panel.setLayout(null);
							panel.setBackground(Color.WHITE);
							ResultSet rs = TeacherDaoImpl.teacherIfo(userName);
							try {
								while(rs.next()) {
									
									Font font = new Font("楷体", 10, 25);
									JLabel title = new JLabel("教师个人信息");
									title.setFont(font);
									JLabel teacId = new JLabel("职工号          "+rs.getString("teacId"));
									JLabel teacName = new JLabel("姓名            "+rs.getString("teacName"));
									JLabel teacSex = new JLabel("性别            "+rs.getString("teacSex"));
									JLabel teacBirth = new JLabel("生日            "+rs.getString("Birthday"));
									JLabel teacPost = new JLabel("职称            "+rs.getString("techPost"));
									JLabel teacDepar = new JLabel("所属院系         "+rs.getString("depar_id"));
									
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
					
					if(selectedNode.equals(pwdModify)) {//修改密码
						JFrame jf = new JFrame();
						jf.setTitle("修改密码");
						jf.setBounds(width+200, height+100, 400, 300);
						JPanel pl = new JPanel();
						JLabel oldPwd = new JLabel("旧密码:");
						JLabel newPwd = new JLabel("新密码:");
						JTextField oldPwdText = new JTextField(15);
						JTextField newPwdText = new JTextField(15);
						JButton confirm = new JButton("确定");
						JButton reset = new JButton("重置");
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
											JOptionPane.showMessageDialog(null, "旧密码错误!");
										}else {
											String sql2 = "update teacher set password='"+newPwdText.getText()+"' where userName='"+userName+"'";
											stmt.executeUpdate(sql2);
											JOptionPane.showMessageDialog(null, "修改成功!");
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
					
					
					if(selectedNode.equals(studGrade)) {//选中成绩查询
						JPanel panel = new JPanel();
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						JLabel classes = new JLabel("班级");
						JLabel courses = new JLabel("课程");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton confirm = new JButton("查询");
						JButton high = new JButton("最高分");
						JButton low = new JButton("最低分");
						
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
									String[] columnNames = {"学号","姓名","性别","班级","课程","成绩"};
									DefaultTableModel model = new DefaultTableModel(){	//设置单元格不可编辑
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
									//设置表格透明
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
										
										high.addMouseListener(new MouseAdapter() {//查询最高分
											@Override
											public void mouseClicked(java.awt.event.MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												String[] highIfo = hGrade.split(",");
												JLabel ifo = new JLabel("学号:"+highIfo[0]+" "+"姓名:"+highIfo[1]+" "+"性别:"+highIfo[2]+" "+"班级:"+highIfo[3]+" "+"课程:"+highIfo[4]+" "+"成绩:"+highIfo[5]+" ");
												JOptionPane.showMessageDialog(null,ifo.getText());
											}
										});
										
										low.addMouseListener(new MouseAdapter() {//查询最低分
											@Override
											public void mouseClicked(java.awt.event.MouseEvent e) {
												// TODO Auto-generated method stub
												super.mouseClicked(e);
												String[] lowIfo = lGrade.split(",");
												JLabel ifo = new JLabel("学号:"+lowIfo[0]+" "+"姓名:"+lowIfo[1]+" "+"性别:"+lowIfo[2]+" "+"班级:"+lowIfo[3]+" "+"课程:"+lowIfo[4]+" "+"成绩:"+lowIfo[5]+" ");
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
					
					
					if(selectedNode.equals(gradeStatics)) {  //成绩统计，优秀率，不及格率
						//新建面板。标签
						JPanel panel = new JPanel();
						JLabel clazz1 = new JLabel("班级");
						JLabel course1 = new JLabel("课程");
						JLabel select1 = new JLabel("选项");
						JComboBox<String> selectItem = new JComboBox<String>();
						JButton find = new JButton("查询");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JComboBox<String> selectCourse = new JComboBox<String>();
						
						//设置面板
						panel.setBackground(Color.WHITE);
						panel.setLayout(null);
						
						//设置位置
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
						
						//添加选项
						selectItem.addItem("优秀率");
						selectItem.addItem("及格率");
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
									String[] columnNames = {"学号","姓名","性别","班级","课程","成绩"};
									DefaultTableModel model = new DefaultTableModel(){	//设置单元格不可编辑
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
									//设置表格透明
									table.setOpaque(false);
									DefaultTableCellRenderer render = new DefaultTableCellRenderer();
									render.setOpaque(false);
									table.setDefaultRenderer(Object.class, render);
									try {
										Statement stmt = conn.createStatement();
										String sql = "";
										if(select.equals("优秀率")) {
										 sql = "select stuId,StuName,StuSex,class_name,course_name,Grade " + 
													"from student,class,studentgrade,course " + 
													"where student.Class_id=class.Class_id  and class_name='"+clazz+"' and course.Course_id=studentgrade.Course_id and course_Name ='"+course+"' and student.StuId = studentgrade.Stu_id and grade >= 90";
										}
										if(select.equals("及格率")) {
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
										if(select.equals("优秀率")) {
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
											JLabel execllent = new JLabel("优秀率:"+n/m*100+"%");
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
											JLabel good = new JLabel("及格率:"+n/m*100+"%");
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
					
					//选择成绩录入
					if(selectedNode.equals(gradeIn)) {
						JPanel panel = new JPanel();
						JLabel clazz = new JLabel("班级");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JLabel courses = new JLabel("课程");
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton output = new JButton("导出名单");
						JButton update = new JButton("录入");
						JButton reset = new JButton("重置");
						
						//设置布局
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						
						//设置标签和选项框位置
						clazz.setBounds(20, 20, 30, 20);
						selectClazz.setBounds(60, 20, 100, 20);
						courses.setBounds(170, 20, 30, 20);
						selectCourse.setBounds(200,20,100,20);
						output.setBounds(320,20,100,20);
						reset.setBounds(180,400,100,20);
						update.setBounds(320,400,100,20);
						
						//添加到面板
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
							output.addMouseListener(new MouseAdapter() { //导出学生名单
								 @Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									String classes =selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String[] columnNames = {"学号","姓名","性别","班级","课程","成绩"};
									DefaultTableModel model = new DefaultTableModel();
									JTable table = new JTable();
									table.setModel(model);
									//设置table透明
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
										
										
										update.addMouseListener(new MouseAdapter() {//录入学生成绩
											
											
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
												JOptionPane.showMessageDialog(null, "成绩录入成功");
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
					
					if(selectedNode.equals(gradeModify)) {//选择成绩修改
						JPanel panel = new JPanel();
						JLabel clazz = new JLabel("班级");
						JComboBox<String> selectClazz = new JComboBox<String>();
						JLabel courses = new JLabel("课程");
						JComboBox<String> selectCourse = new JComboBox<String>();
						JButton output = new JButton("导出名单");
						JButton update = new JButton("修改");
						
						
						//设置布局
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						
						//设置标签和选项框位置
						clazz.setBounds(20, 20, 30, 20);
						selectClazz.setBounds(60, 20, 100, 20);
						courses.setBounds(170, 20, 30, 20);
						selectCourse.setBounds(200,20,100,20);
						output.setBounds(320,20,100,20);
						update.setBounds(220,400,100,20);
						
						//添加到面板
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
							output.addMouseListener(new MouseAdapter() { //导出学生名单
								 @Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									super.mouseClicked(e);
									String classes =selectClazz.getSelectedItem().toString();
									String course = selectCourse.getSelectedItem().toString();
									String[] columnNames = {"学号","姓名","性别","班级","课程","成绩"};
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
										
										update.addMouseListener(new MouseAdapter() {//录入学生成绩
											
											
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
												javax.swing.JOptionPane.showMessageDialog(null, "修改成功!");
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
			
		
			//添加组件
			panel.add(tree);
			frame.add(navigator);
			frame.add(panel);
			frame.add(panel2);
			panel2.add(bg);
			frame.add(panel3);
			
			//设置窗体可见l
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
			//文件上传
			JButton upload = new JButton("上传文件");
			upload.setBounds(50, 400, 100, 30);
			panel3.add(upload);
			upload.addActionListener(new java.awt.event.ActionListener() {
				private FileInputStream fis;

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();//文件选择器
						jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
						jfc.showDialog(new JLabel(),"上传文件");
						File file = jfc.getSelectedFile();
						fis = new FileInputStream(file);
						byte[] buffer = new byte[1024*5];
						int len = 0;
						Client client = new Client();
						client.getDos().writeUTF("上传");
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
