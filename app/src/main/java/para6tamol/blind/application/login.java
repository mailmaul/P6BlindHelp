package para6tamol.blind.application;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.mobile.detection.R;

public class login extends AppCompatActivity {

    private EditText mEmailField;

    private EditText mPasswordField;

    private Button mloginBtn;



    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);





        //Membuat FullScreen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        mEmailField = (EditText) findViewById(R.id.email);

        mPasswordField = (EditText) findViewById(R.id.password);

        mloginBtn = (Button) findViewById(R.id.loginBtn);



        mAuth = FirebaseAuth.getInstance();



        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(login.this, Start.class) );

                }

            }

        };



        mloginBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                startSignIn();

            }

        });

    }



    @Override

    protected void onStart() {

        super.onStart();



        mAuth.addAuthStateListener(mAuthListener);

    }



    private void startSignIn() {

        String email = mEmailField.getText().toString();

        String password = mPasswordField.getText().toString();



        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {

            Toast.makeText(login.this, "Fields are Empty", Toast.LENGTH_SHORT).show();

        } else {



            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() {

                @Override

                public void onComplete(@NonNull Task task) {



                    if (!task.isSuccessful()) {

                        Toast.makeText(login.this, "Login Problem", Toast.LENGTH_SHORT).show();

                    }



                }

            });

        }

    }

}