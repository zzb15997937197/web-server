###课程回顾
1. 字符串相关函数
- 拼接字符串  concat(s1,s2)  s1s2
- 获取字符串长度 char_length(str)
- 获取字符串在另外一个字符串的位置 
	instr(str,substr)  locate(substr,str)
- 插入字符串 insert(str,start,length,newstr)
- 转换大小写   upper(str)  lower(str)
- 去空白  trim(str)
- 截取   left(str,count)  right(str,count)  substring(str,start,length)
- 重复 repeat(str,count)  
- 替换 replace(str,old,new)
- 反转 reverse(str)
2. 数学相关
- floor(num) 向下取整
- round(num,m) 四舍五入  m代表小数位数
- truncate(num,m)非四舍五入 m代表小数位数
- rand() 获取0-1的随机数 不包括1    5-8  0-3   floor(rand()*4)+5
3. 分组查询 group by
4. having  
5. 关键字的顺序 
	select * from ... where .... group by ... having....order by ... limit 
6. 子查询    写在where或having后面做条件的值 写在from后面当新表必须写别名   写在创建表时 
7. 关联查询 
- 等值连接： select * from A,B where A.x=B.x and A.age=18;
- 内连接： select * from A join B on A.x=B.x where A.age=18;
- 外连接：select * from A left/right join B on A.x=B.x where A.age=18; 

- 笛卡尔积 

###作业：

1. 每个部门的人数,根据人数排序
	select deptno,count(*) c from emp
	group by deptno
	order by c;
2. 每个部门中，每个主管的手下人数
	select deptno,mgr,count(*) from emp
	where mgr is not null
	group by deptno,mgr;
3. 每种工作的平均工资
	select job,avg(sal) from emp 
	group by job;
4. 每年的入职人数 
	select extract(year from hiredate) year,count(*) from emp
	group by year;
  5. 少于等于3个人的部门信息
	select * from dept where deptno in(select deptno from emp group by deptno
	having count(*)<=3)
  6. 拿最低工资的员工信息
	 select * from emp where sal=(select min(sal) from emp);
  7. 只有一个下属的主管信息
	select mgr from emp
	where mgr is not null
	group by mgr
	having count(*)=1
	
	select * from emp where empno in(select mgr from emp
	where mgr is not null
	group by mgr
	having count(*)=1);
  8. 平均工资最高的部门编号
	-得到每个部门的平均工资 
	select deptno,avg(sal) from emp 
	group by deptno;
	-得到最高平均工资
	select avg(sal) a from emp 
	group by deptno order by a desc limit 0,1;
	-通过最高平均工资得到部门的编号
	select deptno from emp group by deptno having avg(sal)=(select avg(sal) a from emp 
	group by deptno order by a desc limit 0,1);
	-获取部门信息
	select * from dept where deptno in (上面一坨);
  9. 下属人数最多的人，查询其个人信息
	- 得到下属人数 
	select  mgr,count(*) from emp group by mgr;
	- 得到最多下属的人数
	select  count(*) from emp group by mgr order by count(*) desc limit 0,1;
	- 通过人数得到对应的主管编号
	select mgr from emp group by mgr having count(*)=(select  count(*) from emp group by mgr order by count(*) desc limit 0,1);
	- 通过主管编号得到 主管信息
	select * from emp where empno in (上面一坨);
  10. 拿最低工资的人的信息
	select * from emp where sal=(select min(sal) from emp);
  11. 最后入职的员工信息
	select * from emp where hiredate=(select max(hiredate) from emp);		 
  12. 工资多于平均工资的员工信息
	select * from emp where sal>(select avg(sal) from emp);	 
  13. 查询员工信息，部门名称
	select e.*,d.dname
	from emp e join dept d
	on	e.deptno=d.deptno;
  14. 员工信息，部门名称，所在城市
	select e.*,d.dname,d.loc
	from emp e join dept d
	on	e.deptno=d.deptno;
  15. DALLAS 市所有的员工信息
	select e.*
	from emp e join dept d
	on e.deptno=d.deptno
	where d.loc='DALLAS';

  16. 按城市分组，计算每个城市的员工数量
	select d.loc,count(e.ename)
	from emp e right join dept d
	on e.deptno=d.deptno
	group by d.loc;
  17. 查询员工信息和他的主管姓名
	select e.*,m.ename
	from emp e  join emp m
	on e.mgr=m.empno
 
  18. 员工信息，员工主管名字，部门名
	select e.ename,m.ename,d.dname
	from emp e join emp m
	on e.mgr=m.empno
	join dept d
	on e.deptno=d.deptno;
  20. 员工和他所在部门名
	select e.*,d.dname
	from emp e join dept d
	on e.deptno=d.deptno
  22. 案例：查询emp表中所有员工的姓名以及该员工上级领导的编号，姓名，职位，工资
	select e.ename,m.empno,m.ename,m.job,m.sal
	from emp e left join emp m
	on e.mgr=m.empno;
  23. 案例：查询emp表中名字中没有字母'K'的所有员工的编号，姓名，职位以及所在部门的编号，名称，地址
	select e.empno,e.ename,e.job,d.deptno,d.dname,d.loc
	from emp e left join dept d
	on e.deptno=d.deptno
	where e.ename not like '%k%';
  24. 案例：查询dept表中所有的部门的所有的信息，以及与之关联的emp表中员工的编号，姓名，职位，工资
	select d.*,e.empno,e.ename,e.job,e.sal
	from emp e right join dept d
	on e.deptno=d.deptno;


1. 查询商品表和商品分类表 中每个分类名称对应的商品数量(有多少种商品)
	select c.name,count(*)
	from t_item i join t_item_category c
	on i.category_id=c.id
	group by c.name;
2. 查找每种分类库存总量最大的分类名称
	select sum(num) s from t_item
	group by category_id order by s desc limit 0,1;
	
	select category_id from t_item
	group by category_id having sum(num)=(select sum(num) s from t_item
	group by category_id order by s desc limit 0,1);

	select name from t_item_category where id in (上面一坨);
3. 查询每个城市员工的工资总和
	select d.loc,sum(sal)
	from emp e right join dept d
	on e.deptno=d.deptno
	group by d.loc;

###关联关系之表设计
- 外键： 用来建立关系的字段称为外键
- 主键： 用来表示数据唯一性的字段称为主键
####一对一
- 有AB两张表，A表中的一条数据对应B表中的一条数据同时B表一条对应A表一条，这种关系称为一对一
- 应用场景：商品表和商品详情表，   
- 如何建立关系： 在从表中添加外键，外键的值指向主表的主键
- 练习：请设计表保存以下数据
1. 用户名：wukong 密码：123456 昵称：齐天大圣 电话：13733666633 地址：花果山
2. 用户名：bajie 密码：abcd 昵称：二师兄 电话：13833446622 地址：高老庄
3. 用户名：libai 密码：aabbcc 昵称：李白 电话：13355668877 地址：语文书里
	create table user(id int primary key auto_increment,username varchar(10),password varchar(10));
	create table userinfo(userid int,nick varchar(10),tel varchar(15),address varchar(20));
	insert into user values(null,'wukong','123456'),(null,'bajie','bacd'),(null,'libai','aabbcc');
	insert into userinfo values(1,'齐天大圣','13833446622','花果山'),(2,'二师兄','13833446622','高老庄'),(3,'李白','13833446622','语文书里');
- 完成以下查询：
1. 查询李白的用户名和密码是什么
	select u.username,u.password
	from user u join userinfo ui
	on u.id=ui.userid
	where ui.nick='李白';
2. 查询每一个用户的所有信息
	select *
	from user u join userinfo ui
	on u.id=ui.userid;
3. 查询用户名bajie 的昵称是什么
	select ui.nick
	from user u join userinfo ui
	on u.id=ui.userid
	where u.username='bajie';
####一对多
- AB两张表，A表中的一条数据对应B表中的多条数据，同时B表中的一条数据对应A表中的一条数据，称为一对多
- 应用场景： 员工-部门， 商品-分类
- 如何建立关系： 在多的一端添加外键指向另外一张表的主键 
- 练习：创建表保存以下数据   t_emp t_dept
1. 悟空 28岁 3000月薪 神仙部 花果山 
2. 刘备 34岁 8000月薪 三国部 蜀国
3. 路飞 18岁 1000月薪 海贼部 日本
4. 八戒 30岁 4000月薪 神仙部 花果山
	create table t_emp(empno int primary key auto_increment,ename varchar(10),age int,sal int,deptno int);
	create table t_dept(deptno int primary key auto_increment,dname varchar(10),loc varchar(10));
	insert into t_dept values(null,'神仙部','花果山'),(null,'三国部','蜀国'),(null,'海贼部','日本');
	insert into t_emp values(null,'悟空',28,3000,1),(null,'刘备',34,8000,2),(null,'路飞',18,1000,3),(null,'八戒',30,4000,1);
- 做题：
1. 查询每个员工的姓名和部门名
	select e.ename,d.dname
	from t_emp e join t_dept d
	on e.deptno=d.deptno;
2. 查询工作在花果山的员工姓名及工资
	select e.ename,e.sal
	from t_emp e join t_dept d
	on e.deptno=d.deptno
	where d.loc='花果山';

####多对多
- AB两张表，A表中一条数据对应B表中多条数据同时B表中一条数据对应A表中多条，称为多对多 
- 应用场景： 老师-学生   用户-角色 
- 如何建立关系：需要创建新的关系表，表中添加两个外键，指向两个主表的主键
- 练习：创建表保存以下数据
1. 唐僧的学生有：悟空，传奇哥
2. 苍老师的学生有： 传奇哥，克晶姐
	create table teacher(id int primary key auto_increment,name varchar(10));
	create table student(id int primary key auto_increment,name varchar(10));
	create table t_s(tid int,sid int);
	insert into teacher values(null,'唐僧'),(null,'苍老师');
	insert into student values(null,'悟空'),(null,'传奇哥'),(null,'克晶姐');
	insert into t_s values(1,1),(1,2),(2,2),(2,3);
- 查询苍老师的学生姓名
	select s.name
	from teacher t join t_s ts
	on t.id=ts.tid
	join student s
	on ts.sid=s.id
	where t.name='苍老师';
- 查询传奇哥的老师姓名
	select t.name
	from teacher t join t_s ts
	on t.id=ts.tid
	join student s
	on ts.sid=s.id
	where s.name='传奇哥'
###表设计之权限管理案例
- 创建三张主表user(id,name) role(id,name) module(id,name) 和两张关系表 u_r(uid,rid)（用户和角色） r_m(rid,mid)（角色和权限）
	create table user(id int primary key auto_increment,name varchar(10));	 
	create table role(id int primary key auto_increment,name varchar(10));	
	create table module(id int primary key auto_increment,name varchar(10));
	create table u_r(uid int,rid int);
	create table r_m(rid int,mid int);
	
- 保存以下数据：  
用户表：刘德华，貂蝉
	insert into user values(null,'刘德华'),(null,'貂蝉');
角色表：男游客，男管理员，女游客，女会员
	insert into role values(null,'男游客'),(null,'男管理员'),(null,'女游客'),(null,'女会员');
权限表：男浏览，男发帖，男删帖，女浏览，女发帖
	insert into module values(null,'男浏览'),(null,'男发帖'),(null,'男删帖'),(null,'女浏览'),(null,'女发帖');
关系：男游客->男浏览；男管理员->男浏览，男发帖，男删帖；女游客-》女浏览；女会员-》女浏览，女发帖
刘德华-》男管理员和女游客
貂蝉-》女会员和男游客
	insert into r_m values(1,1),(2,1),(2,2),(2,3),(3,4),(4,4),(4,5);
	insert into u_r values(1,2),(1,3),(2,4),(2,1);
- 练习：
1. 查询每个用户对应的所有权限
	select u.name,m.name
	from user u join u_r ur
	on u.id=ur.uid
	join r_m rm 
	on ur.rid=rm.rid
	join module m
	on rm.mid=m.id;
2. 查询刘德华的所有权限
	select m.name
	from user u join u_r ur
	on u.id=ur.uid
	join r_m rm 
	on ur.rid=rm.rid
	join module m
	on rm.mid=m.id 
	where u.name='刘德华';
3. 查询拥有男浏览权限的用户都是谁
	select u.name
	from user u join u_r ur
	on u.id=ur.uid
	join r_m rm 
	on ur.rid=rm.rid
	join module m
	on rm.mid=m.id 
	where m.name='男浏览';