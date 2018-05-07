<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">

    <title>老杨直播整合</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/mdui/0.4.1/css/mdui.min.css"/>
    <script src="http://cdn.bootcss.com/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
</head><style>
    .mdui-card-primary {
        padding: 0px;
    }
    .mdui-chip-title {
        height: 32px;
        line-height: 32px;
        padding-left: 0px;
        padding-right: 12px;
        font-size: 14px;
        vertical-align: middle;
        display: inline-block;
    }
    .mdui-chip {
        box-sizing: border-box;
        display: inline-block;
        height: 32px;
        border-radius: 0px;
        white-space: nowrap;
        background-color: white;
        cursor: pointer;
        user-select: none;
        margin: 0px 0;
        will-change: box-shadow;
        transition: box-shadow 0.25s cubic-bezier(0.4, 0, 0.2, 1);
    }

    .mdui-card-primary-title {
        display: block;
        font-size: 15px;
        line-height: 15px;
        opacity: 0.87;
    }
.splittip{position: relative; line-height: 15px; max-height: 15px;overflow: hidden;}
.splittip::after{content: "..."; position: absolute; bottom: 0; right: 0; padding-left: 40px;
background: -webkit-linear-gradient(left, transparent, #fff 55%);
background: -o-linear-gradient(right, transparent, #fff 55%);
background: -moz-linear-gradient(right, transparent, #fff 55%);
background: linear-gradient(to right, transparent, #fff 55%);
}
</style>
<body class=" mdui-appbar-with-toolbar">

<div class="mdui-appbar mdui-appbar-fixed mdui-appbar-scroll-hide">
    <div class="mdui-toolbar mdui-color-indigo">
        <a href="javascript:;" id="menuhide" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">menu</i></a>
        <a href="javascript:;" class="mdui-typo-headline">MDUI</a>
        <a href="javascript:;" class="mdui-typo-title">老杨直播整合平台</a>
        <div class="mdui-toolbar-spacer"></div>
        <div style="width: 300px;">
            <div class="mdui-textfield mdui-textfield-expandable mdui-float-right">
                <button class="mdui-textfield-icon mdui-btn mdui-btn-icon"><i
                        class="mdui-icon material-icons">search</i></button>
                <input class="mdui-textfield-input" type="text" placeholder="Search"/>
                <button class="mdui-textfield-close mdui-btn mdui-btn-icon"><i
                        class="mdui-icon material-icons">close</i></button>
            </div>
        </div>
        <a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">refresh</i></a>
        <a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">more_vert</i></a>
    </div>
</div>

<div class="mdui-container-fluid">
    <div id="main-drawer"
         class="main-drawer mdui-drawer-open mdui-col-xs-2 mdui-col-sm-2 mdui-col-md-2 mdui-col-lg-2 mdui-col-xl-1">
        <ul class="mdui-list" mdui-collapse="{accordion: true}" style="margin-bottom: 76px;">

            <li class="mdui-list-item mdui-ripple">
             
                <div class="mdui-list-item-content">英雄联盟</div>
            </li>
             <li class="mdui-list-item mdui-ripple">
                <div class="mdui-list-item-content">颜值</div>
            </li>	
            <li class="mdui-collapse-item mdui-collapse-item-close">
                <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                    
                    <div class="mdui-list-item-content">其他</div>
                    <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
                </div>
                <ul class="mdui-collapse-item-body mdui-list mdui-list-dense">
                    <li class="mdui-list-item mdui-ripple" style="padding-left:30px;">Overview</li>
                    <li class="mdui-list-item mdui-ripple" style="padding-left:30px;">Language</li>
                    <li class="mdui-list-item mdui-ripple" style="padding-left:30px;">Location</li>
                    <li class="mdui-list-item mdui-ripple" style="padding-left:30px;">New vs Returning</li>
                </ul>
            </li>
        </ul>
    </div>
    <div id="gridList" class="mdui-col-xs-10 mdui-col-sm-10 mdui-col-md-10 mdui-col-lg-10 mdui-col-xl-11">
        	<c:forEach items="${list}" var="l">
        		<a href="${l.rootHref}${l.roomID}" target="_blank">
                <div class="mdui-col-sm-6 mdui-col-md-3 mdui-col-xl-2" style="margin-top:20px;">
                    <div class="mdui-card">
                        <div class="mdui-card-media">
                            <img  class="mdui-img-fluid" src="${l.picSrc}"/>
                        </div>
                        <div class="mdui-card-actions mdui-card-actions-stacked">
                            <div class="mdui-card-primary">
                                <div class="mdui-card-primary-title splittip">${l.roomTittle}</div>
                                <div class="mdui-card-primary-subtitle">
                                    <div class="mdui-chip mdui-float-left">
                                        <i class="mdui-icon material-icons">&#xe7fd;</i>
                                        <span class="mdui-chip-title">${l.name}</span>
                                    </div>
                                    <div class="mdui-chip mdui-float-right">
                                        <i class="mdui-icon material-icons">&#xe417;</i>
                                        <span class="mdui-chip-title">${l.num}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        	</c:forEach>
        </div>
    </div>
</div>
</body>
<script>
    $("#menuhide").click(function () {
        if ($("#main-drawer").hasClass("mdui-hidden")) {
            $("#main-drawer").removeClass("mdui-hidden");
            $("#gridList").addClass(" mdui-col-xs-10 mdui-col-sm-10 mdui-col-md-10 mdui-col-lg-10 mdui-col-xl-11");
        } else {
            $("#main-drawer").addClass("mdui-hidden");
            $("#gridList").removeClass();
        }

    })


</script>
</html>