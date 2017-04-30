<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.04.2017
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
    <script src="https://use.fontawesome.com/8916abdc27.js"></script>
</head>
<body>
<div class="section"></div>
<main>
    <div class="center-align">
        <div class="section"></div>

        <h5 class="indigo-text">Регистрация</h5>
        <div class="section"></div>

        <div class="container">
            <div class="z-depth-1 grey lighten-4 row"
                 style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

                <form class="col s12" id="reg_form" method="post" action="/registration">
                    <div class="row">
                        <div class="col s12">
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <input class="validate" type="text" name="login" id="login"  required="" pattern=".{6,20}" title="Must be at least 6 characters and no longer than 20 characters."/>
                            <label for="login">Enter your login</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <input class="validate" type="password" name="password" id="password" required="" pattern=".{6,20}" title="Must be at least 6 characters and no longer than 20 characters."/>
                            <label for="password">Enter your password</label>
                        </div>

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input class="validate" type="password" name="passwordRepeat" id="passwordRepeat"/>
                            <label for="passwordRepeat">Repeat your password</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input class="validate" type="email" name="email" id="email"/>
                            <label for="email">Enter your email</label>
                        </div>

                    </div>
                    <br/>
                    <div class="row">
                        <button type="submit" name="btn_login" id="btn_login" class="col s12 btn btn-large waves-effect indigo">Registrate!
                        </button>
                    </div>
                    <div class="row">
                        <a href="/">I have registered</a>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <div class="section"></div>
    <div class="section"></div>
</main>
<script>
    $("#btn_login").click(function(e) {
        e.preventDefault();
        var form=$("#reg_form"), login = form.find('input:eq(0)'), passes = form.find("input[type=password]"), email=$('input[type=email]');
        if(!/^[a-zA-Z_0-9]+$/.test(login.val()))
            return login.addClass('invalid');
        login.removeClass('invalid').addClass('valid');
        if(!/^[a-zA-Z_0-9]+$/.test(passes.eq(0).val()) || passes.eq(0).val() != passes.eq(1).val())
            return passes.each(function(ndx,el){$(el).addClass('invalid')});
        passes.each(function(ndx,el){$(el).removeClass('invalid').addClass('valid');});

        if(!/^\w+@[a-zA-Z_\.\-]+?\.[a-zA-Z]{2,3}$/.test(email.val()))
            return email.addClass('invalid');
        email.removeClass('invalid').addClass('valid');

        form.submit();
    })
</script>
</body>
</html>
