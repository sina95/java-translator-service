<%-- 
    Document   : index
    Created on : Mar 8, 2021, 10:03:46 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prevoditelj index</title>
        <script type="text/javascript">
            function go() {
                var languageFrom = document.getElementById("language_from").value;
                var languageTo = document.getElementById("language_to").value;
                url = 'http://localhost:8080/WebService/resource/Translator/' + document.getElementById("word").value + '/' + languageFrom + '/' + languageTo;
                window.open(url,'_blank');
            }            
        </script>
    </head>
    <body>
        <h1>Dobrodosli, ovo je servis za prevodjenje sa engleskog na ruski i obrnuto</h1>
        <p>Unesite rijec koju zelite da prevedete, a zatim odaberite jezik sa kojeg prevodite. Sve to potvrdite na obradu sa dugmetom ispod. Uzivajte!</p>
        <p>Primjeri: butterfly:babochka ; hello:privet ; see you:uvidimsya</p>
        <br />
        
        <form>
            Tvoja rijec:
            <input type="text" name="word" id="word" />
            <label for="language_from">Sa jezika:</label>
            <select name="language_from" id="language_from">
                <option value="en">English</option>
                <option value="ru">Russian</option>
            </select>
            <label for="language_to">Na jezik:</label>
            <select name="language_to" id="language_to">                
                <option value="ru">Russian</option>
                <option value="en">English</option>
            </select>
           
        </form>
        <button onclick="go()">Prevedi</button>
    </body>
</html>
