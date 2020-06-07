package com.example.hiot_cloud.ui.ChangeEmailupdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.data.NetworkService;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.login.LoginActivity;
import com.example.hiot_cloud.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeUserEmailActivity extends BaseActivity< ChangeUserEmailView, ChangeUserEmailPresenter > implements ChangeUserEmailView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rec)
    TextView rec;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private EditText editText;
    @Inject
    DataManager dataManager;

    @Inject
    ChangeUserEmailPresenter presenter;


    private NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_user_email_activity );
        ButterKnife.bind( this );


        final EditText email = findViewById( R.id.et_email );

        //修改邮箱
        final Button btnUpdateEmail = findViewById( R.id.btn_ok );
        btnUpdateEmail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeEmail( email.getText().toString() );
            }
        } );

    }

    @Override
    public ChangeUserEmailPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependencies() {
        getActivityComponent().inject( this );

    }

    @Override
    public void changeuseremailSucc() {
        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );

    }

    @OnClick({R.id.back, R.id.rec, R.id.et_email, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                /**
                 * 点击返回我的主界面按钮
                 */
                returnmine();
                break;
            case R.id.rec:
                break;
            case R.id.et_email:
                break;
            case R.id.btn_ok:
                break;
        }
    }
    /**
     * 点击返回我的主界面跳转操作，返回发到我的界面
     */
    public void returnmine() {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
        finish();

    }
}
