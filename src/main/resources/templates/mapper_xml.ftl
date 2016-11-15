<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="templates.RoleMapper">
    <resultMap id="BaseResultMap" type="${qualifiedClassName}">
    </resultMap>

    <sql id="Base_Column_List">
  </sql>

    <select id="get" resultMap="BaseResultMap" parameterType="java.lang.String">
        select
          <include refid="Base_Column_List"/>
        from
          ${tableName}
        where
    </select>

    <update id="delete" parameterType="java.lang.String">
        update
            ${tableName}
        set
        where
    </update>

    <insert id="insert" parameterType="${qualifiedClassName}"
        useGeneratedKeys="true" keyProperty="${primaryPropertyName}">
    insert into
        ${tableName}
    values ()
  </insert>
</mapper>