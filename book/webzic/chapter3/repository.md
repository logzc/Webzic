Repository使用
=======

## 使用

声明一个interface，标注上@Repository即可

## 自定义规则

[参考Spring](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

| Keyword | Sample | JPQL snippet |
| :-------------- | :------------ | :------------ |
| And | findByLastnameAndFirstname | ... where x.lastname = ?1 and x.firstname = ?2 |
| Or | findByLastnameOrFirstname | ... where x.lastname = ?1 or x.firstname = ?2 |


