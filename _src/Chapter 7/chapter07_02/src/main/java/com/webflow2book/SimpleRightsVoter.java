package com.webflow2book;

import java.util.Iterator;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.vote.AccessDecisionVoter;

/**
 * Simple voter which checks for the correct rights.
 * 
 * @author Sven
 * 
 */
public class SimpleRightsVoter implements AccessDecisionVoter {

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class arg0) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition config) {
		// Step through the config attributes
		for (Iterator<ConfigAttribute> it = config.getConfigAttributes()
				.iterator(); it.hasNext();) {
			ConfigAttribute attribute = it.next();

			// No credentials, access is denied
			if (authentication == null) {
				return ACCESS_DENIED;
			}

			// Check if the authenticated principal has the necessary rights
			GrantedAuthority[] authorities = authentication.getAuthorities();
			int length = authorities.length;
			for (int i = 0; i < length; ++i) {
				// Grant access, if the user does have the correct rights
				if (authorities[i].getAuthority().equals(
						attribute.getAttribute())) {
					return ACCESS_GRANTED;
				}
			}
		}
		return ACCESS_ABSTAIN;
	}
}
