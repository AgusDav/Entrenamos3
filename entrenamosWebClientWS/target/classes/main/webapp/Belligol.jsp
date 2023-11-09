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
            background-image: url('https://cloudfront-eu-central-1.images.arcpublishing.com/prisaradio/45AV7TSTWNOSTOQYC473FC5IJY.jpg');
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
            position: relative;
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

        #myVideoText {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }

        #playButton {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: url('https://cdn-icons-png.flaticon.com/512/148/148744.png') no-repeat center center;
            background-size: contain;
            width: 60px;
            height: 60px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="myVideoText">Apreta no seas puto</div>
    <button id="playButton"></button>
    <video controls autoplay id="myVideo">
        <source src="bellingham.mp4" type="video/mp4">
        Tu navegador no soporta la reproducción de videos.
    </video>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var video = document.getElementById('myVideo');
        var playButton = document.getElementById('playButton');

        playButton.addEventListener('click', function() {
            video.play();
            playButton.style.display = 'none';
        });
    });
</script>

</body>
</html>
