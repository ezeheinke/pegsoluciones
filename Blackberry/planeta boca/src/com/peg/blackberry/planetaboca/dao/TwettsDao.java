package com.peg.blackberry.planetaboca.dao;

import java.util.Vector;

import com.peg.blackberry.planetaboca.domain.TwitterUser;

public interface TwettsDao {

	Vector getTwetts(String usuario);	
	
	TwitterUser getUser(String user);
}
