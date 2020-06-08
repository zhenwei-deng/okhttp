/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hiot_cloud.injection.component;


import com.example.hiot_cloud.injection.PerActivity;
import com.example.hiot_cloud.injection.module.ActivityModule;
import com.example.hiot_cloud.test.mvptest.TestMVPActivity;
import com.example.hiot_cloud.test.networktest.TestNetworkPackActivity;
import com.example.hiot_cloud.ui.ChangeEmailupdate.ChangeUserEmailActivity;
import com.example.hiot_cloud.ui.changepasswordupdate.ChangeUserPasswordActivity;
import com.example.hiot_cloud.ui.devicelist.DeviceListFragment;
import com.example.hiot_cloud.ui.login.LoginActivity;
import com.example.hiot_cloud.ui.main.MainActivity;
import com.example.hiot_cloud.ui.main.SplashActivity;
import com.example.hiot_cloud.ui.mine.MineFragment;
import com.example.hiot_cloud.ui.register.RegisterActivity;
import com.example.hiot_cloud.ui.scan.ScanActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(TestMVPActivity testMVPActivity);

    void inject(TestNetworkPackActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(RegisterActivity activity);

    void inject(MineFragment fragment);

    void inject(ChangeUserPasswordActivity activity);

    void inject(ChangeUserEmailActivity activity);

    void inject(ScanActivity activity);

    void inject(DeviceListFragment activity);

    @Component.Builder
    interface ActivityComponentBuilder {

        ActivityComponent build();

        ActivityComponentBuilder applicationComponent(ApplicationComponent applicationComponent);

        ActivityComponentBuilder activityModule(ActivityModule activityModule);
    }
}
