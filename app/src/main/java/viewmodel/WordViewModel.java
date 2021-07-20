package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.WordEntity;
import model.room.repositories.WordRepository;

public class WordViewModel extends AndroidViewModel {

    public WordRepository mRepository;

    private final LiveData<List<WordEntity>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() { return mAllWords; }

    public void insert(WordEntity word) { mRepository.insert(word); }
}