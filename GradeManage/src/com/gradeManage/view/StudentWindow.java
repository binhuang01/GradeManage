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
			frame.setTitle("学生平台");
			frame.setLayout(null);
			
			
			//新建面板
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
			
			
			//面板3添加文件列表
			JLabel ifo = new JLabel("资料下载");
			Font font = new Font("黑体",Font.BOLD,20);
			ifo.setFont(font);
			ifo.setBounds(60, 160,100,50);
			panel3.add(ifo);
			JPanel panel4 = new JPanel();
			ResultSet rs2 = FileDaoImpl.getFile();
			try {
				while(rs2.next()) {
					JLabel file = new JLabel(rs2.getString("file_Names")+"          ");
					file.setToolTipText("点击下载");
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
//										e1.printStackTrace();
										JOptionPane.showMessageDialog(null, "服务器未开放");
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
			
			
			
			//新建标签
			JLabel uname = new JLabel();
			JLabel exit = new JLabel("退出");
			JLabel bg = new JLabel(new ImageIcon("bg1.jpg"));
			bg.setBounds(185,22,530,screenHeight);
			panel.setLayout(null);
			t.setBounds(20,0,150,20);
			panel3.add(t);
			
			
			
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
			stud.add(pwdModify);
			root.add(teac);
			root.add(stud);
			root.add(grade);
			JTree tree = new JTree(root);
			tree.setOpaque(false);
			
			tree.setBounds(0,0,180, 150);
			
			//获取学生姓名
			Connection conn = DBConnection.getConnection();
			try {
				Statement  stmt = conn.createStatement();
				String sql = "select StuName from student where userName="+userName;
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				String name = rs.getString("StuName");
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
			
			
			//添加响应
			tree.addTreeSelectionListener(new TreeSelectionListener() {

				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO Auto-generated method stub
					TreePath path = tree.getSelectionPath();
					if(path == null)
						return;
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
					if(selectedNode.equals(teacIfo)) {//选中教师个人信息
							JOptionPane.showMessageDialog(null,"该功能尚未开放!");
					}
					if(selectedNode.equals(studIfo)) {//选择学生个人信息
						JPanel panel = new JPanel();
						panel.setLayout(null);
						panel.setBackground(Color.WHITE);
						ResultSet rs = StudentDaoImpl.studentIfo(userName);
						try {
							while(rs.next()) {
								
								Font font = new Font("楷体", 10, 25);
								JLabel title = new JLabel("学生个人信息");
								title.setFont(font);
								JLabel stuId = new JLabel("学号               "+rs.getString("StuId"));
								JLabel stuName = new JLabel("姓名               "+rs.getString("StuName"));
								JLabel stuSex = new JLabel("性别               "+rs.getString("StuSex"));
								JLabel stuBirth = new JLabel("生日               "+rs.getString("Birthday"));
								JLabel stuPhone = new JLabel("联系方式           "+rs.getString("Phone"));
								JLabel stuAddress = new JLabel("家庭地址           "+rs.getString("Address"));
								JLabel stuClass = new JLabel("班级               "+rs.getString("class_Name"));
								JLabel stuDepar = new JLabel("所属院系           "+rs.getString("Depar_name"));
								
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
						ResultSet rs = StudentDaoImpl.studentGrade(userName);
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
						
						String[] columnNames = {"学号","姓名","课程","成绩"};
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
							JLabel avgGrade = new JLabel("平均成绩:"+avg);
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
					
					
					if(selectedNode.equals(gradeStatics)) {  //成绩统计，优秀率，不及格率
						JOptionPane.showMessageDialog(null,"权限不足!");
					}
					
					//选择成绩录入
					if(selectedNode.equals(gradeIn)) {
						JOptionPane.showMessageDialog(null,"权限不足!");
					}
					
					if(selectedNode.equals(gradeModify)) {//成绩修改
						JOptionPane.showMessageDialog(null,"权限不足!");
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
