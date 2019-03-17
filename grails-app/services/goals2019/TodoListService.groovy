package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {
    List<TodoItem> list = []
    def save(task) {
        println(task)
        TodoItem newTask = new TodoItem(task: task)
        list.add(newTask)
        println(list)
//        newTask.save()
    }

    def findAll() {
        return list
    }
}
