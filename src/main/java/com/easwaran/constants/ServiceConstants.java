package com.easwaran.constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConstants {
    
    @Value("${site.is.up:Working}")
	public String SITE_IS_UP;
	
	@Value("${site.is.down:Not Working}")
	public String SITE_IS_DOWN;
	
	@Value("${site.is.redirected:Redirected}")
	public String SITE_IS_REDIRECTED;
	
	@Value("${url.incorrect:Incorrect URL}")
	public String INCORRECT_URL;
	
	@Value("${url.invalid:Invalid URL}")
    public String INVALID_URL;
}
