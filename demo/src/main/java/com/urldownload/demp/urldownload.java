package com.urldownload.demp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.observer.demo.observator;

public class urldownload implements Runnable, observable{
	String[] urlList;
	boolean extension;
	String extensionString;
	
	private ArrayList<observator> observators;
	
 	public urldownload(String[] urlList, boolean extension) {
 		this.urlList = urlList;
 		this.extension = extension;
 		observators = new ArrayList<observator>();
 	}
 	
 	public void bondObservator(observator o) {
 		observators.add(o);
 	}
 	
 	@Override
 	public void run() {
 		for(String urlString : urlList) {
 			URL url;
			try {
				url = new URL(urlString);
				String filename;
				if(extension == true) {
					filename = urlString.substring(urlString.lastIndexOf("/") + 1 ).trim() + ".iso";
					extensionString = "iso";
				}
				else {
					filename = urlString.substring(urlString.lastIndexOf("/") + 1 ).trim() + ".zip";
					extensionString= "zip";
				}
				
	 			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	 			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
	 			
	 			String line;
	 			while((line = reader.readLine()) != null) {
	 				writer.write(line);
	 				notification();
	 				 Thread.sleep(50);
	 			}
	 			System.out.println("Page downloaded to"+ filename);
	 			
	 			writer.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 		}
 	}

	@Override
	public void notification() {
		for(observator o:observators) {
			o.update(extensionString);
		}
		
	}
 	
}
