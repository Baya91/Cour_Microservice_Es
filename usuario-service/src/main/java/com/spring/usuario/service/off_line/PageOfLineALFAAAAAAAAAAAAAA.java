package com.spring.usuario.service.off_line;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class PageOfLineALFAAAAAAAAAAAAAA {
	
	 public boolean isInternetConnected() {
	        try {
	            InetAddress.getByName("www.google.com");
	            return true;
	        } catch (UnknownHostException e) {
	            return false;
	        }
	    }

}
