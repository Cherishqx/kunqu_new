![48537e1bb21b269e1ad9e5f3baa67ee](https://github.com/user-attachments/assets/0031e367-5fa0-4399-ae1a-27944e8a7f2d)# 昆曲簿 - Android Studio 应用

[点击查看后端代码](https://github.com/Cherishqx/springboot-kunqu)

本应用旨在为昆曲爱好者提供一个集昆曲文化传播与用户体验为一体的多功能平台。通过以下核心功能，用户可以更好地了解和体验昆曲这一传统艺术：
昆曲与知识卡片随机推荐、昆曲曲库合集、昆曲历史与演出信息、收藏与管理句子、制作与保存书签、心情日历

---

## 核心功能

- **昆曲与知识卡片随机推荐**  
  应用提供随机推荐功能，用户可以每次随机获取四张昆曲知识卡片，以丰富用户的知识储备并提升体验的多样性。

- **昆曲曲库合集**  
  提供丰富的昆曲曲库浏览功能，用户可以轻松浏览并试听各类曲目。支持曲目的收藏与取消收藏功能，用户可以方便地管理自己的音乐偏好。同时，在播放页面配备进度控制功能，提升用户的听歌体验。

- **昆曲历史与演出信息**  
  昆曲历史：展示昆曲的历史背景及发展脉络，帮助用户更好地了解昆曲的文化传承。演出剧目：提供本月昆曲演出的剧目信息展示，用户可以查看具体的演出安排。

- **收藏与管理句子**  
  包括语音、行腔技巧、角色行当等昆曲相关知识内容的展示。用户可以浏览各类昆曲知识卡片，并根据兴趣收藏或取消收藏自己感兴趣的卡片。

- **制作与保存书签**  
  用户可以从收藏的句子中选择或创作句子，配合图片制作个性化书签，并将书签保存到本地。支持将书签分享至微信，便于分享与交流。

- **心情日历**  
  提供心情日历功能，用户可以根据当天的情绪选择对应的情绪人物，并在日历中记录自己的心情。

<p align="center">
  <img src="https://github.com/user-attachments/assets/6f087ae3-d7a8-40ad-8c0e-869d717406fa" width="30%" />
  <img src="https://github.com/user-attachments/assets/512f5403-e478-4dc4-b7fb-2d244781fc93" width="30%" />
  <img src="https://github.com/user-attachments/assets/81e9a8b7-34ed-4a6f-8e1a-8215e654a7ee" width="30%" />
</p>

---

## 技术栈

- **前端**: Android Studio, Java
- **后端**: Spring Boot, SQLite
- **API**: RESTful API
- **数据库**: SQLite

---

## 安装与运行

### 前端 (Android Studio)

1. 克隆项目到本地：
   ```bash
   git clone https://github.com/your_username/kunqu_android.git

2. 在 Android Studio 中打开项目，配置相关依赖。

3. 修改myapplication2/Config.java中的url并运行应用。

### 后端 (Spring Boot)

1. 克隆后端项目到本地：
   ```bash
   git clone https://github.com/Cherishqx/springboot-kunqu.git

2. 修改resources/application.properties中spring.datasource.url对应的数据库地址。

3. 使用 Maven 构建项目，确保正确配置，运行 Spring Boot 应用。
