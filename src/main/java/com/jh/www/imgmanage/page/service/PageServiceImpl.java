package com.jh.www.imgmanage.page.service;

import com.jh.www.imgmanage.domain.Menu;
import com.jh.www.imgmanage.domain.Page;
import com.jh.www.imgmanage.domain.PageModel;
import com.jh.www.imgmanage.page.mapper.PageMapper;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PageServiceImpl extends EgovAbstractServiceImpl implements PageService{

    @Autowired
    private PageMapper pageMapper;

    public List<Menu> getTreeList(List<Menu> list) {
        List<Menu> userMenuList = new ArrayList<>();
        for (Menu i : list) {
            if (i.getPtId() == null) {
                userMenuList.add(i);
            }
        }
        for (Menu value : userMenuList) {
            List<Menu> child = new ArrayList<>();
            for (Menu item : list) {
                if (item.getPtId() != null && value.getId().equals(item.getPtId())) {
                    child.add(item);
                }
                value.setChildren(child);
            }
        }
        return userMenuList;
    }

    @Override
    public List<Menu> getMenu() {
        return getTreeList(pageMapper.getMenu());
    }

    @Override
    public List<Menu> getTreeMenu(Map<String, Object> map){
        return getTreeList(pageMapper.getTreeMenu(map));
    }




}
