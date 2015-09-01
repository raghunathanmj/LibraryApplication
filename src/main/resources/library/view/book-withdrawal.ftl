<#import "/library/view/template/scripts.ftl" as Scripts/>
<#import "/library/view/template/book-searcher.ftl" as BookSearch/>
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
        <form class="text-center">
            <div class="form-group">
                <button type="submit" class="btn btn-primary" ng-click="validBook() && withdrawal()">Withdraw Book</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
