package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    def save(task) {
        TodoItem newTask = new TodoItem(task: task)
        newTask.save()
    }

}
