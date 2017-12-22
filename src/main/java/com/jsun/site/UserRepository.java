package com.jsun.site;

import com.jsun.site.entities.UserPrincipal;

public interface UserRepository extends GenericRepository<Long, UserPrincipal>
{
	UserPrincipal getByUsername(String username);
}
