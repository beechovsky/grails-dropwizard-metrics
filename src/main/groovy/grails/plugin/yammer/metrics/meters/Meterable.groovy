/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.yammer.metrics.meters

import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import grails.util.Holders
import groovy.transform.CompileStatic

@CompileStatic
trait Meterable {

    private MetricRegistry retrieveMetricRegistry() {
        Holders.applicationContext.getBean('yammerMetricsRegistry', MetricRegistry)
    }

    private Meter retrieveMeter(String name) {
        retrieveMetricRegistry().meter(name)
    }

    void markMeter(String name) {
        retrieveMeter(name).mark()
    }
}