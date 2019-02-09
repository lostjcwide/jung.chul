package com.rocomo.mapper;

import java.util.Map;

import com.rocomo.vo.RawVo;
import com.rocomo.vo.RepVo;

public interface Mapper {
	public Map<String, Object> select1() throws Exception;
	
	public int insertRawData(RawVo pVo) throws Exception;
	
	public int insertRepData(RepVo pVo) throws Exception;
	
}
