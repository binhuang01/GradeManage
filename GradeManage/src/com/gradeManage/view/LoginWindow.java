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
		
		//�½�����
		JFrame frame = new JFrame();
		SwingUtilities.updateComponentTreeUI(frame);
		//���ý��泤�Ϳ�
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width*2/3;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
		//���ó�ʼ������λ��
		int width = Toolkit.getDefaultToolkit().getScreenSize().width/6;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height/6;
		frame.setBounds(width,height,screenWidth,screenHeight);
		frame.setLayout(null);
		
		
		//���ô�������
		frame.setTitle("ѧ���ɼ�����ϵͳ");
		
		//���ô���Ĭ�Ϲر�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//�½����
		JPanel panel = new JPanel();
//		JPanel panel2 = new JPanel();
		panel.setBounds(0,0,screenWidth,screenHeight);
//		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
//		panel2.setBounds(600,100,280,330);
//		Color color = new Color(0.0f,0.2f,0.0f,0.5f);
//		panel2.setBackground(color);
//		
		//�½���ǩ
		JLabel uname = new JLabel("�û���:");
		JLabel upwd = new JLabel("����:");
		JLabel check = new JLabel("��֤��:");
		JLabel teacher = new JLabel("��ʦ");
		JLabel student = new JLabel("ѧ��");
		JLabel tip = new JLabel("���ͼƬ��һ��");
		ImageIcon background = new ImageIcon("bg.jpg");
		JLabel bg = new JLabel(background);
		
		//�½��ı���
		JTextField userName = new JTextField(15);
		JPasswordField passWord = new JPasswordField(15);
		JTextField checkReg = new JTextField(10);
		
		//�����ı���ͱ�ǩλ��
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
		
		
		//�½���ť
		JRadioButton teac = new JRadioButton();
		JRadioButton stu = new JRadioButton();
		JButton login = new JButton("��¼");
		JButton regiester = new JButton("ע��");
		teac.setContentAreaFilled(false);//���ð�ť����͸��
		stu.setContentAreaFilled(false);
		ButtonGroup butGrp = new ButtonGroup();
		
		//���ð�ťλ��
		regiester.setBounds(width*8/3, height*3, 75, 25);
		login.setBounds(width*7/2, height*3, 75, 25);
		teac.setBounds(width*3, height*5/2, 75, 25);
		stu.setBounds(width*7/2, height*5/2, 75, 25);
		butGrp.add(teac);
		butGrp.add(stu);
		
		//��������
		Font f = new Font("�����п�",Font.BOLD,30);
		JLabel title = new JLabel("ѧ���ɼ�����ϵͳ");
		title.setFont(f);
		title.setBounds(width/2, height/2, 300, 60);
		
		//������֤��
		String authCode = AuthCode.getAuthCode();
		Image img = AuthCode.getAuthImg(authCode);
		ImageIcon image = new ImageIcon(img);
		jl = new JLabel(image);
		jl.setBounds(width*17/5, height*19/10, 100, 40);
		
		//����������֤��
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
		
		//����������
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
		
		//����¼��ť����¼�
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String uname = userName.getText();//��ȡ�û���
				@SuppressWarnings("deprecation")
				String upwd = passWord.getText();//��ȡ����
				if(!teac.isSelected()&&!stu.isSelected()) {
					JOptionPane.showMessageDialog(null, "����ѡ�����");
				}
				if(stu.isSelected()) {//ѧ�������֤
					if(uname.equals("")) {
						JOptionPane.showMessageDialog(null, "�������û���!");
					}else if(upwd.equals("")) {
						JOptionPane.showMessageDialog(null, "����������!");
					}else if(checkReg.getText().equals("")){
						JOptionPane.showMessageDialog(null, "��������֤��!");
					}else {
						if(checkReg.getText().equals(authCode)) {//��֤��У��
							//System.out.println(uname+":"+upwd);
							boolean flag = StudentDaoImpl.studentCheck(uname,upwd);
							if(flag) {
								frame.dispose();
								new Thread(new StudentWindow(uname,upwd)).start();
							}else {
								JOptionPane.showMessageDialog(null,"�û������������");
							}
						}else {
							JOptionPane.showMessageDialog(null,"��֤�����",null, JOptionPane.ERROR_MESSAGE);
//							String authCode = AuthCode.getAuthCode();
//							Image img = AuthCode.getAuthImg(authCode);
//							ImageIcon image = new ImageIcon(img);
//							jl.setIcon(image);
//							jl.updateUI();
//							panel.updateUI();
						}
					}
				}
				if(teac.isSelected()) {//��ʦ�����֤
					if(uname.equals("")) {
						JOptionPane.showMessageDialog(null, "�������û���!");
					}else if(upwd.equals("")) {
						JOptionPane.showMessageDialog(null, "����������!");
					}else if(checkReg.getText().equals("")){
						JOptionPane.showMessageDialog(null, "��������֤��!");
					}else {
						if(checkReg.getText().equals(authCode)) {//��֤��У��
							boolean flag = TeacherDaoImpl.teacherCheck(uname, upwd);
							if(flag) {
								frame.dispose();
								new Thread(new TeacherWindow(uname,upwd)).start();;
							}else {
								JOptionPane.showMessageDialog(null,"�û������������");
							}
						}else {
							JOptionPane.showMessageDialog(null,"��֤�����",null, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}	
		});
		
		//Ϊע�ᰴť����¼�
		regiester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				JFrame reg = new JFrame();//ע�ᴰ��
				reg.setTitle("ѧ��ע��");
				reg.setBounds(width*2, height, 500, 550);
				JPanel panel = new JPanel();
				//���ÿղ���
				panel.setLayout(null);
				//���ñ�ǩ
				JLabel account = new JLabel("�û���");
				JLabel password = new JLabel("����������:");
				JLabel confirmPwd = new JLabel("���ٴ���������:");
				JLabel stuId = new JLabel("ѧ��:");
				JLabel stuName = new JLabel("����:");
				JLabel stuSex = new JLabel("�Ա�:");
				JLabel birth = new JLabel("����:");
				JLabel phone = new JLabel("��ϵ��ʽ:");
				JLabel clazz = new JLabel("�༶:");
				
				//�����ı���
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
				JLabel boy = new JLabel("��");
				JLabel girl = new JLabel("Ů");
				JLabel yy = new JLabel("��");
				JComboBox<String> year = new JComboBox<String>();
				JLabel mm = new JLabel("��");
				JComboBox<String> month = new JComboBox<String>();
				JLabel dd = new JLabel("��");
				JComboBox<String> day = new JComboBox<String>();
				JTextField phoneText = new JTextField(20);
				JComboBox<String> clazzes = new JComboBox<String>();
				JButton send = new JButton("�ύ");
				JButton reset = new JButton("����");
				//�����ı���ͱ�ǩλ��
				accountText.setToolTipText("������������,��ĸ,�»��߿�ͷ��6-12λ���û���");
				account.setBounds(80,20, 60, 20);
				accountText.setBounds(200,20,150,20);
				password.setBounds(80,70,80,20);
				passwordText.setBounds(200,70,150,20);
				passwordText.setToolTipText("������6-12λ������");
				confirmPwd.setBounds(80,120,100, 20);
				confirmPwdText.setBounds(200,120,150,20);
				confirmPwdText.setToolTipText("���ٴ���������");
				stuId.setBounds(80,170,60,20);
				stuIdText.setBounds(200,170,150,20);
				stuIdText.setToolTipText("������6-10λ��ѧ��");
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
				
				//�������
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
				
				//���ð�ť��Ӽ���
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
				
				//��Ӱ�ť��Ӽ���
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
							JOptionPane.showMessageDialog(null,"�������û���");
						}else if(passwordText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"����������");
						}else if(!confirmPwdText.getText().equals(passwordText.getText())) {
							JOptionPane.showMessageDialog(null,"�����������벻һ��");
						}else if(confirmPwdText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"���ٴ���������!");
						}else if(stuIdText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"������ѧ��!");
						}else if(stuNameText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"����������!");
						}else if(phoneText.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"��������ϵ��ʽ!");
						}else if(!boyButton.isSelected()&&!girlButton.isSelected()) {
							JOptionPane.showMessageDialog(null,"��ѡ���Ա�!");
						}else if(!accountText.getText().trim().toString().matches(accRegex)) {
							JOptionPane.showMessageDialog(null,"�û�����ʽ����!");
						}else if(!passwordText.getText().trim().toString().matches(pwdRegex)) {
							JOptionPane.showMessageDialog(null,"�����ʽ����!");
						}else if(!stuIdText.getText().trim().toString().matches(idRegex)) {
							JOptionPane.showMessageDialog(null,"ѧ�Ÿ�ʽ����!");
						}else if(!phoneText.getText().trim().toString().matches(phoneRegex)) {
							JOptionPane.showMessageDialog(null,"�ֻ��Ÿ�ʽ����!");
						}else {
							Connection conn = DBConnection.getConnection();
							try {
								Statement stmt = conn.createStatement();
								String sql = "select userName from student where userName='"+accountText.getText().trim().toString()+"'";
								ResultSet rs = stmt.executeQuery(sql);
								if(rs.next()) {
									JOptionPane.showMessageDialog(null,"�û����Ѵ���");
								}else {
									String sql2 = "select stuId from student where stuId='"+stuIdText.getText().trim().toString()+"'";
									ResultSet rs2 = stmt.executeQuery(sql2);
									if(rs2.next()) {
										JOptionPane.showMessageDialog(null,"��ѧ����ע��!");
									}else {
										Student stu = new Student();
										stu.setStuId(stuIdText.getText().trim().toString());
										stu.setStuName(stuNameText.getText().trim().toString());
										stu.setUserName(accountText.getText().trim().toString());
										stu.setPassword(passwordText.getText().trim().toString());
										if(boyButton.isSelected()) {
											stu.setSex("��");
										}else {
											stu.setSex("Ů");
										}
										stu.setPhone(phoneText.getText().trim().toString());
										stu.setBirth(year.getSelectedItem().toString()+"-"+month.getSelectedItem().toString()+"-"+day.getSelectedItem().toString());
										stu.setClazz(clazzes.getSelectedItem().toString());
										boolean flag = StudentDaoImpl.addStu(stu);
										if(flag) {
											JOptionPane.showMessageDialog(null,"ע��ɹ�");
										}else {
											JOptionPane.showMessageDialog(null,"ע��ʧ��,�˺��Ѵ���!");
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
				
				//��ӵ����
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
	
		//���ô���ɼ�
		frame.setResizable(false);
		panel.setVisible(true);
		frame.setVisible(true);
	}

}
