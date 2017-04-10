package com.cmc.dp.pattern.adapter;

public class AudioPlayer implements MediaPlayer {

    /* 
     * 不使用MediaAdapter直接使用AdvancedMusicPlayer对象
     * 不符合接口隔离原则和单一职责原则，因为如果是在MP3和VLC
     * 这两个条件的时候生成两个AdvancedMusicPlayer对象，
     * 然后分别进行调用，这样也能达到目的，但是相对于“目前的类不支持MP3和VLC播放”的情况，
     * 应该生成一个接口（适配MP3和VLC的接口），这样才符合接口隔离原则，
     * 即最小接口（MP3和VLC合一才是最小接口），同时两者合一也是当前业务下不可分离的职责。
     * 例如AdvancedMusicPlayer中又添加一个高级的播放方式，
     * “直接生成两个AdvancedMusicPlayer对象”的方式则需要修改AudioPlayer的代码，
     * 实际上该种方式就是组合关系，而后者通过MediaAdapter就是聚合关系，
     * 根据关系强弱（泛化 = 实现 > 组合 > 聚合 > 关联 > 依赖），选择后者。
     */
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // 播放 mp3 音乐文件的内置支持
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }

        // mediaAdapter 提供了播放其他文件格式的支持
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }

}