package goals2019

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TodoItemSpec extends Specification implements DomainUnitTest<TodoItem> {

    def setup() {
    }

    def cleanup() {
    }

    void 'test task cannot be null'() {
        when:
        domain.task = null

        then:
        !domain.validate(['task'])
        domain.errors['task'].code == 'nullable'
    }

    void 'test task cannot be blank'() {
        when:
        domain.task = ''

        then:
        !domain.validate(['task'])
        domain.errors['task'].code == 'blank'
    }

    void 'test task can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.task = str

        then: 'task validation fails'
        !domain.validate(['task'])
        domain.errors['task'].code == 'maxSize.exceeded'

        when: 'for a string of 256 characters'
        str = 'a' * 255
        domain.task = str

        then: 'task validation passes'
        domain.validate(['task'])
    }

    void 'test createdOn and updatedOn accepts a date'() {
        when: 'for a date provided'
        Date today = new Date()
        domain.createdOn = today
        domain.updatedOn = today

        then: 'test validation passes'
        domain.validate(['createdOn'])
        domain.validate(['updatedOn'])
    }

    void 'test updatedOn, completedOn, and isCompleted can be null'() {
        when:
        domain.updatedOn = null
        domain.createdOn = null
        domain.isCompleted = null

        then:
        domain.validate(['updatedOn'])
        !domain.errors['updatedOn']

        domain.validate(['createdOn'])
        !domain.errors['createdOn']

        domain.validate(['isCompleted'])
        !domain.errors['isCompleted']
    }
}
