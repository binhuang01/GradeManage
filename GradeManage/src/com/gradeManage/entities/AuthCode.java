package com.gradeManage.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*
 *  @author binhuang
 *  @version 1.0
 *  @date 2019-10-19
 * 
 */
public class AuthCode {
	
	public static final int AUTHCODE_LENGTH = 4;	//��֤�볤��
	public static final int SINGLECODE_WIDTH = 15;	//������֤����
	public static final int SINGLECODE_HEIGHT = 30;	//������֤��߶�
	public static final int SINGLECODE_GAP = 4;		//������֤����
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP); //��֤��ͼƬ���
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;//��֤��ͼƬ�߶�
	
	public static String getAuthCode() {	//���������֤����
		String authCode = "";
		for(int i = 0; i < AUTHCODE_LENGTH;i++) {
			authCode += (new Random()).nextInt(10);
		}
		return authCode;
	}
	
	public static BufferedImage getAuthImg(String authCode) {
		//����ͼƬ�ĸߣ�������
		BufferedImage  img = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics(); 	//��ȡ����
		g.setColor(Color.YELLOW);			//���û�����ɫ
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);//�û������һ������
		
		g.setColor(Color.BLACK); 			//���û�����ɫ
		g.setFont(new Font("����",Font.PLAIN,SINGLECODE_HEIGHT + 5));
		
		char c;
		for(int i = 0; i < authCode.toCharArray().length;i++) {
			c = authCode.charAt(i);
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP/2 , IMG_HEIGHT); //����һ���ַ���
		}
		
		Random random = new Random();
		//������
		for(int i = 0; i < 20;i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
		g.drawLine(x, y, x+x2, y+y2);
		}
		return img;
	}
	
		public void getImg() {
			String code = "";
			int intCode = (new Random()).nextInt(9999);
			if(intCode < 1000) {
				intCode += 1000;
			}
			code += intCode;
			//����ͼƬ�ߣ�������
			BufferedImage  image = new BufferedImage(35,14,BufferedImage.TYPE_INT_BGR);
			Graphics g = image.getGraphics();
			g.setColor(Color.YELLOW);
			g.fillRect(1, 1, 33, 12);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("����",Font.PLAIN,12));
			
			char c;
			for(int i = 0; i < code.toCharArray().length;i++) {
				c = code.charAt(i);
				g.drawString(c + "", i * 7 + 4 , 11); //����һ���ַ���
			}
			
			//��ʾ�򱣴�
			OutputStream out = null;
			try {
				out = new FileOutputStream(new File("c:\\"+code+".jpg"));
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			try {
				encoder.encode(image);
			}catch(ImageFormatException e) {
				e.printStackTrace();
			}catch(java.io.IOException e) {
				e.printStackTrace();
			}
		}
}
