<#import "/library/view/scripts.ftl" as Scripts/>
<#import "/library/view/book-searcher.ftl" as BookSearch/>
<html ng-app="bookWithdrawalApp">
<@Scripts.Scripts/>
<script src="/Assets/js/book-withdrawal.js"></script>
<title>Book Search and Withdrawal</title>

<style type="text/css">
    .form-group input.ng-invalid.ng-touched {
        border-color: #a94442;
    }

    .form-group input.ng-valid.ng-touched {
        border-color: #3c763d;
    }
</style>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Book Search and Withdrawal</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="#">Author Modification</a></li><!--Current page's link shouldn't be clickable-->
                <li><a href="#">Book Modification</a></li>
                <li class="active"><a>Book Withdrawal</a></li>
                <li><a href="#">User Modification</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>Signed in as</a></li>
                <li class="active"><a>Username</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <@BookSearch.BookSearch/>
    <div class="row" ng-controller="BookWithdrawalController">
        <p>{{chosenBook}}</p>
        <form class="text-center">
            <div class="form-group">
                <button type="submit" class="btn btn-primary" ng-click="validBook() && withdrawal()">Withdraw Book</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<#--<form id = "searcher">-->
    <#--<fieldset>-->
        <#--<legend>Search Book Details by ISBN</legend>-->
        <#--ISBN: <input id = "isbn" type="number"><br>-->
        <#--<input type="button" id = "search_button" value="Search" onclick="search()">-->
    <#--</fieldset>-->
<#--</form>-->

<#--<br>-->
<#--<input type="button" id = "show_all" value="Show entire book table" onclick="showAll()">-->
<#--<p id = "tablesHere">aa</p>-->

<#--<br><a href="http://localhost:8080/user/logout">logout</a>-->

<script>
//    function search() {
//        document.getElementById("tablesHere").innerHTML = "";
//
//        var i = document.getElementById("isbn").value;
//
//        $.getJSON( "http://localhost:8080/library/search/" + i, function( data ) {
//            var $container = document.getElementById("tablesHere");
//
//            var $item = document.createElement("table");
//            $item.style.margin = "3px";
//            $item.style.border = "1";
//
//            var $row = document.createElement("tr");
//            $.each( data, function( key, val ) {
//                var $cell1 = document.createElement("td");
//                $cell1.innerHTML = "" + (key);
//                $row.appendChild($cell1);
//            });
//            $item.appendChild($row);
//
//            $row = document.createElement("tr");
//            $.each( data, function( key, val ) {
//                var $cell1 = document.createElement("td");
//                $cell1.innerHTML = "" + (val);
//                $row.appendChild($cell1);
//            });
//            $item.appendChild($row);
//            $container.appendChild($item);
//        });
//    }
//    function showAll() {
//        document.getElementById("tablesHere").innerHTML = "";
//        $.getJSON( "http://localhost:8080/library/show-all", function( data ) {
//            var i;
//            var $container = document.getElementById("tablesHere");
//            var $item = document.createElement("table");
//            //$item.style.border = "1";
//
//            var $row = document.createElement("tr");
//            $.each( data[0], function( key, val ) {
//                var $cell = document.createElement("td");
//                $cell.innerHTML = "" + key;
//                $row.appendChild($cell);
//            });
//            $item.appendChild($row);
//
//            for (i = 0; i < data.length; i++) {
//                var $row = document.createElement("tr");
//                $.each( data[i], function( key, val ) {
//                    var $cell = document.createElement("td");
//                    $cell.innerHTML = "" + val;
//                    $row.appendChild($cell);
//                });
//                $item.appendChild($row);
//            }
//            $container.appendChild($item);
//        });
//    }
</script>