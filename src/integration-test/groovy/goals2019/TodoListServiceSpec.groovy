package goals2019

import grails.test.hibernate.HibernateSpec
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.spockframework.compiler.model.Spec
import spock.lang.Specification


class TodoListServiceSpec extends Specification implements DataTest  {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }
    TodoListService todoListService = new TodoListService()

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    def 'test add task'() {
        when: 'tasks are already in database'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date()),
                new TodoItem(task: 'task two', createdOn: new Date()),
                new TodoItem(task: 'task three', createdOn: new Date())
        )

        then:
        TodoItem.count() == 3

        when: 'service is called to save'
        todoListService.save('some new task')
        TodoItem taskAdded = TodoItem.findById(4)

        then:
        TodoItem.count() == 4
        taskAdded.task == 'some new task'
    }

}
