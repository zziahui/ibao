<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-sm-2 col-lg-2">
    <div class="sidebar-nav">
        <div class="nav-canvas">
            <div class="nav-sm nav nav-stacked">

            </div>
            <ul class="nav nav-pills nav-stacked main-menu">
                <li class="nav-header">Main</li>
                <li><a class="ajax-link" href="<%=request.getContextPath()%>/index.html"><i class="glyphicon glyphicon-home"></i><span> 首页</span></a>
                </li>
                <li><a class="ajax-link" href="<%=request.getContextPath()%>/admin/user.html"><i class="glyphicon glyphicon-eye-open"></i><span> 用户</span></a>
                </li>
                <li><a class="ajax-link" href="<%=request.getContextPath()%>/admin/style.html"><i class="glyphicon glyphicon-edit"></i><span> 业务类型</span></a></li>
                <li><a class="ajax-link" href="<%=request.getContextPath()%>/admin/account.html"><i class="glyphicon glyphicon-list-alt"></i><span> 账户</span></a>
                </li>
                <li><a class="ajax-link" href="<%=request.getContextPath()%>/admin/task.html"><i class="glyphicon glyphicon-font"></i><span> 任务</span></a>
                </li>
                <li><a class="ajax-link" href="gallery.html"><i class="glyphicon glyphicon-picture"></i><span> Gallery</span></a>
                </li>
                <li class="nav-header hidden-md">Sample Section</li>
                <li><a class="ajax-link" href="table.html"><i class="glyphicon glyphicon-align-justify"></i><span> Tables</span></a></li>
                <li class="accordion">
                    <a href="#"><i class="glyphicon glyphicon-plus"></i><span> Accordion Menu</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="#">Child Menu 1</a></li>
                        <li><a href="#">Child Menu 2</a></li>
                    </ul>
                </li>
                <li><a class="ajax-link" href="calendar.html"><i class="glyphicon glyphicon-calendar"></i><span> Calendar</span></a>
                </li>
                <li><a class="ajax-link" href="grid.html"><i class="glyphicon glyphicon-th"></i><span> Grid</span></a></li>
                <li><a href="tour.html"><i class="glyphicon glyphicon-globe"></i><span> Tour</span></a></li>
                <li><a class="ajax-link" href="icon.html"><i class="glyphicon glyphicon-star"></i><span> Icons</span></a></li>
                <li><a href="error.html"><i class="glyphicon glyphicon-ban-circle"></i><span> Error Page</span></a>
                </li>
                <li><a href="login.html"><i class="glyphicon glyphicon-lock"></i><span> Login Page</span></a>
                </li>
            </ul>
            <label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
        </div>
    </div>
</div>