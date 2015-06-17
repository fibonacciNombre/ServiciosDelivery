package bbva.delivery.services.secure;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.json.simple.JSONObject;
import org.springframework.web.filter.GenericFilterBean;

import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.service.imp.DeliveryServiceImp;

//@Service
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {//implements AuthenticationTokenProcessingFilterService {
	
//	@Autowired
//	DeliveryService deliveryService;

 //   @SuppressWarnings("deprecation")
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
//        @SuppressWarnings("unchecked")
//        Map<String, String[]> parms = request.getParameterMap();
        
        HttpServletRequest h = (HttpServletRequest) request;
       // PrintWriter out = null;
        System.out.println(h.getHeader("token"));
        Usuario usuario = new Usuario();
        DeliveryServiceImp g = new DeliveryServiceImp();
        try {
			g.validarUsuarioToken(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
        //if (parms.containsKey("token")) {
        
        
        if (h.getHeader("token") != null) {
           // String strToken = parms.get("token")[0]; // grab the first "token" parameter
            System.out.println("Token: " + h.getHeader("token"));

           // if (strToken.equals("test")) {
            if ("test".equals(h.getHeader("token"))){
                System.out.println("valid token found");
                
//                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//                authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test", "test");
//                token.setDetails(new WebAuthenticationDetails((HttpServletRequest) request));
//                Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", authorities); //this.authenticationProvider.authenticate(token);
//                
//                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                
            }else{
                System.out.println("invalid token");
                HttpServletResponse k = (HttpServletResponse) response;
                k.setContentType("application/json");
                PrintWriter out = response.getWriter();
                

                JSONObject obj = new JSONObject();
                obj.put("estado", "error");
                obj.put("coderror", "1");
                obj.put("descerror", "Unauthorized: Authentication token was invalid.");
                out.print(obj);
             
//    			k.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was invalid." );
   			
            }
        } else {
            System.out.println("no token found");
            HttpServletResponse k = (HttpServletResponse) response;
            
            k.setContentType("application/json");
            PrintWriter out = response.getWriter();

            JSONObject obj = new JSONObject();
            obj.put("estado", "error");
            obj.put("coderror", "0");
            obj.put("descerror", "Unauthorized: Authentication token was missing.");
            out.print(obj);
            
//			k.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was missing." );
			
        }
        // continue thru the filter chain
        
    }
}