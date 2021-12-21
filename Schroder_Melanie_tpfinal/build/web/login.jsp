<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Inicio de Sesión</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="carpetaLogin/images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="carpetaLogin/vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetaLogin/css/util.css">
        <link rel="stylesheet" type="text/css" href="carpetaLogin/css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="SvLogin" method="POST">
                        <span class="login100-form-logo">
                            <i class="zmdi zmdi-landscape"></i>
                        </span>

                        <span class="login100-form-title p-b-34 p-t-27">
                            Iniciar Sesión
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "Ingrese un usuario válido">
                            <input class="input100" type="text" name="usuario" placeholder="Usuario">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Ingrese una contraseña correcta">
                            <input class="input100" type="password" name="contra" placeholder="Contraseña">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>


                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Iniciar Sesión
                            </button>
                        </div>


                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/bootstrap/js/popper.js"></script>
        <script src="carpetaLogin/vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/daterangepicker/moment.min.js"></script>
        <script src="carpetaLogin/vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="carpetaLogin/js/main.js"></script>

    </body>
</html>
