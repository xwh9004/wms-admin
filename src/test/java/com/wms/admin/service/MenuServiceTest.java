package com.wms.admin.service;

import com.wms.admin.entity.MenuEntity;
import com.wms.admin.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class MenuServiceTest {

    @InjectMocks
    IMenuService menuService;

    @Test
    public void insertMenu(){
        String name ="仓库作业";
        String code ="operate";
        String url ="/wms-admin/operate";
        MenuEntity menu = topMenu(name, code, url);
        menuService.save(menu);
    }
    
    private MenuEntity topMenu(String name,String code,String url){
        int level =1;
        int seq =1;
        return createMenu(name,code,level,seq,url);
    }
 

    public MenuEntity createMenu(String name,String code,int level,int seq,String url){
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(name);
        menu.setMenuCode(code);
        menu.setLevelPath("/"+menu.getId());
        menu.setLevelNo(level);
        menu.setCreateBy("admin");
        menu.setUpdateBy("admin");
        menu.setSeq(seq);
        menu.setDelFlag("1");
        menu.setType("1");
        menu.setParentId("-1"); //-1表示顶级菜单
        menu.setUrl(url); //
        return menu;
    }
}
