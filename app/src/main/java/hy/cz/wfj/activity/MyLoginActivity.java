package hy.cz.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import hy.cz.wfj.Main2Activity;
import hy.cz.wfj.R;

public class MyLoginActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "fengluchun";
    public static final String NAME_PASS_CANNOT_NULL = "用户名或者密码不能为空";
    public static final String ENTER_ERROR = "输入有问题";

    private ImageButton mBackBtn;
    private EditText login_input_name;
    private EditText login_input_password;
    private Button login_comfirm_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MyLoginActivity.this);
        setContentView(R.layout.activity_my_login);

        initializeComponent();
        setListener();
    }

    private void initializeComponent() {
        mBackBtn = (ImageButton) this.findViewById(R.id.common_title_back_btn);
        login_input_name = (EditText) this.findViewById(R.id.login_input_name);
        login_input_password = (EditText) this.findViewById(R.id.login_input_password);
        login_comfirm_button=(Button)this.findViewById(R.id.login_comfirm_button);
    }

    private void setListener() {
        mBackBtn.setOnClickListener(this);
        login_comfirm_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn:
                Intent intent = getIntent();
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.login_comfirm_button:
                String name = login_input_name.getText().toString().trim();
                String password = login_input_password.getText().toString().trim();
                if (name.equals("")&&password.equals("")){
                    Toast.makeText(getApplicationContext(), NAME_PASS_CANNOT_NULL,Toast.LENGTH_SHORT).show();
                }else if (!name.equals("")&&!password.equals("")){
                    Toast.makeText(getApplicationContext(),"你输入的"+name+":"+password,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ENTER_ERROR,Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }
}
