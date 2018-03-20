package com.example.darren.assignment4;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Darren on 3/16/2018.
 */

public class SynonymActivity extends AppCompatActivity {
    /**
     * This is clearly overkill for such a simple assignment.
     * More realistically, we would use something like SharedPreferences,
     * instead of using SQLite / RoomDatabase.
     */
    private static class SynonymGrabber extends AsyncTask<String, Void, Void> {
        private TextView wordView;
        private SynonymPairDao synonymPairDao;

        public SynonymGrabber(SynonymPairDao synonymPairDao, TextView wordView) {
            this.wordView = wordView;
            this.synonymPairDao = synonymPairDao;
        }

        private String getSynonym(String word) {
            SynonymPair synonym = synonymPairDao.findByWord(word);
            return synonym == null ? null : synonym.getSynonym(word);
        }

        @Override
        protected Void doInBackground(String... words) {
            String synonym = getSynonym(words[0]);
            Log.e(SynonymActivity.class.getName(), String.valueOf(synonym));
            if (synonym == null) {
                wordView.setText(R.string.no_synonym);
            } else {
                wordView.setText("Synonym: " + synonym);
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_synonym);
        if (getIntent().hasExtra("word")) {
            AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
            SynonymGrabber synonymGrabber = new SynonymGrabber(db.synonymPairDao(), (TextView)findViewById(R.id.synonym));
            TextView wordView = findViewById(R.id.word);
            String word = getIntent().getStringExtra("word");
            wordView.setText(getString(R.string.display_word, word));
            synonymGrabber.execute(word);
        }
    }
}
