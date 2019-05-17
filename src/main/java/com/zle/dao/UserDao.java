package com.zle.dao;

import com.zle.entity.db.BookEntity;
import com.zle.entity.db.UserEntity;
import com.zle.entity.db.UserEntityExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.FifoCache;

/**
 * 二级缓存配置
 * 1 映射语句文件中的所有 SELECT 语句将会被缓存 。
 * 2 映射语句文件中的所有 时SERT 、 UPDATE 、 DELETE 语句会刷新缓存 。
 * 3 缓存会使用 Least Recently Used ( LRU，最近最少使用的）算法来收回 。
 * 4 根据时间表（如 no Flush Interval ，没有刷新间隔），缓存不会以任何时间顺序来刷新 。
 * 5 缓存会存储集合或对象（无论查询方法返回什么类型的值）的 1024 个引用。
 * 6 缓存会被视为 read/write （可读／可写）的 ， 意味着对象检索不是共享的，而且可以安全
 *   地被调用者修改，而不干扰其他调用者或线程所做的潜在修改 。
 * 7.所有的这些属性都可以通过缓存元素的属性来修改，示例如下 。
 *    <cache
 *       evictio口＝ ” FIFO ”
 *       flushlnterval=” 6000 0”
 *       size=” 512 ”
 *       readOnly=” true " />
 *   这个更高级的配置创建了 一个 FIFO 缓存，并每隔 60 秒刷新一次，存储集合或对象的 512
 *   个引用 ，而且返回的对象被认为是只读的 ， 因此在不同线程中的调用者之间修改它们会导致冲
 *   突 。 cache 可以配置的属性如下。
 *   eviction （收回策略）
 *    LRU （最近最少使用的） ： 移除最长时间不被使用的对象，这是默认值 。
 *    FIFO（先进先出〉 ： 按对象进入缓存的顺序来移除它们 。
 *    SOFT（软引用） ： 移除基于垃圾回收器状态和软引用规则的对象 。
 *    WEAK（弱引用） ： 更积极地移除基于垃圾收集器状态和弱引用规则的对象 。
 *
 * 8. flushinterval （刷新间隔〉。可以被设置为任意的正整数 ， 而且它们代表一个合理
 * 的毫秒形式的时间段 。 默认情况不设置，即没有刷新间 隔 ， 缓存仅仅在调用语句时刷新。
 * 9. size （引用数目）。 可以被设置为任意正整数，要记住缓存的对象数目和运行环境的可
 * 用内存资源数目。默认值是 1024 。
 * 10. readOnly （只读）。属性可以被设置为 true 或 false 。只读的缓存会给所有调用者
 * 返回缓存对象的相同实例，因此这些对象不能被修改 ， 这提供了很重要的性能优势 。 可
 * 读写的缓存会通过序列化返回缓存对象的拷贝 ， 这种方式会慢一些，但是安全 ， 因此默
 * 认是 false.
 */
@Mapper
@CacheNamespace(eviction = FifoCache.class ,
                flushInterval = 10000 ,
                readWrite = true,
                size = 10 )
public interface UserDao {
    long countByExample(UserEntityExample example);

    int deleteByExample(UserEntityExample example);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (name, age, ",
        "email, date, date_time, ",
        "stamp)",
        "values (#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{email,jdbcType=VARCHAR}, #{date,jdbcType=DATE}, #{dateTime,jdbcType=TIMESTAMP}, ",
        "#{stamp,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByExample(UserEntityExample example);

    @Select({
        "select",
        "id, name, age, email, date, date_time, stamp",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.UserDao.BaseResultMap")
    UserEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByExample(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByPrimaryKeySelective(UserEntity record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "date = #{date,jdbcType=DATE},",
          "date_time = #{dateTime,jdbcType=TIMESTAMP},",
          "stamp = #{stamp,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserEntity record);


    /**
     * 查询人员订阅信息
     * @return
     */
    List<UserEntity> selectOrders();


    List<UserEntity> selectOrders2();
    List<UserEntity> selectOrders3();

    @Delete("delete from book where id=10000")
    int deleteBook();

    /**
     * 查询一条记录
     * @return
     */
    @Select({
            "select",
            "a.name,c.name as 'book.name',c.price as 'book.price'",
            "from user a left join book_order b ",
            "on a.id=b.uid",
            "left join book c",
            "on b.bid=c.id",
            "where a.id = 1 and c.id=1"
    })
    @ResultMap("com.zle.dao.UserDao.BaseResultMap")
    UserEntity selectKey();


    @Select({
            "select",
            "id, name, age, email, date, date_time, stamp",
            "from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultType(Map.class)
    Map selectPrimaryKey(Integer id);

}