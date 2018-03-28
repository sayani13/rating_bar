package com.example.sayani.moview_review;

import  android.app.Activity;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class Main2Activity extends Activity {
    public SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = findViewById(R.id.listView);
        sqLiteDatabase = openOrCreateDatabase("reviews",MODE_PRIVATE,null);
        Cursor resultSet = sqLiteDatabase.rawQuery("select * from MovieReview",null);
        resultSet.moveToFirst();
        final ArrayList<Movie> arrayList = new ArrayList<>();
        while(!resultSet.isAfterLast())
        {
            String name = resultSet.getString(0);
            int year = resultSet.getInt(1);
            int rating = resultSet.getInt(2);
            Movie m = new Movie(name,year,rating);
            arrayList.add(m);
            resultSet.moveToNext();
        }
        ArrayList<CharSequence> movieName = new ArrayList<>();
        for(Movie movie : arrayList)
        {
            movieName.add(movie.name);
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, android.R.id.text1, movieName);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Movie m = arrayList.get(i);
                String text = "Name : "+m.name+"\nYear :"+m.year+"\nRating :"+m.rating;
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
    }
}
class Movie
{
    String name;
    int year;
    int rating;
    public Movie(String n,int y,int r)
    {
        name = n;
        year = y;
        rating = r;
    }
}
