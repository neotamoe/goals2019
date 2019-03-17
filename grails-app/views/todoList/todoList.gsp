<div>
  <h1>To Do List</h1>
    %{--<g:link controller="TodoListController" action="addItem">--}%
        %{--<button type="button">Add Item</button>--}%
    %{--</g:link>--}%
    <p>List is: ${list}</p>
    <ul>
        <g:each var="item" in="${list}">
            <li>${item}</li>
        </g:each>
    </ul>
</div>