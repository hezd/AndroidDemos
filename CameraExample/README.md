用到知识点


Matrix使用
Matrix主要是对平面进行平移 缩放 选准 斜切的操作，比如图片旋转
1.如果有setScale覆盖也可以说忽略之前的matrix操作
2.pre操作是倒序执行
3.post操作是顺序执行
4.pre与post交替出现时，先执行pre在执行post
Canvas里的scale，rote，cancat方法都是pre，如果要进行更多变化获取canvas的matrix在进行变化后设置给canvas

---------图片处理----------
图片裁剪以及长图加载BitmapRegionDecoder
像新浪微博大图加载
1.创建Rect裁剪区域
2.BitmapRegionDecoder裁剪解码器调用decodeRegion方法传入要裁剪区域

图片压缩
a尺寸压缩 b文件压缩 c 色彩选择
图片在内存中的存在形式以流和bitmap的形式存在
bitmap是非常占用内存的，我做过一个测试1M的图片加载bitmap时占用的内存为8M多
大图oom主要是获取bitmap造成的。bitmap所占内存计算方式图片宽像素*高像素数（通常说的宽高）
流的形式读取到内存到还好，很少会有图片是十几兆的吧
当然如果图片原始字节数据就非常大也是需要处理的

尺寸压缩：	1.获取BitmapFactory.Options设置injustDecodeBounds = true 
			2.decode解析图片参数传入options只解析bounds宽高不加载到内存获取的bitmap为null
			  同时将injustDecodeBounds设置为false为下面获取压缩的bitmap做准备。
			3.图片高与某个分辨率（如本机屏幕分辨率）做除法作为采样率options.inSampleSize = x;
			4.设置图片色彩深度options.inPreferredConfig = Bitmap.Config.ARGB8888|ARGB4444|RGB565|ALph8
			5.decodefile再次获取bitmap
			
