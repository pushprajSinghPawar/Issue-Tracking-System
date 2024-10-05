package com.filter;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.util.JwtUtil;


// TODO: Auto-generated Javadoc
/**
 * The Class JwtAuthenticationFilter.
 */
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

	/** The route validator. */
	@Autowired
	private RouteValidator routeValidator;

	/** The jwt utility. */
	@Autowired
	private JwtUtil jwtUtility;

	/**
	 * The Class Config.
	 */
	public static class Config {

	}

	/**
	 * Instantiates a new jwt authentication filter.
	 */
	public JwtAuthenticationFilter() {
		super(Config.class);
	}

	/**
	 * Apply.
	 *
	 * @param config the config
	 * @return the gateway filter
	 */
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
					throw new RuntimeException("Missing authorization header");
				}
				String header = exchange.getRequest().getHeaders().get("Authorization").get(0);
				String token = null;
				if (header != null && header.startsWith("Bearer ")) {
					token = header.substring(7);
				}
				try {
					jwtUtility.validateToken(token);
					
					String role = jwtUtility.extractRole(token);
					String path = exchange.getRequest().getURI().getPath();
					
					if (!checkRoleAccess(role, path)) {
						
						throw new RuntimeException("Unauthorized access");
					}
				} catch (Exception e) {
					System.out.println("Invalid access...!");
					
					throw new RuntimeException("un-authorized access to application");
				}
			}
			return chain.filter(exchange);
		});
	}

	/**
	 * Check role access.
	 *
	 * @param role the role
	 * @param path the path
	 * @return true, if successful
	 */
	private boolean checkRoleAccess(String role, String path) {
		if (role.equalsIgnoreCase("ADMIN")) {
			return true;
		} 
		else if (role.equalsIgnoreCase("USER")) {
			return (path.startsWith("/user") || path.startsWith("/expert/addissue/**")  || path.startsWith("/issue/**"));
		}
		else if (role.equalsIgnoreCase("EXPERT")) {
			return (path.startsWith("/expert")|| path.startsWith("/issue/**"));
		} 
		return false; 
	}

}

