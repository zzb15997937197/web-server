###DOM 

####查找元素的几种方式
1. 通过id  document.getElementById("id");
2. 通过标签名 document.getElementsByTagName("div");
3. 通过name document.getElementsByName("gender");
4. 通过class document.getElementsByClassName("class");
5. 上级元素  parentElement
6. 下级元素们  childNodes
####创建元素

	document.createElement("div")

####添加元素

	父元素.appendChild(新元素)

####插入元素
	父元素.insertBefore(新元素，弟弟元素);

####删除元素

	父元素.removeChild(被删除的元素);

####修改元素的文本内容
	innerText()
####修改元素内部的html内容
	innerHTML()
####修改元素的样式
	元素对象.style.样式名称="样式的值";

###事件
- onclick点击事件 onload加载完成事件 onfocus 获取焦点事件 onblur失去焦点事件 onmouseover鼠标移入事件 onmouseout 鼠标移除事件，onchange 值改变事件  onsubmit 提交事件

- 什么是事件：代表了某些特定的时间点，包括状态改变事件，鼠标事件，键盘事件

- 鼠标相关事件：onclick鼠标点击事件 onmouseover鼠标移入事件 onmouseout 鼠标移出事件 onmousedown鼠标按下事件 onmouseup鼠标抬起事件 onmousemove鼠标移动事件

- 键盘相关事件： onkeydown(键盘按下) onkeyup(键盘抬起)

- 状态改变事件：onload（页面加载完）onchange(值发生改变) onblur(失去焦点) onfocus(获取焦点) onsubmit(提交) onresize(窗口尺寸发生改变事件)

- 以下代码获取窗口的宽高：

	document.body.parentElement.clientWidth
	document.body.parentElement.clientHeight

###事件绑定
1. 在元素中添加事件属性 在事件对于的函数中 this代表的是window对象
	<input type="button" value="元素绑定事件" 
	onclick="fn1()">
2. 通过js代码动态绑定事件 js代码和html代码分离 便于升级和维护，在事件对于的函数中 this代表的是事件源
	mybtn.onclick = function(){
			alert("动态绑定成功！");
	}
###event对象
1. event对象中保存着和事件相关的信息
2. 通过event可以获得鼠标事件的坐标 event.clientX/Y
3. 通过event可以获得键盘事件的字符编码 event.keyCode 
4. 通过event可以获得事件源 不同浏览器存在兼容性问题，支持两种写法： 
	var obj = event.target; 
	var obj = event.srcElement
- 以下代码可以解决兼容性问题
	var obj = event.target||event.srcElement;

####僵尸练习步骤：
1. 创建demo06，通过css给html添加背景图片，在页面加载完成的时候获取窗口的宽高，把宽高设置到背景图片上面
2. 在script标签中添加定时器t1每个100毫秒创建一个img添加到body中，通过img.src="a.jpg"形式给img设置显示图片，通过css样式选取所有的img，设置宽高，绝对定位  left的值为动态获取的屏幕的宽度，top的值为随机值（保证图片显示在屏幕范围内的随即值）
3. 在script标签中添加定时器t2每隔10毫秒执行一次，每次执行时获取页面中所有的img元素 然后for循环遍历每一个让每一个的left的值-=5
4. 在body里面通过html代码在左上角添加h3内容为成功次数：0 右上角添加h3内容为失败次数0，在script标签中声明两个全局的变量表示成功次数和失败次数(var success=0; var fail=0;)
5. 每次移动图片的时候判断图片的的位置是否移出窗口如果移出则删除图片并且让fail++ 显示到页面中
6. 在第二部创建img对象的时候给img动态绑定onmouseover事件，在事件中删除图片并且让seccess++ 显示到页面中 搞定！



	