
package eniso.ia2.parkingdata;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView t1;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignup, btnLogin, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        // set the view now
        setContentView(R.layout.activity_login);
      // t1=(TextView)findViewById(R.id.t1);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
if (btnLogin!=null){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final   String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
               // t1.setText(email+"    PW "+password);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password please!", Toast.LENGTH_SHORT).show();
                    return;
                }

              final  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //authenticate user
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Failed! would you try again!", Toast.LENGTH_LONG).show();



                        } else {
                            Toast.makeText(Login.this, "You're authentified!", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                    }
                });
            }

        });}
    }
}