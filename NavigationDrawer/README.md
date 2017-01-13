最简单的方式实现android侧滑拦
顺便加上了TabLayout+viewPager横向切换页面和顶部tab联动
实现方式
1.Drawerlayout+NavigationView
    问题是NavigationView作为菜单局限比较大，无法定制菜单item布局
2.DrawerLayout+自定义布局
    自定义布局就嗨了，高度定制，随心所欲，搞起！

实现步骤
1.在Android Studio中新建一个项目选择一个侧滑栏模板（就是这么简单）
2.根据需求自定义布局文件，具体代码请看MainActiivty布局
    实际上内容布局和菜单布局是被DrawerLayout包裹的，菜单布局必须设置属性layout_gravity
3.与toolbar结合，关联
3.关闭打开drawerlayout就很简单了，自己看代码吧

