###课程回顾：
1. 主键  唯一且非空   primary key
2. 自增  auto_increment   null  只增不减
3. 注释  comment  
4. `修饰表名和字段名 和'修饰字符串
5. 冗余  重复数据     拆分表
6. 事务： 工作单元  全部成功或全部失败 
	show variables like '%autocommit%'
	set autocommit=0/1;
	commit;
	rollback;
	savepoint s1;
	rollback to s1;
7. SQL分类
DDL： 数据定义语言，包括：create alter drop truncate，不支持事务
DML：数据操作语言，包括：insert delete update select(DQL) 支持事务
DQL：数据查询语言，select
TCL：事务控制语言，commit;
	rollback;
	savepoint s1;
	rollback to s1;
DCL：数据控制语言 分配用户权限相关SQL

8. 数据类型
- 整数：int(m) bigint(m)  m代表显示长度 需要结合zerofill
浮点数：double(m,d) m代表总长度 d代表小数长度 decimal(m,d) 超高精度运算时使用
字符串: char(n)固定长度最大255 执行效率高 varchar(n)可变长度 最大65535 超过255使用text   text可变长度 最大65535
日期: date年月日  time时分秒  datetime默认null 最大9999-12-31  timestamp 默认当前时间 最大 2038-1-19 

- 练习题：
1. 创建db3并使用 如果之前存在的话先删除
	create database db3; 
	use db3;
2. 创建emp员工表 有id（主键+自增）姓名，入职日期（hiredate 类型为date）
	create table emp(id int primary key auto_increment,name varchar(10),hiredate date);
3. 在姓名的后面添加工资sal字段和年龄age字段
	alter table emp add sal int after name;
	alter table emp add age int after name;
4. 表中添加以下五位员工的信息： 姚明 35 2000 2018-12-22，周杰伦 36 8000 2018-10-14，范冰冰 40 5000 2018-9-30，貂蝉 24 6000 2018-11-15，武则天 50 9000 2016-10-22
	insert into emp values(null,'姚明',35,2000,'2018-12-22'),(null,'周杰伦',36,8000,'2018-10-14'),(null,'范冰冰',40,5000,'2018-9-30'),(null,'貂蝉',24,6000,'2018-11-15'),(null,'武则天',50,9000,'2016-10-14');
5. 修改表中第二位员工的姓名为周星驰
	update emp set name='周星驰' where id=2;
6. 创建员工类型表，表名type 字段有id，name，loc（工作地点），并且在表中插入数据  体育明星 大陆，歌星 港台，影星 大陆，法师 古代
	create table type(id int primary key auto_increment,name varchar(10),loc varchar(10));
	insert into type values(null,'体育明星','大陆'),(null,'歌星','港台'),(null,'影星','大陆'),(null,'法师','古代');
7. 给员工表添加 typeid字段 并且修改表中的数据 姚明-体育明星，周星驰-歌星，范冰冰-影星，貂蝉和武则天-法师
	alter table emp add typeid int;
	update emp set typeid=1 where id=1;
	update emp set typeid=2 where id=2;
	update emp set typeid=3 where id=3;
	update emp set typeid=4 where id=4;
	update emp set typeid=4 where id=5;
8. 修改法师的工资为8888
	update emp set sal=8888 where typeid=4;
9. 查询年龄在30岁以上的员工姓名和工资
	select name,sal from emp where age>30;
10. 删除影星
	delete from emp where typeid=3;
11. 删除表 删除db3
	drop table emp;
	drop table type;
	drop database db3;


### is null
1. 查询没有上级领导的员工编号姓名和工资
	select empno,ename,sal from emp where mgr is null;
2. 查询emp表中没有奖金comm的员工姓名ename，工资sal，奖金comm
	select ename,sal,comm from emp where comm is null;
### is not null
1. 查询emp表中有奖金的员工信息
	select * from emp where comm is not null;
###比较运算符
1. 查询工资小于等于1600 姓名 工资
	select ename,sal from emp where sal<=1600;
2. 查询部门编号是20的员工姓名职位工资
	select ename,job,sal from emp where deptno=20;
3. 查询职位是manager的所有员工姓名职位部门编号
	select ename,job,deptno from emp where job='manager'
4. 查询不是10号部门的员工姓名部门编号
	select ename,deptno from emp where deptno!=10;
	select ename,deptno from emp where deptno<>10;
5. 查询商品表t_item 单价等于23的商品信息
	select * from t_item where price=23;
6. 查询单价不等于8443的商品信息
	select * from t_item where price!=8443;

####别名 
1. select ename as '姓名',sal as '工资' from emp;
2. select ename '姓名',sal '工资' from emp;
3. select ename 姓名,sal 工资 from emp;
####去重 distinct
1. 查询员工从事的所有职业 
	select distinct job from emp;
2. 查询有员工的部门编号
	select distinct deptno from emp;
####and 和 or
- and和java中的 &&效果一样
- or 和java中的|| 效果一样
1. 查询10号部门工资高于3000块钱的员工信息
	select * from emp where deptno=10 and sal>3000;
2. 查询部门编号为30或者上级领导为7698的员工姓名，职位，上级领导和部门编号
	select ename,job,mgr,deptno from emp where deptno=30 or mgr=7698;
#### in
1. 查询工资为5000,1500,3000的员工信息

	select * from emp where sal=5000 or sal=1500 or sal=3000;
	select * from emp where sal in (5000,1500,3000);

#### between x and y 在x和y之间 包含xy
1. 查询工资在2000到4000之间的员工信息
	select * from emp where sal between 2000 and 4000;
2. 查询工资在2100到2800之外的员工姓名和工资
	select ename,sal from emp where sal not between 2100 and 2800;
3. 查询10号部门工资在2000到3000之间的员工信息

	select * from emp where deptno=10 and sal between 2000 and 3000;
###模糊查询 like
- _: 代表单个未知字符
- %：代表0或多个未知字符
- 举例:
1. 名字以a开头  ename like 'a%'
2. 以a结尾    %a  
3. 包含a     %a%
4. 第二个字母是a   _a%
5. 倒数第三个字符是a   %a__
6. 第二个字母是a最后字母是b    _a%b
- 案例：
1. 查询标题title中包含记事本的商品标题
	select title from t_item where title like '%记事本%';
2. 查询单价低于100的记事本 标题和单价price
	select title,price from t_item where price<100 and title like '%记事本%';
3. 查询单价在50到200之间的得力（title包含得力）商品标题和单价
	select title,price from t_item where price between 50 and 200 and title like '%得力%';
4. 查询有图片(image字段不等于null)的得力商品信息
	select * from t_item where image is not null and title like '%得力%';
5. 查询有赠品的商品信息（sell_point字段包含赠字）
	select * from t_item where sell_point like '%赠%';
6. 商品标题中不包含得力的商品
	select * from t_item where title not like '%得力%';
###排序 order by
- 如果有条件写在条件的后面 没条件写在 表名的后面
- 默认是升序   desc降序  asc升序
1. 查询员工姓名和工资按照工资的降序排序
	select ename,sal from emp order by sal desc;
2. 查询所有的dell商品(title包含dell) 按照单价降序排序
	select title,price from t_item where title like '%dell%' order by price desc;
- 多字段排序
1. 查询员工的姓名工资部门编号 按照部门编号降序如果编号相同则按照工资升序排序
	select ename,sal,deptno from emp 
	order by deptno desc,sal;
###分页查询 limit x,y  
- 第一个参数代表跳过的条数
- 第二个参数代表每页的数量
- limit 关键字通常写在sql语句的最后面

1. 查询所有商品按照单价升序排序 显示第二页 每页7条数据
	select * from t_item order by price limit 7,7;
2. 查询工资前三名的员工姓名和工资
	select ename,sal from emp order by sal desc limit 0,3;

###数值计算 + - * /  5%3等效mod(5,3)

- 查询所有员工的姓名，工资及年终奖(工资*5)
	select ename,sal,sal*5 年终奖 from emp;	
- 查询商品表中商品单价，库存，及总金额(单价*库存)
	select price,num,price*num from t_item;
###ifnull(x,y)函数
- age=ifnull(x,y) 如果x的值为null则赋值y 如果不为null则赋值x
1. 将emp表中奖金为null的全部改成0
	update emp set comm=ifnull(comm,0);
###聚合函数
- 对多行数据进行统计
1. 求和  sum(求和的字段名)
	- 查询所有员工的工资总和 
	select sum(sal) from emp;
	- 查询20号部门的工资总和
	select sum(sal) from emp where deptno=20;
2. 平均值 avg(字段名)
	- 查询10号部门的平均工资
	select avg(sal) from emp where deptno=10;
3. 最大值 max(字段名)
	- 查询30号部门的最高工资
	select max(sal) from emp where deptno=30;
4. 最小值 min(字段名)
	- 查询dell商品中最便宜的商品价格
	select min(price) from t_item where title like '%dell%';
5. 统计数量 count(字段名/*)
	- 查询工资大于等于3000的员工数量
	select count(*) from emp where sal>=3000;

###日期相关函数

select 'helloworld!';

- 获取当前的年月日时分秒  now()
	select now();
- 获取当前年月日 current
	select curdate();
- 获取当前时分秒
	select curtime();
- 从年月日时分秒中 提取年月日  提取时分秒
	select date(now());
	select time(now());
- 提取时间分量  年 月 日 时 分 秒
	select extract(year from now());
	select extract(month from now());
	select extract(day from now());
	select extract(hour from now());
	select extract(minute from now());
	select extract(second from now());
- 日期格式化函数
	-格式：  date_format(日期,format)；
- format：
1. %Y 四位年  %y 两位年
2. %m 两位月  %c 一位月
3. %d 号
4. %H 24小时  %h 12小时
5. %i 分
6. %s 秒
	select now();
1. 把now()格式改成 年月日时分秒
	select date_format(now(),'%Y年%m月%d日%H时%i分%s秒');

- 把非标准格式转回标准格式
	str_to_date(非标准时间,format)
1. 14.08.2018 08:00:00 转回标准时间
	select str_to_date('14.08.2018 08:00:00','%d.%m.%Y %H:%i:%s');


###课程回顾

1. is null 和 is not null
2. 比较运算符 >  < >= <= =  !=和<>
3. and 和 or
4.  in 
5. between x and y 
6. like    _单个未知  %0或多个未知
7. 去重 distinct 
8. 别名  
9. order by 字段名 desc/asc ,字段名
10. limit 跳过条数,每页条数     
11. + - * /  %  mod
12. age = ifnull(x,y)
13. 聚合函数 求和sum 平均值avg  最大值max  最小值 min 统计数量count
14. 日期相关   now()  curdate()  curtime()  date(now())  time(now())   extract(year/month/day/hour/minute/second from now())   date_format(now(),format)  %YymcdHhis  str_to_date(非标准格式时间,format);

### 练习
1. 案例：查询没有上级领导的员工的编号，姓名，工资
 
2. 案例：查询emp表中没有奖金的员工的姓名，职位，工资，以及奖金
 
3. 案例：查询emp表中含有奖金的员工的编号，姓名，职位，以及奖金
 
4. 案例：查询含有上级领导的员工的姓名，工资以及上级领导的编号
	 
5. 案例：查询emp表中名字以‘S’开头的所有员工的姓名
	 
6. 案例：查询emp表中名字的最后一个字符是'S'的员工的姓名
 
7. 案例：查询倒数的第2个字符是‘E’的员工的姓名
 
8. 案例：查询emp表中员工的倒数第3个字符是‘N’的员工姓名
	 
9. 案例：查询emp表中员工的名字中包含‘A’的员工的姓名	
 
10. 案例：查询emp表中名字不是以'K'开头的员工的所有信息
 
11. 案例：查询emp表中名字中不包含‘A’的所有员工的信息
 
12. 案例：做文员的员工人数（job= CLERK 的）
 
13. 案例：销售人员 job: SALESMAN 的最高薪水
 
14. 案例：最早和最晚入职时间
15. 案例：查询类别 163的商品总库存量
16. 案例：查询 类别 163 的商品
17. 案例：查询商品价格不大于100的商品名称列表
 
18. 案例：查询品牌是联想,且价格在40000以上的商品名称和价格
 
19. 案例：查询品牌是三木,或价格在50以下的商品名称和价格
 
20. 案例：查询品牌是三木、广博、齐心的商品名称和价格
 

21. 案例：查询品牌不是联想、戴尔的商品名称和价格
 
22. 案例：查找品牌是联想且价格大于10000的电脑名称
 
23. 案例：查询联想或戴尔的电脑名称列表
 
24. 案例：查询联想、戴尔、三木的商品名称列表
	 
25. 案例：查询不是戴尔的电脑名称列表
 
26. 案例：查询所有是记事本的名称和价格
 
27. 案例：查询品牌是末尾字符是'力'的商品的名称和价格
	 
	29.案例：查询卖点含有'赠'产品名称
	 
	30.案例：查询emp表中员工的编号，姓名，职位，工资，并且工资在1000~2000之间。
	 
	31.案例：查询emp表中员工在10号部门，并且含有上级领导的员工的姓名，职位，上级领导编号以及所属部门的编号
	 
	32.案例：查询emp表中名字中包含'E'，并且职位不是MANAGER的员工的编号，姓名，职位，以及工资
	 

	33.案例：查询emp表中10号部门或者20号部门中员工的编号，姓名，所属部门的编号
	 
	34.案例：查询emp表中没有奖金或者名字的倒数第2个字母不是T的员工的编号，姓名，职位以及奖金
	 
	35.案例：查询工资高于3000或者部门编号是30的员工的姓名，职位，工资，入职时间以及所属部门的编号	
	 
	36.案例：查询不是30号部门的员工的所有信息
	 
	37.案例：查询奖金不为空的员工的所有信息
 
	38.案例：查询emp表中所有员工的编号，姓名，职位，根据员工的编号进行降序排列
	 
	39.案例：查询emp表中部门编号是10号或者30号中，所有员工姓名，职务，工资，根据工资进行升序排列
	 
	40.案例：查询emp表中所有的数据，然后根据部门的编号进行升序排列，如果部门编号一致，根据员工的编号进行降序排列
	 
	41.案例：查询emp表中工资高于1000或者没有上级领导的员工的编号，姓名，工资，所属部门的编号，以及上级领导的编号，根据部门编号进行降序排列，如果部门编号一致根据工资进行升序排列。
	 
	
	42.案例：查询emp表中名字中不包含S的员工的编号，姓名，工资，奖金，根据工资进行升序排列，如果工资一致，根据编号进行降序排列
	 
	43.案例：统计emp表中员工的总数量
	 
	44.案例：统计emp表中获得奖金的员工的数量
	 
	45.案例：求出emp表中所有的工资累加之和
	 
	46.案例：求出emp表中所有的奖金累加之和
	 
	47.案例：求出emp表中员工的平均工资
	 
	48.案例：求出emp表中员工的平均奖金
	 
	49.案例：求出emp表中员工的最高工资
	 
	50.案例：求出emp表中员工编号的最大值
	 
	51.案例：查询emp表中员工的最低工资。
	 
	52.案例：查询emp表中员工的人数，工资的总和，平均工资，奖金的最大值，奖金的最小值,并且对返回的列起别名。
	 
	56.案例：查询工资在1000~3000之间每一个员工的编号，姓名，职位，工资
	 
	57.案例：查询emp表中奖金在500~2000之间所有员工的编号，姓名，工资以及奖金
	 
	58.案例：查询员工的编号是7369，7521，	
	 
	59.案例：查询emp表中，职位是ANALYST，
	 
	60.案例：查询emp表中职位不是ANALYST,
	 

