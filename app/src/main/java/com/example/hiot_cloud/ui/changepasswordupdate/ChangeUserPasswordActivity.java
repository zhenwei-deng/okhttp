package com.example.hiot_cloud.ui.changepasswordupdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.data.NetworkService;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.login.LoginActivity;
import com.example.hiot_cloud.ui.main.MainActivity;
import com.example.hiot_cloud.ui.mine.MineFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeUserPasswordActivity extends BaseActivity< ChangeUserPasswordView, ChangeUserPasswordPresenter > implements ChangeUserPasswordView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_oldpassword)
    EditText etOldpassword;
    @BindView(R.id.et_newpassword)
    EditText etNewpassword;
    @BindView(R.id.et_confirmpassword)
    EditText etConfirmpassword;
    @BindView(R.id.btn_ko)
    Button btnKo;
    private EditText editText;

    @Inject
    DataManager dataManager;

    @Inject
    ChangeUserPasswordPresenter presenter;


    private NetworkService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_user_password );
        //基类中已经创建
      ButterKnife.bind( this );

        editText = findViewById( R.id.et_network_pack_token );
        final EditText oldpassword = findViewById( R.id.et_oldpassword );
        final EditText newpassword = findViewById( R.id.et_newpassword );
        final EditText confirmpassword = findViewById( R.id.et_confirmpassword );


        //修改密码
        Button btnGPassword = findViewById( R.id.btn_ko );
        btnGPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.GPassword( oldpassword.getText().toString(), newpassword.getText().toString(), confirmpassword.getText().toString() );


            }
        } );
    }



    @Override
    public ChangeUserPasswordPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependencies() {

        getActivityComponent().inject( this );
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }

    /**
     * 修改密码成功后跳转登录界面
     */
    @Override
    public void changeuserpasswordSucc() {
        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );
        finish();
    }


    /**
     * 点击返回我的主界面跳转操作，返回发到我的界面
     */
    public void returnmine() {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
        finish();

    }



    @OnClick({R.id.back, R.id.et_oldpassword, R.id.et_newpassword, R.id.et_confirmpassword, R.id.btn_ko})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                /**
                 * 点击返回我的主界面按钮
                 */
                returnmine();
                break;
            case R.id.et_oldpassword:
                break;
            case R.id.et_newpassword:
                break;
            case R.id.et_confirmpassword:
                break;
            case R.id.btn_ko:
                break;
        }
    }
}
