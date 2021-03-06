# Audio File Manager
[![](https://jitpack.io/v/LebogangBantsijang/Audio-File-Manager.svg)](https://jitpack.io/#LebogangBantsijang/Audio-File-Manager) ![API](https://img.shields.io/badge/Android-21+-red.svg) ![](https://img.shields.io/badge/Latest-1.0.0-blue.svg)

![Logo](https://raw.githubusercontent.com/LebogangBantsijang/Audio-File-Manager/master/profile-image.png)

## Introduction

This library allows you to manage Albums, Artists, Playlists,  Genre and Audio Items that are under the [MediaStore.Audio](https://developer.android.com/reference/android/provider/MediaStore.Audio) collection without having to deal with content resolvers or anything along those lines.

## Gettting Started
Before you get started, take a look at a working example of this library [Here.](https://youtu.be/SJoQsasNBAQ)

#### Setup
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

```gradle
dependencies {
    implementation 'com.github.LebogangBantsijang:Audio-File-Manager:1.0.8'
}
```
## Usage

#### Prerequisite
* Before you start ensure that you have permission to read or write to enternal storage. [Guide](https://developer.android.com/guide/topics/permissions/overview)

#### Example
How to get Audio and Album information from the external storage

```
AudioManager audioManger = new AudioManager(context);
audioManger.registerCallbacks(new AudioCallbacks(){
    @Override
    public void onQueryComplete(List<Audio> audioList){
    //use data
    }
}, lifecycleOwner);

AlbumManager albumManager = new AlbumManager(context);
albumManager.registerCallbacks(new AlbumCallbacks(){
    @Override
    public void onQueryComplete(List<Album> albumList){
    //use data
    }
}, lifecycleOwner);
```
Alternatively you can do the following:
```
AudioManager audioManger = new AudioManager(context);
List<Audio> list = audioManger.getAudio();

AlbumManager albumManager = new AlbumManager(context);
List<Album> list = albumManager.getAlbums();
```
The library has: -
* ArtistManager
* PlaylistManager
* GenreManager
* AlbumManager
* AudioManager

[Video Tutorial](https://youtu.be/SJoQsasNBAQ)

### Compatability
* Minimum Android SDK: Audio-File-Manager v4 requires a minimum API level of 21.

### License
Apache 2.0. See the LICENSE file for details.

### Contribute

Contributions are always welcome!
