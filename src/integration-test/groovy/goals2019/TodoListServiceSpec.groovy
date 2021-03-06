package goals2019

import grails.testing.gorm.DataTest
import spock.lang.Specification

class TodoListServiceSpec extends Specification implements DataTest  {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    TodoListService todoListService = new TodoListService()

    def setup() {
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task two', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task three', createdOn: new Date(), updatedOn: new Date(), isCompleted: false)
        )
    }

    def cleanup() {
    }

    def 'test add task'() {
        when: 'tasks are already in database'

        then:
        TodoItem.count() == 3

        when: 'service is called to save'
        Date today = new Date()
        TodoItem newTask = new TodoItem(task: "some new task", isCompleted: false, createdOn: today, updatedOn: today)
        todoListService.addTask(newTask)
        TodoItem taskAdded = TodoItem.findById(4)

        then:
        TodoItem.count() == 4
        taskAdded.task == 'some new task'
    }

    def 'test add task sad path (null task)'() {
        when: 'tasks are already in database'

        then:
        TodoItem.count() == 3

        when: 'service is called to save'
        Date today = new Date()
        TodoItem newTask = new TodoItem(task: null, isCompleted: false, createdOn: today, updatedOn: today)
        def taskAdded = todoListService.addTask(newTask)

        then:
        TodoItem.count() == 3
        !taskAdded
    }

    def 'test update task'() {
        when: 'tasks are already in database'

        then:
        TodoItem.count() == 3
        TodoItem taskAtHand = TodoItem.findById(3)
        taskAtHand.task == 'task three'
        taskAtHand.createdOn == taskAtHand.updatedOn

        when: 'service is called to update task'
        todoListService.updateTask(3,'some new task')

        then:
        TodoItem.count() == 3
        taskAtHand.task == 'some new task'
        taskAtHand.task != 'task three'
        taskAtHand.createdOn != taskAtHand.updatedOn
        taskAtHand.updatedOn.format('yyyy-MM-dd') == new Date().format('yyyy-MM-dd')
    }

    def 'test delete task'() {
        when: 'tasks are already in database'

        then:
        TodoItem.count() == 3

        when: 'service is called to delete task'
        todoListService.deleteTask(2)
        TodoItem taskDeleted = TodoItem.findById(2)

        then:
        TodoItem.count() == 2
        taskDeleted == null
    }

    def 'test complete task'() {
        when: 'tasks are already in db'

        then:
        TodoItem.count() == 3
        TodoItem taskCompleted = TodoItem.findById(2)
        !taskCompleted.isCompleted

        when: 'service is called to complete task'
        todoListService.completeTask(2)

        then:
        taskCompleted.isCompleted
    }

    def 'test moving task back to list'() {
        when: 'tasks are already in db'

        then:
        TodoItem.count() == 3
        TodoItem taskCompleted = TodoItem.findById(2)
        !taskCompleted.isCompleted

        when: 'service is called to complete task'
        todoListService.completeTask(2)

        then:
        taskCompleted.isCompleted

        when: 'service is called to move task back to list'
        todoListService.moveToList(2)

        then:
        !taskCompleted.isCompleted
    }

    def 'test search task with single word'() {
        when: 'tasks are already in db'

        then:
        TodoItem.count() == 3

        when: 'service is called to search tasks'
        String search = "task"
        todoListService.searchTasks(search)

        then:
        String searchLike = "%" + search + "%".toLowerCase()

        when:
        List<TodoItem> results = TodoItem.findAllByTaskIlike(searchLike)

        then:
        results.size() == 3
    }

    def 'test search task with two words'() {
        when: 'tasks are already in db'

        then:
        TodoItem.count() == 3

        when: 'service is called to search tasks'
        String search = "task one"
        todoListService.searchTasks(search)
        String searchLike = "%" + search + "%".toLowerCase()
        searchLike = searchLike.replaceAll("[,\\s;\\-()/]","%")

        then:
        searchLike == "%task%one%"

        when:
        List<TodoItem> results = TodoItem.findAllByTaskIlike(searchLike)

        then:
        results.size() == 1
        results[0].task == "task one"

    }

    def 'test getAll'() {
        when: 'tasks are already in db'

        then:
        TodoItem.count() == 3

        when: 'getAll is called'
        List<TodoItem>  list = TodoItem.getAll()

        then:
        list.size() == 3
        list.get(0).task == 'task one'
        list.get(1).task == 'task two'
        list.get(2).task == 'task three'
    }

}
