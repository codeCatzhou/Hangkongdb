package com.hk.sys.service;

import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysAdmin;

public interface SysAdminService {

	int updatePwd(String username, String oldpw, String newpw1, String newpw2);

	PageObject<SysAdmin> doFindPageObjects(String username, Integer pageCurrent);

	int dodeleteUI(Integer... ids);

	int insertObject(String username, String pwd1, String pwd2);

	void doStatus(Integer id, Integer vaild);

}
