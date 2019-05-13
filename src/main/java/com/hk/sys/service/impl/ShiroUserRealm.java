package com.hk.sys.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sys.dao.SysAdminDao;
import com.hk.sys.entity.SysAdmin;

@Service
public class ShiroUserRealm extends AuthorizingRealm{
	
	@Autowired
	private SysAdminDao sysAdminDao;

	//设置凭证匹配器
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		cMatcher.setHashAlgorithmName("MD5");//设置加密算法
		cMatcher.setHashIterations(1);//加密次数
		super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		String username = upToken.getUsername();
		//基于用户名获取信息
		SysAdmin admin = sysAdminDao.findUserByUserName(username);
		if(admin==null)
			throw new UnknownAccountException();
		if(admin.getVaild()==0)
			throw new LockedAccountException();
		//封装信息
		ByteSource credSalt = ByteSource.Util.bytes(admin.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin, admin.getPassword(), credSalt,getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
