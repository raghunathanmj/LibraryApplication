<#-- @ftlvariable name="" type="com.example.views.PersonView" -->
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="863691724658-7g666g617bn75q24vov9ssuece7m80i1.apps.googleusercontent.com">
<link rel="stylesheet" type="text/css" href="/Assets/css/back.css">
<link rel="stylesheet" type="text/css" href="/Assets/css/navigation_bar.css">
<link rel="stylesheet" type="text/css" href="/Assets/css/forms.css">
<style>
    div#outermost_div {
        display:flex;
        justify-content:space-around;
    }
    div#update_button_div {
        display:flex;
        justify-content:space-around;
    }
    div#insert_button_div {
        display:flex;
        justify-content:space-around;
    }
    div#delete_button_div {
        display:flex;
        justify-content:space-around;
    }
</style>

<body class = "back" onload="start()">
    <div id='cssmenu' class="cssmenu">
        <ul>
            <li class='active'><a href='#'><span>Authors</span></a></li>
            <li><a href='#'><span>Books</span></a></li>
            <li><a href='#'><span>Users</span></a></li>
            <li class='last'><a href="https://accounts.google.com/logout" onclick="signOut()"><span>Logout</span></a></li>
        </ul>
    </div>

    <br>
    <br>

    <div id = 'outermost_div'>
        <div class = "book_input" id="insert_div">
            <h1>Book Creation</h1><br>
            <input id = "isbn_post" type="number" name="isbn" placeholder="ISBN"><br>
            <input id = "title_post" type="text" name="title1" placeholder="Title"><br>
            <div id = "author_list">
                <div><input id = "author_0" type="number" name="title2" placeholder="AuthorID 0"><br></div>
            </div>
            <div id = "insert_button_div">
                <input type = "button" value = "Add author" onclick="new_entry()">
                <input type="button" id = "insert_button" value="Create new book" onclick="poster()">
            </div>
        </div>


        <div class="book_input" id="book_update">
            <h1>Book Updation</h1>
            <input id = "isbn_put" type="number" name="isbn" placeholder="ISBN"><br>
            <input id = "title_put" type="text" name="title" placeholder="Title"><br>
            <div id="update_button_div">
                <input type="button" id = "update_button" value="Update by isbn" onclick="putter()">
            </div>
        </div>

        <div class = "book_input" id="delete_div">
            <h1>Book Deletion</h1>
            <input id = "isbn_delete" type="number" name="isbn" placeholder="ISBN"><br>
            <div id="delete_button_div">
                <input type="button" id = "delete_button" value="Delete by isbn" onclick="deleter()">
            </div>
        </div>
    </div>
    <p id="testing"></p>
    <p id="sas"></p>
    <#--<div class="g-signin2" data-onsuccess="onSignIn"></div>-->
    <input type="button" id="test_button" onclick="tester()">
</body>
<script>
    var $authIndex = 1;
    var id_token = "";
    var auth2;
    function poster() {
        var i, t;
        i = document.getElementById("isbn_post").value;
        t = document.getElementById("title_post").value;

        var iter, authorArray = "";

        for (iter = 0; iter < $authIndex; iter++) {
            authorArray = authorArray.concat(document.getElementById("author_" + iter).value);
            if (iter != $authIndex-1) authorArray = authorArray.concat(",");
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/library/pseudo-create/",
            data: JSON.stringify({pseudoIsbn:i,pseudoTitle:t,authorList:authorArray}),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json"
        });
        document.getElementById("demo").innerHTML = JSON.stringify({pseudoIsbn: i, pseudoTitle: t, authorList:authorArray});

    }
    function putter() {
        var i, t;
        i = document.getElementById("isbn_put").value;
        t = document.getElementById("title_put").value;

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/library/update",
            data: JSON.stringify({isbn: i, title: t}),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json"
        });
    }
    function deleter() {
        var i, t;
        i = document.getElementById("isbn_delete").value;

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/library/del",
            data: JSON.stringify({isbn: i, title: "god knows what"}),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json"
        });
    }

    function new_entry() {
        var $container = document.getElementById('author_list'), $item, $field;

        $item = document.createElement('div');
        //$item.style.margin = '3px';

        //$field = document.createElement('span');
        //$field.innerHTML = 'AuthorID ' + $authIndex + ": ";
        //$field.style.marginRight = '10px';
        //$item.appendChild($field);

        $field = document.createElement('input');
        $field.id = 'author_' + $authIndex;
        $field.type = 'number';
        $field.placeholder = 'AuthorID ' + $authIndex;
        $item.appendChild($field);
        $authIndex = $authIndex + 1;

        $container.appendChild($item);
    }

    function start() {

        var initSigninV2 = function() {
            auth2 = gapi.auth2.init({
                client_id: '863691724658-7g666g617bn75q24vov9ssuece7m80i1.apps.googleusercontent.com',
                scope: 'profile',
                cookie_policy: 'single_host_origin'
            });
            auth2.currentUser.listen(tracker);
        };
        
        var tracker = function (user) {
            id_token = user.getAuthResponse().id_token;
        }

        gapi.load('auth2', initSigninV2);
    }
    function signOut() {
        var authi = gapi.auth2.getAuthInstance();
        authi.signOut().then(function () {
            authi.disconnect();
            alert('User signed out.');
        });
        var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' + id_token;
        $.ajax({
            type: 'GET',
            url: revokeUrl,
            async: false,
            contentType: "application/json",
            dataType: 'jsonp',
            success: function (nullResponse) {
                // Do something now that user is disconnected
                // The response is always undefined.
                alert("logou");
            },
            error: function (e) {
                // Handle the error
                // console.log(e);
                // You could point users to manually disconnect if unsuccessful
                // https://plus.google.com/apps
            }
        });
        alert('Logout');
    }

</script>
</html>
