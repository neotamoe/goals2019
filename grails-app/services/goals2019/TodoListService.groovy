package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    def save(task) {
        TodoItem newTask = new TodoItem(task: task)
        newTask.save()
    }

    def deleteTask(id) {
        println("id in delete task: " + id)
        def task = TodoItem.get(id)
        task.delete(flush: true)
        TodoItem.getAll()
    }

}
