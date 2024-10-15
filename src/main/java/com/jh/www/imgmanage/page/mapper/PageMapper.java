package com.jh.www.imgmanage.page.mapper;

import com.jh.www.imgmanage.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PageMapper {
	List<Menu> getMenu();
	List<Menu> getTreeMenu(Map<String, Object> map);
}
