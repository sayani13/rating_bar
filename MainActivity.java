package com.example.sayani.moview_review;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RatingBar;
        import android.database.sqlite.*;
        import android.widget.Toast;

public class MainActivity extends Activity {
    public SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("reviews",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MovieReview(name VARCHAR,no_of_seats INTEGER,rating REAL);");
    }
    public void SubmitClick(View view)
    {
        EditText editText = findViewById(R.id.editText);
        String moviename = editText.getText().toString();
        EditText editText2 = findViewById(R.id.editText2);
        int movieyear = Integer.parseInt(editText2.getText().toString());
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float rating = ratingBar.getRating();
        sqLiteDatabase.execSQL("INSERT INTO MovieReview VALUES('"+moviename+"',"+movieyear+","+rating+");");
        Toast.makeText(this,"Submitted",Toast.LENGTH_SHORT).show();
    }
    public void ViewClick(View view)
    {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}