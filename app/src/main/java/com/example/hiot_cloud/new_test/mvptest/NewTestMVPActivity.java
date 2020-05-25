package com.example.hiot_cloud.new_test.mvptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.new_test.mvptest.model.Users;

public class NewTestMVPActivity extends AppCompatActivity implements NewTestView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test_mvp);
        final EditText etName = findViewById(R.id.et_name);
        final EditText etNumber = findViewById(R.id.et_number);
        CheckBox ctRemenber = findViewById(R.id.ct_remenber);
        Button btLogin = findViewById(R.id.bt_login);
        final Users users = new Users();
        final NewTestPresenter presenter = new NewTestPresenter(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.setName(etName.getText().toString());
                users.setNumber(etNumber.getText().toString());
                presenter.login(users);

            }
        });
        ctRemenber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(NewTestMVPActivity.this, compoundButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
