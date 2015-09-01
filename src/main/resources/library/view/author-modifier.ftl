<#import "/library/view/template/scripts.ftl" as Scripts/>

<html ng-app="authorModifyApp">
<@Scripts.Scripts/>
<script src="/Assets/js/author-modifier.js"></script>
<title>Author Modification Page</title>

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
                <a class="navbar-brand">Author Modification Page</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a>Author Modification</a></li><!--Current page's link shouldn't be clickable-->
                    <li><a href="#">Book Modification</a></li>
                    <li><a href="#">Book Withdrawal</a></li>
                    <li><a href="#">User Modification</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid" ng-controller="AuthorModifierCtrl">
        <div class = "row">
            <div class = "col-sm-4">
                <form class="form-horizontal" name="ins">
                    <fieldset>
                        <legend class="text-center">Author Insertion</legend>
                        <div class="form-group">
                            <label for="author-id-insert" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="author-id-insert" placeholder="Author ID" ng-model="i.id" required integers name="iId">
                                <span ng-show="ins.iId.$error.required && ins.iId.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="ins.iId.$error.integers && !ins.iId.$error.required" style="color: #a94442">Only positive numbers</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author-name-insert" class="col-sm-3 control-label">Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="author-name-insert" placeholder="Author Name" ng-model="i.name" required alphabets name="iName">
                                <span ng-show="ins.iName.$error.required && ins.iName.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="ins.iName.$error.alphabets && !ins.iName.$error.required" class="text-center" style="color: #a94442">Only one alphabetic name</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author-email-insert" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-8"><input type="email" class="form-control" id="author-email-insert" placeholder="Author Email" ng-model="i.email" required name="iEmail"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8 text-center">
                                <button type="submit" class="btn btn-success" ng-click="ins.$valid && insert(i)">Insert</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class = "col-sm-4">
                <form class="form-horizontal" name="mod">
                    <fieldset>
                        <legend class="text-center">Author Modification by ID</legend>
                        <div class="form-group">
                            <label for="author-id-modify" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="author-id-modify" placeholder="ID of author to be modified" ng-model="m.id" required integers name="mId">
                                <span ng-show="mod.mId.$error.required && mod.mId.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="mod.mId.$error.integers && !mod.mId.$error.required" style="color: #a94442">Only positive numbers</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author-name-modify" class="col-sm-3 control-label">New Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="author-name-modify" placeholder="New Name" ng-model="m.name" required alphabets name="mName">
                                <span ng-show="mod.mName.$error.required && mod.mName.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="mod.mName.$error.alphabets && !mod.mName.$error.required" class="text-center" style="color: #a94442">Only one alphabetic name</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author-email-modify" class="col-sm-3 control-label">New Email</label>
                            <div class="col-sm-8"><input type="email" class="form-control" id="author-email-modify" placeholder="New Email" ng-model="m.email" required></div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8 text-center">
                                <button type="button" class="btn btn-warning" ng-click="mod.$valid && modify(m)">Modify</button>
                                <button type="button" class="btn btn-primary" ng-click="modifyImport()">Import</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="col-sm-4">
                <fieldset>
                    <legend class="text-center">Author Deletion by ID</legend>
                    <form class="form-horizontal" name="dele">
                        <div class="form-group">
                            <label for="author-id-delete" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="author-id-delete" placeholder="ID of author to be deleted" ng-model="d.id" name="dId" required integers>
                                <span ng-show="dele.dId.$error.required && dele.dId.$touched" style="color: #a94442">Can't be empty</span>
                                <span ng-show="dele.dId.$error.integers && !dele.dId.$error.required" style="color: #a94442">Only positive numbers</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8 text-center">
                                <button type="button" class="btn btn-danger" ng-click="dele.$valid && deleter(dId)">Delete</button>
                                <button type="button" class="btn btn-primary" ng-click="deleteImport()">Import</button>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-3">
                <form class="form-horizontal" name="search">
                    <fieldset>
                        <legend class="text-center">Search Criteria</legend>
                        <div class="form-group">
                            <label for="search-id" class="col-sm-4 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="search-id" placeholder="Search ID" ng-model="s.id" name="sId" integerSearch>
                                <span ng-show="search.sId.$error.integers && search.sId.$touched" style="color: #a94442">Only positive numbers</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-name" class="col-sm-4 control-label">Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="search-name" placeholder="name" ng-model="s.name" name="sName" alphabets>
                                <span ng-show="search.sName.$error.alphabets && search.sId.$touched" style="color: #a94442">Only one alphabetic name</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-8 text-center">
                                <button type="submit" class="btn btn-primary" ng-click="search.$valid && validSearch(s.id, s.name) && searchAuthor(s)">Search</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="col-sm-8 text-center">
                <fieldset>
                    <legend>Search Table to find Authors to Modify/Delete</legend>
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th class="col-sm-2">ID</th>
                            <th class="col-sm-4">Name</th>
                            <th class="col-sm-4">Email</th>
                            <th class="col-sm-2">Choose</th>
                        </tr>
                        <tr ng-repeat="a in authors track by $index">
                            <td>{{ a.id }}</td>
                            <td>{{ a.name }}</td>
                            <td>{{ a.email }}</td>
                            <td><input type="checkbox" ng-model="a.check" ng-click="checkBoxSelect(a)"></td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </div>
    </div>
</body>
</html>