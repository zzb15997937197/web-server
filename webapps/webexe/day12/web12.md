1. 事件
-鼠标事件： onclick onmouseover onmouseout onmousedown onmouseup onmousemove
- 键盘事件： onkeydown  onkeyup 
-键盘事件： onkeydown onkeyup
- 状态改变事件： onload onchange onfocus onblur onsubmit onresize 
-状态改变事件：onload onchange onfocus onblur onsubmit onresize
2. 事件绑定
- 元素属性中绑定 函数中的this代表的是window
- js动态绑定 函数中的this代表的是事件源
3. 事件取消
- 通过给事件函数返回false  return false；取消事件
4. event事件对象
- 通过event可以获得和事件相关信息；
- 鼠标事件可以通过event.clientX/Y 得到鼠标的坐标
- 键盘事件可以通过event.keyCode得到键盘按键的字符编码
- 可以获得事件源 var obj = event.target||event.srcElement;
获取事件源var obj=event.target||event.srcElement;
获取事件源var obj=event.target||event.srcElement;
获取事件源var obj=event.target
5. 事件传递（事件冒泡）
- 如果在同一个区域内有多个事件响应，响应的顺序是类似气泡，从下往上，也就是从底层元素往上级元素执行，这个过程称为事件冒泡
- 如果某个元素里面有多个元素需要添加事件，则这个事件可以直接添加给此元素，通过事件源判断到底做什么事儿

###jQuery

- jQuery是一个js语言的框架，作用是简化js代码 

###jQuery优势
- 简化js代码
- 可以像css一样通过选择器查找元素
- 可以直接修改元素的样式
- 解决兼容性问题

###如何引入jQuery
- 因为此框架就是一个js文件 引入方式和一般js文件一样

	<script type="text/javascript" 
	src="../js/jquery-1.4.2.min.js"></script>
###$
- $等效jQuery    $("#id") = jQuery("#id");
###jq对象和js对象互相转换
	$("#id") //jq对象
	var btn = document.getElementById("id");//js对象
- 把js对象转成jq对象
		var jq = $(js);
- 把jq对象转成js对象
		var js = jq[0] / jq.get(0);

###选择器
####基本选择器 和css一样
1. 标签名选择  $("div")
2. id选择器  $("#id")
3. 类选择器 $(".class"）
4. 分组选择器 $("#id,.class,div")
5. 任意元素选择器 $("*")
####层级选择器
1. $("div span") 匹配div下所有的span子孙元素
2. $("div>span") 匹配div下的span子元素
3. $("div+span") 匹配div后面相邻的span兄弟元素
4. $("div~span") 匹配div后面所有的span兄弟元素
####层级函数
1. 获取某个元素的所有兄弟元素
		$("d1").siblings("div"); //参数可以不写，不写代表所有兄弟   
2. 获取某个元素的哥哥元素
		$("d1").prev("div")；//参数不写是获取相邻的上一个元素 写的话指定上一个的元素类型
3. 获取某个元素的哥哥们元素
		$("d1").prevAll("div")；
4. 获取某个元素的弟弟元素
		$("d1").next("div");
5. 获取某个元素的弟弟们元素
		$("d1").nextAll("div");

####过滤选择器
1. $("div:first") 匹配所有div中的第一个
2. $("div:last") 匹配所有div中的最后一个
3. $("div:even") 匹配所有div中的偶数位的元素 从0开始
4. $("div:odd") 匹配所有div中的奇数位的元素 从0开始
5. $("div:eq(n)") 匹配所有div中的第n个   从0开始
6. $("div:lt(n)") 匹配所有div中小于n的所有元素 从0开始 
7. $("div:gt(n)") 匹配所有div中大于n的所有元素 从0开始
8. $("div:not(.one)") 匹配所有div中 class不等于one的所有div元素
####内容选择器
1. $("div:has(p)") 匹配所有包含p标签的div
2. $("div:empty") 匹配所有空的div
3. $("div:parent") 匹配所有非空的div
4. $("div:contains('abc')") 匹配所有包含文本abc的div

####可见选择器
1. $("div:hidden") 匹配所有隐藏的div
2. $("div:visible") 匹配所有可见的div
- 显示隐藏相关的函数
	$("#abc").hide();//隐藏
	$("#abc").show();//显示
	$("#abc").toggle();// 如果当前隐藏则显示，如果显示则隐藏

####属性选择器
1. $("div[id]")  匹配有id属性的div
2. $("div[id='d1']") 匹配有id=d1属性的div
3. $("div[id!='d1']") 匹配有id不等于d1属性的div
####子元素选择器
1. $("div:nth-child(n)") n从1开始 匹配div中第n个子元素
2. $("div:first-child")  匹配div中第一个子元素
2. $("div:last-child") 匹配div中最后一个子元素
####表单选择器
1. $(":input") 匹配所有 文本框 密码框 单选 复选 。。。
2. $(":password") 匹配所有密码框
3. $(":radio") 匹配所有单选
4. $(":checkbox")匹配所有复选框
5. $(":checked") 匹配所有 单选、复选、下拉选
6. $("input:checked") 匹配所有 单选、复选
7. $(":selected") 匹配所有被选中的下拉选 


###文档操作
1. 创建元素
	var div = $("<div>abc</div>");
2. 添加元素
	父元素.append(div); //添加到最后面
	父元素.prepend(div);//添加到最前面
3. 插入元素
	兄弟元素.after(div); //插入到兄弟元素的后面
	兄弟元素.before(div); //插入到兄弟元素的前面
4. 删除元素
	元素对象.remove(); 






	
####课程回顾
1. jq js的框架 简化代码
2. jq->js      js = jq[0]/jq.get(0)
3. js->jq      jq = $(js)
4. 选择器
- 基础选择器   标签名   #id  .class   ,  *
- 过滤选择器  div:first   div:last   div:even div:odd  div:eq(0)  div:lt()  div:gt()  div:not(.class)
- 层级选择器  div span   div>span   div+span div~span 
- 函数   siblings()   prev()  prevAll()  next()  nextAll()
- 内容选择器 div:contains('aaa')   div:empty  div:parent  div:has(span) 
- 可见选择器   div:hidden  div:visible   函数 show() hide()  toggle()
- 属性选择器  div[id]  div[id='a']  div[id!='a'] 
- 子元素选择器 div:nth-child(1)   div:first-child  div:last-child
- 表单选择器 ：input   :password   :radio  :checkbox   :checked 
input:checked    :selected 

- 创建jq元素对象   var div = $("<div></div>");
- 添加   父元素.append(div)   父元素.prepend(div)
- 插入  兄弟元素.before(div)  兄弟元素.after(div)
- 删除 元素对象.remove()
