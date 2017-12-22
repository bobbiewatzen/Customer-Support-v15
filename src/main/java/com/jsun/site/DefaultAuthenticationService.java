package com.jsun.site;

import com.jsun.site.entities.UserPrincipal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
	private static final Logger Log = LogManager.getLogger();
	private static final SecureRandom RANDOM;
	private static final int HASHING_ROUNDS = 10;
	static
	{
		try
		{
			RANDOM = SecureRandom.getInstanceStrong();
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new IllegalStateException();
		}
	}
	
	@Inject UserRepository userRepository;

	@Override
	@Transactional
	public UserPrincipal authenticate(String username, String password) 
	{
		UserPrincipal principal = this.userRepository.getByUsername(username);
		if(principal == null)
		{
			Log.warn("Authentication failed for non-existent user {}.", username);
			return null;
		}
		
		if(!BCrypt.checkpw(
				password,
				new String(principal.getPassword(), StandardCharsets.UTF_8)
				))
		{
			Log.warn("Authentication failed for user {}.", username);
		}
		
		Log.debug("User {} successfully authenticated.", username);
		
		return principal;
	}

	@Override
	@Transactional
	public void saveUser(UserPrincipal principal, String newPassword) 
	{
		if(newPassword != null && newPassword.length() > 0)
		{
			String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
			principal.setPassword(BCrypt.hashpw(newPassword, salt).getBytes());
		}
		
		if(principal.getId() <1)
			this.userRepository.add(principal);
		else
			this.userRepository.update(principal);
	}
}
