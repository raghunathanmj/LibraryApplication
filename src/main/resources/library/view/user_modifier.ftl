<#-- @ftlvariable name="" type="com.example.views.PersonView" -->
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<body>
<!-- calls getPerson().getName() and sanitizes it -->

<form id = "insert">
    <fieldset>
        <legend>User Creation</legend>
        Username: <input id = "username_post" type="text"><br>
        Password: <input id = "password_post" type="text"><br>
        Name: <input id = "name_post" type = "text"><br>
        <input type="button" id = "insert_button" value="Create new User" onclick="poster()">
    </fieldset>
</form>
<!--<a href="http://127.0.0.1:8080/user/logout">logout</a>-->
<#--<form>
    <fieldset id = "update">
        <legend>Author Updation</legend>
        ID: <input id = "id_put" type="number"><br>
        Name: <input id = "name_put" type="text"><br>
        <input type="button" id = "update_button" value="Update the Author with given ID" onclick="putter()">
    </fieldset>
</form>-->

<#--<form>-->
    <#--<fieldset>-->
        <#--<legend>User Deletion</legend>-->
        <#--ID: <input id = "id_delete" type="number"><br>-->
        <#--<input type="button" id = "delete_button" value="Delete the Author with given id" onclick="deleter()">-->
    <#--</fieldset>-->
<#--</form>-->

<p id = "return_value"></p>
<script>
    function poster() {
        var u, p, n;
        $u = document.getElementById("username_post").value;
        $p = document.getElementById("password_post").value;
        $n = document.getElementById("name_post").value;

        $.ajax({
            type: "POST",
            url: "http://127.0.0.1:8080/user/create",
            data: JSON.stringify({username: $u, password: $p, name: $n}),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                document.getElementById("return_value").innerHTML = data;
            },
            error: function() {
                document.getElementById("return_value").innerHTML = "ERR";
            }
        });
    }
//    function putter() {
//        var i, t;
//        i = document.getElementById("id_put").value;
//        t = document.getElementById("name_put").value;
//
//        $.ajax({
//            type: "PUT",
//            url: "http://localhost:8080/author/update",
//            data: JSON.stringify({authorID: i, name: t}),
//            contentType: "application/json; charset=utf-8",
//            crossDomain: true,
//            dataType: "json"
//        });
//    }
//    function deleter() {
//        var i, t;
//        i = document.getElementById("id_delete").value;
//
//        $.ajax({
//            type: "DELETE",
//            url: "http://localhost:8080/author/del",
//            data: JSON.stringify({authorID: i, name: "god knows what"}),
//            contentType: "application/json; charset=utf-8",
//            crossDomain: true,
//            dataType: "json"
//        });
//    }
</script>
</body>
</html>

