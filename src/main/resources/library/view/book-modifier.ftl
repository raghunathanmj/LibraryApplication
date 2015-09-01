<#import '/library/view/scripts.ftl' as Scripts/>
<#import '/library/view/book-searcher.ftl' as BookSearch/>

<html ng-app="bookModifierApp">
<@Scripts.Scripts/>
<script src="/Assets/js/book-modifier.js"></script>
<title>Book Modification</title>
<style>
    .form-group input.ng-invalid.ng-touched {
        border-color: #a94442;
    }

    .form-group input.ng-valid.ng-touched {
        border-color: #3c763d;
    }
</style>
<body>
<nav class="nav navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Book Modification Page</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="#">Author Modification</a></li>
                <li class="active"><a>Book Modification</a></li>
                <li><a href="#">Book Withdrawal</a></li>
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
    <div class="row" ng-controller="BookModifierCtrl">
        <div class="col-sm-4">
            <form class="form-horizontal" name="insert">
                <fieldset>
                    <legend class="text-center">Book Insertion</legend>
                    <div class="form-group">
                        <label for="book-insert-isbn" class="col-sm-3 control-label">ISBN</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="book-insert-isbn" ng-model="i.isbn" name="iIsbn" placeholder="ISBN" required integers>
                            <span ng-show="insert.iIsbn.$error.required && insert.iIsbn.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="insert.iIsbn.$error.integers && !insert.iIsbn.$error.required" style="color: #a94442">Only positive numbers</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bool-insert-name" class="col-sm-3 control-label">Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bool-insert-name" ng-model="i.name" name="iName" placeholder="Book Name" required alphabets>
                            <span ng-show="insert.iName.$error.required && insert.iName.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="insert.iName.$error.alphabets && !insert.iName.$error.required" style="color: #a94442">Only one alphabetic Name</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="book-insert-qty" class="col-sm-3 control-label">Quantity</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="book-insert-qty" ng-model="i.qty" name="iQty" placeholder="Initial Quantity" required integers>
                            <span ng-show="insert.iQty.$error.required && insert.iQty.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="insert.iQty.$error.integers && !insert.iQty.$error.required" style="color: #a94442">Only positive integers</span>
                        </div>
                    </div>
                    <div class="form-group" ng-repeat="ite in iAnames track by $index">
                        <label for="book-insert-isbn" class="col-sm-3 control-label">Author {{$index+1}}</label>
                        <div class="col-sm-8">
                            <ng-form name="newAuthor">
                                <input type="text" class="form-control" id="book-insert-isbn" ng-model="ite.name" name="name" placeholder="Autrhor Name" required alphabets>
                                <span ng-show="newAuthor.name.$error.required && newAuthor.name.$touched" style="color: #a94442">Can't be Empty</span>
                                <span ng-show="newAuthor.name.$error.alphabets && !newAuthor.name.$error.required" style="color: #a94442">Only one alphabetical name</span>
                            </ng-form>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8 text-center">
                            <button type="button" class="btn btn-primary" ng-click="insertAddAuthor()">Add Author</button>
                            <button type="button" class="btn btn-success" ng-click="insert.$valid && insertBook(i)">Insert</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col-sm-4">
            <form name="modify" class="form-horizontal">
                <fieldset>
                    <legend class="text-center">Book Modification by ISBN</legend>
                    <div class="form-group">
                        <label for="book-modify-isbn" class="col-sm-3 control-label">ISBN</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="book-modify-isbn" ng-model="m.isbn" name="mIsbn" required integers placeholder="ISBN of book to be modified">
                            <span ng-show="modify.mIsbn.$error.required && modify.mIsbn.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="modify.mIsbn.$error.integers && !modify.mIsbn.$error.required" style="color: #a94442">Only positive integers</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="book-modify-name" class="col-sm-3 control-label">Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="book-modify-name" ng-model="m.name" name="mName" required alphabets placeholder="Book Name">
                            <span ng-show="modify.mName.$error.required && modify.mName.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="modify.mName.$error.alphabets && !modify.mName.$error.required" style="color: #a94442">Only one alphabetic name</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="book-modify-qty" class="col-sm-3 control-label">Quantity</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="book-modify-qty" ng-model="m.qty" name="mQty" required integers placeholder="New Quantity">
                            <span ng-show="modify.mQty.$error.required && modify.mQty.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="modify.mQty.$error.integers && !modify.mQty.required" style="color: #a94442">Only positive integers</span>
                        </div>
                    </div>
                    <div class="form-group" ng-repeat="ite in mAnames track by $index">
                        <label for="book-modify-author" class="col-sm-3 control-label">Author {{$index+1}}</label>
                        <div class="col-sm-8">
                            <ng-form name="newAuthor">
                                <input type="text" class="form-control" id="book-modify-author" name="name" ng-model="ite.name" placeholder="Author Name" required alphabets>
                                <span ng-show="newAuthor.name.$error.required && newAuthor.name.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="newAuthor.name.$error.alphabets && !newAuthor.name.required" style="color: #a94442">Only one alphabetic name</span>
                            </ng-form>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8 text-center">
                            <button type="button" class="btn btn-primary" ng-click="modifyAddAuthor()">Add Author</button>
                            <button type="button" class="btn btn-warning" ng-click="modify.$valid && modifyBook(m)">Modify</button>
                            <button type="button" class="btn btn-primary" ng-click="import()">Import</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>
        <div class="col-sm-4">
            <form name="dele" class="form-horizontal">
                <fieldset>
                    <legend>Book Deletion by ISBN</legend>
                    <div class="form-group">
                        <label for="book-delete-isbn" class="col-sm-3 control-label">ISBN</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="book-delete-isbn" ng-model="d.isbn" name="dIsbn" required integers placeholder="ISBN of book to be deleted">
                            <span ng-show="dele.dIsbn.$error.required && dele.dIsbn.$touched" style="color: #a94442">Can't be empty</span>
                            <span ng-show="dele.dIsbn.$error.integers && !dele.dIsbn.$error.required" style="color: #a94442">Only positive integers</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8 text-center">
                            <button type="button" class="btn btn-danger" ng-click="dele.$valid">Delete</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        {{chosenBook}}
    </div>
    <@BookSearch.BookSearch/>
</div>
</body>
</html>