<!doctype html>
<html>
    <head>
        <title>To Do List</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container">
            <h1>To Do List</h1>
            <div style="display: inline-block; width: 50%;">
                <h1>Add Task</h1>
                <g:form name="addTask" controller="TodoList" action="addTask" method="POST" autocomplete="off" >
                    <input type="text" placeholder="What's on your to do list?" name="task" style="width: 80%"/>
                    <button type="submit">Add Task</button>
                    <g:hasErrors>
                        <g:eachError><p><g:message error="${it}"/></p></g:eachError>
                    </g:hasErrors>
                </g:form>
            </div>
            <div style="display: inline-block">
                <g:link controller="TodoList" action="searchIndex" params="${[search: ""]}">
                    Search Tasks
                </g:link>
            </div>
            <div style="display: inline-block; margin-left: 25px">
                <g:link controller="TodoList" action="viewCompleted">
                    View Completed Tasks
                </g:link>
            </div>
            <div>
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
        </div>
    </body>
</html>


