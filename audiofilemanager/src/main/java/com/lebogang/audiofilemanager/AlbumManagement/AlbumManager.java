package com.lebogang.audiofilemanager.AlbumManagement;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.lebogang.audiofilemanager.Models.Album;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AlbumManager extends DatabaseOperations{
    private final Context context;
    private AlbumCallbacks callbacks;
    private final MutableLiveData<List<Album>> liveData;

    public AlbumManager(Context context) {
        this.context = context;
        liveData = new MutableLiveData<>();
    }

    /**
     * To avoid getting album items manually, register collBacks using this method
     * @param lifecycleOwner is required
     * @param callbacks is also required
     * */
    public void registerCallbacks(LifecycleOwner lifecycleOwner,AlbumCallbacks callbacks){
        this.callbacks = callbacks;
        lifecycleOwner.getLifecycle().addObserver(getLifecycleObserver());
    }

    @Override
    public List<Album> getAlbums(){
        return super.queryItems(context);
    }

    @Override
    public Album getAlbumItemWithID(long id){
        return super.queryItemID(context,id);
    }

    @Override
    public Album getAlbumItemWithName(String name){
        return super.queryItemName(context, name);
    }

    /**
     * Some albums can have the same name with different IDs. This will return a list without the
     * duplicates. However, the song count specified in the album and the actual song count may not match
     * @param albumList list containing the albums
     * @return filtered list.
     * */
    public static List<Album> groupByName(List<Album> albumList){
        LinkedHashMap<String, Album> linkedHashMap = new LinkedHashMap<>();
        for (Album album:albumList){
            if (!linkedHashMap.containsKey(album.getTitle()))
                linkedHashMap.put(album.getTitle(), album);
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private DefaultLifecycleObserver getLifecycleObserver(){
        return new DefaultLifecycleObserver() {
            @Override
            public void onCreate(@NonNull LifecycleOwner owner) {
                liveData.observe(owner, albumList -> {
                    callbacks.onQueryComplete(albumList);
                });
            }

            @Override
            public void onResume(@NonNull LifecycleOwner owner) {
                liveData.setValue(getAlbums());
            }

            @Override
            public void onDestroy(@NonNull LifecycleOwner owner) {
                liveData.removeObservers(owner);
                owner.getLifecycle().removeObserver(this);
            }
        };
    }
}
