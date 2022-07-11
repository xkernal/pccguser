package com.pccg.pccguser.infrastructure.persistence.mapper;

import java.util.List;

import com.pccg.pccguser.infrastructure.persistence.entity.UserDO;
import com.pccg.pccguser.interfaces.api.UserQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("insert into user1(user_name, password, email, mobile, status, create_time, modify_time) values"
        + "(#{userName}, #{password}, #{email}, #{mobile}, #{status}, #{createTime}, #{modifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insert(UserDO userDO);

    @Select("select * from user1 where user_name = #{userName}")
    UserDO selectByUserName(String userName);

    @Select("select * from user1 where id = #{id}")
    UserDO selectById(long id);

    @Select({"<script>",
        "SELECT * FROM user1",
        "        <where>",
        "            <foreach item=\"item\" index=\"index\" collection=\"status\" open=\"status in (\" ",
        "separator=\",\" close=\")\" nullable=\"true\">",
        "                #{item}",
        "            </foreach>",
        "        </where>",
        "</script>"})
    List<UserDO> selectByPage(UserQuery userQuery);

    @Update({"<script>",
        "update user1",
        "  <set>",
        "    <if test='password != null'>password=#{password},</if>",
        "    <if test='email != null'>email=#{email},</if>",
        "    <if test='mobile != null'>mobile=#{mobile},</if>",
        "    <if test='status != null'>status=#{status},</if>",
        "    modify_time=#{modifyTime}",
        "  </set>",
        "where id=#{id}",
        "</script>"})
    int updateByPK(UserDO userDO);

    @Update({"<script>",
        "update user1 set status = #{status}",
        "        <where>",
        "            <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"id in (\" separator=\",\"",
        " close=\")\" nullable=\"true\">",
        "                #{item}",
        "            </foreach>",
        "        </where>",
        "</script>"})
    int batchUpdateByPK(List<Long> ids, int status);
}
