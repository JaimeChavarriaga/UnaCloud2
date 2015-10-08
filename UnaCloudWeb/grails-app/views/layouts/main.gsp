<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>UnaCloud | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <asset:stylesheet src="main.css"/>
        <asset:stylesheet src="application.css"/>
        <!-- bootstrap 3.0.2 -->
        <asset:stylesheet src="bootstrap.min.css"/>
        <!-- font Awesome -->
        <asset:stylesheet src="font-awesome.min.css"/>
        <!-- Ionicons -->
        <asset:stylesheet src="ionicons.min.css"/>
        <asset:stylesheet src="plugins/datatables/dataTables.bootstrap.css"/>
        <!-- jQuery 2.0.2 -->
        <asset:javascript src="/plugins/jquery/jquery_2_0_2.min.js"/>
        <!-- Bootstrap -->
        <asset:javascript src="/plugins/bootstrap/bootstrap.min.js"/>    
        <asset:javascript src="/plugins/bootbox/bootbox.js"/>  
        <!-- Datatables -->
        <asset:javascript src="/plugins/datatables/jquery.dataTables.js"/>    
        <asset:javascript src="/plugins/datatables/dataTables.bootstrap.js"/>
        <asset:javascript src="ui.js"/>  
        <g:layoutTitle/>
    </head>
    <body class="skin-black fixed">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="/" class="logo"><i class="fa fa-cloud"></i>
                UnaCloud
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>${session.user.name}<i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                   <g:img dir="images/user" file="icon_user.png" class="img-circle" alt="User Image" />
                                    <p>
                                        ${session.user.name } - ${session.user.description}
                                        <small>Member since ${session.user.registerDate}</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                   		<a href="${createLink(uri: '/logout', absolute: true)}" class="btn btn-default btn-flat">Sign Out</a>                                       
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">                
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <g:img dir="images/user" file="icon_user.png" class="img-circle" alt="User Image"/>
                        </div>
                        <div class="pull-left info">
                            <p>Hello, ${session.user.username}</p>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li id="services-tree" class="treeview">
                            <a href="#">
                                <i class="fa fa-bar-chart-o"></i>
                                <span>Services</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="${createLink(uri: '/services/image/list', absolute: true)}"><i class="fa fa-angle-double-right"></i> My Images</a></li>
                                <li><a href="${createLink(uri: '/services/cluster/list', absolute: true)}"><i class="fa fa-angle-double-right"></i> My Clusters</a></li>
                                <li><a href="${createLink(uri: '/services/deployment/list', absolute: true)}"><i class="fa fa-angle-double-right"></i> My Deployments</a></li>
                            </ul>
                        </li>
                         <li id="external-tree" class="treeview">
                            <a href="#">
                                <i class="fa fa-cloud-upload"></i>
                                <span>External Services</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i>My Accounts</a></li>
                                <li><a href="pages/charts/flot.html"><i class="fa fa-angle-double-right"></i>My Deployments</a></li>
                                <li><a href="pages/charts/inline.html"><i class="fa fa-angle-double-right"></i>My Storage</a></li>
                            </ul>
                        </li>
                        <g:if test="${session.user.isAdmin()}">
	                        <li id="admin-tree" class="treeview">
	                            <a href="#">
	                                <i class="fa fa-laptop"></i>
	                                <span>Administration</span>
	                                <i class="fa fa-angle-left pull-right"></i>
	                            </a>
	                            <ul class="treeview-menu">
	                                <li><a href="pages/UI/general.html"><i class="fa fa-angle-double-right"></i> General</a></li>
	                                <li><a href="pages/UI/icons.html"><i class="fa fa-angle-double-right"></i> Icons</a></li>
	                                <li><a href="pages/UI/buttons.html"><i class="fa fa-angle-double-right"></i> Buttons</a></li>
	                                <li><a href="pages/UI/sliders.html"><i class="fa fa-angle-double-right"></i> Sliders</a></li>
	                                <li><a href="pages/UI/timeline.html"><i class="fa fa-angle-double-right"></i> Timeline</a></li>
	                            </ul>
	                        </li>
	                        <li id="monitoring-tree" class="treeview">
	                            <a href="#">
	                                <i class="fa fa-bar-chart-o"></i> <span>Monitoring</span>
	                                <i class="fa fa-angle-left pull-right"></i>
	                            </a>
	                            <ul class="treeview-menu">
	                                <li><a href="pages/forms/general.html"><i class="fa fa-angle-double-right"></i> General Elements</a></li>
	                                <li><a href="pages/forms/advanced.html"><i class="fa fa-angle-double-right"></i> Advanced Elements</a></li>
	                                <li><a href="pages/forms/editors.html"><i class="fa fa-angle-double-right"></i> Editors</a></li>                                
	                            </ul>
	                        </li>
	                        <li id="config-tree" class="treeview">
	                            <a href="#">
	                                <i class="fa fa-edit"></i> <span>Configuration</span>
	                                <i class="fa fa-angle-left pull-right"></i>
	                            </a>
	                            <ul class="treeview-menu">
	                                <li><a href="pages/tables/simple.html"><i class="fa fa-angle-double-right"></i> Simple tables</a></li>
	                                <li><a href="pages/tables/data.html"><i class="fa fa-angle-double-right"></i> Data tables</a></li>
	                            </ul>
	                        </li>  
                        </g:if>                   
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <g:layoutBody/>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

		<asset:javascript src="main.js"/>
		<asset:javascript src="cloud.js"/>   		
		<script>$(document).on('ready',activator())</script>
    </body>
</html>