package grails.plugin.yammer.metrics.meters

import com.codahale.metrics.MetricRegistry
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class MeterableSpec extends Specification {

    static doWithSpring = {
        yammerMetricsRegistry MetricRegistry
    }

    void 'test markMeter method'() {
        setup:
        def registry = applicationContext.yammerMetricsRegistry
        def obj = new SomeClass()

        when:
        obj.someAction()
        obj.someAction()
        obj.someAction()

        then:
        registry.meter('some meter').count == 3
    }
}

// tag::sample_class[]
class SomeClass implements Meterable {

    def someAction() {
        markMeter 'some meter'

        // ...
    }
}
// end::sample_class[]

