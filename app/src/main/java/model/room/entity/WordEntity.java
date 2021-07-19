package model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class WordEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public WordEntity(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}

    public int getId(){return this.id;}

    public void setId(int id){this.id = id;}
}