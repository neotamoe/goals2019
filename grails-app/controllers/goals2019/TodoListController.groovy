package goals2019

class TodoListController {

//    def list = ['grocery shopping', 'play soccer', 'eat ice cream', 'read book']
    List<TodoItem> list
    def title = "this is a title"
    TodoListService todoListService

    def index() {
        list = todoListService.findAll()
        println('list in index is: ' + list)
        render(view: 'todoList', model:[list: list, title: title])
    }

    def addItem() {
        // no back end yet, so using title to display what was submitted
        title = "${params.newTask}"
        todoListService.save(title)
        list = todoListService.findAll()
        render(view: 'todoList', model:[list: list, title: title])
    }
}
