package com.example.darren.assignment4;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Darren on 3/18/2018.
 */

@Dao
public interface SynonymPairDao {
    @Query("SELECT * FROM synonympair")
    List<SynonymPair> getAll();

    @Query("SELECT * FROM synonympair WHERE firstWord=(:word) OR secondWord=(:word)")
    SynonymPair findByWord(String word);

    @Insert
    void insertAll(SynonymPair... synonymPairs);

    @Delete
    void delete(SynonymPair pair);
}
