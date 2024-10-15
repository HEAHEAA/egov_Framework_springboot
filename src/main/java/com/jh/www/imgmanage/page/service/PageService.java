package com.jh.www.imgmanage.page.service;

import com.jh.www.imgmanage.domain.Menu;
import com.jh.www.imgmanage.domain.Page;
import com.jh.www.imgmanage.domain.PageModel;

import java.util.List;
import java.util.Map;

public interface PageService {

    List<Menu> getMenu();
    List<Menu> getTreeMenu(Map<String, Object> map);


}
