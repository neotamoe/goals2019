package goals2019


class TodoListController {

//    def list = ['grocery shopping', 'play soccer', 'eat ice cream', 'read book']
    List<TodoItem> list
    def title = "this is a title"
    TodoListService todoListService

    def index() {
        list = TodoItem.getAll()
        println('list in index is: ' + list)
        render(view: 'todoList', model:[list: list, title: title])
    }

    def addItem() {
        // no back end yet, so using title to display what was submitted
        title = "${params.newTask}"
        todoListService.save(title)
        list = TodoItem.getAll()
        println('list in addItem is: ' + list)
        render(view: 'todoList', model:[list: list, title: title])
    }
}
