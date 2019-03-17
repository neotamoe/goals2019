package goals2019

class TodoListController {

    def list = ['grocery shopping', 'play soccer', 'eat ice cream', 'read book']
    def title = "this is a title"

    def index() {
        render(view: 'todoList', model:[list: list, title: title])
    }

    def addItem() {
        // no back end yet, so using title to display what was submitted
        title = "${params.newTask}"
        render(view: 'todoList', model:[list: list, title: title])
    }
}
