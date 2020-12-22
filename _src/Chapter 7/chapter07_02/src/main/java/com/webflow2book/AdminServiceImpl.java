package com.webflow2book;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Service;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    // Only users with SPECIAL_ADMIN_RIGHTS are
    // allowed to call this method
	@RolesAllowed(value = "SPECIAL_ADMIN_RIGHTS")
	@Override
	public List<String> getSecretStrings() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("This is secret");
		return stringList;
	}

	@Override
	public List<String> getPublicStrings() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("This is public");
		return stringList;
	}
}
