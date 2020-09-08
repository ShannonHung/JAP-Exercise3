# JAP-Exercise3
有幾個要注意的點
1. Let’s also set the ==cascade to `CascadeType.ALL`== for `List<Plant> plants` in Delivery.java, to make it easier for us to persist everything at once for testing.
> 幹這很重要，不然的話delivery新增可是plant那邊都不會有變動，也不會更新幹! 忘記改了，之前教學是寫delete，一定要記得改成ALL

2. NamedQueries有幾項要注意，在註解裡面
```java
@NamedQueries({
            //這裡個query不能使用List<plant>因為他們是透過joined的方式把兩個table連接起來，實際上delivery並沒有plants
            @NamedQuery(name = "FIND_DELIVERY_BY_NAME",
                query = "SELECT new com.udacity.JPAExercise2.Repository.DeliveryDTO(" +
                        "d.name, d.address, d.deliveryTime)" +
                        "from Delivery d where d.name = :name"),
            @NamedQuery(name = "FIND_BY_NAME",
                    query = "select d from Delivery d where d.name = :name")
            //query裡面的from entity一定要跟class名稱一樣因為他是jpql是透過object去資料庫找
}
)
```
