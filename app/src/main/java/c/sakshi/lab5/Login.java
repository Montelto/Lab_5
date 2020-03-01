package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernmaeKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",
                Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernmaeKey, "").equals("")) {
            String username = sharedPreferences.getString(usernmaeKey, "");
            goToNotes(username);
        } else {
            setContentView(R.layout.login_activity);
        }
    }

    public void loginFunction(View view) {
        EditText textName = (EditText) findViewById(R.id.username);
        EditText textPassword = (EditText) findViewById(R.id.password);
        String username = textName.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();

        goToNotes(username);
    }

    public void goToNotes(String username) {
        Intent intent = new Intent(this, Notes.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
