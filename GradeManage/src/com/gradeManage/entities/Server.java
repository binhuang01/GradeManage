package com.gradeManage.entities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.gradeManage.controller.FileDaoImpl;

public class Server {
	
	private static ServerSocket server;

	public static void main(String[] args) {
		
		try {
			server = new ServerSocket(8888);
			while(true) {
				Socket client = server.accept();
				Runnable r = new ThreadedEchoHandler(client);
				Thread t = new Thread(r);
				t.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class ThreadedEchoHandler implements Runnable {
	private Socket socket;
	private FileOutputStream fos;
	
	public ThreadedEchoHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ThreadedEchoHandler(Socket socket) {
		super();
		this.socket = socket;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(in);
			DataOutputStream dos = new DataOutputStream(out);
			File  directory = new File("G:\\");
			if(!directory.exists()) {
				directory.mkdir();
			}
			String choose = dis.readUTF();
			if(choose.equals("上传")) {
				String fileName = dis.readUTF();
				
				File file = new File(directory.getAbsoluteFile()+fileName);
				fos = new FileOutputStream(file);
				
				byte[] buffer = new byte[1024*5];
				int len = 0;
				while(( len = dis.read(buffer,0,buffer.length))!=-1) {
					fos.write(buffer,0,len);
					fos.flush();
				}
				dos.writeUTF("上传成功!");
				FileDaoImpl.addFile(directory.getAbsolutePath(),String.valueOf(fileName));
				System.out.println(fileName);
				System.out.println("succeed");
				fos.close();
				dis.close();
				dos.close();
			}
			if(choose.equals("下载")) {
				String fileName = dis.readUTF();
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024*5];
				int len = 0;
				while(( len = fis.read(buffer,0,buffer.length))!=-1) {
					dos.write(buffer,0,len);
					dos.flush();
				}
				System.out.println("ok");
				fis.close();
				dos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}