<#macro BookSearch>
<div class="row" ng-controller="BookSearcherCtrl">
    <div class="col-sm-3">
        <form class="form-horizontal" name="book">
            <fieldset>
                <legend class="text-center">Search by Book Details</legend>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="book-isbn">ISBN</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="book-isbn" placeholder="ISBN" ng-model="b.isbn" integers name="bIsbn">
                        <span ng-show="book.bIsbn.$error.integers" style="color: #a94442">Only positive integers</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="book-name">Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="book-name" placeholder="Book Name" ng-model="b.name" alphabets name="bName">
                        <span ng-show="book.bName.$error.alphabets" style="color: #a94442">Only one alphabetic name</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-49 text-center">
                        <button type="submit" class="btn btn-primary" ng-click="author.$valid && validSearch('Book', b.isbn, b.name) && bookSearch(b)">Search</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <form class="form-horizontal" name="author">
            <fieldset>
                <legend class="text-center">Search by Author Details</legend>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="author-id">ID</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="author-id" placeholder="Author ID" ng-model="a.id" name="aId" integers>
                        <span ng-show="author.aId.$error.integers" style="color: #a94442">Only positive integers</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="author-name">Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="author-name" placeholder="Author Name" ng-model="a.name" name="aName" alphabets>
                        <span  ng-show="author.aName.$error.alphabets" style="color: #a94442">Only one alphabetic name</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9 text-center">
                        <button type="submit" class="btn btn-primary" ng-click="author.$valid && validSearch('author', a.id, a.name) && authorSearch(a)">Search</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <form class="form-horizontal">
            <div class="col-sm-12 text-center">
                <button type="button" class="btn btn-primary" ng-click="getAllBooks()">Show All Books</button>
            </div>
        </form>
    </div>
    <div class="col-sm-9">
        <fieldset>
            <legend>{{tableTitle}}</legend>
            <table class="table table-hover table-bordered">
                <tr>
                    <th class="col-sm-2">ISBN</th>
                    <th class="col-sm-3">Title</th>
                    <th class="col-sm-5">Authors</th>
                    <th class="col-sm-1">Remaining</th>
                    <th class="col-sm-1">Choose</th>
                </tr>
                <tr ng-repeat="row in bookTable track by $index">
                    <td>{{row.isbn}}</td>
                    <td>{{row.name}}</td>
                    <td><em ng-repeat="author in row.authNames track by $index">{{author}}<em ng-show="!$last">,  </em></em></td>
                    <td>{{row.remaining}}</td>
                    <#--<td><input type="checkbox" ng-show="row.remaining > 0" ng-change="checkBoxSelect(row)" ng-bind-html="modelText(row.isbn)" ></td>-->
                    <td><input type="checkbox" ng-show="row.remaining > 0" ng-model="row.check" ng-change="checkBoxSelect(row)"></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Selected Book</legend>
            <table class="table table-hover table-bordered">
                <tr>
                    <th class="col-sm-2">ISBN</th>
                    <th class="col-sm-4">Title</th>
                    <th class="col-sm-6">Authors</th>
                </tr>
                <tr ng-repeat="book in selectedBooks track by $index">
                    <td>{{book.isbn}}</td>
                    <td>{{book.name}}</td>
                    <td><em ng-repeat="author in book.authors track by $index">{{author}}<em ng-show="!$last">, </em></em></td>
                </tr>
            </table>
        </fieldset>
    </div>
</div>
</#macro>