package com.wms.admin.service;

import com.wms.admin.BaseTest;
import com.wms.admin.auth.UserInfoContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserServiceImplTest extends BaseTest {

    @Before
    public void before() {
        PowerMockito.mockStatic(UserInfoContext.class);
        PowerMockito.when(UserInfoContext.getUsername()).thenReturn("test");
    }
}
