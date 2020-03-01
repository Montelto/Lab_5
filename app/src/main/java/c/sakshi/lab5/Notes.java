package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Notes extends AppCompatActivity {

    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        welcomeText.setText("Welcome " + username + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String usernameKey = "username";
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(this, Login.class);
                SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",
                        Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                startActivity(intent);
                return true;
            case R.id.addNote:
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
