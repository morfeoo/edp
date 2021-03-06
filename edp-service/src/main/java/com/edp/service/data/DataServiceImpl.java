package com.edp.service.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edp.common.utils.BeanCopyUtils;
import com.edp.dao.domain.DataInfoPo;
import com.edp.dao.domain.DataInfoPoCriteria;
import com.edp.dao.mapper.DataInfoPoMapper;
import com.edp.serviceI.data.DataServiceI;
import com.edp.serviceI.dto.DataInfoDto;

@Service(value = "DataServiceI")
public class DataServiceImpl implements DataServiceI{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);
	@Autowired
	private DataInfoPoMapper dataMapper;
	
	
	@Override
	public List<DataInfoDto> findAllData(String teamId, Integer userId) {
		List<DataInfoPo> po = dataMapper.findAllData(teamId,userId);
		return BeanCopyUtils.populateTListbyDListBySpring(po,DataInfoDto.class);
	}


	@Override
	public Integer addData(DataInfoDto dto) {
		DataInfoPo po = BeanCopyUtils.populateTbyDBySpring(dto, DataInfoPo.class);
		return dataMapper.insert(po);
	}


	@Override
	public Integer editData(DataInfoDto dto) {
		DataInfoPo po = BeanCopyUtils.populateTbyDBySpring(dto, DataInfoPo.class);
		return dataMapper.updateByPrimaryKeySelective(po);
	}


	@Override
	public DataInfoDto findDataById(DataInfoDto dto) {
		DataInfoPo po=dataMapper.selectByPrimaryKey(dto.getId());
		return BeanCopyUtils.populateTbyDBySpring(po, DataInfoDto.class);
	}


	@Override
	public List<DataInfoDto> findDataByproductId(String productId) {
		DataInfoPoCriteria example = new DataInfoPoCriteria();
		DataInfoPoCriteria.Criteria Crteria = example.createCriteria();
		Crteria.andProductIdEqualTo(productId);
		List<DataInfoPo> po = dataMapper.selectByExample(example);
		return BeanCopyUtils.populateTListbyDListBySpring(po,DataInfoDto.class);
	}

}
