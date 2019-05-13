package com.hk.common.utils;

import java.util.List;

import com.hk.common.vo.PageObject;

public class PageUtil {
	public static <T> PageObject<T> newPageObject(Integer pageCurrent, 
			int rows, int pageSize, List<T> records) {
		PageObject<T> po = new PageObject<>();
		po.setRowCount(rows);
		po.setRecords(records);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		//计算页面方法
		po.setPageCount((rows-1)/pageSize+1);
		return po;
	}
}
