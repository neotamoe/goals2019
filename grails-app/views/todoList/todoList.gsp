<div>
    <h1>To Do List</h1>
    <g:form controller="TodoList" action="addItem">
        <input type="text" placeholder="add task" name="newTask"/>
        <button type="submit">Add Item</button>
    </g:form>
    %{--these two lines are for debugging--}%
    <p>Title is: ${title}</p>
    <p>List is: ${list}</p>
    <g:if test="${list.size()>0}">
        <ul>
            <g:each var="item" in="${list}">
                <li>${item.task}</li>
            </g:each>
        </ul>
    </g:if>
</div>