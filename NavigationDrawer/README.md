
最简单的方式实现android侧滑拦<br>
![Alt text](https://github.com/dreamkid/Image-Folders/blob/master/DrawerLayout/0.png)


顺便加上了TabLayout+viewPager横向切换页面和顶部tab联动<br>
 ![alt_Text](https://github.com/dreamkid/Image-Folders/blob/master/DrawerLayout/1.png)
实现方式<br>
1.Drawerlayout+NavigationView<br>
    问题是NavigationView作为菜单局限比较大，无法定制菜单item布局<br>
2.DrawerLayout+自定义布局<br>
    自定义布局就嗨了，高度定制，随心所欲，搞起！
<br>
实现步骤<br>
1.在Android Studio中新建一个项目选择一个侧滑栏模板（就是这么简单）<br>
2.根据需求自定义布局文件，具体代码请看MainActiivty布局<br>
    实际上内容布局和菜单布局是被DrawerLayout包裹的，菜单布局必须设置属性layout_gravity<br>
3.与toolbar关联，点击toolbar菜单键可以打开侧滑栏，创建ActionBarDrawerToggle作为Draweralyout方法addDrawerListener的参数
    ，ActionBarDrawerToggle通过syncState状态同步协调两者状态<br>
4.关闭打开drawerlayout就很简单了，自己看代码吧

 ![image](https://github.com/dreamkid/Image-Folders/blob/master/DrawerLayout/drawer1.gif)
