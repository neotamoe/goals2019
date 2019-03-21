package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    def save(task) {
        Date today = new Date()
        println("today in save: " + today)
        TodoItem newTask = new TodoItem(task: task, createdOn: today, updatedOn: today)
        newTask.save()
    }

    def deleteTask(id) {
        println("id in delete task: " + id)
        def task = TodoItem.get(id)
        task.delete(flush: true)
        TodoItem.getAll()
    }

}
