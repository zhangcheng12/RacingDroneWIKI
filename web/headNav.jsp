<%--
  Created by IntelliJ IDEA.
  User: asus-pc
  Date: 2017/8/28
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <button id="back" onclick="javascript:history.back(-1);" title="返回">
        <P>&nbsp;&lt;&nbsp;</P>
    </button>
    <button id="home" title="返回首页">
        <a href="index.html">
            <P>首页</P>
        </a>
    </button>
    <nav >
        <button class="item">
            <a href="/moto.list" >
                <p>电机</p>
            </a>
        </button>
        <button class="item">
            <a href="/esc.list" >
                <p>电调</p>
            </a>
        </button>
        <button class="item">
            <a href="/fra.list" >
                <p>机架</p>
            </a>
        </button>
        <button class="item">
            <a href="/it.list" >
                <p>图传</p>
            </a>
        </button>
        <button class="item">
            <a href="/cam.list" >
                <p>摄像头</p>
            </a>
        </button>
        <button class="item">
            <a href="/fc.list" >
                <p>飞控</p>
            </a>
        </button>
        <button class="item">
            <a href="/ant.list" >
                <p>天线</p>
            </a>
        </button>
        <button class="item">
            <a href="/ph.list" >
                <p>分电板</p>
            </a>
        </button>
        <button class="item">
            <a href="prop.list" >
                <p>桨叶</p>
            </a>
        </button>
    </nav>
    <form id="searchBar" method="get" action="/search">
        <input id="search" type="text" name="model" placeholder="  输入设备型号搜索...">
        <input id="searchButton" type="submit" value="搜索">
    </form>
</header>