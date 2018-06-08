<%--
  Created by IntelliJ IDEA.
  User: 24757
  Date: 2018/5/21
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Log in / Sign up</title>



  <link rel="stylesheet" href="css/index-style.css">


</head>

<body>
<div class="themetitle"><h1>OnlineQuiz</h1></div>
<div class="container">
  <div class="box"></div>
  <div class="container-forms">
    <div class="container-info">
      <div class="info-item">
        <div class="table">
          <div class="table-cell">
            <p>
              已经注册过啦?
            </p>
            <div class="btn">
              登录～
            </div>
          </div>
        </div>
      </div>
      <div class="info-item">
        <div class="table">
          <div class="table-cell">
            <p>
              没有账户?
            </p>
            <div class="btn">
              注册～
            </div>

          </div>
        </div>
      </div>
    </div>
    <div class="container-form">
      <form method="post" action="login" class="form-horizontal">
        <div class="form-item log-in">
          <div class="table">
            <div class="table-cell">
              <input name="username" placeholder="邮箱" type="text" value="a0@test.com"/>
              <input name="password" placeholder="密码" type="Password" value="123456"/>
              <%--<div class="btn">--%>
                <%--Log in--%>
              <%--</div>--%>
                <div style="text-align: center">

                <input type="radio" name="identity" value="用户" id="l_gamer" checked="checked"/>
                <label class="choice" for="l_gamer">玩家</label>

                <input type="radio" name="identity" id="l_admin" value="管理员"/>
                <label class="choice" for="l_admin">管理员</label>
              </div>
              <div class="btn" onClick="document.forms[0].submit();">
                登录
              </div>

            </div>
          </div>
        </div>
      </form>

      <div class="form-item sign-up">
        <form method="post" action="register" class="form-horizontal">
        <div class="table">

          <div class="table-cell">
            <input name="email" placeholder="邮箱" type="text" />
            <input name="name" placeholder="用户名" type="text" />
            <input name="mobile" placeholder="电话" type="text" />
            <input name="password" placeholder="Password" type="Password" />
            <div style="text-align: center">

              <input type="radio" name="type" value="gamer" id="gamer" checked="checked">
              <label class="choice" for="gamer">玩家</label>

              <input type="radio" name="type" value="admin" id="admin">
              <label class="choice" for="admin">管理员</label>
            </div>
            <div class="btn" onClick="document.forms[1].submit();">
              注册
            </div>
            <%--<input type="submit" class="btn btn-primary btn-sm" value="提交"/>--%>
          </div>
        </div>
        </form>
      </div>

    </div>
  </div>
</div>
<script src='js/jquery-2.2.4.min.js'></script>
<script  src="js/index.js"></script>
</body>
</html>

