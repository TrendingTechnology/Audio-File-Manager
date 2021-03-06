package com.lebogang.audiofilemanager.AlbumManagement;

import com.lebogang.audiofilemanager.Models.Album;

import java.util.List;

public interface AlbumCallbacks {

    /**
     * This method is called when the querying process is complete
     * It always returns an updated list
     * @param albumList contains all the collected albums
     * */
    void onQueryComplete(List<Album> albumList);
}
