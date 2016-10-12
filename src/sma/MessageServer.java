/**
* @Author: mdrhri-6
* @Date:   2014-09-12T09:32:19+02:00
* @Last modified by:   mdrhri-6
* @Last modified time: 2016-10-12T02:45:25+02:00
*/



// package se.ltu.netprog.javaprog.sma;

import java.net.*;
import java.io.*;
import java.util.*;

public class MessageServer extends Thread {
	private ServerSocket callListener;
	private Hashtable subscribers;
	public static final boolean logging = true;

	public void log(String s) {
		if (!logging) return;
		System.err.println("MessageServer: " + s);
	}

	public MessageServer(int port) throws IOException {
		log("Simple Messaging Architecture (SMA) version 1.0");

		callListener = new ServerSocket(port);
		subscribers = new Hashtable();
		log("Created MessageServer instance fully!");
	}

	public void subscribe(int messageType, Deliverable d) {
		subscribers.put(messageType + "", d);
	}

	public Deliverable getSubscriber(int messageType) {
		return (Deliverable) subscribers.get(messageType + "");
	}

	public void run() {
		log("MessageServer thread started. run() dispatched.");
		while (true) {
			try {
				Socket s=callListener.accept();
				MessageServerDispatcher csd;
				csd = new MessageServerDispatcher(this, s);
				csd.setDaemon(false);
				csd.start();
			} catch(Exception e) {
				log("Exception " + e);
				e.printStackTrace();
			}
		}
	}
}
