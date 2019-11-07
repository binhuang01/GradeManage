package com.gradeManage.view;


import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gradeManage.controller.DBConnection;
import com.gradeManage.controller.StudentDaoImpl;
import com.gradeManage.controller.TeacherDaoImpl;
import com.gradeManage.entities.AuthCode;
import com.gradeManage.entities.Student;

/*
	 * @author bin huang
	 * @version 1.0
	 * @date 2019-10-19
	 * 
	*/
public class LoginWindow {

		private static JLabel jl;
		
		public static void main(String[] args) {
			new LoginWindow();
		}
	
	public LoginWindow() {
		// TODO Auto-generated method stub
		
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//新建窗体
		JFrame frame = new JFrame();
		SwingUtilities.updateComponentTreeUI(frame);
		//设置界面长和宽
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width*2/3;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
		//设置初始化界面位置
		int width = Toolkit.getDefaultToolkit().getScreenSize().width/6;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height/6;
		frame.setBounds(width,height,screenWidth,screenHeight);
		frame.setLayout(null);
		
		
		//设置窗体名称
		frame.setTitle("学生成绩管理系统");
		
		//设置窗体默认关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//新建面板
		JPanel panel = new JPanel();
//		JPanel panel2 = new JPanel();
		panel.setBounds(0,0,screenWidth,screenHeight);
//		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
//		panel2.setBounds(600,100,280,330);
//		Color color = new Color(0.0f,0.2f,0.0f,0.5f);
//		panel2.setBackground(color);
//		
		//新建标签
		JLabel uname = new JLabel("用户名:");
		JLabel upwd = new JLabel("密码:");
		JLabel check = new JLabel("验证码:");
		JLabel teacher = new JLabel("教师");
		JLabel student = new JLabel("学生");
		JLabel tip = new JLabel("点击图片换一张");
		ImageIcon background = new ImageIcon("bg.jpg");
		JLabel bg = new JLabel(background);
		
		//新建文本框
		JTextField userName = new JTextField(15);
		JPasswordField passWord = new JPasswordField(15);
		JTextField checkReg = new JTextField(10);
		
		//设置文本框和标签位置
		bg.setBounds(0, 0, screenWidth, screenHeight);
		uname.setBounds(width*8/3, height, 100, 25);
		upwd.setBounds(width*8/3, height*3/2, 100, 25);
		check.setBounds(width*8/3, height*2, 100, 25);
		userName.setBounds(width*3,height,150,25);
		passWord.setBounds(width*3,height*3/2,150,25);
		checkReg.setBounds(width*3, height*2, 80, 25);
		teacher.setBounds(width*11/4, height*5/2, 150, 25);
		student.setBounds(width*13/4, height*5/2, 150, 25);
		tip.setBounds(width*17/5, height*11/5, 150, 25);
		tip.setOpaque(false);
		
		
		//新建按钮
		JRadioButton teac = new JRadioButton();
		JRadioButton stu = new JRadioButton();
		JButton login = new JButton("登录");
		JButton regiester = new JButton("注册");
		teac.setContentAreaFilled(false);//设置按钮背景透明
		stu.setContentAreaFilled(false);
		ButtonGroup butGrp = new ButtonGroup();
		
		//设置按钮位置
		regiester.setBounds(width*8/3, height*3, 75, 25);
		login.setBounds(width*7/2, height*3, 75, 25);
		teac.setBounds(width*3, height*5/2, 75, 25);
		stu.setBounds(width*7/2, height*5/2, 75, 25);
		butGrp.add(teac);
		butGrp.add(stu);
		
		//设置字体
		Font f = new Font("华文行楷",Font.BOLD,30);
		JLabel title = new JLabel("学生成绩管理系统");
		title.setFont(f);
		title.setBounds(width/2, height/2, 300, 60);
		
		//生成验证码
		String authCode = AuthCode.getAuthCode();
		Image img = AuthCode.getAuthImg(authCode);
		ImageIcon image = new ImageIcon(img);
		jl = new JLabel(image);
		jl.setBounds(width*17/5, height*19/10, 100, 40);
		
		//重新生成验证码
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String authCode = AuthCode.getAuthCode();
				Image img = AuthCode.getAuthImg(authCode);
				ImageIcon image = new ImageIcon(img);
				jl.setIcon(image);
			}
		});
		
		//向面板添组件
		panel.add(title);
		panel.add(uname);
		panel.add(userName);
		panel.add(upwd);
		panel.add(passWord);
		panel.add(check);
		panel.add(checkReg);
		panel.add(tip);
		panel.add(login);
		panel.add(regiester);
		panel.add(teacher);
		panel.add(student);
		panel.add(teac);
		panel.add(stu);
		panel.add(jl);
		panel.add(bg);
//		frame.add(panel2);
		frame.add(panel);
		
		//给登录按钮添加事件
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String uname = userName.getText();//获取用户名
				@SuppressWarnings("deprecation")
				String upwd = passWord.getText();//获取密码
				if(!teac.isSelected()&&!stu.isSelected()) {
					JOptionPane.showMessageDialog(null, "请先选择身份");
				}
				if(stu.isSelected()) {//学生身份验证
					if(uname.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入用户名!");
					}else if(upwd.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入密码!");
					}else if(checkReg.getText().equals("")){
						JOptionPane.showMessageDialog(null, "请输入验证码!");
					}else {
						if(checkReg.getText().equals(authCode)) {//验证码校验
							//System.out.println(uname+":"+upwd);
							boolean flag = StudentDaoImpl.studentCheck(uname,upwd);
							if(flag) {
								frame.dispose();
								new Thread(new StudentWindow(uname,upwd)).start();
							}else {
								JOptionPane.showMessageDialog(null,"用户名或密码错误");
							}
						}else {
							JOptionPane.showMessageDialog(null,"验证码错误",null, JOptionPane.ERROR_MESSAGE);
//							String authCode = AuthCode.getAuthCode();
//							Image img = AuthCode.getAuthImg(authCode);
//							ImageIcon image = new ImageIcon(img);
//							jl.setIcon(image);
//							jl.updateUI();
//							panel.updateUI();
						}
					}
				}
				if(teac.isSelected()) {//教师身份验证
					if(uname.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入用户名!");
					}else if(upwd.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入密码!");
					}else if(checkReg.getText().equals("")){
						JOptionPane.showMessageDialog(null, "请输入验证码!");
					}else {
						if(checkReg.getText().equals(authCode)) {//验证码校验
							boolean flag = TeacherDaoImpl.teacherCheck(uname, upwd);
							if(flag) {
								frame.dispose();
								new Thread(new TeacherWindow(uname,upwd)).start();;
							}else {
								JOptionPane.showMessageDialog(null,"用户名或密码错误");
							}
						}else {
							JOptionPane.showMessageDialog(null,"验证码错误",null, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}	
		});
		
		//为注册按钮添加事件
		regiester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				JFrame reg = new JFrame();//注册窗口
				reg.setTitle("学生注册");
				reg.setBounds(width*2, height, 500, 550);
				JPanel panel = new JPanel();
				//设置空布局
				panel.setLayout(null);
				//设置标签
				JLabel account = new JLabel("用户名");
				JLabel password = new JLabel("请输入密码:");
				JLabel confirmPwd = new JLabel("请再次输入密码:");
				JLabel stuId = new JLabel("学号:");
				JLabel stuName = new JLabel("姓名:");
				JLabel stuSex = new JLabel("性别:");
				JLabel birth = new JLabel("生日:");
				JLabel phone = new JLabel("联系方式:");
				JLabel clazz = new JLabel("班级:");
				
				//设置文本框
				JTextField accountText = new JTextField(20);
				JPasswordField passwordText = new JPasswordField(20);
				JPasswordField confirmPwdText = new JPasswordField(20);
				JTextField stuIdText = new JTextField(20);
				JTextField stuNameText = new JTextField(20);
				JRadioButton boyButton = new JRadioButton();
				JRadioButton girlButton = new JRadioButton();
				ButtonGroup btuG = new ButtonGroup();
				btuG.add(boyButton);
				btuG.add(girlButton);
				JLabel boy = new JLabel("男");
				JLabel girl = new JLabel("女");
				JLabel yy = new JLabel("年");
				JComboBox<String> year = new JComboBox<String>();
				JLabel mm = new JLabel("月");
				JComboBox<String> month = new JComboBox<String>();
				JLabel dd = new JLabel("日");
				JComboBox<String> day = new JComboBox<String>();
				JTextField phoneText = new JTextField(20);
				JComboBox<String> clazzes = new JComboBox<String>();
				JButton send = new JButton("提交");
				JButton reset = new JButton("重置");
				//设置文本框和标签位置
				accountText.setToolTipText("请输入以数字,字母,下划线开头的6-12位的用户名");
				account.setBounds(80,20, 60, 20);
				accountText.setBounds(200,20,150,20);
				password.setBounds(80,70,80,20);
				passwordText.setBounds(200,70,150,20);
				passwordText.setToolTipText("请输入6-12位的密码");
				confirmPwd.setBounds(80,120,100, 20);
				confirmPwdText.setBounds(200,120,150,20);
				confirmPwdText.setToolTipText("请再次输入密码");
				stuId.setBounds(80,170,60,20);
				stuIdText.setBounds(200,170,150,20);
				stuIdText.setToolTipText("请输入6-10位的学号");
				stuName.setBounds(80,220,60,20);
				stuNameText.setBounds(200,220,150,20);
				stuSex.setBounds(80,270,60,20);
				boy.setBounds(200,270,30,20);
				girl.setBounds(270,270,30,20);
				boyButton.setBounds(240,270,20,20);
				girlButton.setBounds(310,270,20,20);
				birth.setBounds(80,320,60,20);
				yy.setBounds(200,320,30,20);
				year.setBounds(220,320,50,20);
				mm.setBounds(280,320,30,20);
				month.setBounds(300,320,50,20);
				dd.setBounds(360,320,30,20);
				day.setBounds(380,320,50,20);
				phone.setBounds(80,370,80,20);
				phoneText.setBounds(200,370,150,20);
				clazz.setBounds(80,420,40,20);
				clazzes.setBounds(200,420,150,20);
				reset.setBounds(150,470,80,20);
				send.setBounds(280,470,80,20);
				
				//添加日期
				for(int i = 1970; i <= 2019;i++) {
					year.addItem(String.valueOf(i));
				}
				for(int i = 1; i <= 12;i++) {
					month.addItem(String.valueOf(i));
				}
				for(int i = 1; i <= 31;i++) {
					day.addItem(String.valueOf(i));
				}
				
				ResultSet rs = StudentDaoImpl.getStuClazz();
				try {
					while(rs.next()) {
						clazzes.addItem(rs.getString("class_name"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//重置按钮添加监听
				reset.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					super.mouseClicked(arg0);
					accountText.setText("");
					passwordText.setText("");
					confirmPwdText.setText("");
					stuIdText.setText("");
					stuNameText.setText("");
					year.setSelectedIndex(0);
					month.setSelectedIndex(0);
					day.setSelectedIndex(0);
					phoneText.setText("");
				}
				});
				
				//添加按钮添加监听
				send.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						String accRegex = "^[a-zA-Z0-9_]{6,12}$";
						String  pwdRegex = "^[a-zA-Z0-9_./]{6,12}$";
						String idRegex = "^[0-9]{6,10}$";
						String phoneRegex = "^1[3|5|8][0-9]{9}$";
						
						if(accountText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请输入用户名");
						}else if(passwordText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请输入密码");
						}else if(!confirmPwdText.getText().equals(passwordText.getText())) {
							JOptionPane.showMessageDialog(null,"两次输入密码不一致");
						}else if(confirmPwdText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请再次输入密码!");
						}else if(stuIdText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请输入学号!");
						}else if(stuNameText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请输入姓名!");
						}else if(phoneText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"请输入联系方式!");
						}else if(!boyButton.isSelected()&&!girlButton.isSelected()) {
							JOptionPane.showMessageDialog(null,"请选择性别!");
						}else if(!accountText.getText().trim().toString().matches(accRegex)) {
							JOptionPane.showMessageDialog(null,"用户名格式错误!");
						}else if(!passwordText.getText().trim().toString().matches(pwdRegex)) {
							JOptionPane.showMessageDialog(null,"密码格式错误!");
						}else if(!stuIdText.getText().trim().toString().matches(idRegex)) {
							JOptionPane.showMessageDialog(null,"学号格式错误!");
						}else if(!phoneText.getText().trim().toString().matches(phoneRegex)) {
							JOptionPane.showMessageDialog(null,"手机号格式错误!");
						}else {
							Connection conn = DBConnection.getConnection();
							try {
								Statement stmt = conn.createStatement();
								String sql = "select userName from student where userName='"+accountText.getText().trim().toString()+"'";
								ResultSet rs = stmt.executeQuery(sql);
								if(rs.next()) {
									JOptionPane.showMessageDialog(null,"用户名已存在");
								}else {
									String sql2 = "select stuId from student where stuId='"+stuIdText.getText().trim().toString()+"'";
									ResultSet rs2 = stmt.executeQuery(sql2);
									if(rs2.next()) {
										JOptionPane.showMessageDialog(null,"该学号已注册!");
									}else {
										Student stu = new Student();
										stu.setStuId(stuIdText.getText().trim().toString());
										stu.setStuName(stuNameText.getText().trim().toString());
										stu.setUserName(accountText.getText().trim().toString());
										stu.setPassword(passwordText.getText().trim().toString());
										if(boyButton.isSelected()) {
											stu.setSex("男");
										}else {
											stu.setSex("女");
										}
										stu.setPhone(phoneText.getText().trim().toString());
										stu.setBirth(year.getSelectedItem().toString()+"-"+month.getSelectedItem().toString()+"-"+day.getSelectedItem().toString());
										stu.setClazz(clazzes.getSelectedItem().toString());
										boolean flag = StudentDaoImpl.addStu(stu);
										if(flag) {
											JOptionPane.showMessageDialog(null,"注册成功");
										}else {
											JOptionPane.showMessageDialog(null,"注册失败,账号已存在!");
										}
									}
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				
				});
				
				//添加到面板
				panel.add(account);
				panel.add(accountText);
				panel.add(password);
				panel.add(passwordText);
				panel.add(confirmPwd);
				panel.add(confirmPwdText);
				panel.add(stuId);
				panel.add(stuIdText);
				panel.add(stuName);
				panel.add(stuNameText);
				panel.add(stuSex);
				panel.add(boy);
				panel.add(boyButton);
				panel.add(girl);
				panel.add(girlButton);
				panel.add(birth);
				panel.add(yy);
				panel.add(year);
				panel.add(mm);
				panel.add(month);
				panel.add(dd);
				panel.add(day);
				panel.add(phone);
				panel.add(phoneText);
				panel.add(clazz);
				panel.add(clazzes);
				panel.add(send);
				panel.add(reset);
				reg.add(panel);
				reg.setVisible(true);
				
			}
		});
	
		//设置窗体可见
		frame.setResizable(false);
		panel.setVisible(true);
		frame.setVisible(true);
	}

}
