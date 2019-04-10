<!doctype html>
<html>
<head>
    <title>Search Results</title>
    <meta name="layout" content="main">
</head>
<body>
<div class="container" style="margin-top: 15px">
    <div>
        <div>
            <g:link controller="TodoList" action="index">Back to List</g:link>
        </div>
        <div style="width: 40%;">
            <h1>Search Results for "${search}"</h1>
        </div>
        <div style="display: inline-block; width: 49%;">
            <h1>Search Tasks</h1>
            <g:form name="search" controller="TodoList" action="searchTasks" method="POST" autocomplete="off" >
                <input type="text" placeholder="What are you looking for?" name="search" style="width: 70%"/>
                <button type="search">Search</button>
            </g:form>
        </div>
        <g:if test="${list?.size()>0}">
            <table class="table table-bordered">
                <thead>
                <th style="width: 50%">Task</th>
                <th>Done</th>
                <th>Delete</th>
                <th>Edit</th>
                </thead>
                <tbody>
                <g:each var="item" in="${list}">
                    <tr>
                        <td>
                            ${item.task}
                        </td>
                        <td>
                            <g:link controller="TodoList" action="completeTask" params="${[taskId: item.id]}" class="green">
                                &#10004;
                            </g:link>
                        </td>
                        <td>
                            <g:link controller="TodoList" action="deleteTask" params="${[taskId: item.id]}" class="red">
                                &#10008;
                            </g:link>
                        </td>
                        <td>
                            <g:link controller="TodoList" action="editTask" params="${[taskId: item.id]}">
                                &#9999;
                            </g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <div>No tasks found that match your search.</div>
        </g:else>
    </div>
    <div>
        <g:if test="${completed?.size()>0}">
            <h1>Completed Tasks</h1>
            <table class="table table-bordered">
                <thead>
                <th>Task</th>
                <th>Delete</th>
                <th>Move to List</th>
                </thead>
                <tbody>
                <g:each var="item" in="${completed}">
                    <tr>
                        <td style="width: 50%">
                            ${item.task}
                        </td>
                        <td style="width: 25%">
                            <g:link controller="TodoList" action="deleteTask" params="${[taskId: item.id]}" class="red">
                                &#10008;
                            </g:link>
                        </td>
                        <td style="width: 25%">
                            <g:link controller="TodoList" action="moveToList" params="${[taskId: item.id]}">
                                &#11014;
                            </g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
    </div>
</div>
</body>
</html>


