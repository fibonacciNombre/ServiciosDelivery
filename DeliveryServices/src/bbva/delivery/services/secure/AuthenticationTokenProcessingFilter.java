package bbva.delivery.services.secure;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    @SuppressWarnings("deprecation")
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        @SuppressWarnings("unchecked")
        Map<String, String[]> parms = request.getParameterMap();
        
        HttpServletRequest h = (HttpServletRequest) request;
        PrintWriter out = null;
        System.out.println(h.getHeader("token"));
        
        //if (parms.containsKey("token")) {
        if (h.getHeader("token") != null) {
           // String strToken = parms.get("token")[0]; // grab the first "token" parameter
            System.out.println("Token: " + h.getHeader("token"));

           // if (strToken.equals("test")) {
            if ("test".equals(h.getHeader("token"))){
                System.out.println("valid token found");
                
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test", "test");
                token.setDetails(new WebAuthenticationDetails((HttpServletRequest) request));
                Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", authorities); //this.authenticationProvider.authenticate(token);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                
            }else{
                System.out.println("invalid token");
                HttpServletResponse k = (HttpServletResponse) response;
                
    			k.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was invalid." );
    			
            }
        } else {
            System.out.println("no token found");
            HttpServletResponse k = (HttpServletResponse) response;
            
			k.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was missing." );
			
        }
        // continue thru the filter chain
        
    }
}