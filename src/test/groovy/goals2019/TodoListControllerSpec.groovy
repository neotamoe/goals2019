package goals2019

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TodoListControllerSpec extends Specification implements ControllerUnitTest<TodoListController>, DataTest {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    def setup() {
        new TodoItem(task: 'create test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()
        new TodoItem(task: 'test again', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()
        new TodoItem(task: 'one more test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()

    }

    def cleanup() {
    }

    void "add task will call service save"(){
        given:
        TodoItem newTask = new TodoItem(task: "new task to add")
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.addTask(newTask)

        then:
        controller.todoListService.addTask("new task to add")
    }

    void "delete task will call service deleteTask"(){
        given:
        params.taskId = 1
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.deleteTask()

        then:
        controller.todoListService.deleteTask(1)
//        '/todoList/deleteTask.gsp' == view
    }

    void "update task will call service updateTask"(){
        given:
        params.taskId = 1
        params.task = 'new task to add'
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.updateTask()

        then:
        controller.todoListService.updateTask(params.taskId, params.task)
//        '/todoList/updateTask.gsp' == view
    }

    void "test if index loads"() {
        when:
        controller.index()


        then:
        response.status == 200
        '/todoList/todoList' == view

    }

    void "test if editTask renders correct view and calls updateTask"() {
        given:
        params.taskId = 1
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.editTask()

        then:
        '/todoList/editTask' == view

        when:
        controller.updateTask()
        params.task = 'new task to add'

        then:
        controller.todoListService.updateTask(params.taskId, params.task)
    }

    void "test if completeTask calls right service method"() {
        given:
        params.taskId = 1
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.completeTask()

        then:
        controller.todoListService.completeTask(params.taskId)
    }

    void "test if moveToList calls right service method"() {
        given:
        params.taskId = 1
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.moveToList()

        then:
        controller.todoListService.moveToList(params.taskId)
    }

    void "test viewCompleted renders correct view"() {
        when:
        controller.viewCompleted()

        then:
        response.status == 200
        '/todoList/completedTasks' == view
    }

    void "test searchIndex renders correct view"() {
        when:
        controller.searchIndex()

        then:
        response.status == 200
        '/todoList/searchResults' == view
    }

    void "test if searchTasks calls right service method"() {
        given:
        params.search = "test"
        List<TodoItem> searched = [
                new TodoItem(task: 'create test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'test again', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'one more test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
        ]
        controller.todoListService = Stub(TodoListService) {
            searchTasks(_) >> searched
        }

        when:
        controller.searchTasks()

        then:
        List<TodoItem> results = controller.todoListService.searchTasks(params.taskId)
    }
}
