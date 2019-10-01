package com.example.android37_nguyen_dinh_thai_btvn_day05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername,txtPassword;
    Button btnCancel,btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_name);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        final String str_notify="Mật khẩu không hợp lệ (phải lớn hơn 6 ký tự gồm: chữ hoa, thường, số, ký tự đặc biệt và không gồm khoảng trắng";
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code cho Cancel button
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.title_exit);
                builder.setMessage(R.string.msg_str);
                builder.setPositiveButton(R.string.yesexitnow_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.notnow_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code cho Login button
                int length_str;
                String pass=txtPassword.getText().toString();
                String user=txtUsername.getText().toString();
                length_str=txtPassword.length();
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.error_str);
                //chen msg ở đây
                if (checkString(pass))
                {
                    if(user.equals("Admin") && pass.equals("Admin123*"))
                    {
                        Intent intent = new Intent(getBaseContext(), NoteScreen.class);
                        startActivity(intent);
                    }
                    else
                    {
                        final AlertDialog.Builder builder1= new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle(R.string.title_exit);
                        builder1.setMessage(R.string.login_wrong);
                        builder1.setPositiveButton(R.string.ok_str, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(),str_notify,Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    //Khai báo hàm check chuỗi password
    private static boolean checkString(String str) {
        char ch;
        boolean upcaseFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean len_str=false;
        boolean space_char=false;
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(str.trim());
        if (str.trim().length()>=6)
        {
            len_str=true;
        }
        for(int i=0;i < str.trim().length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;//kiem tra number
            }
            else if (Character.isUpperCase(ch)) {
                upcaseFlag = true;//kiem tra upcase
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;//kiem tra lowercase
            }
            else if(Character.isWhitespace(ch))
            {
                space_char=true;//kiem tra space
            }
            if(numberFlag && upcaseFlag && lowerCaseFlag && len_str && space_char==false && hasSpecial.find())
                return true;
        }
        return false;
    }
}
