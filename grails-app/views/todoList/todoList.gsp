<div>
  <h1>To Do List</h1>
    <g:form controller="TodoList" action="addItem">
        <input type="text" placeholder="add task" name="newTask"/>
        <button type="submit">Add Item</button>
    </g:form>
    <p>Title is: ${title}</p>  // these two lines are for debugging
    <p>List is: ${list}</p>
    <ul>
        <g:each var="item" in="${list}">
            <li>${item.task}</li>
        </g:each>
    </ul>
</div>