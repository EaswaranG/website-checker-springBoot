package com.easwaran.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.easwaran.constants.ServiceConstants;


@Service
public class ControllerService {
  
    @Autowired
    ServiceConstants serviceConstants ;
    
    public String pingSite(String url){

        String responseString = serviceConstants.SITE_IS_DOWN;
		HttpURLConnection urlConn = null;	
			
			try {
				URL requestUrl = new URL(url);
				urlConn = (HttpURLConnection) requestUrl.openConnection();
				urlConn.connect();
				int responseCode = urlConn.getResponseCode();				
				
				// if HTTP response is 200 series then success response
				if(responseCode/100 == 2) {
					responseString = serviceConstants.SITE_IS_UP;
				}else if (responseCode/100 == 3) {
					// if HTTP response is 300 series then request is redirected
					responseString = serviceConstants.SITE_IS_REDIRECTED;
				}
				
			} catch (MalformedURLException e) {
				responseString = serviceConstants.INCORRECT_URL;
			} catch (IOException e) {
				responseString = serviceConstants.SITE_IS_DOWN;
			}finally {
				if(urlConn != null) {
					urlConn.disconnect();
				}
			}
		
        return responseString;
    }

	
}
