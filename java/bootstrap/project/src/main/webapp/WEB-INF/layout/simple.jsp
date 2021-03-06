<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/_shared/taglib.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="<c:url value='/resources/ico/favicon.ico' />">

    <title><tiles:getAsString name="title"/></title>

    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular-touch.min.js"></script>
    <script src="<c:url value='/resources/lib/angular-ui/0.11.0/ui-bootstrap-tpls-0.11.0.min.js' />"></script>
    <script src="<c:url value='' />"></script>

    <!-- Bootstrap core CSS -->
    <%-- 
    <link href="<c:url value='/resources/lib/bootstrap/3.1.1/css/bootstrap.min.css' />" rel="stylesheet"> 
    <link href="<c:url value='/resources/lib/bootstrap/3.1.1/css/font-awesome.min.css' />" rel="stylesheet">
    --%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <tiles:insertAttribute name="header"/>

    <div class="container">
      <tiles:insertAttribute name="body"/>
    </div><!-- /.container -->

    <div id="footer">
      <div class="container">
	      <tiles:insertAttribute name="footer"/>
      </div>
    </div><!-- /.footer -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <%-- <script src="<c:url value='/resources/lib/bootstrap/3.1.1/js/bootstrap.min.js' />"></script> --%>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
  </body>
</html>
