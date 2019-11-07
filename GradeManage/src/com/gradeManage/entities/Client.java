package com.gradeManage.entities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
		
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
		try {
			client  = new Socket("localhost",8888);
			OutputStream out = client.getOutputStream();
			dos = new DataOutputStream(out);
			InputStream in = client.getInputStream();
			dis = new DataInputStream(in);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "服务器未开放");
			e.printStackTrace();
		}
	}

	public Client(Socket client, DataOutputStream dos) {
		super();
		this.client = client;
		this.dos = dos;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	
	
}
