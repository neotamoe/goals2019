package goals2019


class TodoListController {

    List<TodoItem> list
    TodoListService todoListService

    def index() {
        list = TodoItem.getAll()
        render(view: 'todoList', model:[list: list])
    }

    def addTask() {
        String newTask = params.newTask
        todoListService.save(newTask)
        list = TodoItem.getAll()
        render(view: 'todoList', model:[list: list])
    }
}
