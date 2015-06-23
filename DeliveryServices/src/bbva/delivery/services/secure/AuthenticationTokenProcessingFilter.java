package bbva.delivery.services.secure;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jboss.resteasy.util.Base64;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.service.imp.DeliveryServiceImp;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	private final static String USERNAME = "USERNAME";
	private final static String PASSWORD = "PASSWORD";
	
	
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException  {
//
//        try {
//				
//	        HttpServletRequest h = (HttpServletRequest) request;
//	        System.out.println(h.getHeader("token"));
//	        
//	        Usuario usuario = new Usuario();
//	        DeliveryServiceImp g = new DeliveryServiceImp();
//			g.validarUsuarioToken(usuario);
//	        System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
//	        
//	        if (h.getHeader("token") != null) {
//	            System.out.println("Token: " + h.getHeader("token"));
//	            
//	            if ("test".equals(h.getHeader("token"))){
//	                System.out.println("valid token found");
//	                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	                UserDetails userDetails = new User(USERNAME, PASSWORD, true, true, true, true,authorities);
//	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
//	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
//	                SecurityContextHolder.getContext().setAuthentication(authentication);   
//	                chain.doFilter(request, response);
//	            }else{
//	                System.out.println("invalid token");
//	                this.customPoint(response, "error", "1", "Unauthorized: Authentication token was invalid.");
//
//	            }
//	        } else {
//	            System.out.println("no token found");
//	            this.customPoint(response, "error", "0", "Unauthorized: Authentication token was missing.");
//	        }
//	        
//        } catch (Exception e) {
//        	e.printStackTrace();
//        	StringWriter errors = new StringWriter();
//        	e.printStackTrace(new PrintWriter(errors));
//			System.out.println("hubo un error");
//			this.customPoint(response, "error 500", "2", errors.toString());
//			
//		}
//    }
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME  = "Basic";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException  {
		
		String authorization = null;
		String encodedUserPassword = null;
		String usernameAndPassword = null;
        try {
				
	        HttpServletRequest h = (HttpServletRequest) request;
	        System.out.println(h.getHeader(AUTHORIZATION_PROPERTY));
	        authorization = h.getHeader(AUTHORIZATION_PROPERTY);
	        
//	        Usuario usuario = new Usuario();
//	        DeliveryServiceImp g = new DeliveryServiceImp();
//			g.validarUsuarioToken(usuario);
//	        System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
	        
	        if (authorization == null || authorization.isEmpty()) {
	        	System.out.println("no authorization found");
	            this.customPoint(response, "error", "0", "Unauthorized: Authentication authorization was missing.");
	        }else{
//	        	System.out.println("AUTHORIZATION_PROPERTY: " + authorization);
//	        	 
//	        	encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", ""); 
//	        	 
//	     		usernameAndPassword = new String(Base64.decode(encodedUserPassword));
//	     		System.out.println("usernameAndPassword --> "+usernameAndPassword);
//	     		
//	     		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
//	    	    final String username = tokenizer.nextToken();
//	    	    final String password = tokenizer.nextToken();
//	    	    
//	    	    System.out.println("username --> "+username);
//	    	    System.out.println("password --> "+password);
//	    	    
//	    	    Usuario u = new Usuario();
//	    	    u.setUsuario(username);
//	    	    u.setPassword(password);
//	     		
//	            //if ("android".equals(username) && "pepito123".equals(password) ){
//	    	    if(g.validarUsuario(u)){
	                System.out.println("Authorization found");
	                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	                UserDetails userDetails = new User(USERNAME, PASSWORD, true, true, true, true,authorities);
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);   
	                chain.doFilter(request, response);
//	            }else{
//	                System.out.println("invalid authorization");
//	                this.customPoint(response, "error", "1", "Unauthorized: Authentication authorization was invalid.");
//	            }
	        }
	        
        } catch (Exception e) {
        	e.printStackTrace();
        	StringWriter errors = new StringWriter();
        	e.printStackTrace(new PrintWriter(errors));
			System.out.println("hubo un error");
			this.customPoint(response, "error 500", "2", errors.toString());
			
		}
    }
	
	@SuppressWarnings("unchecked")
	public void customPoint(ServletResponse response, String error, String codError, String descError) throws IOException{
		 HttpServletResponse k = (HttpServletResponse) response;
         k.setContentType("application/json");
         PrintWriter out = response.getWriter();
         JSONObject obj = new JSONObject();
         obj.put("estado", error);
         obj.put("codError", codError);
         obj.put("descError", descError);
         out.print(obj);
	}
}