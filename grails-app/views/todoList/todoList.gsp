<div>
    <h1>To Do List</h1>
    <g:form name="addTask" controller="TodoList" action="addTask" method="POST" autocomplete="off">
        <input type="text" placeholder="add task" name="newTask"/>
        <button type="submit">Add Task</button>
    </g:form>
    <g:if test="${list.size()>0}">
        <ul>
            <g:each var="item" in="${list}">
                <li>${item.task}
                    <button>
                        <g:link controller="TodoList" action="deleteTask" params="${[taskId: item.id]}">
                            Done!
                        </g:link>
                    </button>
                </li>
            </g:each>
        </ul>
    </g:if>
    <g:else>
        <div>No items in your to do list!</div>
    </g:else>
</div>