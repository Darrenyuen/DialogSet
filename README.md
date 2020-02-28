DialogSet简介
-------
为Android devers 提供用途广泛、易于使用、可高度自定义的会话框。

添加方法
-------
1. 在Project的build.gradle文件中配置仓库地址：
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
     }
}
```
2. 在Module的build.gradle下添加：
```
dependencies {
    implementation 'com.github.jarvisyuen:DialogSet:1.2'
}
```
弹出Banner
-------
+ 效果图

![bannerDialog.png](https://github.com/jarvisyuen/DialogSet/blob/master/Screenshots/bannerdialog.gif)

```
List<String> imageList = new LinkedList<>();
                imageList.add("http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170123/cf35207a32f84f4fb93e2ea2139739c8_th.jpg");
                imageList.add("http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170123/cf35207a32f84f4fb93e2ea2139739c8_th.jpg");
                imageList.add("http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170123/cf35207a32f84f4fb93e2ea2139739c8_th.jpg");
                new BannerDialog.Builder(this)
                        .imageList(imageList)
                        .onImageClickListener(new com.yuan.library.bannerdialog.OnImageClickListener() {
                            @Override
                            public void onImageClick(int index) {
                                Toast.makeText(MainActivity.this, "" + index, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
```

图片对话框
-------
+ 效果图

![b4c453c50dc7533b91a57f60a7ed5e34.png](https://github.com/jarvisyuen/DialogSet/blob/master/Screenshots/imagedialog.jpg)

```
new DialogSingleImage.Builder()
                        .context(MainActivity.this)
                        .imageURL("http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170123/cf35207a32f84f4fb93e2ea2139739c8_th.jpg")
                        .onImageClickListener(new OnImageClickListener() {
                            @Override
                            public void onImageClickListener() {
                                Toast.makeText(MainActivity.this, "跳转至广告页", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
```

底部弹出对话框
-------
##### 垂直布局

+ 效果图

![b4c453c50dc7533b91a57f60a7ed5e34.png](https://github.com/jarvisyuen/DialogSet/blob/master/Screenshots/vertical.png)


```
new BottomDialog.Builder(MainActivity.this)
                        .orientation(BottomDialog.VERTICAL)
                        .title("选择分享方式：")   //默认为空
                        .titleSize(20)  //默认为20
                        .menu(R.menu.menu_bottom_dialog)    //必须参数，传入用户自定义的menu
                        .padding(5)     //item与边框的距离，单位为dp
                        .paddingInItem(10)  //item中图标与文本的距离，单位为dp
                        .itemSize(16)   //item中文本字体的大小，默认为16
                        //.itemTextColor()
                        .onItemClickListener(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                //item.getId();
                                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })  //为item设置点击监听器
                        .isCanceled(true)   //点击弹框外部是否dismiss，默认为true
                        .build();
```

##### 水平布局

+ 效果图

![ad7792474f14c772c347dc76e9e94a6f.png](https://github.com/jarvisyuen/DialogSet/blob/master/Screenshots/horizontal.png)

```
new BottomDialog.Builder(MainActivity.this)
                        .orientation(BottomDialog.HORIZONTAL)
                        .count(5) //每行展示的数目，默认为5
                        .title("选择分享方式：")   //默认为空
                        .titleSize(20)  //默认为20
                        .menu(R.menu.menu_bottom_dialog)    //必须参数，传入用户自定义的menu
                        .padding(5)     //item与边框的距离，单位为dp
                        .paddingInItem(10)  //item中图标与文本的距离，单位为dp
                        .itemSize(16)   //item中文本字体的大小，默认为16
                        //.itemTextColor()
                        .onItemClickListener(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                //item.getId();
                                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })  //为item设置点击监听器
                        .isCanceled(true)   //点击弹框外部是否dismiss，默认为true
                        .build();

```


简单提示对话框
-------


| 方法 | 说明 | 默认值 |
| --- | --- | --- |
| title() | 标题 | 提示 |
| titleSize() | 标题字体大小 | 18sp |
| titleColor() | 标题字体颜色 | #414449 |
| content() | 内容 | （空） |
| contentSize() | 内容字体大小 | 18sp |
| contentColor() |内容字体颜色  |#848B99  |
|negativeColor()  | 字体颜色 |#D0604D  |
| positiveColor() |字体颜色  | #414449 |
| isCanceledTouchOutside() |点击外部是否消失  | false |
|positiveOnClickListener()  | 点击事件监听器 | （空） |
|negativeOnClickListener()  | 点击事件监听器 | （空） |

```
final TipDialog tipDialog = new TipDialog(MainActivity.this);
tipDialog.content("提示号外");
tipDialog.positiveOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
       tipDialog.dismiss();
   }
});
tipDialog.negativeOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
       tipDialog.dismiss();
    }
});
tipDialog.show();

```

+ 效果图

![36c0022aca882b13bbf27383d8cde5b0.png](https://github.com/jarvisyuen/DialogSet/blob/master/Screenshots/tipdialog.png)

更新日志
-------
- [v1.2](https://github.com/jarvisyuen/DialogSet/releases/tag/1.2)
发布弹出轮播图

- [v1.1](https://github.com/jarvisyuen/DialogSet/releases/tag/1.1)
发布底部弹框与提示会话框(需要申请网络权限)

- [v1.0](https://github.com/jarvisyuen/DialogSet/releases/tag/1.0)
发布底部弹框与提示会话框

License
-------

    Copyright 2020 yuansssf@gmail.com
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[▲ 回到顶部](#top)
