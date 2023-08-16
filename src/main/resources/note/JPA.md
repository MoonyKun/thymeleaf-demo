# JPA

框架：框架就是一套规范。 既然是规范，你使用这个框架就要遵守这个框架所规定的约束。 在Java开发中，框架是用一套规则+一群jar包来表示的

规范：它是一种人为的、约定成俗的，即使不按照那种规定也不会出错，这种规定就叫作规范。. 虽然我们不按照规范也不会出错，但是那样代码写得就会很乱。

## 概念

JPA：是一套ORM（对象[关系模型](https://so.csdn.net/so/search?q=关系模型&spm=1001.2101.3001.7020)）规范，可以通过**注解**或者**XML**描述【对象-关系表】之间的映射关系，并将实体对象**持久化**到数据库中。

spirng data jpa：是spring提供的一套简化JPA开发的框架，按照约定好的【方法命名规则】写dao层接口，就可以在不写接口实现的情况下，实现对数据库的访问和操作。

## 注解

常用注解如下：

- **@Entity** 声明类为实体或表。

- **@Table** 声明表名。

- **@Basic** 指定非约束明确的各个字段。该注解默认已经添加

- **@Id** 指定的类的属性，用于识别（一个表中的主键）。

- @GeneratedValue 指定如何标识属性可以被初始化，例如自动、手动、或从序列表中获得的值。

  TABLE：使用一个特定的数据库表格来保存主键。
  SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
  IDENTITY：主键由数据库自动生成（主要是自动增长型）
  AUTO：主键由程序控制。

- **@Transient** 指定的属性，它是不持久的，即：该值永远不会存储在数据库中

- **@JoinColumn** 指定一个实体组织或实体的集合。这是用在多对一和一对多关联。

- **@Column** 指定持久属性栏属性。

## 查询方式

- JPQL 

```JAVA
@Query(value = "delete from Emp s where s.empId in (?1)")
```

- 原生SQL

```
@Query(value = "delete from Emp s where s.empId in (?1)",nativeQuery = true)
```

- 方法名约定查询

- 通过接口查询

## 接口

### Repository接口：

若只是简单的对单表进行crud只需要继承JpaRepository接口,传递了两个参数:1.实体类,2.实体类中主键类型

### JPASpecificationExecutor接口：

泛型内传入实体类,只要简单实现`toPredicate`方法就可以实现复杂的查询,

接口中提供的方法：

```JAVA
public interface JpaSpecificationExecutor<T> {
// 查询单个对象
    T findOne(Specification<T> spec);
// 查询所有对象
    List<T> findAll(Specification<T> spec);
// 查询所有对象并分页
    Page<T> findAll(Specification<T> spec, Pageable pageable);
// 查询所有对象并排序
    List<T> findAll(Specification<T> spec, Sort sort);
// 根据条件统计
    long count(Specification<T> spec);
}
```

**Specification中参数**

需要自定义自己的Specification中的参数

- root: 查询的根对象（查询的任何属性都可以从根对象中获取）

- creteriaQuery: 顶层查询对象，自定义查询方式（了解一般不用）

- CriteriaBuilder：查询构造器，封装了许多查询条件（模糊匹配）（or,and）

查询方式

1. 查询方式：CB对象
2. 比较的属性：root对象

## 分页查询实现

分页查询需要使用到 Pageable 初始化的参数为当前页，以及每页的限制

```JAVA
// 例子
public List<Emp> listEmp(EmpQuery empQuery) {
        Specification<Emp> empSpecification = (root, criteriaQuery, criteriaBuilder) -> getEmpPredicate(root, criteriaBuilder, empQuery);
        Pageable pageable = PageRequest.of(empQuery.getStart(), empQuery.getLimit());
        Page<Emp> all = empRepository.findAll(empSpecification, pageable);
        return all.getContent();
    }
// 查询构建
private Predicate getEmpPredicate(Root<Emp> root, CriteriaBuilder criteriaBuilder, EmpQuery empQuery) {
        List<Predicate> predicates = new ArrayList<>();
        if (empQuery.getName() != null && !"".equals(empQuery.getName())) {
            Path<Object> name = root.get("name");
            Predicate like = criteriaBuilder.like(name.as(String.class), "%" + empQuery.getName() + "%");
            predicates.add(like);
        }
        if (empQuery.getStartDate() != null) {
            Path<Object> birthday = root.get("birthday");
            Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(birthday.as(Date.class), empQuery.getStartDate());
            predicates.add(startDate);
        }
        if (empQuery.getEndDate() != null) {
            Path<Object> birthday = root.get("birthday");
            Predicate endDate = criteriaBuilder.lessThanOrEqualTo(birthday.as(Date.class), empQuery.getEndDate());
            predicates.add(endDate);
        }
        Predicate[] p = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(p);
    }
```

## JPQL 或 原生SQL 语句查询

当前端返回的参数无法被SQl语句直接处理时：

```JAVA
public void deleteEmp(String ids) {
        // 接收包含empId的字符串，并将它分割成字符串数组
        String[] empList = ids.split(",");
        // 将字符串数组转为List<Integer> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for (String str : empList) {
            LString.add(new Integer(str));
        }
        empRepository.deleteBatch(LString);
    }
    
    // 这个是通过spring data拼接关键字进行的操作
    @Modifying
    @Transactional
    @Query(value = "delete from Emp s where s.empId in (?1)",nativeQuery = true)
    void deleteBatch(List<Integer> ids);
```

## 多表操作

多表关系：一对一，一对多（多的一方称为从表，一的为主表，主要通过主键与外键实现），多对多（中间表实现；为两个表的主键）

实体类中的关系：包含关系：通过包含关系描述表关系，继承关系。

### 一对多

注解配置一对多关系

```JAVA
    // 声明关系，配置外键
    @OneToMany(targetEntity = Emp.class)
    @JoinColumn(name = "dept_id",referencedColumnName = "dept_id")
    private Set<Emp> empSet = new HashSet<>();
    
    //    多对一关系可以通过注解实现
	//    外键可以通过注解生成
	//    @JoinColumn(referencedColumnName = "dept_id",name = "dept_id")
	//    referencedColumnName： 主表主键名称
	//    name：从表外键名称
    @ManyToOne(targetEntity = Dept.class)
    @JoinColumn(referencedColumnName = "dept_id",name = "dept_id")
    private Dept dept;
```

**如果在多的一方和一的一方都配置了关联关系，在双方都可以维护外键**

放弃维护外键需要放弃外键维护权（通过注解实现）；

```JAVA
	// 只声明关系 mappedBy: 为参照类中的属性
	@OneToMany(mappedBy = "dept")
    private Set<Emp> empSet = new HashSet<>();
```

删除操作：

级联：操作一个对象的同时操作他的关联对象（级联添加，级联删除，级联更新）(**级联与维护外键是两个操作**)

注意：需要区分操作主体，在操作主体的实体类上，添加级联属性 cascade(配置级联)（**你在操作的类，操作那个类就在那个类上添加属性**）就是在实体的关联注解上使用级联类型:

级联添加，级联删除都需要配置cascade 一下就可以了，注意配置的位置在操作的对象中

```JAVA
	@ManyToOne(targetEntity = Dept.class,cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "dept_id",name = "dept_id")
    private Dept dept;
```

### 多对多

配置用户到角色的多对多

声明表关系，配置中间表（包含两个外键）

```JAVA
// 在不同类中配置注意都要改
// Jointable： name:中间表的名称，
//	joinColumns： 当前对象在中间表的外键，
//	inverseJoinColumns：对方对象在中间表的外键
//		JoinColumn：
//			name ：在中间表中的外键名
//			referencedColumnName：参照主表的主键名
@ManyToMany(targetEntity = "")
    @JoinTable(name = ""
    ,joinColumns = {@JoinColumn(name = "",referencedColumnName = "")}
    ,inverseJoinColumns = {@JoinColumn(name = "",referencedColumnName = "")})
```

多对多的级联操作与一对多的相似

### 多表查询

对象导航查询（查询一个对象同时查询其关联对象）

对象导航查询默认使用延迟加载的形式

调用get对象不会立即发送查询，而是在关联对象被使用时才会查询 

fetch eager：立即加载，lazy延迟加载 相当于左外连接查询

多查询一：

### JPA级联查询出现的死循环问题

当实体使用一对一/一对多/多对多双向关联时，出现如下两种情况时，会出现死循环:

1.实体类的toString()方法中包含对级联对象的输出，代码中调用了实体类的toString()方法。

2.接口查询时，对实体对象进行序列化。

