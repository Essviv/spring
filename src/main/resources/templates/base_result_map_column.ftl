<#-- 接受ColumnInfo对象,将它转化成BaseResultMap的一行输出 -->
<#macro baseResultColumn columnInfo>
    <#if columnInfo.isPrimary == true>
        <id column="${columnInfo.colName}" property="${columnInfo.propName}" jdbcType="${columnInfo.type}" />
    <#else>
        <result column="${columnInfo.colName}" property="${columnInfo.propName}" jdbcType="${columnInfo.type}" />
    </#if>
</#macro>

<#-- 接受columnInfo对象,将它转化成insert中的一行输出, 主键不做输出 -->
<#macro insertColumn columnInfo>
    <#if columnInfo.isPrimary != true>
        \#\{${columnInfo.propName},jdbcType=${columnInfo.type}\}
    </#if>
</#macro>

<#-- 主键属性条件 -->
<#macro primaryCondition cols>
    <#list cols as col>
        <#if col.isPrimary>
            ${col.colName} = \#\{${col.propName},jdbcType=${col.type}\}
        </#if>
    </#list>
</#macro>

<#-- 主键属性名称 -->
<#macro primaryColName cols>
    <#list cols as col>
        <#if col.isPrimary>
           ${col.colName}
        </#if>
    </#list>
</#macro>

<#-- 主键属性类型 -->
<#macro primaryColType cols>
    <#list cols as col>
        <#if col.isPrimary>
            ${col.type}
        </#if>
    </#list>
</#macro>

<#-- 接受List<ColumnInfo>, 将它转化成base_column_list -->
<#macro baseColumnList cols>
   <#list cols as col>
       <#if col_has_next>
           ${col.propName},
       <#else>
           ${col.propName}
       </#if>
   </#list>
</#macro>

<#-- 接受List<ColumnInfo>, 将它转化成base_column_list -->
<#macro insertColumnList cols>
    <#list cols as col>
        <#if !col.isPrimary>
            <#if col_has_next>
                ${col.propName},
            <#else>
                ${col.propName}
            </#if>
        </#if>
    </#list>
</#macro>

