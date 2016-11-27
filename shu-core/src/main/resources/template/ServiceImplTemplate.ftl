package ${bussPackage}.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${bussPackage}.dao.${className}Mapper;
import ${bussPackage}.model.${className};
import ${bussPackage}.service.${className}Service;
import com.shupro.core.common.AbstractService;
import com.shupro.core.common.BaseMapper;

@Service
public class ${className}ServiceImpl extends AbstractService<${className}> implements ${className}Service {
	
	@Autowired
	private ${className}Mapper ${lowerName}Mapper;
	
	/**具体子类service的实现需要使用的mapper*/
	@Override
	@Autowired
	public void setBaseMapper(BaseMapper<${className}> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

}
