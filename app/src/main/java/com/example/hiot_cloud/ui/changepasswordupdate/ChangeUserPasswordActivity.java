package com.example.hiot_cloud.ui.changepasswordupdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.data.NetworkService;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.login.LoginActivity;


import javax.inject.Inject;

public class ChangeUserPasswordActivity extends BaseActivity< ChangeUserPasswordView, ChangeUserPasswordPresenter > implements ChangeUserPasswordView {
    private EditText editText;
    @Inject
    DataManager dataManager;

    @Inject
    ChangeUserPasswordPresenter presenter;
    EditText editText1;

    private NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);



        editText = findViewById( R.id.et_network_pack_token );
        final EditText oldpassword = findViewById( R.id.et_oldpassword );
        final EditText newpassword = findViewById( R.id.et_newpassword );
        final EditText confirmpassword = findViewById( R.id.et_confirmpassword);





        //修改密码
        Button btnGPassword = findViewById( R.id.btn_ko );
        btnGPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.GPassword(oldpassword.getText().toString(), newpassword.getText().toString(),confirmpassword.getText().toString() );


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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeuserpasswordSucc() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
