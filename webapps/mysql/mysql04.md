##课程回顾
1. is null 和 is not null
2. 别名
3. distinct 
4. >  <  >=  <=  =  !=  <>
5. and  or
6. in 
7. between x and y
8. like   _  %
9. 排序  order by 字段名 asc/desc ，字段
10. limit 跳过数量,每页数量
11. 数值计算  + - * / %  mod(7,3)
12. 日期
- now()
- curdate()
- curtime()
- date(now())
- time(now())
- extract(year from now()) month day hour minute second
- date_format(now(),'格式')
 Y y m c d H h i s
- str_to_date(非标准格式的时间，格式)
13. ifnull(x,y)
14. 聚合函数
- sum()
- avg()
- max()
- min()
- count(*)
15. 字符串
- concat
- charlength
- instr   locate
- insert
- upper  lower
- left    right
- trim  去空白
- substring 
- repeat  
- replace
- reverse 反转







### 练习
1. 案例：查询没有上级领导的员工的编号，姓名，工资
	select empno,ename,sal from emp
	where mgr is null;
2. 案例：查询emp表中没有奖金的员工的姓名，职位，工资，以及奖金
	select ename,job,sal,comm from emp
	where comm is null;
3. 案例：查询emp表中含有奖金的员工的编号，姓名，职位，以及奖金
	select ename,job,sal,comm from emp
	where comm is not null;
4. 案例：查询含有上级领导的员工的姓名，工资以及上级领导的编号
	select ename,sal,mgr from emp
	where mgr is not null;
5. 案例：查询emp表中名字以‘S’开头的所有员工的姓名
	select ename from emp
	where ename like 's%';
6. 案例：查询emp表中名字的最后一个字符是'S'的员工的姓名
	select ename from emp
	where ename like '%s';
7. 案例：查询倒数的第2个字符是‘E’的员工的姓名
	select ename from emp
	where ename like '%e_';
8. 案例：查询emp表中员工的倒数第3个字符是‘N’的员工姓名
	select ename from emp
	where ename like '%n__';
9. 案例：查询emp表中员工的名字中包含‘A’的员工的姓名	
	select ename from emp
	where ename like '%a%';
10. 案例：查询emp表中名字不是以'K'开头的员工的所有信息
	select ename from emp
	where ename not like 'k%';
11. 案例：查询emp表中名字中不包含‘A’的所有员工的信息
	select ename from emp
	where ename not like '%a%';
12. 案例：做文员的员工人数（job= CLERK 的）
	select count(*) from emp 
	where job='clerk';
13. 案例：销售人员 job: SALESMAN 的最高薪水
	select max(sal) from emp
	where job='salesman'
14. 案例：最早和最晚入职时间
	select min(hiredate),max(hiredate) from emp
15. 案例：查询类别 163的商品总库存量
	select sum(num) from t_item 
	where category_id=163;
16. 案例：查询 类别 163 的商品
	select * from t_item 
	where category_id=163;
17. 案例：查询商品价格不大于100的商品名称列表
	select title from t_item 
	where price<=100;
18. 案例：查询品牌是联想,且价格在40000以上的商品名称和价格
	select title,price from t_item
	where title like '%联想%' 
	and price>40000;
19. 案例：查询品牌是三木,或价格在50以下的商品名称和价格
	select title,price from t_item
	where title like '%三木%' or price<50;
20. 案例：查询品牌是三木、广博、齐心的商品名称和价格
	select title,price from t_item
	where title like '%三木%'
	or title like '%广博%'
	or title like '%齐心%'

21. 案例：查询品牌不是联想、戴尔的商品名称和价格
	select title,price from t_item
	where title not like '%联想%'
	and title not like '%戴尔%';
	22.案例：查找品牌是联想且价格大于10000的电脑名称
	select title from t_item
	where title like '%联想%'
	and price>10000 and title like '%电脑%';
23. 案例：查询联想或戴尔的电脑名称列表
	select title from t_item
	where title like '%联想%'
	or title like '%戴尔%'
24. 案例：查询联想、戴尔、三木的商品名称列表
	select title from t_item
	where title like '%联想%'
	or title like '%戴尔%'
	or title like '%三木%'
25. 案例：查询不是戴尔的电脑名称列表
	select title from t_item
	where title not like '%戴尔%'
26. 案例：查询所有是记事本的名称和价格
	select title,price from t_item
	where title like '%记事本%';
27. 案例：查询品牌是末尾字符是'力'的商品的名称和价格
	select title,price from t_item
	where title like '%力';
	29.案例：查询卖点含有'赠'产品名称
	select title from t_item
	where sell_point like '%赠%';
	30.案例：查询emp表中员工的编号，姓名，职位，工资，并且工资在1000~2000之间。
	select empno,ename,job,sal from emp
	where sal between 1000 and 2000;
	31.案例：查询emp表中员工在10号部门，并且含有上级领导的员工的姓名，职位，上级领导编号以及所属部门的编号
	select ename,job,mgr,deptno from emp
	where deptno=10 and mgr is not null;
	32.案例：查询emp表中名字中包含'E'，并且职位不是MANAGER的员工的编号，姓名，职位，以及工资
	select empno,ename,job,sal from emp
	where ename like '%e%' 
	and job!='manager';

	33.案例：查询emp表中10号部门或者20号部门中员工的编号，姓名，所属部门的编号
	select empno,ename,deptno from emp
	where deptno=10 or deptno=20;
	34.案例：查询emp表中没有奖金或者名字的倒数第2个字母不是T的员工的编号，姓名，职位以及奖金
	select empno,ename,job,comm from emp
	where comm is null 
	or ename not like '%t_';
	35.案例：查询工资高于3000或者部门编号是30的员工的姓名，职位，工资，入职时间以及所属部门的编号	
	select ename,job,sal,hiredate,deptno from emp
	where sal>3000 or deptno=30;
	36.案例：查询不是30号部门的员工的所有信息
	select * from emp where deptno!=30;
	37.案例：查询奖金不为空的员工的所有信息
	select * from emp where comm is not null;
	38.案例：查询emp表中所有员工的编号，姓名，职位，根据员工的编号进行降序排列
	select empno,ename,job from emp
	order by empno desc;
	39.案例：查询emp表中部门编号是10号或者30号中，所有员工姓名，职务，工资，根据工资进行升序排列
	select ename,job,sal,deptno from emp
	where deptno=10 or deptno=30
	order by sal;
	40.案例：查询emp表中所有的数据，然后根据部门的编号进行升序排列，如果部门编号一致，根据员工的编号进行降序排列
	select * from emp order by deptno, empno desc;
	41.案例：查询emp表中工资高于1000或者没有上级领导的员工的编号，姓名，工资，所属部门的编号，以及上级领导的编号，根据部门编号进行降序排列，如果部门编号一致根据工资进行升序排列。
	select empno,ename,sal,deptno,mgr from emp
	where sal>1000 or mgr is null
	order by deptno desc, sal;
	
	42.案例：查询emp表中名字中不包含S的员工的编号，姓名，工资，奖金，根据工资进行升序排列，如果工资一致，根据编号进行降序排列
	select empno,ename,sal,comm from emp
	where ename not like '%s%'
	order by sal,empno desc;
	43.案例：统计emp表中员工的总数量
	select count(*) from emp;
	44.案例：统计emp表中获得奖金的员工的数量
	select count(*) from emp
	where comm is not null and comm>0;
	45.案例：求出emp表中所有的工资累加之和
	select sum(sal) from emp;
	46.案例：求出emp表中所有的奖金累加之和
	select sum(comm) from emp;
	47.案例：求出emp表中员工的平均工资
	select avg(sal) from emp;
	48.案例：求出emp表中员工的平均奖金
	select avg(comm) from emp;
	49.案例：求出emp表中员工的最高工资
	select max(sal) from emp;
	50.案例：求出emp表中员工编号的最大值
	select max(empno) from emp;
	51.案例：查询emp表中员工的最低工资。
	select min(sal) from emp;
	52.案例：查询emp表中员工的人数，工资的总和，平均工资，奖金的最大值，奖金的最小值,并且对返回的列起别名。
	select count(*) 人数,sum(sal) 工资总和,avg(sal) 平均工资, max(comm) 奖金最大值, min(comm) 奖金最小值 from emp;
	53.案例：查询emp表中每个部门的编号，人数，工资总和，最后根据人数进行升序排列，如果人数一致，根据工资总和降序排列。
	54.案例：查询工资在1000~3000之间的员工信息，每个部门的编号，平均工资，最低工资，最高工资，根据平均工资进行升序排列。
	55.案例：查询含有上级领导的员工，每个职业的人数，工资的总和，平均工资，最低工资，最后根据人数进行降序排列，如果人数一致，根据平均工资进行升序排列
	56.案例：查询工资在1000~3000之间每一个员工的编号，姓名，职位，工资
	select empno,ename,job,sal from emp
	where sal between 1000 and 3000;
	57.案例：查询emp表中奖金在500~2000之间所有员工的编号，姓名，工资以及奖金
	select empno,ename,comm,sal from emp
	where comm between 500 and 2000;
	58.案例：查询员工的编号是7369，7521，	
	select * from emp
	where empno in (7369,7521);
	59.案例：查询emp表中，职位是ANALYST，
	select * from emp
	where job='analyst';
	60.案例：查询emp表中职位不是ANALYST,
	select * from emp
	where job!='analyst';

###数学相关函数
- floor(num) 向下取整
	
		select floor(3.84); 值为3
- round(num) 四舍五入

		select round(23.8); 值为24
- round(num,m) 四舍五入  m代表小数位数
	
		select round(23.869,2); 值为23.87
- truncate(num,m) 和上面一样（非四舍五入）

		select truncate(23.869,2); 值为23.86
- rand() 随机数 获取0-1的随机数
		
			select rand();
1. 获取 0-5的随机数
2. 获取 3-8的随机数
		
		select floor(rand()*6)+3;
3. 获取 8-10的随机数  0-2  *3 +8
		select floor(rand()*3)+8;

###分组查询
- 分组查询通常和聚合函数结合使用
- 一般情况下 每个部门(职位、分类) 就以部门(职位、分类)作为分组的条件
- 可以有多个分组条件

1. 查询每个部门的最高工资
	
		select deptno,max(sal) from emp group by deptno;
2. 查询每个职位的平均工资
		
		select job,avg(sal) from emp 
		group by job;

3. 查询每个部门下每个主管的手下人数
		
		select deptno,mgr,count(*) from emp
		group by deptno,mgr;

- group by 存在的位置
	select * from emp 
	where ......
	group by ...
	order by ...
	limit ...
- 练习：把昨天作业超纲的三道题做出来  做10分钟休息10分钟
 
53. 案例：查询emp表中每个部门的编号，人数，工资总和，最后根据人数进行升序排列，如果人数一致，根据工资总和降序排列。
	select deptno,count(*),sum(sal) from emp
	group by deptno
	order by count(*),sum(sal) desc;
54. 案例：查询工资在1000~3000之间的员工信息，每个部门的编号，平均工资，最低工资，最高工资，根据平均工资进行升序排列。
	
	select deptno,avg(sal),min(sal),max(sal) from emp 
	where sal between 1000 and 3000
	group by deptno
	order by avg(sal);

55. 案例：查询含有上级领导的员工，每个职业的人数，工资的总和，平均工资，最低工资，最后根据人数进行降序排列，如果人数一致，根据平均工资进行升序排列
	select job,count(*) c,sum(sal),avg(sal) a,min(sal) from emp
	where mgr is not null
	group by job
	order by c desc,a;

###
1. 查询每个部门的平均工资，要求平均工资大于2000。
- 以下为错误语法： where后面不能写聚合函数
		
		select deptno,avg(sal) a from emp
		where a>2000
		group by deptno;
- 使用having 解决聚合函数的条件过滤问题，having写在group by 后面
- where后面写普通字段的过滤条件，having后面写聚合函数的过滤条件

		select deptno,avg(sal) a from emp
		group by deptno
		having a>2000;		
1. 查询每个分类商品的库存总量，高于199999的库存总量
	select category_id,sum(num) s from t_item
	group by category_id
	having s>199999;
2. 查询每个分类商品所对应的平均单价，要求平均单价低于100
	select category_id,avg(price) a from t_item
	group by category_id
	having a<100;
3. 查询分类id为238和917两个分类的平均单价
	select category_id,avg(price) from t_item
	where category_id in(238,917)
	group by category_id;
	
4. 查询emp表中每个部门的平均工资高于2000的部门编号，部门人数，平均工资，最后根据平均工资降序排序
	select deptno,count(*),avg(sal) a from emp
	group by deptno
	having a>2000 
	order by a desc;
5. 查询emp表中工资在1000-3000之间的员工，每个部门的编号，工资总和，平均工资，过滤掉平均工资低于2000的部门，按照平均工资升序排序
	select deptno,sum(sal),avg(sal) a from emp
	where sal between 1000 and 3000
	group by deptno
	having a>=2000 order by a;
6. 查询emp表中名字不是以s开头，每个职位的名字，人数，工资总和，最高工资，过滤掉平均工资是3000的职位，根据人数升序排序，如果一致根据工资总和降序排序
	select job,count(*) c,sum(sal) s,max(sal) from emp where ename not like 's%'
	group by job
	having avg(sal)!=3000
	order by c,s desc;
7. 查询emp表 每年入职的人数（提高题）

	select extract(year from hiredate) year,count(*) from emp group by year;
###子查询(嵌套查询)

1. 查询emp表中工资最高的员工信息
	select max(sal) from emp;
	select * from emp where sal=5000;
- 将以上两条合并成一条sql
	select * from emp where sal=(select max(sal) from emp);
2. 查询emp表中工资超过平均工资的所有员工信息
	select * from emp where sal>(select avg(sal) from emp);
3. 查询工资高于20号部门平均工资的员工信息
	select * from emp where sal>(select avg(sal) from emp where deptno=20);
4. 查询和Jones相同工作的其它员工信息
	select job from emp where ename='jones';
	select * from emp where job=(select job from emp where ename='jones') and ename!='jones';
5. 查询工资最低的员工的相同部门的员工信息
	select min(sal) from emp;

	select deptno from emp where sal=(select min(sal) from emp);

	select * from emp where deptno=(select deptno from emp where sal=(select min(sal) from emp));

- having 要和 group by 结合使用 
6. 查询最后入职的员工信息 

	select * from emp where hiredate=(select max(hiredate) from emp);
	
7. 查询姓名为king的部门编号和部门名称（需要使用dept表）
	select deptno from emp where ename='king';

	select deptno,dname from dept where deptno=(select deptno from emp where ename='king');
8. 查询有商品的分类id和分类名称（有商品 就是在商品表中出现的分类，需要使用t_item_category表）
- 先从商品表中得到所有的分类id 
	 
	select distinct category_id from t_item;
- 从分类表中查询id等于上面结果的分类信息
	select id,name from t_item_category
	where id in(select distinct category_id from t_item);
9. 查询有员工的部门信息
	
	select distinct deptno from emp;

	select * from dept where deptno in(select distinct deptno from emp);
10. 扩展题（难度最高）：查询平均工资最高的部门信息
- 得到最高的平均工资
	select avg(sal) a from emp group by deptno order by a desc limit 0,1;
- 通过最高的平均工资 得到 部门的编号
	select deptno from emp
	group by deptno
	having avg(sal)=(select avg(sal) a from emp group by deptno order by a desc limit 0,1);
- 通过部门编号得到部门信息
	select * from dept where deptno in(select deptno from emp
	group by deptno
	having avg(sal)=(select avg(sal) a from emp group by deptno order by a desc limit 0,1));

###子查询总结
1. 嵌套在SQL语句中查询语句称为子查询
2. 子查询能嵌套n层 
3. 子查询可写的位置：
- 可以写在 where/having的后面作为查询条件的值
- 可以写在 from后面 当一张新表 **新表必须有别名**
- 可以写在创建表的时候
	
		create table t_emp_10 as (select * from emp where deptno=10);
###关联查询
- 同时查询多张表的数据称为关联查询
1. 查询每一个员工的姓名和对应的部门名称
	select e.ename,d.dname
	from emp e,dept d
	where e.deptno=d.deptno;
2. 查询在纽约工作的所有员工的信息
	select e.*
	from emp e,dept d
	where e.deptno=d.deptno and d.loc='new york';
###笛卡尔积

- 关联查询如果不写关联关系，则查询结果为两张表的乘积，这个乘积称为 笛卡尔积
- 笛卡尔积是一种错误的查询结果，工作中切记不要出现

###等值连接和内连接
1. 等值连接：
	select * from A,B 
	where A.x=B.x and A.age=18
2. 内连接 用的更多
	select * from A join B
	on A.x=B.x
	where A.age=18;
- 查询每个员工的姓名和对应的部门名称
	select e.ename,d.dname
	from emp e join dept d
	on e.deptno=d.deptno;
###外连接
	
- 左外连接： 以join 左边表为主表 左边表显示所有数据右边交集数据

	select e.ename,d.dname
	from emp e left join dept d
	on e.deptno=d.deptno;
- 右外连接： 以join 右边表为主表 右边表显示所有数据左边交集数据

	select e.ename,d.dname
	from emp e right join dept d
	on e.deptno=d.deptno;

###关联查询总结：
	
		等值连接，内连接，外连接都是关联查询的查询方式使用哪一种取决于具体业务需求
1. 查两个表的交集数据 使用内连接(推荐)或等值连接
2. 查一个表所有数据另外一个表交集数据使用外连接
	
###内容回顾：
1. 数学相关函数
- floor
- round(num,m) m代表小数位数
- truncat(num,m）非四舍五入
- rand()
2.  group by  ....   having
3. 子查询   写在 where/having 后面 写在 from后面当新表 必须写别名  写在 创建表的时候
4. 关联查询  等值  内  外（左和右）


###作业：

 1. 每个部门的人数,根据人数排序
	 
  2. 每个部门中，每个主管的手下人数
	 
  3. 每种工作的平均工资
	 
  4. 每年的入职人数 
	 
	 
  5. 少于等于3个人的部门信息
 
  6. 拿最低工资的员工信息
	 
  7. 只有一个下属的主管信息
 

  8. 平均工资最高的部门编号
	     
  9. 下属人数最多的人，查询其个人信息
 
  10. 拿最低工资的人的信息
		 
  11. 最后入职的员工信息
		 
  12. 工资多于平均工资的员工信息
		 
  13. 查询员工信息，部门名称
        
  14. 员工信息，部门名称，所在城市
       
  15. DALLAS 市所有的员工信息
		 
16.按城市分组，计算每个城市的员工数量
 
Select count(*),d.deptno,d.dname from emp e right join dept d on e.deptno=d.deptno group by d.deptno;
		 
  17. 查询员工信息和他的主管姓名
	 
  18. 员工信息，员工主管名字，部门名
	 
  20. 员工和他所在部门名
		 
  22. 案例：查询emp表中所有员工的姓名以及该员工上级领导的编号，姓名，职位，工资
		 
  23. 案例：查询emp表中名字中没有字母'K'的所有员工的编号，姓名，职位以及所在部门的编号，名称，地址
		 
  24. 案例：查询dept表中所有的部门的所有的信息，以及与之关联的emp表中员工的编号，姓名，职位，工资
 
	 




