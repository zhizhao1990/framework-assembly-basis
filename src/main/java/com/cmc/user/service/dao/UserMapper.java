package com.cmc.user.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.cmc.user.facade.entity.User;

public interface UserMapper {
    
    @Delete({
    	"update `user`",
        "set is_delete = '0'",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into user (name, sex, ",
        "age, create_time, ",
        "update_time, delete_time, ",
        "is_delete, temp1, temp2, ",
        "temp3)",
        "values (#{name,jdbcType=VARCHAR}, #{sex,jdbcType=CHAR}, ",
        "#{age,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{deleteTime,jdbcType=TIMESTAMP}, ",
        "#{isDelete,jdbcType=CHAR}, #{temp1,jdbcType=VARCHAR}, #{temp2,jdbcType=VARCHAR}, ",
        "#{temp3,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=String.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=String.class)
    // 将其设置为true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false。
    @Options(flushCache = true) 
    int insertSelective(User record);

    @Select({
        "select",
        "id, name, sex, age, create_time, update_time, delete_time, is_delete, temp1, ",
        "temp2, temp3",
        "from user",
        "where id = #{id,jdbcType=VARCHAR} AND is_delete ='1'"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_time", property="deleteTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.CHAR),
        @Result(column="temp1", property="temp1", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp2", property="temp2", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp3", property="temp3", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String id);
    
    @Select({
    	"SELECT *",
    	"FROM `user`",
    	"WHERE is_delete = '1'",
    	"ORDER BY update_time DESC",
    	"LIMIT #{begin,jdbcType=INTEGER}, #{offset,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_time", property="deleteTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.CHAR),
        @Result(column="temp1", property="temp1", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp2", property="temp2", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp3", property="temp3", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectList(@Param("begin") Long begin, @Param("offset")Long offset);
    
    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=CHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "delete_time = #{deleteTime,jdbcType=TIMESTAMP},",
          "is_delete = #{isDelete,jdbcType=CHAR},",
          "temp1 = #{temp1,jdbcType=VARCHAR},",
          "temp2 = #{temp2,jdbcType=VARCHAR},",
          "temp3 = #{temp3,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);
    
	 @Select({
		 "SELECT COUNT(*)",
		 "FROM `user`",
		 "WHERE is_delete = '1'"
	})
	Long countUsers();
	
	 @Select({
    	"SELECT *",
    	"FROM `user`",
    	"WHERE is_delete = '1'",
    	"AND name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_time", property="deleteTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.CHAR),
        @Result(column="temp1", property="temp1", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp2", property="temp2", jdbcType=JdbcType.VARCHAR),
        @Result(column="temp3", property="temp3", jdbcType=JdbcType.VARCHAR)
 })
    User selectByName(String name);

}