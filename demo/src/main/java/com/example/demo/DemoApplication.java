package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.observer.demo.observator;
import com.urldownload.demp.urldownload;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args){
		SpringApplication.run(DemoApplication.class, args);
		String[] urls = new String[2];
		urls[0] = "https://phoenixnap.dl.sourceforge.net/project/reactos/ReactOS/0.4.14/ReactOS-0.4.14-iso.zip";
		urls[1] = "https://downloads-global.3cx.com/downloads/debian10iso/debian-amd64-netinst-3cx.iso";
		
		
		urldownload T1 = new urldownload(Arrays.copyOfRange(urls,0,urls.length - 1),false);
		observator O1 = new observator();
		T1.bondObservator(O1);
		Thread downloaderZip = new Thread(T1);

		urldownload T2 = new urldownload(Arrays.copyOfRange(urls,1,urls.length),true);
		observator O2 = new observator();
		T2.bondObservator(O2);
		Thread dowloaderIso = new Thread(T2);
		
		
		try {
			
			long startTime = System.currentTimeMillis();
			downloaderZip.start();
			dowloaderIso.start();
			downloaderZip.join();
			dowloaderIso.join();
			long endTime = System.currentTimeMillis();
			
			System.out.println("Total time taken: " + (endTime-startTime)/100 + "s");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
