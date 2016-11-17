<#-- 输出mapper.xml文件, 输入参数为tableName(String), cols(List<ColumnInfo>), qualifiedClassName<String> -->
<#import "base_result_map_column.ftl" as my/>

<#assign qualifiedClassName=mapperInfo.qulifierClassName tableName=mapperInfo.tableName  cols=mapperInfo.cols/>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="templates.RoleMapper">
    <resultMap id="BaseResultMap" type="${qualifiedClassName}">
        <#list cols as col>
            <@my.baseResultColumn col/>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <@my.baseColumnList cols/>
    </sql>

    <select id="get" resultMap="BaseResultMap" parameterType="<@my.primaryColType cols/>">
        select
            <include refid="Base_Column_List"/>
        from
            ${tableName}
        where
            <@my.primaryCondition cols/>
    </select>

    <update id="delete" parameterType="<@my.primaryColType cols/>">
        update
            ${tableName}
        set

        where
            <@my.primaryCondition cols/>
    </update>

    <insert id="insert" parameterType="${qualifiedClassName}"
            useGeneratedKeys="true" keyProperty="<@my.primaryColName cols/>">
        insert into
            ${tableName}(<@my.insertColumnList cols/>)
        values
            (<#list cols as col>
                <@my.insertColumn col/>
            </#list>)
    </insert>
</mapper>