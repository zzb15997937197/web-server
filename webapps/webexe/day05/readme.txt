

###盒子模型
 -宽高：元素的宽高
 -外边距：距相邻元素或上级元素的距离
 -边框：元素的边框
 -内边距：内容距离元素边框的距离
 
 -移动元素有两种方式
 1、给上级元素添加内边距，会影响上级元素的距离。
 2、给元素自身添加外边距，推荐使用这种方式。
 
 -行内元素关于盒子模型的问题
 1、宽高没有效果，行内元素的宽高只受内容的影响。
 2、行内元素外边距，上下外边距不生效。
 3、边框，四个边框都会有效果但是左右边框占显示范围，上下边框不占显示范围。
 4、内边距，四个方向都生效，但是左右内边距会占显示范围，上下不占显示范围。
 
 //不管行内和块级元素都一样
 5、相邻两个元素，左右外边距是相加，上下外边距取最大值。
 
 ###定位
 -文档流定位(静态定位);
 static,默认定位方式。
 -相对定位：relative;不会脱离文档流，上下左右的值相对于元素的初始位置。
 -绝对定位：absolute;脱离文档流，上下左右的值相对于窗口或祖先元素中
 做过非static定位的元素(一般使用relative,因为其它会导致脱离文档流，影
 响其它元素的显示效果)
 -固定定位：fixed;相对于窗口，没有给外边距的话就自动排在父元素的(0,0)位置
 -浮动定位：float:left/right,脱离文档流，元素可以在当前所在行内左侧或
 右侧浮动，当撞到父元素的边框或者是另一个浮动元素时停止。如果一行装不下
 会自动换行，换行时可能会出现被卡住的现象。
 通过使用overflow:hidden来解决浮动后父元素的边框高度自动识别为0的情况
  -clear:both/left/right 两侧/左侧/右侧不允许有浮动元素（自身元素不能
  浮上去）
 
 
 ###行内元素的垂直对齐方式
  -vertical-align:top/middle/bottom/baseline(默认的)
  
###颜色的设置方式
1、通过颜色单词设置
2、3位16进制设置#0f0
3、6位16进制设置#ffffff;
4、10进制rgb(0-255,0-255,0-255)
5、

 
 
 