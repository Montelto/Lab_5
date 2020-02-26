package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void loginFunction(View view) {
        EditText text = (EditText) findViewById(R.id.username);
        String username = text.getText().toString();

        goToNotes(username);
    }

    public void goToNotes(String username) {
        Intent intent = new Intent(this, Notes.class);
        intent.putExtra("name", username);
        startActivity(intent);
    }
}
