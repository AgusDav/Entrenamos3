<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Consulta Usuario</title>
    <style>
        body {
            background-image: url('https://wallpapercave.com/dwp1x/wp3006474.jpg');
            background-size: cover;
            background-attachment: fixed;
            background-repeat: no-repeat;
            background-position: center top;
        }

        body {
            background-color: #363D4B;
            color: #FFFFFF;
            font-family: Arial, sans-serif;
        }

        .container {
            margin: 0 auto;
            max-width: 600px;
            padding: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #FFFFFF;
        }

        input, select {
            width: 100%;
            padding: 5px;
            margin: 5px 0;
            background-color: #FFFFFF;
            color: #000000;
            border: 1px solid #000000;
        }

        input:focus, select:focus {
            filter: none;
            outline: none;
            border: 1px solid #007BFF;
        }
    </style>
</head>
<body>
<div class="container">
    <video controls autoplay muted id="myVideo">
        <source src="bellingham.mp4" type="video/mp4">
        Tu navegador no soporta la reproducción de videos.
    </video>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var video = document.getElementById('myVideo');

        // Espera hasta que el video esté listo para reproducirse
        video.addEventListener('canplaythrough', function() {
            // Reproduce el video
            video.play().then(function() {
                // Quitar el mute después de iniciar la reproducción
                video.muted = false;
            }).catch(function(error) {
                console.log("Error al intentar reproducir el video con sonido:", error);
            });
        });
    });
</script>
</body>
</html>
