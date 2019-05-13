package com.hk.view.dao;

public interface ViewSeatDao {
	
	//修改一等座
	int findFnumberByFightId(String fightId);
	int updateFsByFightId(String fightId);
	//修改二等座
	int findSnumberByFightId(String fightId);
	int updateSsByFightId(String fightId);
	//修改三等座
	int findOnumberByFightId(String fightId);
	int updateOsByFightId(String fightId);

}
