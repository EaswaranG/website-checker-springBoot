package com.easwaran.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteCheckController {

	@Value("${site.is.up:Working}")
	private String SITE_IS_UP;
	
	@Value("${site.is.down:Not Working}")
	private String SITE_IS_DOWN;
	
	@Value("${site.is.redirected:Redirected}")
	private String SITE_IS_REDIRECTED;
	
	@Value("${url.incorrect:Invalid URL}")
	private String INCORRECT_URL;
	
	@GetMapping(path = "/check")
	public String isSiteUp(@RequestParam String url) {
		String responseString = SITE_IS_DOWN;
		HttpURLConnection urlConn = null;
	
			URL requestUrl;
			try {
				requestUrl = new URL(url);
				urlConn = (HttpURLConnection) requestUrl.openConnection();
				urlConn.connect();
				int responseCode = urlConn.getResponseCode();				
				
				// if HTTP response is 200 series then success response
				if(responseCode/100 == 2) {
					responseString = SITE_IS_UP;
				}else if (responseCode/100 == 3) {
					// if HTTP response is 300 series then request is redirected
					responseString = SITE_IS_REDIRECTED;
				}
				
			} catch (MalformedURLException e) {
				responseString = INCORRECT_URL;
			} catch (IOException e) {
				responseString = SITE_IS_DOWN;
			}finally {
				if(urlConn != null) {
					urlConn.disconnect();
				}
			}
		
		return responseString;
	}
}
