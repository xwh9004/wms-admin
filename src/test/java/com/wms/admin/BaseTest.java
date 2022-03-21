package com.wms.admin;

import com.wms.admin.auth.UserInfoContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest(UserInfoContext.class)
@RunWith(PowerMockRunner.class)
public class BaseTest {
    @Before
    public void before() {
        PowerMockito.mockStatic(UserInfoContext.class);
        PowerMockito.when(UserInfoContext.getUsername()).thenReturn("test");
    }
}
