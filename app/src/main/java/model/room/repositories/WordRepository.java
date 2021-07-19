package model.room.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.dao.WordDao;
import model.room.entity.WordEntity;
import model.room.roomdatabase.WordRoomDatabase;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<WordEntity>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }
    public LiveData<WordEntity> getFirstWord(){
        return mWordDao.getFirstWord();
    }

    public void setAllWords(){

    }

    public void insert(WordEntity word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}