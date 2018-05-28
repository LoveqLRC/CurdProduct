<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/24
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" align="center" width="88%">
    <tr>
        <td colspan="7">
            <form action="${pageContext.request.contextPath }/findProductByCondition" method="post">
                商品名称:<input name="name">&nbsp;&nbsp;&nbsp;&nbsp;关键词:<input name="kw">&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="查询">
            </form>
        </td>
        <td colspan="1" align="right">
            <input type="button" value="删除选中" onclick="delCheck()">
        </td>
    </tr>
    <tr>
        <td><input type="checkbox" onclick="checkAll(this)"></td>
        <th>pid</th>
        <th>商品图片</th>
        <th>商品名称</th>
        <th>市场价</th>
        <th>商城价</th>
        <th>商品描述</th>
        <th>操作</th>
    </tr>
    <form id="formId" action="${pageContext.request.contextPath }/delCheckedProduct" method="post">
        <c:forEach items="${list }" var="p">
            <tr>
                <td width='1%'><input type="checkbox" name="pid" value="${p.pid }"></td>
                <td width='8%'>${p.pid }</td>
                <td width='8%'><img alt="" src="${pageContext.request.contextPath }/${p.pimage}" width="80"></td>
                <td width='8%'>${p.pname }</td>
                <td width='8%'>${p.market_price }</td>
                <td width='8%'>${p.shop_price }</td>
                <td>${p.pdesc }</td>
                <td width='8%'>
                    <a href="${pageContext.request.contextPath }/getProductById?pid=${p.pid}">修改</a>
                    |
                    <a href="javascript:void(0)" onclick="deleteP('${p.pid}')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </form>
</table>
</body>
<script type="text/javascript">
    function deleteP(obj) {
        if (confirm("确定删除?")) {
            location.href = "${pageContext.request.contextPath}/deleteProductById?pid=" + obj;
        }
    }

    function checkAll(obj) {
        var arr = document.getElementsByName("pid");

        for (var i = 0; i < arr.length; i++) {
            arr[i].checked = obj.checked;
        }
    }

    function delCheck() {
        //提交表单
        // document.getElementById("formId").submit();
        document.getElementById("formId").submit();
    }

</script>
</html>
