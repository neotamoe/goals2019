package goals2019

class TodoListController {

    def list = ['grocery shopping', 'play soccer', 'eat ice cream', 'read book']
    def title = "this is a title"

    def index() {
        println "is this hit?"
        render(view: 'todoList', model:[list: list, title: title])
    }

    def addItem() {
    }
}
