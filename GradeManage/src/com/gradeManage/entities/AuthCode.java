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
	
	public static final int AUTHCODE_LENGTH = 4;	//验证码长度
	public static final int SINGLECODE_WIDTH = 15;	//单个验证码宽度
	public static final int SINGLECODE_HEIGHT = 30;	//单个验证码高度
	public static final int SINGLECODE_GAP = 4;		//单个验证码间隔
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP); //验证码图片宽度
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;//验证码图片高度
	
	public static String getAuthCode() {	//生成随机验证数字
		String authCode = "";
		for(int i = 0; i < AUTHCODE_LENGTH;i++) {
			authCode += (new Random()).nextInt(10);
		}
		return authCode;
	}
	
	public static BufferedImage getAuthImg(String authCode) {
		//设置图片的高，宽，类型
		BufferedImage  img = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics(); 	//获取画笔
		g.setColor(Color.YELLOW);			//设置画笔颜色
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);//用画笔填充一个矩形
		
		g.setColor(Color.BLACK); 			//设置画笔颜色
		g.setFont(new Font("宋体",Font.PLAIN,SINGLECODE_HEIGHT + 5));
		
		char c;
		for(int i = 0; i < authCode.toCharArray().length;i++) {
			c = authCode.charAt(i);
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP/2 , IMG_HEIGHT); //画出一个字符串
		}
		
		Random random = new Random();
		//干扰素
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
			//设置图片高，宽，类型
			BufferedImage  image = new BufferedImage(35,14,BufferedImage.TYPE_INT_BGR);
			Graphics g = image.getGraphics();
			g.setColor(Color.YELLOW);
			g.fillRect(1, 1, 33, 12);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("宋体",Font.PLAIN,12));
			
			char c;
			for(int i = 0; i < code.toCharArray().length;i++) {
				c = code.charAt(i);
				g.drawString(c + "", i * 7 + 4 , 11); //画出一个字符串
			}
			
			//显示或保存
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
