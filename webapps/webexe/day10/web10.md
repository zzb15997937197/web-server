
作业：假轮播图
1. 搭建界面
2. 给图片们添加样式 修改大小 并且让所有图片隐藏
3. 在页面加载完的时候(onload) 得到所有图片 从里面取出第一张 设置style让其显示
4. 开启每两秒执行一次的定时器
5. 在定时器里面准备一个递增的整数
6. 对递增的数值进行取余，得到的值为数组下标
7. 变量数组 判断i的值等于上一步的下标时 让当前遍历的图片显示 其它图片隐藏



###电子时钟练习
1. 创建demo02.html
2. 在body里面添加一个h1 标签 设置id
3. 在script标签中 开启定时器 每隔一秒执行一次，在执行的方法中获取当前的客户端时间 date   从date中获取时分秒  把得到的时分秒显示到h1标签中 搞定！


###定时器
- 开启定时器 var timeId = setInterval(函数,时间)
- 停止定时器 clearInterval(timeId)
- setTimeout(函数,时间);

###DOM Document Object Model 文档对象模型
- 学习DOM主要学习的就是对页面当中的元素进行增删改查操作

####查找元素
1. 通过id查找  
		var input = document.getElementById("id");
2. 通过标签名查找
		var divs = document.getElementsByTagName("div");
3. 通过标签的name属性值查找
		var arr = document.getElementsByName("gender");
4. 通过标签的class属性值查找
		var arr = document.getElementsByClassName("");
####通过上下级关系获得元素
1. 获取元素的上级元素
	元素对象.parentElement  得到的是一个上级元素
2. 获取元素的下级元素们
	元素对象.childNodes 得到的是一个数组里面装着多个元素和文本

####创建元素
	var div = document.createElement("div");
- 添加到某个元素里面
	上级元素.appendChild(div);
- 添加到某个元素的上面
	上级元素.insertBefore(div,弟弟元素);

 
 
