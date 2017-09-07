/*package com.retailstore.auth;

import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class WebAuthenticator implements Authenticator<BasicCredentials, AuthUser> {

	public Optional<AuthUser> authenticate(BasicCredentials credentials) throws AuthenticationException {

		String readKey = AuthUser.Credentials.READ_KEY;
		String readPass = AuthUser.Credentials.READ_PASS;
		String readWriteKey = AuthUser.Credentials.READ_WRITE_KEY;
		String readWritePass = AuthUser.Credentials.READ_WRITE_PASS;

		if (readKey.equals(credentials.getUsername())) {
			if (readPass.equals(credentials.getPassword())) {
				return Optional.of(new AuthUser(AuthUser.Roles.READ_ONLY));
			}
		} else if (readWriteKey.equals(credentials.getUsername())) {
			if (readWritePass.equals(credentials.getPassword())) {
				return Optional.of(new AuthUser(AuthUser.Roles.READ_WRITE));
			}
		}

		return Optional.absent();
	}

}
*/