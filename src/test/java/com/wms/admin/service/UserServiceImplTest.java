package com.wms.admin.service;

import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.entity.ProdCategoryEntity;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest(UserInfoContext.class)
@RunWith(PowerMockRunner.class)
public class UserServiceImplTest {

    @Before
    public void before() {
        PowerMockito.mockStatic(UserInfoContext.class);
        PowerMockito.when(UserInfoContext.getUsername()).thenReturn("test");
    }
}
