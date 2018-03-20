package com.example.darren.assignment4;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterValuesActivity extends AppCompatActivity {
    /**
     * This is clearly overkill for such a simple assignment.
     * More realistically, we would use something like SharedPreferences,
     * instead of using SQLite / RoomDatabase.
     */
    private static class SynonymSetter extends AsyncTask<Void, Void, Void> {
        private SynonymPairDao synonymPairDao;
        private EditText word = null;
        private EditText synonym = null;

        public SynonymSetter(SynonymPairDao synonymPairDao, EditText word, EditText synonym) {
            this.synonymPairDao = synonymPairDao;
            this.word = word;
            this.synonym = synonym;
            word.clearFocus();
            synonym.clearFocus();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            word.clearFocus();
            word.setEnabled(false);
            synonym.clearFocus();
            synonym.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SynonymPair pair = new SynonymPair(word.getText().toString(), synonym.getText().toString());
            synonymPairDao.insertAll(pair);
            Snackbar.make(word.getRootView().findViewById(R.id.base_layout), R.string.synonym_added, Snackbar.LENGTH_LONG).show();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            word.setEnabled(true);
            word.setText("");
            synonym.setEnabled(true);
            synonym.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_values);
        final EditText wordField = findViewById(R.id.input_word);
        final EditText synonymField = findViewById(R.id.input_synonym);
        final Button addSynonym = findViewById(R.id.add_synonym);
        wordField.setText(getIntent().hasExtra("word") ? getIntent().getStringExtra("word") : "");
        synonymField.setText(getIntent().hasExtra("synonym") ? getIntent().getStringExtra("synonym") : "");
        addSynonym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                new SynonymSetter(db.synonymPairDao(), wordField, synonymField).execute();
            }
        });
    }
}
