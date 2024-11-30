package com.example.myapplication2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataProvider {

    public static List<ImageItem> getAllItems() {
        List<ImageItem> items = new ArrayList<>();
        items.add(new ImageItem(R.drawable.music_library_1, "艳云亭", "简介：讲述的是书生洪绘与枢密使萧凤韶之女惜芬几经波折，终于结为夫妇的爱情故事，共三十二出。", "music_library_1"));
        items.add(new ImageItem(R.drawable.music_library_2, "玉簪记", "简介：讲述的是宋代书生潘必正与女道士陈妙常的恋爱故事，共三十三出。", "music_library_2"));
        items.add(new ImageItem(R.drawable.music_library_3, "跃鲤记", "简介：讲述的是汉代姜诗的妻子庞三娘躬行妇道、侍奉婆婆的故事，共四十二折，无折目。", "music_library_3"));
        items.add(new ImageItem(R.drawable.music_library_4, "占花魁", "简介：讲述的是卖油郎独占花魁女的故事，共二十八出。", "music_library_4"));
        items.add(new ImageItem(R.drawable.music_library_5, "长生殿", "简介：共二卷，全剧共五十出", "music_library_5"));
        items.add(new ImageItem(R.drawable.music_library_6, "白罗衫", "简介：讲述了徐继祖寻白罗衫，揭开身世，面对仇父进行抉择的故事，共十五出。", "music_library_6"));
        items.add(new ImageItem(R.drawable.music_library_7, "宝剑记", "简介：该剧本取材于中国白话小说名著《水浒传》并有所改动。", "music_library_7"));
        items.add(new ImageItem(R.drawable.music_library_8, "春江花月夜", "简介：改编自唐代诗人张若虚的作品《春江花月夜》，讲述了一个历经50年、穿越人鬼仙三界，由爱恋情愫生发，进而超越生死的故事。", "music_library_8"));
        items.add(new ImageItem(R.drawable.music_library_9, "单刀会", "简介：该剧目讲述的是三国时关羽、鲁肃之间围绕着荆州的得失而展开的斗争，共四折。", "music_library_9"));
        items.add(new ImageItem(R.drawable.music_library_10, "挡马", "简介：昆曲单折武戏，出于清代乾隆时期的乱弹腔，《缀白裘》十一集卷三选录，方传芸和汪传钤于1954年改编所作。", "music_library_10"));
        items.add(new ImageItem(R.drawable.music_library_11, "窦娥冤", "简介：1958年江苏省苏昆剧团为纪念关汉卿戏剧创作七百年，整理关氏原作，首演于南京，是今人用昆曲谱唱元人北曲杂剧原本的剧目。", "music_library_11"));
        items.add(new ImageItem(R.drawable.music_library_12, "风云会", "简介：共二十七出。", "music_library_12"));
        items.add(new ImageItem(R.drawable.music_library_13, "凤凰山（又称《百花记》）", "简介：原本失传，明清戏曲选本中存有散出，有折子戏留存。，讲述的是元朝时安西王谋逆，江花右、江六云姐弟潜作内应，内乱平定后，百花公主与江六云联姻。", "music_library_13"));
        items.add(new ImageItem(R.drawable.music_library_14, "邯郸梦", "简介：讲述的是吕洞宾度卢生而使其经历荣辱梦幻的故事，共三十出。", "music_library_14"));
        items.add(new ImageItem(R.drawable.music_library_15, "红梨记", "简介：讲述的是北宋书生赵汝州与歌妓谢素秋的恋爱故事，原本共三十出。", "music_library_15"));
        items.add(new ImageItem(R.drawable.music_library_16, "红楼梦", "简介：全剧分为上下两本，总历时5个小时。", "music_library_16"));
        return items;
    }

    public static List<ImageItem> getRandomItems(int count) {
        List<ImageItem> allItems = getAllItems();
        Collections.shuffle(allItems);
        return allItems.subList(0, Math.min(count, allItems.size()));
    }
}