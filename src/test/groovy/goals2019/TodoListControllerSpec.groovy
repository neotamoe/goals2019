package goals2019

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.springframework.web.servlet.ModelAndView
import spock.lang.Specification

class TodoListControllerSpec extends Specification implements ControllerUnitTest<TodoListController>, DataTest {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    def setup() {
        new TodoItem(task: 'create test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()
        new TodoItem(task: 'test again', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()
        new TodoItem(task: 'one more test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false).save()
        new TodoItem(task: 'one and done', createdOn: new Date(), updatedOn: new Date(), isCompleted: true).save()
        new TodoItem(task: 'two and through', createdOn: new Date(), updatedOn: new Date(), isCompleted: true).save()
        new TodoItem(task: 'three and free', createdOn: new Date(), updatedOn: new Date(), isCompleted: true).save()
    }

    def cleanup() {
    }

    void "add task will call service save"(){
        given:
        TodoItem newTask = new TodoItem(task: "new task to add")
        controller.todoListService = Stub(TodoListService) {
            addTask(newTask) >> newTask
        }
        def persistedItem

        when:
        controller.addTask(newTask)

        then:
        controller.todoListService.addTask("new task to add")
        response.forwardedUrl == '/todoList/index'
    }

    void "add task sad path"(){
        given:
        TodoItem newTask = new TodoItem(task: "new task to add")
        controller.todoListService = Mock(TodoListService) {
            addTask(newTask) >> null
        }

        when:
        controller.addTask(newTask)
        def persistedItem = controller.todoListService.addTask("new task to add")

        then:
        persistedItem == null
        response.forwardedUrl == '/todoList/index'
        controller.modelAndView == null  // null because we are forwarding, not render
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
        response.redirectedUrl == '/todoList/index'
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
        response.redirectedUrl == '/todoList/index'
    }

    void "update task sad path save fail"(){
        given:
        params.id = 1
        params.task = 'new task to add'
        controller.todoListService = Mock(TodoListService) {
            updateTask(params.id, params.task) >> null
        }

        when:
        controller.updateTask()
        def updatedItem = controller.todoListService.updateTask(params.taskId, params.task)

        then:
        updatedItem == null

        when:
        !updatedItem

        then:
        controller.modelAndView == null
        response.forwardedUrl.startsWith('/todoList/index')
    }

    void "test if index loads"() {
        given:
        List<TodoItem> list = TodoItem.findAllByIsCompleted(false)
        List<TodoItem> completed = TodoItem.findAllByIsCompleted(true)

        when:
        controller.index()

        then:
        response.status == 200
        '/todoList/todoList' == view
        controller.modelAndView.model.list == list
        controller.modelAndView.model.completed == completed
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
        model.item == TodoItem.findById(1)

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

    void "test completeTask sad path"() {
        given:
        params.taskId = 1
        controller.todoListService = Mock(TodoListService) {
            completeTask(params.taskId) >> null
        }

        when:
        controller.completeTask()
        def completedTask = controller.todoListService.completeTask(params.taskId)

        then:
        completedTask == null

        when:
        !completedTask

        then:
        controller.modelAndView == null
        response.forwardedUrl.startsWith('/todoList/index')
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

    void "test moveToList sad path - fails to save"() {
        given:
        params.taskId = 1
        controller.todoListService = Mock(TodoListService) {
            moveToList(params.taskId) >> null
        }

        when:
        controller.moveToList()
        def movedItem = controller.todoListService.moveToList(params.taskId)

        then:
        movedItem == null

        when:
        !movedItem

        then:
        controller.modelAndView == null
        response.forwardedUrl.startsWith('/todoList/index')
    }

    void "test viewCompleted renders correct view"() {
        when:
        controller.viewCompleted()

        then:
        List<TodoItem> completed = TodoItem.findAllByIsCompleted(true)
        response.status == 200
        '/todoList/completedTasks' == view
        model.completed == completed
    }

    void "test searchIndex renders correct view"() {
        when:
        controller.searchIndex()

        then:
        response.status == 200
        '/todoList/searchResults' == view
    }

    void "test if searchTasks calls right service method and returns correct model and view"() {
        given:
        params.search = "test"
        List<TodoItem> results = [
                new TodoItem(task: 'create test', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'test again', createdOn: new Date(), updatedOn: new Date(), isCompleted: false),
                new TodoItem(task: 'one more test', createdOn: new Date(), updatedOn: new Date(), isCompleted: true),
        ]
        List<TodoItem> completed = new ArrayList<TodoItem>()
        List<TodoItem> list = new ArrayList<TodoItem>()
        controller.todoListService = Stub(TodoListService) {
            searchTasks(_) >> results
        }

        when:
        controller.searchTasks()

        then:
        results == controller.todoListService.searchTasks(params.taskId)

        when:
        results != null
        searchTasksSort(list, completed, results)

        then:
        '/todoList/searchResults' == view
        model.search == "test"
        model.completed == completed
        model.list == list
    }

    void "test searchTasks when no search results"() {
        given:
        params.search = "test"
        List<TodoItem> results = []
        List<TodoItem> completed = new ArrayList<TodoItem>()
        List<TodoItem> list = new ArrayList<TodoItem>()
        controller.todoListService = Stub(TodoListService) {
            searchTasks(_) >> results
        }

        when:
        controller.searchTasks()

        then:
        results == controller.todoListService.searchTasks(params.taskId)

        when:
        results.isEmpty()

        then:
        '/todoList/searchResults' == view
        model.search == "test"
        model.completed == completed
        model.list == list
    }

    private searchTasksSort(List<TodoItem> list, List<TodoItem> completed, List<TodoItem> results){
        results?.each {
            if(it.isCompleted){
                completed.add(it)
            } else {
                list.add(it)
            }
        }
    }
}
