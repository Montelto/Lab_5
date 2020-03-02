package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Edit extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        EditText editText = (EditText) findViewById(R.id.note);
        final Intent intent = getIntent();

        if (intent.hasExtra("noteid"))
            noteid = Integer.parseInt(intent.getStringExtra("noteid"));

        if (noteid != -1) {
            Note note = NotesView.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveFunction(View view) {
        EditText editText = (EditText) findViewById(R.id.note);
        String noteContent = editText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",
                Context.MODE_PRIVATE);
        String username = "";
        if (!sharedPreferences.getString("username", "").equals("")) {
            username = sharedPreferences.getString("username", "");
        } else {
            setContentView(R.layout.login_activity);
        }

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (NotesView.notes.size() + 1);
            dbHelper.saveNotes(username, title, noteContent, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(username, title, noteContent, date);
        }

        Intent intent = new Intent(getApplicationContext(), NotesView.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
