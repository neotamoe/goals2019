<!doctype html>
<html>
    <head>
        <title>To Do List</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container">
            <div style="display: inline-block; width: 50%;">
                <h1>Add Task</h1>
                <g:form name="addTask" controller="TodoList" action="addTask" method="POST" autocomplete="off" >
                    <input type="text" placeholder="What's on your to do list?" name="task" style="width: 70%"/>
                    <button type="submit">Add Task</button>
                    <g:hasErrors>
                        <g:eachError><p><g:message error="${it}"/></p></g:eachError>
                    </g:hasErrors>
                </g:form>
            </div>
            %{--<div style="display: inline-block; width: 49%;">--}%
                %{--<h1>Search Tasks</h1>--}%
                %{--<g:form name="search" controller="TodoList" action="searchTasks" method="POST" autocomplete="off" >--}%
                    %{--<input type="text" placeholder="What are you looking for?" name="search" style="width: 70%"/>--}%
                    %{--<button type="search">Search</button>--}%
                %{--</g:form>--}%
            %{--</div>--}%
            <div>
                <h1>To Do List</h1>
                <g:if test="${list?.size()>0}">
                    <table class="table table-bordered">
                        <thead>
                        <th style="width: 50%">Task</th>
                        <th style="width: 16%">Mark as Done</th>
                        <th style="width: 16%">Delete</th>
                        <th style="width: 16%">Edit</th>
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
                    <div>No items in your to do list!</div>
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
                            <tr style="background-color: lightgray">
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


